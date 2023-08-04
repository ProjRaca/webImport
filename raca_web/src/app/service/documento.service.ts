import { HttpClient, HttpParams, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { DocumentoDTO } from '../entity/documento-dto.entity';

let apiUrl = environment.apiUrl+'/document'

@Injectable({
  providedIn: 'root'
})
export class DocumentoService {

  constructor(private http: HttpClient) { }

  async findAll():Promise<any> {
    const req = new HttpRequest('GET',`${apiUrl}`);
    return await this.http.request(req).toPromise();
  }

  async findByFilter(filter: DocumentoDTO):Promise<any> {


    const options = {params: new HttpParams()
                                            .set('datadocumentesc', filter.datadocumentesc || '' )
                                            .set('datavalidade', filter.datavalidade || '' )
                                            .set('emissor', filter.emissor || '' )
                                            .set('filial', filter.filial || '' )
                                            .set('iddocpai', filter.iddocpai || '' )
                    }
    const req = new HttpRequest('GET',`${apiUrl}/filter`, options);
    return await this.http.request(req).toPromise();
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
