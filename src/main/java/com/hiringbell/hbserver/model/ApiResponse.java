package com.hiringbell.hbserver.model;

import org.springframework.http.HttpStatus;

public class ApiResponse {

    public String Token;
    public Object ResponseBody;
    public int HttpStatusCode;
    public String Message;

    public ApiResponse(String token) {
        this.Token = token;
    }

    public ApiResponse() { }

    public static ApiResponse Ok(Object data) {
        ApiResponse response = new ApiResponse();
        response.setResponseBody(data);
        response.setHttpStatusCode(HttpStatus.OK.value());
        response.setMessage("successfull");
        return response;
    }

    public static ApiResponse Ok(Object data, String token) {
        ApiResponse response = new ApiResponse();
        response.setResponseBody(data);
        response.setMessage("successfull");
        response.setToken(token);
        response.setHttpStatusCode(HttpStatus.OK.value());
        return response;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }


    public Object getResponseBody() {
        return ResponseBody;
    }

    public void setResponseBody(Object responseBody) {
        ResponseBody = responseBody;
    }

    public int getHttpStatusCode() {
        return HttpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        HttpStatusCode = httpStatusCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
