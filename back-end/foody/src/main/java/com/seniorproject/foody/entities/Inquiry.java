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
@Table(name ="inquiries")
@Getter
@Setter
public class Inquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="inquiry_tracking_number")
    private String inquiryTrackingNumber;

    @Column(name="status")
    private String status;

    @Column(name="date_created")
    @CreationTimestamp
    private Date date_created;

    @Column(name="last_updated")
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
}
