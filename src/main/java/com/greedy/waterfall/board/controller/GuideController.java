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
import com.greedy.waterfall.board.model.dto.GuideDTO;
import com.greedy.waterfall.board.model.service.GuideService;
import com.greedy.waterfall.common.exception.GuideModifyException;
import com.greedy.waterfall.common.exception.GuideRegistException;
import com.greedy.waterfall.common.exception.GuideRemoveException;
import com.greedy.waterfall.common.paging.Pagenation;
import com.greedy.waterfall.common.paging.SelectCriteria;

/**
 * <pre>
 * Class : GuideController
 * Comment : 페이징 처리를 위한 값과 가이드 게시판 게시글의 내용을 화면에 보여주기 위한 클래스
 * 
 * History
 * 2022. 2. 19.  (박성준)
 * @version 1
 * @author 박성준
 */
@Controller
@RequestMapping("/guide")
public class GuideController {
	
	private final GuideService guideService;

	@Autowired
	private GuideController(GuideService guideService) {
		this.guideService = guideService;
	}
	
	/**
	 * guideList : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 박성준
	 */
	@GetMapping("/list")
	public ModelAndView guideList(HttpServletRequest request, ModelAndView mv) {
		
		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;
		
		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}
		
		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");
		
		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);
		
		System.out.println("컨트롤러에서 검색조건 확인하기 : " + searchValue);
		
		int totalCount = guideService.selectTotalCount(searchMap);
		
		System.out.println("totalGuideCount : " + totalCount);
		
		int limit = 10;
		
		int buttonAmount = 5;
		
		SelectCriteria selectCriteria = null;
		
		if(searchCondition != null && !"".equals(searchCondition)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}
		
		System.out.println(selectCriteria);
		
		List<GuideDTO> guideList = guideService.selectAllGuideList(selectCriteria);
		
		mv.addObject("guideList", guideList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.addObject("intent", "/guide/list");
		mv.setViewName("/board/guide/guideList");
		
		return mv;
	}
	
	@PostMapping("/regist")
	public String registGuide(@ModelAttribute GuideDTO guide, HttpServletRequest request,
			RedirectAttributes rttr, @RequestParam MultipartFile singleFile, Model model) throws GuideRegistException {
		/* 나중에 로그인 기능이 구현되면 HttpServletRequest를 webRequst로 바꿔서 해보자 */
		/* @ModelAttribute는 view에서 넘어온 데이터를 GuideDTO와 바인딩 해주는 역할을 함 */
		/* 첨부파일은 일반적인 request객체에서 값을 꺼내는것이 아닌 multi-part 객체에서 꺼내야된다 */

		/* 로그인 기능이 구현되면 쓸 코드 */
//		int writerMemberNo = ((GuideMemberDTO) request.getSession().getAttribute("loginMember").getNo();
		
		
		/* 로그인 기능이 구현되면 쓸 코드 */
//		guide.setWriterMemberNo(writerMemberNo);
		
		guideService.registGuide(guide);
		
		rttr.addFlashAttribute("message", "게시글 등록에 성공하셨습니다.");
		
		System.out.println("singleFile : " + singleFile);
		
		String root = request.getSession().getServletContext().getRealPath("resources");		//임시 사용코드, 파일을 이 위치에 저장하기 위해 어거지로 넣은것, 실제로 현업에서는 파일서버를 따로둠
		
		System.out.println("root : " + root);		//절대경로 반환
		
		String filePath = root + "\\uploadFiles";
		
		File mkdir = new File(filePath);
		
		/* 폴더 생성 */
		if(!mkdir.exists()) {
			mkdir.mkdirs();
		}
		
		/* 파일 명 변경처리 */
		String originFileName = singleFile.getOriginalFilename();
		String ext = originFileName.substring(originFileName.lastIndexOf("."));		//확장자
		String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
		
		System.out.println("originFileName : " + originFileName);
		System.out.println("savedName : " + savedName);
		
		/* 파일 저장 */
		try {
			singleFile.transferTo(new File(filePath + "\\" + savedName));
			model.addAttribute("message", "파일 업로드 성공" );
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			
			/* 실패 시 파일 삭제*/
			new File(filePath + "\\" + savedName).delete();
			model.addAttribute("message", "파일업로드 실패");
		}
		
		return "redirect:/guide/list";
		
	}
	
	@GetMapping("/delete")
	public String removeGuide(@ModelAttribute GuideDTO guide, HttpServletRequest request, 
			RedirectAttributes rttr) throws GuideRemoveException {
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		System.out.println("삭제하기 위해 no 받기 " + no );
		
		guideService.removeGuide(no);
		
		rttr.addFlashAttribute("message", "가이드 게시판 삭제에 성공하셨습니다.");
		
		return "redirect:/guide/list";
		
	}
	
	@PostMapping("/update")
	public String modifyGuide(@ModelAttribute GuideDTO guide, HttpServletRequest request,
			RedirectAttributes rttr) throws GuideModifyException {
				
		guideService.modifyGuide(guide);
		
		System.out.println("modifyGuide : " + guide);
		
		rttr.addFlashAttribute("message", "가이드 게시판 수정에 성공하셨습니다");
		
		return "redirect:/guide/list";
	}
	
	@GetMapping(value="guideDetail", produces ="application/json; charset= UTF-8")
    @ResponseBody
    public String findguideDetail(HttpServletRequest request) {
    
    int no = Integer.parseInt(request.getParameter("no"));
    GuideDTO guideDetail= guideService.findGuideDetail(no);
    System.out.println("상세조회 guideDetail : " +guideDetail);
    Gson gson = new GsonBuilder()
          .setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
          .setPrettyPrinting()
          .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
          .serializeNulls()
          .disableHtmlEscaping()
          .create();
   
    
   return gson.toJson(guideDetail); }
	
}