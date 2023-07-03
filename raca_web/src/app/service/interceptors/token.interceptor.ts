import { UsuarioService } from 'src/app/service/usuario.service';
import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse,
  HttpResponse} from '@angular/common/http';
import { Observable, catchError, throwError, of, tap, finalize } from 'rxjs';
import { environment } from 'src/environments/environment';


@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(private usuarioService: UsuarioService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>{

    const token = this.usuarioService.obterTokenUsuario;
    const requestUrl: Array<any> = request.url.split('/');
    const apiUrl: Array<any> = environment.apiUrl.split('/');
    const started = Date.now();
    let ok: string;
    let tokenExpired: boolean = false;

    if (token && requestUrl[2] === apiUrl[2]) {

      request = request.clone({
        headers: request.headers.set('Authorization', `Bearer ${token}`)
                                .set('Accept', 'application/json')
      });
    }

    // extend server response observable with logging
    return next.handle(request)
    .pipe(
      tap({
        // Succeeds when there is a response; ignore other events
        next: (event) => (ok = event instanceof HttpResponse ? 'succeeded' : ''),
        // Operation failed; error is an HttpErrorResponse
        error: (error) => {
          if(error.status == 403 || error.status == 401 ){
            ok = 'failed';
            this.usuarioService.deslogar()
          }
        }
      }),
      // Log when response observable either completes or errors
      finalize(() => {
        const elapsed = Date.now() - started;
        const msg = `${request.method} "${request.urlWithParams}"
           ${ok} in ${elapsed} ms.`;
     })
    );



  }
}
