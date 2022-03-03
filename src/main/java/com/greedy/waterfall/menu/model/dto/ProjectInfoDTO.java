package com.greedy.waterfall.menu.model.dto;

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
public class ProjectInfoDTO {
	private int outputAmount;
	private int causedIssueAmount;
	private int solvedIssueAmount;
	private int finishedTaskAmount;
	private int progressingTaskAmount;
}
