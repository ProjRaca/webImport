import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UsuarioService } from 'src/app/service/usuario.service';

@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrls: ['./principal.component.css']
})
export class PrincipalComponent implements OnInit {

  title = 'racaApp';

  constructor(private usuarioService: UsuarioService,
    private router: Router ) { }
  ngOnInit(): void {
  }

  deslogar(){
    this.usuarioService.deslogar();
  }

  home(){
    this.router.navigate(['']);
  }

}
