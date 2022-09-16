package com.ema.exception;

import com.ema.entity.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(FailedMailException.class)
    public ResponseEntity<ErrorResponse> handlerForFailedMailException(final FailedMailException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setDebugMessage("Mail not sent");
        errorResponse.setHttpStatus(HttpStatus.FAILED_DEPENDENCY);
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

//    @ExceptionHandler(com.ema.exception.UsernameNotFoundException.class)
//    public ResponseEntity<ErrorResponse> handlerForUsernameNotFoundException(final UsernameNotFoundException ex){
//        ErrorResponse errorResponse = new ErrorResponse();
//        errorResponse.setMessage(ex.getMessage());
//        errorResponse.setDebugMessage("Username not found, please check your input");
//        errorResponse.setHttpStatus(HttpStatus.NOT_FOUND);
//        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
//    }

    @ExceptionHandler(VerifyEmailException.class)
    public ResponseEntity<ErrorResponse> handlerForVerifyEmailException(final VerifyEmailException ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setDebugMessage("Please verify your email");
        errorResponse.setHttpStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DepartmentAlreadyExists.class)
    public ResponseEntity<ErrorResponse> handlerForDepartmentAlreadyExists(final DepartmentAlreadyExists e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setDebugMessage("Department might already exists. Please check");
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(DepartmentNotFound.class)
    public ResponseEntity<ErrorResponse> handlerForDepartmentNotFound(final DepartmentNotFound e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setDebugMessage("Department may not exist. Please check");
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(EmailAlreadyConfirmed.class)
    public ResponseEntity<ErrorResponse> handlerForEmailAlreadyConfirmed(final EmailAlreadyConfirmed e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setDebugMessage("Email might have already been confirmed. Please check");
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(ListIsEmpty.class)
    public ResponseEntity<ErrorResponse> handlerForListIsEmpty(final ListIsEmpty e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setDebugMessage("List might be empty. Please check");
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(PasswordsDoNotMatch.class)
    public ResponseEntity<ErrorResponse> handlerForPasswordsDoNotMatch(final PasswordsDoNotMatch e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setDebugMessage("Passwords do not match. Please check");
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(SuperAdminAlreadyExists.class)
    public ResponseEntity<ErrorResponse> handlerForSuperAdminAlreadyExists(final SuperAdminAlreadyExists e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setDebugMessage("Super Admin Might Already Exist. Please check");
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(TokenNotFound.class)
    public ResponseEntity<ErrorResponse> handlerForTokenNotFound(final TokenNotFound e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setDebugMessage("No token has been created for this user. Please check");
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<ErrorResponse> handlerForUserAlreadyExists(final UserAlreadyExists e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setDebugMessage("This user might already exist. Please check");
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ErrorResponse> handlerForUserNotFound(final UserNotFound e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setDebugMessage("This user might not exist. Please check");
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerForMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setDebugMessage("Email might be invalid. Please check");
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handlerForConstraintViolationException(final ConstraintViolationException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setDebugMessage("Password might not be invalid. Please check");
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }


}
