package com.greedy.waterfall.board.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * Class : GuideDTO
 * Comment : TBL_BOARD 테이블과 대응할 테이블
 * 
 * History
 * 2022. 2. 21.  (박성준)
 * </pre>
 * @version 1.1
 * @author 박성준
 */
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GuideDTO {

	private int no;							//글번호
	private java.util.Date postingDate;		//등록일
	private String title;					//제목
	private String content;					//본문내용
	private String status;					//게시물 삭제 여부
	private int projectNo;					//프로젝트 번호
	private int categoryNo;					//게시판 카테고리 번호(종류)
	private java.util.Date UpdatedDate;		//수정일
	private int writerMemberNo;				//회원번호(작성자) (조인해서 멤버 이름 가져오는 용도, 등록할때도 사용)
	private int count;						//조회 수
	private GuideMemberDTO writer;			//TBL_MEMBER 테이블과 join하는 경우 1:1 조인이 될 것이기 때문에 MemberDTO 타입으로 생성
	private int rnum;						//게시판을 순차적으로 표시할 식별자
	private GuideFileDTO file;				//File 테이블과 join하는 경우 1:1 조인이 될 것이기 때문에 GuideFileDTO로 생성
	private int fileNo;						//첨부파일 번호
	private String originalName;			//첨부파일 이름
}
