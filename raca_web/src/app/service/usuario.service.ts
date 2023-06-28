import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Login } from '../entity/login.entity';
import { Observable, of, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
const apiUrlUsuario = environment.apiUrl + "Usuario";
@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(
    private router: Router) { }

    logar(usuario: Login): Observable<any> {
      return this.mockUsuarioLogin(usuario).pipe(tap((resposta) => {
        if(!resposta.sucesso) return;
        localStorage.setItem('token', btoa(JSON.stringify("TokenQueSeriaGeradoPelaAPI")));
        localStorage.setItem('usuario', btoa(JSON.stringify(usuario)));
        this.router.navigate(['']);
      }));
    }

    private mockUsuarioLogin(usuario: Login): Observable<any> {
      var retornoMock: any = [];
      if(usuario.email === "teste@teste.io" && usuario.senha == "123"){
        retornoMock.sucesso = true;
        retornoMock.usuario = usuario;
        retornoMock.token = "TokenQueSeriaGeradoPelaAPI";
        return of(retornoMock);
      }
      retornoMock.sucesso = false;
      retornoMock.usuario = usuario;
      return of(retornoMock);
    }

    deslogar() {
      localStorage.clear();
      this.router.navigate(['login']);
  }

  // get obterUsuarioLogado(): Login {
  //   return localStorage.getItem('usuario')
  //     ? JSON.parse(atob(localStorage.getItem('usuario')))
  //     : null;
  // }

  // get obterIdUsuarioLogado(): string {
  //   return localStorage.getItem('usuario')
  //     ? (JSON.parse(atob(localStorage.getItem('usuario'))) as Login).id
  //     : null;
  // }

  // get obterTokenUsuario(): string {
  //   return localStorage.getItem('token')
  //     ? JSON.parse(atob(localStorage.getItem('token')))
  //     : null;
  // }

  get logado(): boolean {
    return localStorage.getItem('token') ? true : false;
  }
}
