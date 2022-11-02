import { Customer } from "./customer";
import { InquiryDetail } from "./inquiry-detail";

export class Inquiry {
    customer:Customer;
    inquiryTrackingNumber:String;
    status:String;
    InquiryDetails:InquiryDetail[];
    
}
