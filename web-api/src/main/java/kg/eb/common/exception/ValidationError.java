package kg.eb.common.exception;

public record ValidationError (
        String field,
        ValidationErrorCode code
) {}
