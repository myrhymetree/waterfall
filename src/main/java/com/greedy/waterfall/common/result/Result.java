package com.greedy.waterfall.common.result;

import org.springframework.stereotype.Component;

public class Result {
	
	boolean result;
	
	public Result() {
		this.result = false;
	}
	
	public Result(Integer testValue) {
		if(testValue != null) {
			if(testValue > 0) {
				this.result = true;
			} else {
				this.result = false;
			}
		} else {
			this.result = false;
		}
	}
	
	public Result and(Integer testValue) {
		if(testValue != null) {
			if(testValue > 0) {
				this.result = true;
			} else {
				this.result = false;
			}
		} else {
			this.result = false;
		}
		
		return this;
	}
	
	public boolean isOk() {
		
		return this.result;
	}

}
