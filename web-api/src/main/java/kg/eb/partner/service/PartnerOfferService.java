package kg.eb.partner.service;

import kg.eb.partner.entity.Offer;
import kg.eb.partner.entity.Partner;
import kg.eb.partner.exception.PartnerNotFoundException;
import kg.eb.partner.models.PartnerOfferDto;
import kg.eb.partner.repository.OfferRepository;
import kg.eb.partner.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PartnerOfferService {
    private final OfferRepository partnerOfferRepository;
    private final PartnerRepository partnerRepository;

    public Long createDraft(PartnerOfferDto offerDto) {

        Partner partner = partnerRepository.findById(offerDto.partnerId()).orElseThrow(() -> new PartnerNotFoundException(offerDto.partnerId()));
        Offer offer = new Offer(partner, offerDto.title());
        Offer savedOffer = partnerOfferRepository.save(offer);
        return savedOffer.getId();
    }
}
