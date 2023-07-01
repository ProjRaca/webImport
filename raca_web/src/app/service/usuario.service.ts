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

    httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };

    async logar(usuario: Login):Promise<any> {
      const req = new HttpRequest('POST',`${apiUrlUsuario}/auth`,{"login": usuario.login, "senha": usuario.senha},this.httpOptions);
      const data = await this.http.request(req).toPromise();
      console.log(data);
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

  async findAll():Promise<any> {
     let httpOptionsToken = this.getHeader();

    const req = new HttpRequest('GET',`${apiUrlUsuario}/all`,httpOptionsToken);
    return await this.http.request(req).toPromise();

  }

  async findById(id: number):Promise<any> {
    let httpOptionsToken = this.getHeader();
    const req = new HttpRequest('GET',`${apiUrlUsuario}/${id}`,httpOptionsToken);
    return await this.http.request(req).toPromise();


  }

  getHeader(): any{
    let httpOptionsToken = {
      headers: new HttpHeaders().set('Authorization', 'Bearer '+localStorage.getItem('token'))
                                .set('Content-Type', 'application/json')
    };
    return httpOptionsToken;
  }

  async save(usuario: Usuario):Promise<any>{
    let httpOptions = this.getHeader();
    const req = new HttpRequest('POST',`${apiUrlUsuario}`,usuario,httpOptions);
    return await this.http.request(req).toPromise();
  }

  async findbyNane(nome: string):Promise<any>{
    let httpOptions = this.getHeader();
    const req = new HttpRequest('GET',`${apiUrlUsuario}/find?nome=${nome}`,httpOptions);
    return await this.http.request(req).toPromise();
  }
}
