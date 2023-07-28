import { HttpClient, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

let apiUrl = environment.apiUrl+'/contas'

@Injectable({
  providedIn: 'root'
})
export class ContaService {

  constructor(private http: HttpClient) { }

  async findAllHistorico():Promise<any> {
    const req = new HttpRequest('GET',`${apiUrl}/historico`);
    return await this.http.request(req).toPromise();
  }
}
