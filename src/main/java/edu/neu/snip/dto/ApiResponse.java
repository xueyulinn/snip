package edu.neu.snip.dto;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private int code;
    private T data;
    private String message;
    private long timeStamp;

    public ApiResponse(){
        this.timeStamp = System.currentTimeMillis();
    }

    public ApiResponse(int code, String message){
        this();
        this.code = code;
        this.message = message;
    }

    public ApiResponse(int code, String message, T data){
        this();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T>ApiResponse<T> success(String message, T data){
        return new ApiResponse<>(200, message, data);
    }

    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(200, message);
    }

    public static <T> ApiResponse<T> fail(int code, String message) {
        return new ApiResponse<>(code, message);
    }
}
