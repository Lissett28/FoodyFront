package com.seniorproject.foody.services;

import com.seniorproject.foody.dto.InquiryInstruction;
import com.seniorproject.foody.dto.InquiryResponse;

public interface InquiryService {
    InquiryResponse makeInquiry(InquiryInstruction inquiryInstruction);
}
