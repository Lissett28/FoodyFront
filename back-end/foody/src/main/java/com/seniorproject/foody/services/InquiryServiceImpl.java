package com.seniorproject.foody.services;

import com.seniorproject.foody.dao.CustomerRepository;
import com.seniorproject.foody.dto.InquiryInstruction;
import com.seniorproject.foody.dto.InquiryResponse;
import com.seniorproject.foody.entities.Customer;
import com.seniorproject.foody.entities.Inquiry;
import com.seniorproject.foody.entities.InquiryDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

/**
 * implementing the inquiry service
 */

@Service
public class InquiryServiceImpl implements InquiryService{

    private CustomerRepository customerRepository;

    @Autowired
    public InquiryServiceImpl(CustomerRepository customerRepository){
        // inject customer repository
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public InquiryResponse makeInquiry(InquiryInstruction inquiryInstruction){
        // retrive inquiry info from dto
        Inquiry inquiry = inquiryInstruction.getInquiry();

        // generate tracking number
        String inquiryTrackingNumber = generateOrderTrackingNumber();
        inquiry.setInquiryTrackingNumber(inquiryTrackingNumber);

        // populate with details
        Set<InquiryDetail> inquiryDetails = inquiryInstruction.getInquiryDetails();
        inquiryDetails.forEach(item -> inquiry.addDetail(item));

        Customer customer = inquiryInstruction.getCustomer();
        customer.addInquiry(inquiry);
        customerRepository.save(customer);
        return new InquiryResponse(inquiryTrackingNumber);
    }
    /**
     * generate a unique id that is hard to guess
     * @return UUID(Universally Unique Identifier)
     */
    private String generateOrderTrackingNumber() {
        // UUID number genearating it
        // universally unique identifier
        // very low probability it collision.
        // if you get a duplicate , you just won a jackpot
        return UUID.randomUUID().toString();// so easy
    }
}
