package kg.eb.partner.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kg.eb.partner.enums.OfferStatusEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "offers")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Offer {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(nullable = false)
    private String title;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OfferStatusEnum status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "partner_id", nullable = false)
    private Partner partner;

    public Offer(Partner partner, String title) {
        this.partner = partner;
        this.title = title;
        this.status = OfferStatusEnum.DRAFT;
    }
}
