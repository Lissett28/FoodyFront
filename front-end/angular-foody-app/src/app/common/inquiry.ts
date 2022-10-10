import { Customer } from "./customer";
import { InquiryDetail } from "./inquiry-detail";

export class Inquiry {
    customer:Customer;
    inquiryTrackingNumber:string;
    status:string;
    InquiryDetails:InquiryDetail[];
    
}
