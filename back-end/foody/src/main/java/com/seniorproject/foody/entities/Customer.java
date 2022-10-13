package com.seniorproject.foody.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String firstName;


    private String lastName;


    private String email;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private Set<Inquiry> inquiries = new HashSet<>();

    public void addInquiry(Inquiry inquiry){
        if(inquiry != null){
            inquiries.add(inquiry);
            inquiry.setCustomer(this);
        }
    }
}
