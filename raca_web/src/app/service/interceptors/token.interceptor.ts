import { UsuarioService } from 'src/app/service/usuario.service';
import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse} from '@angular/common/http';
import { Observable, catchError, throwError} from 'rxjs';
import { environment } from 'src/environments/environment';


@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(private usuarioService: UsuarioService) {}
  // intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
  //   throw new Error('Method not implemented.');
  // }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const token = this.usuarioService.obterTokenUsuario;
    const requestUrl: Array<any> = request.url.split('/');
    const apiUrl: Array<any> = environment.apiUrl.split('/');


    if (token && requestUrl[2] === apiUrl[2]) {
      request = request.clone({
          setHeaders: {
              Authorization: `Bearer ${token}`,
              token: `${token}`
          }
      });
      return next.handle(request).pipe(catchError(error => {
          if (error instanceof HttpErrorResponse && error.status === 401)
            this.usuarioService.deslogar();
          else
            return throwError(error.message);
          }));
      }
  else {
      return next.handle(request);
  }
  }
}
