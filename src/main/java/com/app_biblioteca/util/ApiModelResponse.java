package com.app_biblioteca.util;

import lombok.Data;

@Data
public class ApiModelResponse<T> {
	
	private boolean success;
    private String message;
    private T data;
    
    public ApiModelResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
