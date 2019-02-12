import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {environment} from 'environments/environment';
import {Frontend} from 'core/interfaces/frontend.interface';
import {Layout} from 'core/interfaces/layout.interface';
import {Observable} from 'rxjs';


@Injectable()
export class IChatService {

  constructor(private httpClient: HttpClient) {
  }

  createFrontEnd(chatFrontEnd): Observable<HttpResponse<any>> {
    return this.httpClient.post(environment.iChatFrontEndApi, chatFrontEnd, {responseType: 'text' as 'json', observe: 'response'});
  }

  createLayout(layout): Observable<HttpResponse<any>> {
    const logo = this._createImageFormData(layout.logo, 'logo');
    const backgroundImg = this._createImageFormData(layout.backgroundImg, 'backgroundImg');
    const data = this._createFormData(layout);

    console.log('data', data);

    return this.httpClient.post(environment.iChatLayoutApi,
      data,
      {responseType: 'text' as 'json', observe: 'response', headers: {'Accept': 'application/json'}});
  }

  updateFrontEnd(id, chatFrontEnd): Observable<HttpResponse<any>> {
    return this.httpClient.put(`${environment.iChatFrontEndApi}/${id}/`, chatFrontEnd, {
      responseType: 'text' as 'json',
      observe: 'response'
    });
  }

  updateLayout(id, chatLayout): Observable<HttpResponse<any>> {
    return this.httpClient.put(`${environment.iChatFrontEndApi}/${id}/`, chatLayout, {responseType: 'text' as 'json', observe: 'response'});

  }

  getChatFrontends(): Observable<Frontend[]> {
    return this.httpClient.get<Frontend[]>(environment.iChatFrontEndApi);
  }

  getChatLayouts(): Observable<Layout[]> {
    return this.httpClient.get<Layout[]>(environment.iChatLayoutApi);
  }

  private _createImageFormData(file, fileName) {
    console.log(fileName, file);

    const form = new FormData();
    form.append(fileName, file, fileName);
    return form;
  }

  private _createFormData(data) {

    const form = new FormData();
    Object.keys(data).forEach(key => {
      if (key !== 'logo' && key !== 'backgroundImg') {
        form.append(key, data[key]);
      } else {
        form.append(key, data[key], key);
      }
    });
    console.log('logo', form.get('logo'));
    console.log('font', form.get('font')['fontFamily']);
    return form;
  }
}
