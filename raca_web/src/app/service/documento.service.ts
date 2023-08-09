import { HttpClient, HttpParams, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { DocumentoDTO } from '../entity/documento-dto.entity';
import { Observable } from 'rxjs';

let apiUrl = environment.apiUrl+'/document'

@Injectable({
  providedIn: 'root'
})
export class DocumentoService {

  constructor(private http: HttpClient) { }

  findAll():Observable<any> {
    const req = new HttpRequest('GET',`${apiUrl}`);
    return this.http.request(req);
  }

  findByFilter(filter: DocumentoDTO): Observable<any>{


    let parametros = new HttpParams();
    parametros = this.getCondictionalParams(filter, parametros);

    const options = {params: parametros}
    const req = new HttpRequest('GET',`${apiUrl}/filter`, options);
    return this.http.request(req);
  }

  private getCondictionalParams(filter: any, parametros: HttpParams): HttpParams {
    if (filter.id != '')
      parametros = parametros.append('id', filter.id);
    if (filter.datadocumentesc != undefined)
      parametros = parametros.append('datadocumentesc', filter.datadocumentesc);
    if (filter.datavalidade != undefined)
      parametros = parametros.append('datavalidade', filter.datavalidade);
    if (filter.emissor != undefined)
      parametros = parametros.append('emissor', filter.emissor);
    if (filter.filial != undefined)
      parametros = parametros.append('filial', filter.filial);
    if (filter.iddocpai != undefined)
      parametros = parametros.append('iddocpai', filter.iddocpai);
    return parametros;
  }

  async save(filter: DocumentoDTO):Promise<any> {
    const req = new HttpRequest('POST',`${apiUrl}`, filter);
    return await this.http.request(req).toPromise();
  }

  async update(filter: DocumentoDTO):Promise<any> {
    const req = new HttpRequest('PUT',`${apiUrl}`, filter);
    return await this.http.request(req).toPromise();
  }

  async delete(id: string):Promise<any> {
    const req = new HttpRequest('DELETE',`${apiUrl}/${id}`);
    return await this.http.request(req).toPromise();
  }
}