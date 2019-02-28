import {Injectable} from '@angular/core';
import {HttpBackend, HttpClient, HttpHeaders} from '@angular/common/http';
import {BehaviorSubject, Observable, of, Subject} from 'rxjs';

@Injectable()
export class IChatService {
  private server: string;
  private httpClient: HttpClient;
  private token = '';
  private isConnected:  Subject<boolean> = new BehaviorSubject<boolean>(false);
  constructor(httpBackend: HttpBackend) {
    this.httpClient = new HttpClient(httpBackend);
  }

  checkConnection() {
    return this.isConnected;
  }

  setConnection(isConnected) {
    this.isConnected.next(isConnected);
  }

  setServer(server: string) {
    this.server = server + '/chats';
  }

  setToken(token: string) {
    this.token = token;
  }

  createNewChat(body): Observable<any> {
    return this.httpClient.post(this.server, body);
  }

  sendNewMessage(chatId: number, message): Observable<any> {
    return this.httpClient.post(`${this.server}/${chatId}/messages`, message, this._createHeaders());
  }

  pollingMessenger(chatId: number): Observable<any> {
    return this.httpClient.get(`${this.server}/${chatId}/changes`,  this._createHeaders());
  }

  stopCurrentChat(chatId: number): Observable<any> {
    return this.httpClient.post(`${this.server}/${chatId}/stop`,  null, this._createHeaders());

  }


  private _createHeaders() {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('X-novomind-iAGENT-chat-token', this.token)
      .set('Access-Control-Allow-Origin', window.location.origin);
    return {headers: headers};
  }
}
