import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
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
    console.log('layout', layout);


    return this.httpClient.post(environment.iChatLayoutApi,
      this._createFormDataForLayout(layout),
      {responseType: 'text' as 'json', observe: 'response', headers: {'Accept': 'application/json'}});
  }

  updateFrontEnd(id, chatFrontEnd): Observable<HttpResponse<any>> {
    return this.httpClient.put(`${environment.iChatFrontEndApi}/${id}/`, chatFrontEnd, {
      responseType: 'text' as 'json',
      observe: 'response'
    });
  }

  setAsDefaultFrontend(chatFrontendId): Observable<HttpResponse<any>> {
    return this.httpClient.put(`${environment.iChatFrontEndApi}/${chatFrontendId}/default`, null, {
      responseType: 'text' as 'json',
      observe: 'response'
    });
  }

  updateLayout(id, chatLayout): Observable<HttpResponse<any>> {
    console.log('update', chatLayout);
    return this.httpClient.put(`${environment.iChatLayoutApi}/${id}/`,
      this._createFormDataForLayout(chatLayout),
      {responseType: 'text' as 'json', observe: 'response', headers: {'Accept': 'application/json'}});

  }

  setAsDefaultLayout(chatLayoutId): Observable<HttpResponse<any>> {
    return this.httpClient.put(`${environment.iChatLayoutApi}/${chatLayoutId}/default`, null, {
      responseType: 'text' as 'json',
      observe: 'response'
    });
  }

  getChatFrontends(): Observable<Frontend[]> {
    return this.httpClient.get<Frontend[]>(environment.iChatFrontEndApi);
  }

  getChatLayouts(): Observable<Layout[]> {
    return this.httpClient.get<Layout[]>(environment.iChatLayoutApi);
  }

  getLogo(chatLayoutId): Observable<any> {
    return this.httpClient.get(`${environment.iChatLayoutApi}/${chatLayoutId}/logoImg`, {responseType: 'blob'});
  }

  getBackgroundImg(chatLayoutId): Observable<any> {
    return this.httpClient.get(`${environment.iChatLayoutApi}/${chatLayoutId}/backgroundImg`, {responseType: 'blob'});
  }


  private _createFormDataForLayout(layout) {

    const form = new FormData();
    const chatLayoutCreateDTO = new Blob([JSON.stringify(this._createCloneJson(layout))], {type: 'application/json'});

    form.append('layoutDto', chatLayoutCreateDTO, 'layoutDto');
    if (layout.logo && layout.logo !== '') {
      console.log('logo');
      form.append('logo', layout.logo, 'logo.png');
    }
    if (layout.backgroundImg && layout.backgroundImg !== '') {
      form.append('backgroundImg', layout.backgroundImg, 'backgroundImg.png');
    }
    return form;
  }

  private _createCloneJson(data) {
    const newData = {};
    Object.keys(data).forEach(key => {
      if (key !== 'logo' && key !== 'backgroundImg') {
        newData[key] = data[key];
      }
    });
    return newData;
  }

}
