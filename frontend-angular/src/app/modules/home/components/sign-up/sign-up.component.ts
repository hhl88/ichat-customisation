import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {UserService} from 'core/services/user.service';


@Component({
  selector: 'sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss'],
})
export class SignUpComponent implements OnInit {

  formSignUp: FormGroup;
  isSigningUp = false;
  // genders: Gender[] = [];

  @Output() signedUp = new EventEmitter<any>();
  @Output() switchToSignIn = new EventEmitter<number>();

  constructor(private userService: UserService) {
  }

  ngOnInit() {

    this.formSignUp = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
    }, {
      updateOn: 'blur'
    });
  }


  onSubmit() {
    Object.keys(this.formSignUp.controls).forEach(key => {
      this.formSignUp.get(key).markAsDirty();
    });
    this.isSigningUp = true;
    const data = {
      email: this.formSignUp.value.email,
    };
    this.userService.register(data);
  }

  isFormValid() {
    return !this.formSignUp.invalid;
  }

  onSwitchToSignIn() {
    this.switchToSignIn.emit(0);
  }

}
