package com.greedy.waterfall.test.model.dto;

//@Setter
//@Getter
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
public class TestDTO {
	
	private int no;
	private String name;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "TestDTO [no=" + no + ", name=" + name + "]";
	}
	protected TestDTO() {
		super();
	}
	protected TestDTO(int no, String name) {
		super();
		this.no = no;
		this.name = name;
	}
	
}
