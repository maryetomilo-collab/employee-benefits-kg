package kg.eb.common.exception;

import org.springframework.http.HttpStatusCode;

import java.util.List;

public record ApiError (HttpStatusCode status, String code, List<ValidationError> errors) {
}
