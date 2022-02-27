package com.example.creditsystem.Model.Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapping;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "customer")
public class Customer implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "T.C. kimlik numarası boş bırakılamaz!")
    @Column(name = "identity_number")
    private String identityNumber;

    @NotNull(message = "Ad kısmı boş bırakılamaz!")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "Soyad kısmı boş bırakılamaz!")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Aylık gelir kısmı boş bırakılamaz!")
    @Column(name = "monthly_income")
    private int monthlyIncome;

    @NotNull(message = "Telefon numarası bilgisi boş bırakılamaz!")
    @Column(name = "phone_number")
    private String phoneNumber;

/*    @OneToOne(mappedBy = "CreditScore",cascade = CascadeType.MERGE)
    @JoinColumn(name = "identity_number")
    private CreditScore creditScore;*/


    //kimlik numarası, ad-soyad, aylık gelir ve telefon bilgileri
}
