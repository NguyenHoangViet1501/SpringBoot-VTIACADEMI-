//package com.data.exception;
//
//import com.data.dto.ApiResponseDTO;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.ui.Model;
//import org.springframework.web.context.request.WebRequest;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestControllerAdvice
//public class GlobalException {
//
//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public ResponseEntity argException(MethodArgumentNotValidException exception){
//        List<ApiResponseDTO> result = new ArrayList<>();
//
//        for (Object object : exception.getBindingResult().getAllErrors()){
//            FieldError err = (FieldError) object;
//            String key = err.getDefaultMessage();
//
//            ErrorCode errorCode = ErrorCode.valueOf(key);
//
//            ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
//            apiResponseDTO.setCode(errorCode.getCode());
//            apiResponseDTO.setMesssage(errorCode.getMesagge());
//            apiResponseDTO.setStatus(errorCode.getStatus());
//
//            result.add(apiResponseDTO);
//        }
//
//        return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(value = AppException.class)
//    public ResponseEntity<?> appEx(AppException appException){
//        ErrorCode errorCode = appException.getErrorCode();
//
//        ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
//        apiResponseDTO.setCode(errorCode.getCode());
//        apiResponseDTO.setMesssage(errorCode.getMesagge());
//
//        return new ResponseEntity<>(apiResponseDTO, errorCode.getStatus());
//    }
//}
//
