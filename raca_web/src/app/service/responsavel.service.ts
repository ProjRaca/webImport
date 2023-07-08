import { Responsavel } from 'src/app/entity/responsavel.entity';
import { HttpClient, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

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

  async save(Responsavel: Responsavel):Promise<any>{
    const req = new HttpRequest('POST',`${apiUrl}`,Responsavel);
    return await this.http.request(req).toPromise();
  }
}
