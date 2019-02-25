import { Injectable } from '@angular/core';
import {HttpBackend, HttpClient} from '@angular/common/http';
import {environment} from 'environments/environment';
import {Observable} from 'rxjs';

@Injectable()
export class IChatSettingsService {

  private httpClient: HttpClient;

  constructor(handler: HttpBackend) {
    this.httpClient = new HttpClient(handler);
  }

  getSettings(userId: string): Observable<any>  {
    return this.httpClient.get(`${environment.iChatApi}/${userId}`);
  }

  getLogo(chatLayoutId): Observable<any> {
    return this.httpClient.get(`${environment.iChatLayoutApi}/${chatLayoutId}/logoImg`, {responseType: 'blob'});
  }

  getBackgroundImg(chatLayoutId): Observable<any> {
    return this.httpClient.get(`${environment.iChatLayoutApi}/${chatLayoutId}/backgroundImg`, {responseType: 'blob'});
  }
}
