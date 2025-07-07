package com.data.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    //auth
    //username
    INVALID_USERNAME_LENGTH(1003,"Username length: 2 - 50" , HttpStatus.BAD_REQUEST),
    USERNAME_REQUIRED(1004, "Username must not be null", HttpStatus.BAD_REQUEST),
    USER_EXIST(1005, "User already exists", HttpStatus.BAD_REQUEST),

    //password
    INVALID_PASSWORD_LENGTH(1006, "Password length must be 3 - 30 characters", HttpStatus.BAD_REQUEST),
    PASSWORD_REQUIRED(1007, "Password must not be null", HttpStatus.BAD_REQUEST),

    //email
    INVALID_EMAIL_LENGTH(1008, "Email length must be 3 - 100 characters", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL(1009, "Email is incorrect format", HttpStatus.BAD_REQUEST),
    EMAIL_REQUIRED(10010, "Email must not be null", HttpStatus.BAD_REQUEST),
    EMAILL_EXIST(1011, "Email already exists", HttpStatus.BAD_REQUEST),

    //dob
    INVALID_DOB_FORMAT(1012, "Date of birth must be in yyyy-MM-dd format and date of birth must be in the past", HttpStatus.BAD_REQUEST),

    //login
    USERNAME_AND_PASSWORD_REQUIRED(1013, "Username and password must not be null", HttpStatus.BAD_REQUEST),
    USERNAME_OR_PASSWORD_INCORRECT(1014,"Username or password is incorrect", HttpStatus.BAD_REQUEST),

    //Course
    //course_name
    COURSE_NAME_REQUIRED(2001, "Course name must not be null", HttpStatus.BAD_REQUEST),
    INVALID_COURSE_NAME_LENGTH(2002, "Course name length must be between 2 and 100 characters", HttpStatus.BAD_REQUEST),
    COURSE_EXIST(2003, "Course already exists", HttpStatus.BAD_REQUEST),

    //sessions and hours > 0
    INVALID_SESSION_COUNT(2004, "Session count must be >= 0", HttpStatus.BAD_REQUEST),
    INVALID_HOURS(2005, "Hours must be >= 0", HttpStatus.BAD_REQUEST),

    //search
    CATEGORY_NOT_FOUND(2006, "Course category not found", HttpStatus.NOT_FOUND),
    COURSE_NOT_FOUND(2007, "Course not found", HttpStatus.NOT_FOUND),
    ;

    int code;
    String mesagge;
    HttpStatus status;

    ErrorCode(int code, String mesagge, HttpStatus status) {
        this.code = code;
        this.mesagge = mesagge;
        this.status = status;
    }
}
