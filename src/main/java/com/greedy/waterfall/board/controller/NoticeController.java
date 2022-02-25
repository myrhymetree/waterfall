
package com.greedy.waterfall.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.greedy.waterfall.board.model.dto.NoticeDTO;
import com.greedy.waterfall.board.model.noticedto.NoticeAttachmentDTO;
import com.greedy.waterfall.board.model.service.NoticeService;
import com.greedy.waterfall.common.paging.Pagenation;
import com.greedy.waterfall.common.paging.SelectCriteria;

/**
 * <pre>
 * Class : NoticeController Comment : 공지사항 목록 조회(페이징가능), 상세조회, 수정, 삭제 기능이 존재합니다.
 * 
 * History 2022. 2. 17. (김서영)
 * 
 * @version 1
 * @author 김서영
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {

	private final NoticeService noticeService;

	@Autowired
	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	/**
	 * noticeFindList : 공지사항 목록조회
	 * 
	 * @param first  : HttpServletRequest(request)
	 * @param second : ModelAndView(response)
	 * @return : ModelAndView
	 */
	@GetMapping("/list")
	public ModelAndView noticeFindList(HttpServletRequest request, ModelAndView mv) {

		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if (currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);

		}

		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");

		System.out.println("searchCondition : " + searchCondition);
		System.out.println("searchValue : " + searchValue);

		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);

		System.out.println("컨트롤러에서 검색조건 확인하기 : " + searchMap);

		int totalCount = noticeService.findTotalCount(searchMap);

		System.out.println("totalNoticeCount : " + totalCount);

		int limit = 20;

		int buttonAmount = 5;

		SelectCriteria selectCriteria = null;

		if (searchCondition != null && !"".equals(searchCondition)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition,
					searchValue);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}

		System.out.println(selectCriteria);

		List<NoticeDTO> noticeList = noticeService.findNoticeList(selectCriteria);

		System.out.println("NoticeList : " + noticeList);

		mv.addObject("noticeList", noticeList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("board/notice/noticeList");

		return mv;
	}

	/**
	 * findNoticeDetail : 공지사항 상제 조회
	 * 
	 * @param first  : HttpServletRequest(request)
	 * @param second : ModelAndView(response)
	 * @return : gson.toJson(noticeDetail) : 클릭한 게시물에 대한 정보를 담은 gson객체
	 */
	@GetMapping(value = "noticeDetail", produces = "application/json; charset= UTF-8")
	@ResponseBody
	public String findNoticeDetail(HttpServletRequest request) {

		/* request에서 공지사항 번호를 변수 no에 저장 */
		int no = Integer.parseInt(request.getParameter("no"));
		
		NoticeDTO noticeDetail = noticeService.findNoticeDetail(no);

		System.out.println("상세조회 noticeDetail : " + noticeDetail);

		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls().disableHtmlEscaping()
				.create();

		return gson.toJson(noticeDetail);
	}

	/**
	 * registNotice : 공지사항 등록
	 * 
	 * @param first  : @ModelAttribute NoticeDTO
	 * @param second : HttpServletRequest request
	 * @param third  : RedirectAttributes rttr
	 * @return : 공지사항 목록조회로 리다이렉트(String 반환)
	 */
	@PostMapping("/regist")
	public String registNotice(@ModelAttribute NoticeDTO notice, HttpServletRequest request, RedirectAttributes rttr
			,@RequestParam MultipartFile noticeFile) {
		
		int memberNo = 1;
		String content = request.getParameter("content");

		notice.setMemberNo(memberNo);
		System.out.println("content 확인" + content);
		
		/* 파일 저장될 root 설정 */
		String root = request.getServletContext().getRealPath("resources");
		System.out.println("file root : " +root);
		String fileUploadDirectory = root + "/noticeUpload/noticeOriginal";
		
		/* filepath 경로인 폴더가 존재하는지 확인  */
		File directory = new File(fileUploadDirectory);
		if(!directory.exists()) {
			directory.mkdirs();
		}
		
		
		
		if(noticeFile.getSize() > 0 ) {
			/* request로 들어온 파일 확인 */
			System.out.println("singleFile : " + noticeFile);
			
			/* savedName 생성 */
			String originFileName = noticeFile.getOriginalFilename();
			String ext = originFileName.substring(originFileName.lastIndexOf("."));
			String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
			
			System.out.println("originFileName : " + originFileName);
			System.out.println("savedName : " + savedName);
			
			/* attachmentDTO에 file 정보 저장해서 noticeDTO에 set */
			NoticeAttachmentDTO attachmentDTO = new NoticeAttachmentDTO();
			attachmentDTO.setFilePath(fileUploadDirectory);
			attachmentDTO.setOriginName(originFileName);
			attachmentDTO.setRandomName(savedName);
			notice.setAttachmentDTO(attachmentDTO);
			
			/* 파일 저장 */
			try {
				noticeFile.transferTo(new File(fileUploadDirectory + "/" + savedName));
				System.out.println("등록용 noticeDTO 확인 : " + notice);
				
				noticeService.registNotice(notice);
				
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				new File(fileUploadDirectory + "\\" + savedName).delete();
				
			}
			
			rttr.addFlashAttribute("message", "공지사항 등록에 성공하셨습니다.");
		} else {
			noticeService.registNotice(notice);
		}
		
		

		return "redirect:/notice/list";
	}

	/**
	 * modifyNotice : 공지사항 수정
	 * 
	 * @param first  @ModelAttribute NoticeDTO notice
	 * @param second HttpServletRequest request
	 * @return 공지사항 목록조회로 redirect
	 * 
	 * @author 김서영
	 */
	@PostMapping("/update")
	public String modifyNotice(@ModelAttribute NoticeDTO notice, HttpServletRequest request) {

		int no = Integer.parseInt(request.getParameter("no"));

		notice.setContent(request.getParameter("content"));
		notice.setMemberNo(1);
		notice.setProjectNo(3);

		noticeService.modifyNotice(notice);

		return "redirect:/notice/list";
	}

	/**
	 * removeNotice : 공지사항 삭제
	 * @param first HttpServletRequest request
	 * @param second RedirectAttributes rttr
	 * @return 공지사항 목록 조회로 redirect
	 * 
	 * @author 김서영
	 */
	@GetMapping("/delete")
	public String removeNotice(HttpServletRequest request, RedirectAttributes rttr) {
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		noticeService.removeNotice(no);
		
		rttr.addFlashAttribute("message", "공지사항 삭제에 성공하셨습니다.");
		
		return "redirect:/notice/list";
	}



}
