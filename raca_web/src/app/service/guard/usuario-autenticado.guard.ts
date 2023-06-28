import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { UsuarioService } from '../usuario.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioAutenticadoGuard implements CanActivate {
  constructor(
       private usuarioService: UsuarioService,
       private router: Router){}

  canActivate(route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot) :
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
      if (this.usuarioService.logado) {
        return true;
      }
      this.router.navigate(['login']);
      return false;
  }

}
