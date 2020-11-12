package id.nexbyte.erp.branchservice.exception;

import id.nexbyte.erp.branchservice.exception.model.Error;
import id.nexbyte.erp.branchservice.exception.model.SubError;
import id.nexbyte.erp.branchservice.exception.model.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<SubError> errorList = new ArrayList<>(ex.getBindingResult().getFieldErrorCount());
        ex.getBindingResult().getFieldErrors().forEach(e -> {
            ValidationError error = ValidationError.builder()
                    .message(e.getDefaultMessage())
                    .object(e.getObjectName())
                    .field(e.getField())
                    .rejectedValue(e.getRejectedValue())
                    .build();
            errorList.add(error);
        });

        Error error = Error.builder()
                .message(ex.getMessage())
                .subErrors(errorList)
                .status(HttpStatus.BAD_REQUEST)
                .build();

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        List<SubError> errorList = new ArrayList<>(ex.getConstraintViolations().size());
        ex.getConstraintViolations().forEach(e -> {
            ValidationError error = ValidationError.builder()
                    .message(e.getMessage())
                    .object(e.getRootBeanClass().getSimpleName())
                    .field(e.getPropertyPath().toString())
                    .rejectedValue(e.getInvalidValue())
                    .build();
            errorList.add(error);
        });

        Error error = Error.builder()
                .message(ex.getMessage())
                .subErrors(errorList)
                .status(HttpStatus.BAD_REQUEST)
                .build();

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFound(NotFoundException ex) {
        Error error = Error.builder()
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .build();

        return ResponseEntity.ok(error);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Object> handleAlreadyExists(AlreadyExistsException ex) {
        Error error = Error.builder()
                .message(ex.getMessage())
                .status(HttpStatus.CONFLICT)
                .build();

        return ResponseEntity.status(error.getStatus()).body(error);
    }

}
