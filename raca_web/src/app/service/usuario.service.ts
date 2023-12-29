import { Usuario } from 'src/app/entity/usuario.entity';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Login } from '../entity/login.entity';
import { HttpClient, HttpHeaders, HttpRequest } from '@angular/common/http';
import { Router } from '@angular/router';


const apiUrlUsuario = environment.apiUrl + "/usuarios";
@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(
    private router: Router,
    private http: HttpClient) { }

    async logar(usuario: Login):Promise<any> {
      const req = new HttpRequest('POST',`${apiUrlUsuario}/auth`,{"login": usuario.login, "senha": usuario.senha},this.getHeader());
      const data = await this.http.request(req).toPromise();
      return data;
    }

    deslogar() {
      localStorage.clear();
      this.router.navigate(['login']);
  }

  get logado(): boolean {
    return localStorage.getItem('token') ? true : false;
  }


  get obterTokenUsuario(): string | null {
    return localStorage.getItem('token') ? localStorage.getItem('token') : null;
      ;
  }

  get isUsuarioAdmin(): boolean {
    return localStorage.getItem('role') ==  'true' ? true : false;
  }

  async findAll():Promise<any> {
      const req = new HttpRequest('GET',`${apiUrlUsuario}/all`, this.getHeader());
    return await this.http.request(req).toPromise();

  }

  async findById(id: number):Promise<any> {
    const req = new HttpRequest('GET',`${apiUrlUsuario}/${id}`, this.getHeader());
    return await this.http.request(req).toPromise();
  }

  getHeader(): any{
    let httpOptionsToken = {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    };
    return httpOptionsToken;
  }

  async save(usuario: Usuario):Promise<any>{
    const req = new HttpRequest('POST',`${apiUrlUsuario}`,usuario, this.getHeader());
    return await this.http.request(req).toPromise();
  }

  async findbyName(nome: string):Promise<any>{
    const req = new HttpRequest('GET',`${apiUrlUsuario}/nome/${nome}`, this.getHeader());
    return await this.http.request(req).toPromise();
  }

  async update(usuario: Usuario):Promise<any>{
    const req = new HttpRequest('PUT',`${apiUrlUsuario}`,usuario, this.getHeader());
    return await this.http.request(req).toPromise();
  }
}
