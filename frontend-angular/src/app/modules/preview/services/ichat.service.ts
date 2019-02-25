import {Injectable} from '@angular/core';
import {HttpBackend, HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {RequestOptions} from '@angular/http';

@Injectable()
export class IChatService {
  private server: string;
  private httpClient: HttpClient;
  private token = '';

  constructor(httpBackend: HttpBackend) {
    this.httpClient = new HttpClient(httpBackend);
  }


  setServer(server: string) {
    this.server = server + '/chats';
  }

  setToken(token: string) {
    this.token = token;
  }

  createNewChat(body): Observable<any> {
    console.log('server', this.server);
    return this.httpClient.post(this.server, body);
  }

  sendNewMessage(chatId: string, message): Observable<any> {
    return this.httpClient.post(`${this.server}/${chatId}`, message, this._createHeaders());
  }

  pollingMessenger(chatId: number) {
    return this.httpClient.post(`${this.server}/${JSON.stringify(chatId)}/changes`, null, this._createHeaders());
  }


  private _createHeaders() {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('X-novomind-iAGENT-chat-token', JSON.stringify(this.token))
      .set('Access-Control-Allow-Origin', window.location.origin);

    return new RequestOptions({headers: headers});
    // console.log('headers', headers);
    // console.log('headers', headers.keys());
    //
    // const options = new RequestOptions({headers: headers});
    //  console.log('opt', options);
    // return options;

  }
}
