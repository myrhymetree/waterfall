package com.greedy.waterfall.project.model.dto;

import java.util.List;

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
public class MyProjectDTO {

	private int no;
	private List<ProjectDTO> manageProject;
	private List<ProjectDTO> joinProject;
	
	
	
}
