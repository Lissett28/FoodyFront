package com.seniorproject.foody.entities;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Inquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String inquiryTrackingNumber;


    private String status;


    @CreationTimestamp
    private Date date_created;


    @UpdateTimestamp
    private Date last_updated;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inquiry")
    private Set<InquiryDetail> inquiryDetails = new HashSet<>();

    public void addDetail(InquiryDetail inquiryDetail){
        if(inquiryDetail != null){
            inquiryDetails.add(inquiryDetail);
            inquiryDetail.setInquiry(this);
        }
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
