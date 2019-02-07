import {Injectable} from '@angular/core';
import {HttpBackend, HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {URLSearchParams} from '@angular/http';

@Injectable()
export class IAgentServerService {
  private httpClient: HttpClient;

  constructor(handler: HttpBackend) {
    this.httpClient = new HttpClient(handler);
  }

  fetchServer(server) {
    let address = server.address;
    address = address.split(/(https|http):\/\/|:|\//)[2];

    address = 'https://' + address;
    address += '/iMail/api/rest/token';
    const body = new HttpParams()
      .set('grant_type', 'password')
      .set('username', server.userAPI)
      .set('password', server.password)
      .set('redirect_uri', 'https://showroom2.novomind.com/freeboard/token.html');

    const headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded',
      'Authorization': 'Basic ' + btoa(server.clientId + ':' + server.secret),
    });


    return this.httpClient.post(address, body.toString(), {headers: headers});
  }
}
