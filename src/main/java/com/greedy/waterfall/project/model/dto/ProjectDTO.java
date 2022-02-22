package com.greedy.waterfall.project.model.dto;

import com.greedy.waterfall.board.model.dto.MeetingMemberDTO;

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
public class ProjectDTO {

   private int no;                  //프젝번호
   private String name;            //프젝명
   private java.sql.Date startDate;   //시작일
   private java.sql.Date deadLine;    //마감일
   private int progression;         //진행율
   private String status;           //프로젝트상태여부 
   private java.sql.Date completed;   //종료일
   private String statusCode;          //상태코드
   
   private MeetingMemberDTO member;
   

}