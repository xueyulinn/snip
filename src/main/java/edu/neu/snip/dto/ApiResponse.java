package edu.neu.snip.dto;

import lombok.Data;

/**
 * Generic envelope for REST API responses, carrying a status code, message, payload and timestamp.
 *
 * @param <T> the type of the response payload
 */
@Data
public class ApiResponse<T> {

  private int code;
  private T data;
  private String message;
  private long timeStamp;

  public ApiResponse() {
    this.timeStamp = System.currentTimeMillis();
  }

  /**
   * Creates a response with the given code and message and no payload.
   *
   * @param code the status code
   * @param message the response message
   */
  public ApiResponse(int code, String message) {
    this();
    this.code = code;
    this.message = message;
  }

  /**
   * Creates a response with the given code, message and payload.
   *
   * @param code the status code
   * @param message the response message
   * @param data the response payload
   */
  public ApiResponse(int code, String message, T data) {
    this();
    this.code = code;
    this.message = message;
    this.data = data;
  }

  public static <T> ApiResponse<T> success(String message, T data) {
    return new ApiResponse<>(200, message, data);
  }

  public static <T> ApiResponse<T> success(String message) {
    return new ApiResponse<>(200, message);
  }

  public static <T> ApiResponse<T> fail(int code, String message) {
    return new ApiResponse<>(code, message);
  }
}
