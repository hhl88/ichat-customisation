import {AbstractControl} from '@angular/forms';

export class PasswordValidator {
  static matchPassword(AC: AbstractControl) {
    const password = AC.get('password').value; // to get value in input tag
    const confirmPassword = AC.get('retypedPassword').value; // to get value in input tag
    if (password !== confirmPassword) {
      AC.get('retypedPassword').setErrors({MatchPassword: true});
    } else {
      return null;
    }
  }
}
