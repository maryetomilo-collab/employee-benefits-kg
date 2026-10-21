package kg.eb.partner.service;

import kg.eb.partner.entity.Partner;
import kg.eb.partner.exception.PartnerEmailAlreadyExistsException;
import kg.eb.partner.models.PartnerRegisterDto;
import kg.eb.partner.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PartnerRegistrationService {

    private final PartnerRepository repository;

    @Transactional
    public void create(PartnerRegisterDto partnerRegisterDto) {

        if(repository.existsByEmail(partnerRegisterDto.email())) {
            throw new PartnerEmailAlreadyExistsException();
        }
        Partner partner = new Partner(partnerRegisterDto.businessName(), partnerRegisterDto.email());

        repository.save(partner);
    }
}
