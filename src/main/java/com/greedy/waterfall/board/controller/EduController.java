package com.greedy.waterfall.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greedy.waterfall.board.model.dto.EduDTO;
import com.greedy.waterfall.board.model.dto.EduFileDTO;
import com.greedy.waterfall.board.model.service.EduService;
import com.greedy.waterfall.common.exception.board.BoardModifyException;
import com.greedy.waterfall.common.exception.board.BoardRegistException;
import com.greedy.waterfall.common.exception.board.BoardRemoveException;
import com.greedy.waterfall.common.paging.Pagenation;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.project.model.dto.ProjectAuthorityDTO;

@Controller
@RequestMapping("/edu")
public class EduController {

	private final EduService eduService;
	
	@Autowired
	public EduController(EduService eduService) {
		this.eduService = eduService;
	}
	
	@GetMapping("/list")
	public ModelAndView eduSelectList(HttpServletRequest request, ModelAndView mv) {
		
		int projectNo = (((ProjectAuthorityDTO) request.getSession().getAttribute("projectAutority")).getProjectNo());
		
		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;
		
		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}
		
		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");
		
		Map<Object, Object> searchMap = new HashMap<>();
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);
		searchMap.put("projectNo", projectNo);
		
		int totalCount = eduService.selectTotalCount(searchMap);		
		
		/* 게시물 수*/
		int limit = 10;
		
		/* 버튼 수*/
		int buttonAmount = 5;
		/* 페이징 관한 정보 인스턴스 */
		SelectCriteria selectCriteria = null;
		
		/* 검색 결과가 담겨져 있을 경우 담을 곳*/
		if(searchCondition != null && !"".equals(searchCondition) ) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}
		selectCriteria.setProjectNo(projectNo);
		
		/* 전체 조회*/
		List<EduDTO> eduList = eduService.selectEduList(selectCriteria);
		
		System.out.println("확인용3 " + eduList);	

		mv.addObject("eduList", eduList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.addObject("intent", "/edu/list");
		mv.setViewName("/board/edu/eduList");
		
		return mv;
 	}
	
	@GetMapping("edu/eduList")
	public void registEduBoard() {
		
	}
	
	@PostMapping("/regist")
	public String registEduBoard(@ModelAttribute EduDTO eduBoard, HttpServletRequest request,
			RedirectAttributes rttr, @RequestParam MultipartFile singleFile) throws BoardRegistException {
		
		int projectNo = ((ProjectAuthorityDTO) request.getSession().getAttribute("projectAutority")).getProjectNo();
		eduBoard.setProjectNo(projectNo);
		
		int memberNo = (((MemberDTO) request.getSession().getAttribute("loginMember")).getNo());
		
		eduBoard.setMemberNo(memberNo);
		
		String root = request.getSession().getServletContext().getRealPath("resources");
		
		String filePath = root + "/eduUploadFiles";
		
		File mkdir = new File(filePath);
		
		if(!mkdir.exists()) {
			mkdir.mkdirs();
		}
		
		if(singleFile.getSize() > 0) {
			
			String originFileName = singleFile.getOriginalFilename();
			String ext = originFileName.substring(originFileName.lastIndexOf(".")); //확장자
			String savedName = UUID.randomUUID().toString().replace("-","") + ext;
			
			EduFileDTO eduFileDTO = new EduFileDTO();
			eduFileDTO.setSavedPath(filePath);
			eduFileDTO.setOriginalName(originFileName);
			eduFileDTO.setRandomName(savedName);
			eduBoard.setFile(eduFileDTO);
			
			/*파일저장*/
			try {
				singleFile.transferTo(new File(filePath + "\\" + savedName));
				
				eduService.registEdu(eduBoard);
				
				rttr.addFlashAttribute("message", "파일 업로드 성공");
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				
				/*실패 시 파일 삭제*/
				new File(filePath + "\\" + savedName).delete();
				
				rttr.addFlashAttribute("message", "파일 업로드 실패");
			}
			
		} else {
			eduService.registEdu(eduBoard);
			
			rttr.addFlashAttribute("message", "게시글 등록에 성공하셨습니다.");
		}
		/*
		 * String title = request.getParameter("title"); String body =
		 * request.getParameter("body");
		 * 
		 * eduBoard.setTitle(title); eduBoard.setContent(body);
		 * 
		 * eduService.registEdu(eduBoard);
		 */
		
		return "redirect:/edu/list";
	}
	
	@GetMapping(value="eduDetail")
    @ResponseBody
    public ModelAndView findNoticeDetail(HttpServletRequest request,
    HttpServletResponse response ,ModelAndView mv) {
    
    int no = Integer.parseInt(request.getParameter("no"));
		EduDTO eduDetail = eduService.findEduDetail(no);
		EduDTO eduFileDetail = eduService.findEduFileDetail(no);
		
		response.setContentType("application/json; charset=UTF-8");
		
		Gson gson = new GsonBuilder()
		      .setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
		      .setPrettyPrinting()
		      .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
		      .serializeNulls()
		      .disableHtmlEscaping()
		      .create();
		
		if(eduFileDetail == null) {
			mv.addObject("eduDetail", gson.toJson(eduDetail));
			mv.setViewName("jsonView");
			return mv; 
		}
		
		mv.addObject("eduFileDetail", gson.toJson(eduFileDetail));
		mv.setViewName("jsonView");
		
		return mv; 
	}
	
	@GetMapping("/delete")
	public String removeEduBoard(HttpServletRequest request, RedirectAttributes rttr) throws BoardRemoveException {
			System.out.println("확인용" + request.getParameter("no"));
			System.out.println("확인용" + request.getParameter("no"));
			System.out.println("확인용" + request.getParameter("no"));
			System.out.println("확인용" + request.getParameter("no"));
			System.out.println("확인용" + request.getParameter("no"));
			System.out.println("확인용" + request.getParameter("no"));
			System.out.println("확인용" + request.getParameter("no"));
			System.out.println("확인용" + request.getParameter("no"));
			System.out.println("확인용" + request.getParameter("no"));
		int no = Integer.parseInt(request.getParameter("no"));
		
		eduService.removeEduBoard(no);
		
		rttr.addFlashAttribute("message", "공지사항 삭제에 성공하셨습니다.!");
		
		return "redirect:/edu/list";
	}
	
	@PostMapping("/update")
	public String modifyEduBoard(@ModelAttribute EduDTO edu, HttpServletRequest request,
			RedirectAttributes rttr) throws BoardModifyException {
		eduService.modifyEduBoard(edu);
		
		rttr.addFlashAttribute("message", "게시판 수정에 성공하셨습니다.");
		
		return "redirect:/edu/list";
		
	}
	
	@GetMapping("/download/{fileNo}")
	public ModelAndView downloadFile(@PathVariable("fileNo") String fileNo) throws IOException {
		int no = Integer.parseInt(URLDecoder.decode(fileNo, "UTF-8"));
		
		Map<String, Object> fileInfo = new HashMap<String, Object>();
		
		EduFileDTO file = eduService.findFile(no);
		fileInfo.put("filePath", file.getSavedPath());
		fileInfo.put("fileOriginName", file.getOriginalName());
		fileInfo.put("fileRandomName", file.getRandomName());
		
		return new ModelAndView("fileDownloadView", "downloadFile", fileInfo);
	}
	
	@GetMapping("/deleteFile/{fileNo}")
	public ModelAndView deleteFile(@PathVariable("fileNo") String fileNo, HttpSession session,
			RedirectAttributes rttr, ModelAndView mv) throws NumberFormatException, UnsupportedEncodingException {
		int fileNumber = Integer.parseInt(URLDecoder.decode(fileNo, "UTF-8"));
		
		EduFileDTO eduFileDTO = eduService.removeEduFile(fileNumber);
		
		String root = session.getServletContext().getRealPath("resources");
		
		String filePath = root + "/eduUploadFiles";
		
		File file = new File(filePath + "\\" + eduFileDTO.getRandomName());
		
		if(file.exists()) {
			file.delete();
		}
		
		mv.addObject("intent", "/edu/deleteFile");
		mv.setViewName("redirect:/edu/list");
		
		rttr.addFlashAttribute("message", "게시판 첨부파일 삭제에 성공하셨습니다.");
		
		return mv;
	}
	
}
