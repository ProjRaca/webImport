import { Responsavel } from 'src/app/entity/responsavel.entity';
import { HttpClient, HttpParams, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

const apiUrl = environment.apiUrl + "/responsavel";
@Injectable({
  providedIn: 'root'
})
export class ResponsavelService {

  constructor(
    private router: Router,
    private http: HttpClient ) { }

  async findAll():Promise<any> {
      const req = new HttpRequest('GET',`${apiUrl}/all`);
    return await this.http.request(req).toPromise();
  }

  async findByName(nome: string):Promise<any> {
    const req = new HttpRequest('GET',`${apiUrl}/nome/${nome}`);
    return await this.http.request(req).toPromise();
  }

  async save(responsavel: Responsavel):Promise<any>{
    const req = new HttpRequest('POST',`${apiUrl}`,responsavel);
    return await this.http.request(req).toPromise();
  }

  async findById(id: string):Promise<any> {
    const req = new HttpRequest('GET',`${apiUrl}/${id}`);
    return await this.http.request(req).toPromise();
  }

  async deleteById(id: string):Promise<any> {
    const req = new HttpRequest('DELETE',`${apiUrl}/${id}`);
    return await this.http.request(req).toPromise();
  }

  async findAllFiliais():Promise<any> {
    const req = new HttpRequest('GET',`${apiUrl}/filiais`);
    return await this.http.request(req).toPromise();
  }

  findByFilter(filter: any): Observable<any>{
    let parametros = new HttpParams();
    parametros = this.getCondictionalParams(filter, parametros);

    const options = {params: parametros}
    const req = new HttpRequest('GET',`${apiUrl}/filter`, options);
    return this.http.request(req);
  }

  private getCondictionalParams(filter: any, parametros: HttpParams): HttpParams {
    if (filter.id !== '' && filter.id !== undefined)
      parametros = parametros.append('id', filter.id);
    if (filter.nome !== '' && filter.nome !== undefined)
      parametros = parametros.append('nome', filter.nome);
    if (filter.cpfcnpj !== '' && filter.cpfcnpj !== undefined)
      parametros = parametros.append('cpfcnpj', filter.cpfcnpj);
    if (filter.telefone !== '' && filter.telefone !== undefined)
      parametros = parametros.append('telefone', filter.telefone);
    if (filter.email !== '' && filter.email !== undefined)
      parametros = parametros.append('email', filter.email);
    if ( filter.filial !== '' && filter.filial !== undefined)
      parametros = parametros.append('filial', filter.filial);

    return parametros;
  }
}
