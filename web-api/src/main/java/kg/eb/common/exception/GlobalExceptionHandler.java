package kg.eb.common.exception;

import kg.eb.partner.exception.PartnerEmailAlreadyExistsException;
import kg.eb.partner.exception.PartnerNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ValidationErrorCode mapValidationCode(String code) {
        if (code == null) {
            return ValidationErrorCode.INVALID_VALUE;
        }

        return switch (code) {
            case "NotBlank", "NotNull" -> ValidationErrorCode.REQUIRED;
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

                        return new ApiError(exception.getStatusCode().value(), "VALIDATION_ERROR", errors);
    }

    @ExceptionHandler(PartnerEmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handlePartnerEmailAlreadyExistsException() {



        return new ApiError(HttpStatus.CONFLICT.value(), "PARTNER_EMAIL_ALREADY_EXISTS",  List.of());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleDataIntegrityViolationException() {
        return new ApiError(HttpStatus.CONFLICT.value(), "DATA_CONFLICT", List.of());
    }

    @ExceptionHandler(PartnerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handlePartnerNotFoundException() {
        return new ApiError(HttpStatus.NOT_FOUND.value(), "PARTNER_NOT_FOUND", List.of());
    }
}
