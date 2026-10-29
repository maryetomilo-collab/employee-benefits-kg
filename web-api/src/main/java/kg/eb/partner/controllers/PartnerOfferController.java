package kg.eb.partner.controllers;

import jakarta.validation.Valid;
import kg.eb.partner.models.CreateOfferResponse;
import kg.eb.partner.models.PartnerOfferDto;
import kg.eb.partner.service.PartnerOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/partner/offers")
@RequiredArgsConstructor
public class PartnerOfferController {
    private final PartnerOfferService partnerOfferService;

    @PostMapping
    public ResponseEntity<CreateOfferResponse> createOffer(@Valid @RequestBody PartnerOfferDto offer) {
        Long id = partnerOfferService.createDraft(offer);

        return ResponseEntity.status(HttpStatus.CREATED).body(new CreateOfferResponse(id));
    }
}
