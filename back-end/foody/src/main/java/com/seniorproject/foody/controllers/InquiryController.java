package com.seniorproject.foody.controllers;
import com.seniorproject.foody.dto.InquiryInstruction;
import com.seniorproject.foody.dto.InquiryResponse;
import com.seniorproject.foody.services.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/v1/inquiry")
public class InquiryController {
    @Autowired
    private InquiryService inquiryService;

    // use construcer injection here
    @Autowired
    public InquiryController(InquiryService inquiryService){
        this.inquiryService = inquiryService;
    }

    @PostMapping("/inquiryInstruction")
    public InquiryResponse makeInquiry(@RequestBody InquiryInstruction inquiryInstruction){
        InquiryResponse inquiryResponse = inquiryService.makeInquiry(inquiryInstruction);
        return inquiryResponse;
    }
}
