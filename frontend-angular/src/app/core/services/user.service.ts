import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import 'rxjs-compat/add/operator/map';
import 'rxjs-compat/add/operator/catch';
import 'rxjs/operators/timeout';
import {environment} from 'environments/environment';
import {User} from 'core/interfaces/user.interface';

@Injectable()
export class UserService {

  constructor(private httpClient: HttpClient) {
  }


  changePassword(user: User, data: any) {
    const requestParam = {
      old_password: data.oldPassword,
      password: data.newPassword,
      retyped_password: data.confirmNewPassword
    };
    return this.httpClient.put(`${environment.apiServerUrl}/my_profile/password`, user.email, {params: requestParam});
  }

  register(data: any) {
    const user = {
      'email': data.email,
    };

    this.httpClient.post(environment.userApi, user).subscribe(r => {
    });
  }

  updateSetting(body) {
    return this.httpClient.post(environment.settingApi, body);
  }

}
