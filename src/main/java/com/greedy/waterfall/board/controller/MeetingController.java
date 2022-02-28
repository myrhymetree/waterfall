package com.greedy.waterfall.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greedy.waterfall.board.model.dto.FileDTO;
import com.greedy.waterfall.board.model.dto.MeetingDTO;
import com.greedy.waterfall.board.model.service.MeetingService;
import com.greedy.waterfall.common.paging.Pagenation;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.project.model.dto.ProjectAuthorityDTO;

/**
 * <pre>
 * Class : MeetingController
 * Comment : 회의록 게시판의 게시물 전체목록 조회, 게시글 상세조회, 수정, 삭제, 등록을 담당하는 컨트롤러
 * 
 * History
 * 2022. 2. 18.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
@Controller
@RequestMapping("/meeting/*")
public class MeetingController {
	private final MeetingService meetingService;
	
	@Autowired
	public MeetingController(MeetingService meetingService) {
		this.meetingService = meetingService;
	}
	
	/**
	 * findMeetingBoardList : 조회된 회의록 게시글 전체 목록을 view로 전달해주는 메소드
	 * @param mv : 전체 게시글을 담을 매개변수 
	 * @param request : 현재 페이지 정보를 담고있는 매개변수 
	 * @return mv : 전달받은 현재페이지로 조회한 게시글 전체 목록과, 되돌아갈 주소를 저장한 ModelAndView 변수 반환
	 * 
	 * @author 홍성원
	 * @throws IOException 
	 */
	
	@GetMapping("/download/{fileNo}")
	public ModelAndView downloadFile(ModelAndView mv, @PathVariable("fileNo") String fileNo) throws IOException {
		int no = Integer.parseInt(URLDecoder.decode(fileNo, "UTF-8"));
		
		Map<String, Object> fileInfo = new HashMap<String, Object>();
		
		FileDTO file = meetingService.findFile(no);
		fileInfo.put("filePath", file.getFilePath());
		fileInfo.put("fileOriginName", file.getFileOriginName());
		fileInfo.put("fileRandomName", file.getFileRandomName());
		mv.addObject("downloadFile", fileInfo);
		mv.setViewName("fileDownloadView");
		
		return mv;
	}
	
	@GetMapping("/list")
	public ModelAndView findMeetingBoardList(ModelAndView mv, HttpServletRequest request) {
		/* 페이징 처리를 위한 변수 선언 및 초기화 */
		Map<String, String> searchMap = new HashMap<>();					//검색조건과 검색값을 담을 HashMap 변수를 선언한다.
		String projectNo = Integer.toString(((ProjectAuthorityDTO) request.getSession().getAttribute("projectAutority")).getProjectNo());
		String currentPage = request.getParameter("currentPage");
		String searchCondition = request.getParameter("searchCondition");	
		String searchValue = request.getParameter("searchValue");			
		
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);
		searchMap.put("projectNo", projectNo);
		searchMap.put("currenPage", currentPage);

		/* 검색조건을 전달해, 게시물 목록을 반환받는다. */
		Map<String, Object> findResult = meetingService.findMeetingBoardList(searchMap);
		List<MeetingDTO> meetingList = (List<MeetingDTO>) findResult.get("meetingList");
		SelectCriteria selectCriteria = (SelectCriteria) findResult.get("selectCriteria");
		
		/* 반환받은 게시물 목록과, 검색조건, 전달할 주소값을 저장한 뒤  반환 주소로 전달한다.*/
		mv.addObject("meetingList", meetingList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.addObject("intent", "/meeting/list");
		mv.setViewName("/board/meeting/meetingList");
		
		return mv;
	}
	
	/**
	 * findMeetingBoardDetail : 회의록 게시물의 상세조회를 위해 전달받은 게시물 번호로 게시물 정보를 조회 후 전달해주는 메소드
	 * @param meetingNo : 게시물의 정보를 조회하기 위한 게시물 번호
	 * @param mv : 조회한 게시물을 전달하기 위한 ModelAndView 변수
	 * @return mv : 조회한 게시물의 정보와 요청 url정보를 담고있는 ModelAndView변수 반환.
	 * 
	 * @author 홍성원
	 * @throws JsonProcessingException 
	 */
	@GetMapping("/detail/{meetingNo}")
	public ModelAndView findMeetingBoardDetail(ModelAndView mv, @PathVariable("meetingNo") int meetingNo
			, HttpServletResponse response) throws JsonProcessingException {
		/* 전달받은 게시물번호로 조회된 게시물 정보를 저장한다. */
		
		
		MeetingDTO meeting = meetingService.findMeetingBoardDetail(meetingNo);
		
		response.setContentType("application/json; charset=UTF-8");
		
		ObjectMapper mapper = new ObjectMapper();
		
		/* 조회된 게시물의 정보와 요청 url을 저장한 후 반환한다. */
		mv.addObject("meeting", mapper.writeValueAsString(meeting));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	/**
	 * removeMeetingBoard : 게시물을 삭제하기 위한 컨트롤러 
	 * @param mv : 요청주소를 저장할 mv변수
	 * @param meetingNo : 삭제할 게시물의 번호를 담고있는 변수
	 * @return mv : 요청주소를 담은 변수를 반환한다.
	 * 
	 * @author 홍성원
	 */
	@GetMapping("/remove/{meetingNo}")
	public ModelAndView removeMeetingBoard(ModelAndView mv, @PathVariable("meetingNo") int meetingNo) {
		/* 매개변수로 받은 게시물번호로 해당 게시물을 삭제 한 후 결과에 따라 메세지를 저장한다. */
		String message = "";
		
		if(meetingService.removeMeetingBoard(meetingNo)) {
			message = "게시물을 삭제했습니다.";
		} else {
			message = "게시물 삭제에 실패했습니다.";
		}
		
		/* 요청 주소값을 저장한 후 반환한다. */
		mv.setViewName("redirect:/meeting/list");
		
		return mv;
	}
	
	/**
	 * registMeetingBoard : 회의록 게시글 등록을 담당하는 컨트롤러
	 * @param mv : 요청주소값을 담을 ModelAndView변수
	 * @param parameter : 게시글 제목과 내용을 담고있는 Map 변수
	 * @return mv : 요청 주소값을 반환
	 * 
	 * @author 홍성원
	 */
	@PostMapping("/regist")
	public ModelAndView registMeetingBoard(ModelAndView mv, 
			@RequestParam(name = "meetingfile",required = false) List<MultipartFile> multiFile, 
			@ModelAttribute MeetingDTO meeting,
			HttpServletRequest request) throws UnsupportedEncodingException {
		/* 매개변수로 받은 게시물번호로 해당 게시물을 등록 한 후 결과에 따라 메세지를 저장한다. */
		String message = "";
		Map<String, String> parameter = new HashMap<>();
		
		String root = request.getSession().getServletContext().getRealPath("resources");
		String filePath = root + "\\uploadFiles\\meetingBoard";
		File mkdir = new File(filePath);
		if(!mkdir.exists()) {
			mkdir.mkdirs();
		}
		List<FileDTO> fileList = new ArrayList<FileDTO>();
		if(multiFile.get(0).getOriginalFilename().length() != 0) {
			System.out.println(multiFile);
			System.out.println(multiFile.isEmpty());
			System.out.println("multiFile.size() : " + multiFile.size());
			System.out.println("multiFiles.get(0) is not null");
			for(int i = 0; i < multiFile.size(); i++) {
				String fileOriginName = multiFile.get(i).getOriginalFilename();
				String ext = fileOriginName.substring(fileOriginName.lastIndexOf("."));
				String fileRandomName = UUID.randomUUID().toString().replace("-", "") + ext;
				FileDTO file = new FileDTO().builder().fileOriginName(fileOriginName).fileRandomName(fileRandomName).filePath(filePath).build();
				
				fileList.add(file);
			}
			
			
			try {
				for(int i = 0; i < multiFile.size(); i++) {
					FileDTO uploadFile = fileList.get(i);
					multiFile.get(i).transferTo(new File(filePath + "\\" + uploadFile.getFileRandomName()));
				}
			} catch (Exception e) {
				e.printStackTrace();
				
				for(int i = 0; i < multiFile.size(); i++) {
					FileDTO uploadFile = fileList.get(i);
					
					new File(filePath + "\\" + uploadFile.getFileRandomName()).delete();
				}
				
				System.out.println("업로드 실패");
				System.out.println("업로드 실패");
				System.out.println("업로드 실패");
				System.out.println("업로드 실패");
			}
			
			meeting.setFile(fileList);
		}
		if(meetingService.registMeetingBoard(meeting)) {
			message = "게시글을 등록했습니다.";
			System.out.println(message);
		} else {
			message = "게시글등록에 실패했습니다.";
			System.out.println(message);
		}
		
		/* 요청 주소를 저장 후 반환한다. */
		mv.setViewName("redirect:/meeting/list");
		
		return mv;
	}
	
	/**
	 * modifyBoard : 수정할 게시물의 상세정보를 조회하는 메소드
	 * @param mv : 상세정보와 요청주소를 저장할 변수
	 * @param meetingNo : 수정할 게시물의 번호를 저장하는 변수
	 * @return mv : 게시물의 상세정보와 요청주소를 저장 후 반환하기 위한 변수
	 * 
	 * @author 홍성원
	 */
	@GetMapping("/modify/{meetingNo}")
	public ModelAndView modifyBoard(ModelAndView mv, @PathVariable("meetingNo") int meetingNo) {
		/* 전달받은 게시물 번호로 해당 게시물의 상세 내용을 조회한다. */
		MeetingDTO meeting = meetingService.findOneMeetingBoard(meetingNo);		
		
		/* 수정할 게시물의 상세정보와 요청주소를 저장 후 반환한다. */
		mv.addObject("meeting", meeting);
		mv.setViewName("/board/meeting/meetingModify");
		
		return mv;
	}
	
	/**
	 * modifyMeetingBoard : 전달받은 게시글번호와 수정내용으로 수정기능을 담당하는 메소드
	 * @param mv : 수정한 게시물 내용과, 요청주소를 저장할 변수 
	 * @param meeting : 수정할 게시물의 정보를 전달하는 변수 
	 * @return mv : 업데이트한 게시글의 정보와, 요청 주소값을 반환
	 * 
	 * @author 홍성원
	 */
	@PostMapping("/modify")
	public ModelAndView modifyMeetingBoard(ModelAndView mv, @RequestParam Map<String, String> meeting) {
		/* 매개변수로 받은 게시물 번호와 , 수정내용으로 게시물 수정 한 결과에 따라 해당 메세지를 저장한다. */
		String message = "";
		if(meetingService.modifyMeetingBoard(meeting)) {
			message = "게시물 업데이트에 성공했습니다.";
		} else {
			message = "게시물 업데이트에 실패했습니다.";
		}
		
		/* 요청 주소값과 수정한 수정한 게시물을 저장 후 반환한다. */
		mv.addObject("meeting", meeting);
		mv.setViewName("redirect:/meeting/list");
		
		return mv;
	}
}