import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import 'rxjs-compat/add/operator/do';

import {AuthService} from '../authentication/authentication.service';
import 'rxjs-compat/add/operator/timeout';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {


  constructor(private authService: AuthService) {

  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.authService.getToken();
    request = request.clone({
      setHeaders: {
        'Accept-Version': '1',
        'Accept': '*/*',
        'Access-Control-Allow-Origin': window.location.origin,
        'Access-Control-Allow-Headers': 'Access-Control-Allow-Headers, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization',
        'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS, HEAD',
        'Vary': 'Accept-Encoding',
      }
    });
    if (token != null) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
    }

    // return next.handle(request)
    //   .catch(error => {
    //     if (error instanceof HttpErrorResponse) {
    //       switch ((<HttpErrorResponse>error).status) {
    //         case 400:
    //           return this.handle400Error(error);
    //         case 401:
    //           return this.handle401Error(request, next);
    //       }
    //     } else {
    //       return Observable.throw(error);
    //     }
    //   });
    return next.handle(request).timeout(5000).do((event: HttpEvent<any>) => {
      if (event instanceof HttpResponse) {
      }
    }, (err: any) => {
      console.log('err', err);
      // this.authService.logout();
      // location.reload();
      // console.log('err', err);
      return throwError(err);
      // Observable.throw(err);
    });
    // return next.handle(request).pipe(catchError((err: HttpErrorResponse) => {
    //   // console.log("err", err);
    //   // return Observable.throw(err);
    //   return nu;
    // }));
  }


}
