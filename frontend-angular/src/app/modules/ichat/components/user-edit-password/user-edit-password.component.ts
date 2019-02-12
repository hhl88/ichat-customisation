import {ChangeDetectorRef, Component, OnInit, ViewChild} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {UserService} from 'core/services/user.service';
import {Store} from '@ngrx/store';
import * as fromRoot from 'store/reducers';
import {SwalComponent} from '@toverux/ngx-sweetalert2';
import {PasswordValidator} from 'shared/validators/password.validator';
import {User} from 'core/interfaces/user.interface';

@Component({
  selector: 'app-user-edit-password',
  templateUrl: './user-edit-password.component.html',
  styleUrls: ['./user-edit-password.component.scss']
})
export class UserEditPasswordComponent implements OnInit {
  form: FormGroup;
  isSubmitting = false;
  isSuccessful = true;
  @ViewChild('passwordUpdatedSwal') private passwordUpdatedSwal: SwalComponent;
  @ViewChild('passwordWrongSwal') private passwordWrongSwal: SwalComponent;

  currentUser: User;

  constructor(private userService: UserService,
              private store: Store<fromRoot.State>,
              private cd: ChangeDetectorRef) {
  }

  ngOnInit() {
    this.form = new FormGroup({
      password: new FormControl('', [Validators.required]),
      retypedPassword: new FormControl('', [Validators.required]),
      oldPassword: new FormControl('', [Validators.required]),
    }, {
      validators: [PasswordValidator.matchPassword],
    });
    this.store.select(fromRoot.getUser).subscribe(user => {
      this.currentUser = user;
    });
  }

  onSubmit() {
    console.log('user', this.currentUser);
    if (this.currentUser) {
      Object.keys(this.form.controls).forEach(key => {
        this.form.get(key).markAsDirty();
      });
      this.isSubmitting = true;
      const data = {
        newPassword: this.form.value.password,
        retypedPassword: this.form.value.retypedPassword,
        oldPassword: this.form.value.oldPassword,
      };

      this.userService.changePassword(this.currentUser.user_id, data).subscribe(res => {
        this.isSubmitting = false;
        this.isSuccessful = true;
        this.cd.detectChanges();

        this.form.reset();
        this.passwordUpdatedSwal.show();
      }, er => {
        this.isSuccessful = false;
        this.isSubmitting = false;
        this.form.reset();
        this.cd.detectChanges();
        this.passwordWrongSwal.show();
      });
    }
  }

}
