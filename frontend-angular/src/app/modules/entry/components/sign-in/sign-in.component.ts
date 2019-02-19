import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Store} from '@ngrx/store';
import * as fromRoot from 'app/store/reducers';
import {UserLoadingAction, UserLoginAction} from 'store/actions/entry';
import {Router} from '@angular/router';
import {ICHAT_PAGE, SIGN_UP_PAGE} from 'core/constants/routing.constants';
import {AuthService} from 'core/authentication/authentication.service';
import {LOCAL_STORAGE_AUTH_TOKEN} from 'core/constants/storage.constants';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent implements OnInit {
  form: FormGroup;
  passwordPattern = '^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$';

  isLoggingIn = false;
  wrongPassword = false;

  constructor(private authService: AuthService,
              private store: Store<fromRoot.State>,
              private router: Router) {
  }

  ngOnInit() {
    this.form = new FormGroup({
      email: new FormControl('', [Validators.email]),
      password: new FormControl(''),
    });
  }

  onSubmit() {
    if (this.form.invalid) {
      return;
    }
    console.log('signinnnn', this.form.getRawValue());
    this.isLoggingIn = true;
    this.store.dispatch(new UserLoadingAction());
    this.authService.login(this.form.value.email, this.form.value.password).subscribe(res => {
      if (res != null) {
        const newRes = JSON.parse(JSON.stringify(res));
        console.log(newRes);
        console.log('access_token', newRes.access_token);

        this.store.dispatch(new UserLoginAction(newRes));
        localStorage.setItem(LOCAL_STORAGE_AUTH_TOKEN, newRes.access_token);

        this.router.navigate([ICHAT_PAGE]);
      } else {
        this.wrongPassword = true;
      }
      this.isLoggingIn = false;
    });
  }

  onSwitchToSignUp() {
    this.router.navigate([SIGN_UP_PAGE]);

  }


}
