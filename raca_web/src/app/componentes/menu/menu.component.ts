import { Component, ViewEncapsulation } from '@angular/core';
import { UsuarioService } from 'src/app/service/usuario.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],
  encapsulation: ViewEncapsulation.None
 })
export class MenuComponent  {

  isAdmininstrador: boolean = false;

  constructor(private usuarioService: UsuarioService) {}

  ngOnInit(): void {
    this.isAdmininstrador = this.usuarioService.isUsuarioAdmin;
  }

  csvInputChange() {

  }
}
