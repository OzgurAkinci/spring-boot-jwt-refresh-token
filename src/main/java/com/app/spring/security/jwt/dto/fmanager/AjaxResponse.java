package com.app.spring.security.jwt.dto.fmanager;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AjaxResponse {
    private String fileName;
    private boolean uploaded;
    private String url;
    private String message;
    private Error error;

    @Data
    @AllArgsConstructor
    public static class Error {
        private String message;
    }
}
