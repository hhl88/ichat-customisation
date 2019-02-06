import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {environment} from 'environments/environment';
import {Observable} from 'rxjs';

@Injectable()
export class UserService {

  constructor(private httpClient: HttpClient) {
  }

  register(email: string): Observable<HttpResponse<any>> {
    return this.httpClient.post(environment.userApi, { 'email': email}, {responseType: 'text' as 'json', observe: 'response'});
  }

  changePassword (data): Observable<HttpResponse<any>> {
    return this.httpClient.put(environment.userApi, data, {responseType: 'text' as 'json', observe: 'response'});
  }

}
