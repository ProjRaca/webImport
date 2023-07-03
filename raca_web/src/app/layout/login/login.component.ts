import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Login } from 'src/app/entity/login.entity';
import { UsuarioService } from 'src/app/service/usuario.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  formLogin: FormGroup = this.formBuilder.group({
    login: ['', [Validators.required]],
    senha: ['', [Validators.required]]
  });

  constructor(private formBuilder: FormBuilder,
              private usuarioService: UsuarioService,
              private snackBar: MatSnackBar,
              private router: Router) { }
  ngOnInit(): void {

  }

  logar(){
    if(this.formLogin.invalid) return;
    var usuario = this.formLogin.getRawValue() as Login;

    this.usuarioService.logar(usuario).then( response => {
        if (!response.ok) {
          this.snackBar.open('Falha na autenticação', 'Usuário ou senha incorretos.', {
            duration: 3000
          });
        } else {
          localStorage.setItem('token', response.body.token);
          localStorage.setItem('login', response.body.login);
          this.router.navigate(['']);
        }
    });
  }


}
