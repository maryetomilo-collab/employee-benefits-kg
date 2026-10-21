package kg.eb.partner.exception;

public class PartnerEmailAlreadyExistsException extends RuntimeException{

    public PartnerEmailAlreadyExistsException() {
        super("This email already exists");
    }
}
