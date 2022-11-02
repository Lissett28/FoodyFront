import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { RegisterInstructions } from 'src/app/common/register-instructions';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  registerFromGroup: FormGroup;
  
  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.registerFromGroup = this.formBuilder.group({
      _appuser: this.formBuilder.group({
        firstName: new FormControl('',[Validators.required,Validators.minLength(2)]),
        lastName: new FormControl('',[Validators.required,Validators.minLength(2)]),
        email: new FormControl('',[Validators.required, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,5}$')]),
        username: new FormControl(''),
        password: new FormControl(''),
        displayName: new FormControl('')
      }),
      get appuser(){
        return this._appuser;
      },
      set appuser(value){
        this._appuser = value;
      },
      location: this.formBuilder.group({
        street:[''],
        city:[''],
        state:[''],
        zipCode:['']
      }),
      userprof: this.formBuilder.group({

      })
    });
  }

  get firstName(){
    return this.registerFromGroup.get('appuser.firstName');
  }

  onSubmit(){
    
    // making sure the everything is valid
    /*
    if(this.registerFromGroup.invalid){
      // not not 
      this.registerFromGroup.markAllAsTouched();
      return;
    }
    */

    let registerInstructions = new RegisterInstructions();
    registerInstructions.firstName = this.registerFromGroup.get('appuser').value;
    console.log(this.registerFromGroup.get('appuser').value);
  }
}
