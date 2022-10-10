package com.seniorproject.foody.dto;

import com.seniorproject.foody.entities.Customer;
import com.seniorproject.foody.entities.Inquiry;
import com.seniorproject.foody.entities.InquiryDetail;
import lombok.Data;

import java.util.Set;

@Data
public class InquiryInstruction {
    // realationship customer,inquiry, inquirydetail[]
    private Customer customer;
    private Inquiry inquiry;
    private Set<InquiryDetail> inquiryDetails;
}
