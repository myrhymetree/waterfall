package com.greedy.waterfall.project.model.dto;

import java.util.List;

import com.greedy.waterfall.board.model.dto.EduDTO;
import com.greedy.waterfall.board.model.dto.GuideDTO;
import com.greedy.waterfall.board.model.dto.MeetingDTO;
import com.greedy.waterfall.board.model.dto.NoticeDTO;
import com.greedy.waterfall.board.model.dto.TodoDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class ProjectMainBoardDTO {

	private List<MeetingDTO> meetingBoard;
	private List<TodoDTO> todoBoard;
	private List<NoticeDTO> noticeBoard;
	private List<EduDTO> eduBoard;
	private List<GuideDTO> guideBoard;
	
}
