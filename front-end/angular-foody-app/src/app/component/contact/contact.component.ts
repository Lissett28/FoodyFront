import { Component, OnInit } from '@angular/core';
import { Form, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Customer } from 'src/app/common/customer';
import { Inquiry } from 'src/app/common/inquiry';
import { InquiryDetail } from 'src/app/common/inquiry-detail';
import { InquiryInstruction } from 'src/app/common/inquiry-instruction';
import { ContactService } from 'src/app/service/contact.service';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  // declear the formgroup
  contactFormGroup:FormGroup;


  // inject formBuilder
  constructor(private formBuilder: FormBuilder,private contactService:ContactService) { }

  ngOnInit(): void {

    // create out contact form
    this.contactFormGroup = this.formBuilder.group({
      _customer: this.formBuilder.group({
        firstName: new FormControl('', [Validators.required, Validators.minLength(2)]),
        lastName: new FormControl('', [Validators.required, Validators.minLength(2)]),
        email: new FormControl('',
         // regular expression here to get right input
         // language accepted per class 310 is letter and digit and @ symbol and any letter and digits and . and 2-5 letter for domain extension
         [Validators.required, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,5}$')]
         ),
         
      }),
      // populate the message 
      message: this.formBuilder.group({
        theMessage:[''],
      })
    })
  }

  get firstName(){
    return this.contactFormGroup.get('customer.fistName');
  }
  get lastName(){
    return this.contactFormGroup.get('customer.lastName');
  }
  get email(){
    return this.contactFormGroup.get('customer.email');
  }
  get message(){
    return this.contactFormGroup.get('message.theMessage');
  }

  private resetForm(){
    this.contactFormGroup.reset();
  }

  // add event handler on btn
  onSubmit(){
    // debuging logs
    //console.log(this.contactFormGroup.get('_customer').value);
    // validation
    if (this.contactFormGroup.invalid){
      this.contactFormGroup.markAllAsTouched();
      return;
    }
    // set up the inquiry
    let customer = new Customer();
    customer.firstName = this.contactFormGroup.get('_customer.firstName').value;
    customer.lastName = this.contactFormGroup.get('_customer.lastName').value;
    customer.email = this.contactFormGroup.get('_customer.email').value;
    let inquiry = new Inquiry; 
    inquiry.customer = customer;
    let details:InquiryDetail[] =[];
    details.push(new InquiryDetail(this.contactFormGroup.get('message.theMessage').value));
    let inquiryInstruction = new InquiryInstruction();
    inquiryInstruction.customer = customer;
    inquiryInstruction.inquiry = inquiry;
    inquiryInstruction.inquiryDetails = details;
    // call the REST API via contactService
  
    this.contactService.makeInquiry(inquiryInstruction).subscribe({
      next: response =>{
        alert(`We have recived your inquiry \n tracking number: ${response.inquiryTrackingNumber}`);
        // reset the form
        this.resetForm;
      },
      error: err =>{
        alert(`Sorry there is something wrong, please try again later, ${err.message}`);// handles the message
      }
    })
    
  }
}
