import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import 'rxjs-compat/add/operator/do';
import 'rxjs-compat/add/operator/filter';
import 'rxjs-compat/add/operator/take';
import 'rxjs-compat/add/operator/switchMap';
import 'rxjs-compat/add/operator/delay';
import {AuthService} from '../authentication/authentication.service';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {


  constructor(private authService: AuthService) {

  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.authService.getToken();
    request = request.clone({
        headers: new HttpHeaders({
          'Accept-Version': '1',
          'Accept': '*/*',
          'Access-Control-Allow-Origin': window.location.origin,
          'Content-Type': 'application/json',
          'Access-Control-Allow-Headers': 'Access-Control-Allow-Headers, Origin, Accept, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization',
          'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS, HEAD',
          'Vary': 'Accept-Encoding',
        })
      });
    if (token != null) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
    }
    //
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

    return next.handle(request).do((event: HttpEvent<any>) => {
      if (event instanceof HttpResponse) {
      }
    }, (err: any) => {
      // Observable.throw(err);
    });
    // return next.handle(request).pipe(catchError((err: HttpErrorResponse) => {
    //   // console.log("err", err);
    //   // return Observable.throw(err);
    //   return nu;
    // }));
  }


}
