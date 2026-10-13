package kg.eb.partner.controllers;

import jakarta.validation.Valid;
import kg.eb.partner.models.PartnerRegisterDto;
import kg.eb.partner.service.PartnerRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/partners")
@RequiredArgsConstructor
public class PartnerController {

    private final PartnerRegistrationService partnerRegistrationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void partnerRegister(@Valid @RequestBody PartnerRegisterDto partnerRegisterDto) {
        partnerRegistrationService.create(partnerRegisterDto);

    }
}
