import {ChangeDetectorRef, Component, OnInit, ViewChild} from '@angular/core';
import {AuthService} from 'core/authentication/authentication.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {UserService} from 'core/services/user.service';
import {Router} from '@angular/router';
import {LOGIN_PAGE} from 'core/constants/routing.constants';
import {catchError, map, timeout, timeoutWith} from 'rxjs/operators';
import {SwalComponent} from '@toverux/ngx-sweetalert2';
import {throwError} from 'rxjs';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {
  form: FormGroup;
  isSigningUp = false;

  @ViewChild('userCreatedSwal') private userCreatedSwal: SwalComponent;
  @ViewChild('userDuplicatedSwal') private userDuplicatedSwal: SwalComponent;
  @ViewChild('signupErrorSwal') private signupErrorSwal: SwalComponent;

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

    this.userService
      .register(this.form.value.email)
      .subscribe(res => {
        if (res.status === 201) {
          this.userCreatedSwal.show().then(() => {
            this.form.reset();
          });
        }
        this.isSigningUp = false;
      }, (err) => {
        if (err.status === 409) {
          this.userDuplicatedSwal.show().then(() => {
            this.form.reset();
          });
        } else {
          this.signupErrorSwal.show().then(() => {
          });
        }
        this.isSigningUp = false;
      });
  }

}
