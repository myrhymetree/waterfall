package com.greedy.waterfall.task.model.dto;

import com.greedy.waterfall.project.model.dto.ProjectDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ChildTaskDTO {
	
	private int taskNo;							//업무번호
	private int parentTaskNo;					//상위업무번호
	private TaskCategoryDTO parentTaskName;		//상위업무명
	private TaskCategoryDTO childTaskName;		//하위업무명	
	private java.sql.Date startDate;			//시작일
	private java.sql.Date deadline;				//마감기한
	private int progression;					//진행률
	private String progressStatus;				//진행상태("진행중","진행")
	private String typeNo;						//1 : 일반 2: 마일스톤
	private String taskStatus;					//업무 삭제여부
	private int projectNo;						//업무에 해당하는 프로젝트번호
	private int managerNo;						//업무 담당하는 사람 번호
	private String taskCode;					//하위업무 코드명(ex.TEST_ALL)
	private ProjectDTO projectName;
	
	private TaskCategoryDTO taskCategory;
	private TaskDTO parentTask;

}
