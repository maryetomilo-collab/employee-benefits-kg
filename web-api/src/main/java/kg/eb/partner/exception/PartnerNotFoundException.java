package kg.eb.partner.exception;

public class PartnerNotFoundException extends RuntimeException {
    public PartnerNotFoundException(Long partnerId) {
        super("Partner not found with id: " + partnerId);
    }
}
