import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { ScackBarCustomComponent } from 'src/app/componentes/scack-bar-custom/scack-bar-custom.component';
import { Login } from 'src/app/entity/login.entity';
import { UsuarioService } from 'src/app/service/usuario.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent extends ScackBarCustomComponent implements OnInit {

  formLogin: FormGroup = this.formBuilder.group({
    login: ['', [Validators.required]],
    senha: ['', [Validators.required]]
  });

  isLoginwait: boolean = false;
  constructor(private formBuilder: FormBuilder,
              private usuarioService: UsuarioService,
              snackBar: MatSnackBar,
              private router: Router) {
                super(snackBar);
               }
  ngOnInit(): void {

  }

  logar(){
    this.isLoginwait = true;
    if(this.formLogin.invalid) return;
    var usuario = this.formLogin.getRawValue() as Login;

    this.usuarioService.logar(usuario).then( async response => {
      await sleep(5000);
      if (response.body.code === 404) {
        this.exibirMensagemSucesso('Falha na autenticação',response.body.message);
      } else {
        localStorage.setItem('token', response.body.token);
        localStorage.setItem('login', response.body.login);
        this.router.navigate(['']);
      }
      this.isLoginwait = false;
    });
  }


}

function sleep(ms: number) {
  return new Promise( resolve => setTimeout(resolve, ms) );
}

