import {Component, OnInit} from '@angular/core';
import {AuthService} from 'core/authentication/authentication.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {UserService} from 'core/services/user.service';
import {Router} from '@angular/router';
import {LOGIN_PAGE} from 'core/constants/routing.constants';
import {catchError} from 'rxjs/operators';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {
  form: FormGroup;
  isSigningUp = false;

  constructor(private authService: AuthService,
              private userService: UserService,
              private router: Router) {
  }

  ngOnInit() {
    this.form = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email])
    });
  }

  onSwitchToSignIn() {
    this.router.navigate([LOGIN_PAGE]);
  }

  onSubmit() {
    Object.keys(this.form.controls).forEach(key => {
      this.form.get(key).markAsDirty();
    });
    this.isSigningUp = true;
    this.userService.register(this.form.value.email).subscribe(res => {
      console.log('register', res);
    });
  }

}
