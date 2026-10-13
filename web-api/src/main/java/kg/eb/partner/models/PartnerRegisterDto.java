package kg.eb.partner.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PartnerRegisterDto(
        @NotBlank
        @Size(min = 2, max = 30)
        String businessName,

        @NotBlank
        @Email
        String email
) {}
