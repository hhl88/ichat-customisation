import {Injectable} from '@angular/core';
import {CookieService} from 'ngx-cookie-service';
import {Store} from '@ngrx/store';
import * as fromRoot from 'store/reducers';
import {Observable, of} from 'rxjs';
import {environment} from 'environments/environment';
import {HttpClient, HttpParams} from '@angular/common/http';
import {catchError, map, tap, timeout} from 'rxjs/operators';
import {USER_LOGOUT, UserLoginAction, UserLogoutAction} from 'store/actions/entry';
import {LOCAL_STORAGE_AUTH_TOKEN} from 'core/constants/storage.constants';
import {Router} from '@angular/router';
import {ICHAT_PAGE, LOGIN_PAGE} from 'core/constants/routing.constants';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private _isAuthenticated: Observable<boolean> = of(false);

  constructor(private httpClient: HttpClient,
              private store: Store<fromRoot.State>,
              private router: Router,
              private cookieService: CookieService) {
  }

  login(email: string, password: string): Observable<any> {
    console.log('login');
    return this.httpClient.post<any>(`${environment.tokenApi}`, null, {
      params: new HttpParams()
        .set('username', email)
        .set('password', password)
        .set('grant_type', 'password')
        .set('client_id', 'clientid')
        .set('client_secret', 'secret')
    });
  }

  getToken() {
    return localStorage.getItem(LOCAL_STORAGE_AUTH_TOKEN);
  }

  private _fetchUser() {
    return this.httpClient.get(`${environment.authApi}`, {withCredentials: true, observe: 'response'}).pipe(map(res => {
      if (res && res.body) {
        this._isAuthenticated = of(false);
        this.store.dispatch(new UserLoginAction(res.body));
      } else {
        this._isAuthenticated = of(true);
        this.logout();
      }
      return res;
    }));
  }

  logout() {
    localStorage.removeItem(LOCAL_STORAGE_AUTH_TOKEN);
    this.cookieService.deleteAll();
    this.store.dispatch(new UserLogoutAction());
  }

  isAuthenticated(): Observable<boolean> {
    return this._isAuthenticated;
  }

  isSessionAlive() {

    this._fetchUser()
      .subscribe((res) => {

        this._isAuthenticated = of(true);
        return of(true);
      }, (err) => {
        console.log('wrong');
        this._isAuthenticated = of(false);
        this.logout();
        this.router.navigate([LOGIN_PAGE]);
        return of(false);
        // observer.error(err); // won't work here you need to use next
      });
  }


// isSessionAlive(): Observable<any> {
//   return Observable.create((observer) => {
//     this._fetchUser()
//       .subscribe((res) => {
//         this._isAuthenticated = of(true);
//         observer.next(true); // your server response
//       }, (err) => {
//
//         this._isAuthenticated = of(false);
//         observer.next(false);
//         // observer.error(err); // won't work here you need to use next
//       });
//   });
// }

}
