import { HttpEvent, HttpRequest, HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

let apiUrlMovimentacao = environment.apiUrl+'/movimentacao'
@Injectable({
  providedIn: 'root'
})

export class MovimentacaoService {

  constructor(private http: HttpClient) { }

 getHeader(): any{
    let httpOptionsToken = {
      headers: new HttpHeaders().set('Authorization', 'Bearer '+localStorage.getItem('token'))
                                .set('Content-Type', 'application/json')
    };
    return httpOptionsToken;
  }

  async upload(file: FormData):Promise<any> {

    const headpHeaders = new HttpHeaders().set('isUpload', 'true')

    const req = new HttpRequest('POST', `${apiUrlMovimentacao}/upload-xls?xls_file`,file,  {
      reportProgress: true,
      responseType: 'json',
      headers: headpHeaders
    });
    return await this.http.request(req).toPromise();
  }

  getFiles(): Observable<any> {
    return this.http.get(`${apiUrlMovimentacao}/files`);
  }

  async getAll():Promise<any> {

  const req = new HttpRequest('GET',`${apiUrlMovimentacao}/all`);
  return await this.http.request(req).toPromise();
 }

 //TODO criar endpoint para atualizar dados da tela de conferencia
}
