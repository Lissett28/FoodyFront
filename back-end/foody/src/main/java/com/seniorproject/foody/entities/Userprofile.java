package com.seniorproject.foody.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Userprofile {

    @Id
    @Column(name = "appUser_id")
    private Long id;

    private String firstName;
    private String lastNameInit;
    private Date memberSince;


    // locations
    private String street;
    private String city;
    private String state;
    private String zipCode;

    @OneToOne
    @MapsId
    @JoinColumn(name = "appUser_id")
    private AppUser appUser;

    public Userprofile(String firstName, String lastNameInit, Date memberSince, String street, String city, String state, String zipCode) {
        this.firstName = firstName;
        this.lastNameInit = lastNameInit;
        this.memberSince = memberSince;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
