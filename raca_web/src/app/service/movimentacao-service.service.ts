import { HttpEvent, HttpRequest, HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

let baseUrl = environment.apiUrl+'/movimentacao'
@Injectable({
  providedIn: 'root'
})

export class MovimentacaoService {

  constructor(private http: HttpClient) { }

  upload(file: FormData): Observable<HttpEvent<any>> {

    const head:HttpHeaders = new HttpHeaders().set('Authorization', 'Bearer '+localStorage.getItem('token'))
                                .set('Content-Type', 'multipart/form-data')
                                .set('isUpload', 'true')



    var body = {
      'xls_file': file
    }
    //let formData = new FormData().append('file', file);

    const req = new HttpRequest('POST', `${baseUrl}/upload-xls?xls_file`, body, {
      reportProgress: true,
      responseType: 'json',
      headers: head
    });
    return this.http.request(req);
  }

  getFiles(): Observable<any> {
    return this.http.get(`${baseUrl}/files`);
  }
}
