package kg.eb.common.exception;

import java.util.List;

public record ApiError (int status, String code, List<ValidationError> errors) {
}
