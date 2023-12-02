package com.prevalentware.prueba_tecnica.infrastructure.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class APIResponse <T>{

    private String status;
    private Integer httpStatus;
    private String message;
    private String internalCode;
    private T data;

    public static <T> APIResponse<T> ok(T data, Map<String, String> responseHashMap, String key) {
        return APIResponse.<T>builder()
                .httpStatus(HttpStatus.OK.value())
                .status(Constant.RESULT_OK)
                .message(responseHashMap.get(key))
                .internalCode(key)
                .data(data)
                .build();
    }

    public static <T> APIResponse<T> badRequest(T data, Map<String, String> responseHashMap, String key) {
        return APIResponse.<T>builder()
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .status(Constant.RESULT_KO)
                .message(responseHashMap.get(key))
                .internalCode(key)
                .data(data)
                .build();
    }

    public static <T> APIResponse<T> notFound(T data, Map<String, String> responseHashMap, String key) {
        return APIResponse.<T>builder()
                .httpStatus(HttpStatus.NOT_FOUND.value())
                .status(Constant.RESULT_KO)
                .message(responseHashMap.get(key))
                .internalCode(key)
                .data(data)
                .build();
    }

    public static <T> APIResponse<T> internalServerError(T data, Map<String, String> responseHashMap, String key) {
        return APIResponse.<T>builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(Constant.RESULT_KO)
                .message(responseHashMap.get(key))
                .internalCode(key)
                .data(data)
                .build();
    }

}