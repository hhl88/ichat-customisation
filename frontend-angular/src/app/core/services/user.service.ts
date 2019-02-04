import { Injectable } from '@angular/core';
import {AuthService} from 'core/authentication/authentication.service';
import {HttpClient} from '@angular/common/http';
import {environment} from 'environments/environment';

@Injectable()
export class UserService {

  constructor(private httpClient: HttpClient, private authService: AuthService) {
  }

  register(email: string) {
    return this.httpClient.post(environment.userApi, { 'email': email});
  }

  changePassword (data) {
    return this.httpClient.put(environment.userApi, data);
  }

}
