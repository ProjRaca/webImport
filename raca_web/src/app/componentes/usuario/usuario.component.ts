import {Component, OnInit} from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import {FloatLabelType} from '@angular/material/form-field';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Usuario } from 'src/app/entity/usuario.entity';
import { Sizes } from 'src/app/enums/sizes.enum';
import { ModalService } from 'src/app/service/modalService.service';
import { UsuarioService } from 'src/app/service/usuario.service';
import { ScackBarCustomComponent } from '../scack-bar-custom/scack-bar-custom.component';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css'],

})
export class UsuarioComponent extends ScackBarCustomComponent implements OnInit {

  displayedColumns: string[] = ['id','Login', 'Nome', 'Email','Ações'];
  hideRequiredControl = new FormControl(false);
  floatLabelControl = new FormControl('auto' as FloatLabelType);
  exibirDetalhes: boolean = false;
  usuarioDetalhes!: Usuario;

  usuarios!: Usuario[];
  formulario!: FormGroup;
  nomePesquisa: string = ''


  constructor( private formBuilder: FormBuilder,
    protected modalService: ModalService,
    private usuarioService: UsuarioService,
     snackBar: MatSnackBar ) {
      super(snackBar);
    }

    ngOnInit(): void {
    this.usuarios = this.getAll()
    this.criarFormulario()
  }

  criarFormulario(){
    this.formulario = this.formBuilder.group({
      nome: ['', [Validators.required]],
      login: ['', [Validators.required]],
      senha: ['', [Validators.required]],
      confimaSenha: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]]
    })
  }

  pesquisar(){
    if (this.nomePesquisa.length == 0) return
    console.log(this.nomePesquisa)

  }

  getSizeModal(): string{
    return Sizes.Middle.toString();
  }

  getAll(): Usuario[]{
      this.usuarioService.findAll().then( response => {
        if (!response.ok) {
          this.exibirMensagemErro('Falha na autenticação','Usuário ou senha incorretos.');
        }
        this.usuarios = response.body;

      });
    return [];
  }

  detalhes(id: any){
    this.usuarioService.findById(id).then( response => {
      if (!response.ok) {
        this.exibirMensagemErro('Ocorreu um erro na requisição','Verifique os dados informados.');
      }
      this.usuarioDetalhes = response.body;
      this.exibirDetalhes = true;
      this.formulario.get('login')?.setValue(this.usuarioDetalhes.login)

      this.formulario.get('nome')?.setValue(this.usuarioDetalhes.nome)
      this.formulario.get('email')?.setValue(this.usuarioDetalhes.email)
      this.modalService.open('modalUsuario');
      console.log(this.usuarioDetalhes);
    });
  }

  openModalNovo(){
    this.exibirDetalhes = false;
    this.modalService.open('modalUsuario');
  }

  save(){
    if(this.formulario.status == 'INVALID') return;

    let usuarioInclusao = {
      nome: this.formulario?.value.nome,
      email: this.formulario?.value.email,
      login: this.formulario?.value.login,
      senha: this.formulario?.value.senha,
      status: 'A'
    }
    this.usuarioService.save(usuarioInclusao).then( response => {
      if (!response.ok) {
        this.exibirMensagemErro('Ocorreu um problema ao tentar salvar o usuário','Verifique os dados informados.');
      }
      usuarioInclusao = response
      this.modalService.close();
      this.exibirMensagemSucesso('Registro Incluído com Sucesso','')
    })
  }
}


