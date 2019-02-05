import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {environment} from 'environments/environment';
import {Frontend} from 'core/interface/frontend.interface';
import {Layout} from 'core/interface/layout.interface';
import {Observable} from 'rxjs';

@Injectable()
export class IChatService {

  constructor(private httpClient: HttpClient) {
  }

  createFrontEnd(chatFrontEnd): Observable<HttpResponse<any>> {
    return this.httpClient.post(environment.iChatFrontEndApi, chatFrontEnd, {responseType: 'text' as 'json', observe: 'response'});
  }

  createLayout(layout): Observable<HttpResponse<any>> {
    return this.httpClient.post(environment.iChatLayoutApi, layout, {responseType: 'text' as 'json', observe: 'response'});

  }

  updateFrontEnd(id, chatFrontEnd): Observable<HttpResponse<any>> {
    return this.httpClient.put(`${environment.iChatFrontEndApi}/${id}/`, chatFrontEnd, {
      responseType: 'text' as 'json',
      observe: 'response'
    });
  }

  updateLayout(id, chatLayout: Observable<HttpResponse<any>>) {
    return this.httpClient.put(`${environment.iChatFrontEndApi}/${id}/`, chatLayout, {responseType: 'text' as 'json', observe: 'response'});

  }

  getChatFrontends(): Observable<Frontend[]> {
    return this.httpClient.get<Frontend[]>(environment.iChatFrontEndApi);
  }

  getChatLayouts(): Observable<Layout[]> {
    return this.httpClient.get<Layout[]>(environment.iChatLayoutApi);
  }

}
