import { Customer } from "./customer";
import { Inquiry } from "./inquiry";
import { InquiryDetail } from "./inquiry-detail";

export class InquiryInstruction {
    customer:Customer;
    inquiry:Inquiry;
    inquiryDetails:InquiryDetail[];
    constructor(){
        this.inquiryDetails = [];
    }
}
