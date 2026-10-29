package kg.eb.partner.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record PartnerOfferDto(
        @NotBlank
        @Size(min = 1, max = 255)
        String title,

        @NotNull
        @Positive
        Long partnerId
) {
}
