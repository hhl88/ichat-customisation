import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService} from 'core/authentication/authentication.service';

@Component({
  selector: 'sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent implements OnInit {

  formSignIn: FormGroup;
  passwordPattern = '^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$';
  @Output() switchToSignUp = new EventEmitter<number>();
  @Output() signedIn = new EventEmitter<any>();

  isLoggingIn = false;
  wrongPassword = false;

  constructor(private authService: AuthService) {
  }

  ngOnInit() {
    this.formSignIn = new FormGroup({
      email: new FormControl('', [Validators.email]),
      password: new FormControl(''),
    });
  }

  onSubmit() {
    if (this.formSignIn.invalid) {
      return;
    }
    this.isLoggingIn = true;
    this.authService.login(this.formSignIn.value.email, this.formSignIn.value.password).subscribe(r => {
      if (r != null) {
        this.signedIn.emit(null);
      } else {
        this.wrongPassword = true;
      }
      this.isLoggingIn = false;
    });
  }


  onSwitchToSignUp() {
    this.switchToSignUp.emit(1);
  }

}
