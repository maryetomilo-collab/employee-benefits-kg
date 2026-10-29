package kg.eb.partner.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kg.eb.partner.enums.PartnerStatusEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "partners")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 30)
    @Column(nullable = false)
    @NotNull
    private String businessName;

    @Email
    @Column(nullable = false, unique = true)
    @NotNull
    private String email;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PartnerStatusEnum status;

    public Partner(String businessName, String email) {
        this.businessName = businessName;
        this.email = email;
        this.status = PartnerStatusEnum.DRAFT;
    }

}
