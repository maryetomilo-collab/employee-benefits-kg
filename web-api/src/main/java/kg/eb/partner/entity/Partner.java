package kg.eb.partner.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import kg.eb.partner.enums.PartnerStatusEnum;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "partners")

public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 30)
    @Column(nullable = false)
    @NonNull
    private String businessName;

    @Email
    @Column(nullable = false, unique = true)
    @NonNull
    private String email;



    @Enumerated(
            jakarta.persistence.EnumType.STRING
    )
    @Column(nullable = false)
    private PartnerStatusEnum status;

    public Partner(String businessName, String email) {
        this.businessName = businessName;
        this.email = email;
        this.status = PartnerStatusEnum.DRAFT;
    }

    protected Partner() {}
}
