package kg.eb.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ValidationErrorCode mapValidationCode(String code) {
        return switch (code) {
            case "NotBlank" -> ValidationErrorCode.REQUIRED;
            case "Email" -> ValidationErrorCode.INVALID_EMAIL;
            case "Size" -> ValidationErrorCode.INVALID_SIZE;
            default -> ValidationErrorCode.INVALID_VALUE;
        };
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidation(MethodArgumentNotValidException exception) {

        List<ValidationError> errors = exception
        .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new ValidationError(fieldError.getField(), mapValidationCode(fieldError.getCode()))).toList();

                        return new ApiError(exception.getStatusCode(), "VALIDATION_ERROR", errors);
    }
}
