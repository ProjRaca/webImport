import {Component, OnInit, ViewChild} from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { FloatLabelType } from '@angular/material/form-field';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Usuario } from 'src/app/entity/usuario.entity';
import { Sizes } from 'src/app/enums/sizes.enum';
import { ModalService } from 'src/app/service/modalService.service';
import { UsuarioService } from 'src/app/service/usuario.service';
import { ScackBarCustomComponent } from '../scack-bar-custom/scack-bar-custom.component';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css'],

})
export class UsuarioComponent extends ScackBarCustomComponent implements OnInit {

  displayedColumns: string[] = ['id','Login', 'Nome', 'Email','Tipo Usuário','Ações'];
  hideRequiredControl = new FormControl(false);
  floatLabelControl = new FormControl('auto' as FloatLabelType);
  exibirDetalhes: boolean = false;
  usuarioDetalhes!: Usuario;
  usuarioEditar!: Usuario;
  exibirEditar: boolean = false;
  usuarios!: Usuario[];
  formulario!: FormGroup;
  nomePesquisa: string = ''
  exibirAdm: boolean = false
  adminModel: boolean = false
  dataSourceWithPageSize = new MatTableDataSource(this.usuarios);
  @ViewChild('paginatorPageSize') paginatorPageSize!: MatPaginator;

  pageSizes = [10,20,30,40,50];

  constructor( private formBuilder: FormBuilder,
    protected modalService: ModalService,
    private usuarioService: UsuarioService,
     snackBar: MatSnackBar ) {
      super(snackBar);
    }

    ngOnInit(): void {
    this.usuarios = this.getAll()
    this.criarFormulario()
    this.exibirAdm = this.usuarioService.isUsuarioAdmin
  }

  criarFormulario(){
    this.formulario = this.formBuilder.group({
      nome: ['', [Validators.required]],
      login: ['', [Validators.required]],
      senha: ['', [Validators.required]],
      confimaSenha: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      admin: false
    })
  }

  pesquisar(){
    if (this.nomePesquisa.length == 0) return
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
        this.dataSourceWithPageSize = new MatTableDataSource(this.usuarios);
        this.dataSourceWithPageSize.paginator = this.paginatorPageSize;

      });
    return [];
  }

  detalhes(id: any){
    this.usuarioService.findById(id).then( response => {
      if (!response.ok) {
        this.exibirMensagemErro('Ocorreu um erro na requisição','Verifique os dados informados.');
      }
      this.usuarioDetalhes = response.body;
      this.adminModel = response.body.admin;
      this.exibirDetalhes = true;
      this.formulario.get('login')?.setValue(this.usuarioDetalhes.login)

      this.formulario.get('nome')?.setValue(this.usuarioDetalhes.nome)
      this.formulario.get('email')?.setValue(this.usuarioDetalhes.email)
      this.modalService.open('modalUsuario');
    });
  }

  openModalNovo(){
    this.exibirDetalhes = false;
    this.exibirEditar = false;
    this.modalService.open('modalUsuario');
  }

  save(){
    if(this.formulario.status == 'INVALID') return;

    let usuarioAux = {
      nome: this.formulario?.value.nome,
      email: this.formulario?.value.email,
      login: this.formulario?.value.login,
      senha: this.formulario?.value.senha,
      status: 'A',
      admin: this.formulario?.value.admin,
    }
    if(this.usuarioEditar && this.usuarioEditar.id != undefined ){
      Object.assign(usuarioAux,{id:this.usuarioEditar.id });
      this.usuarioService.update(usuarioAux).then( response => {
        if (!response.ok) {
          this.exibirMensagemErro('Ocorreu um problema ao tentar atualizar o usuário','Verifique os dados informados.');
        }
        usuarioAux = response
        this.modalService.close();
        this.exibirMensagemSucesso('Registro Alterado com Sucesso','')
        this.getAll()
        this.formulario.reset();
        this.adminModel = false;
      })
    }else{
      this.usuarioService.save(usuarioAux).then( response => {
        if (!response.ok) {
          this.exibirMensagemErro('Ocorreu um problema ao tentar salvar o usuário','Verifique os dados informados.');
        }
        usuarioAux = response
        this.modalService.close();
        this.exibirMensagemSucesso('Registro Incluído com Sucesso','')
        this.getAll()
        this.formulario.reset();
        this.adminModel = false;
      })
    }
  }

  editar(id: number){
    this.usuarioService.findById(id).then( response => {
      if (!response.ok) {
        this.exibirMensagemErro('Ocorreu um erro na requisição','Verifique os dados informados.');
      }
      this.usuarioEditar = response.body;
      this.formulario.get('login')?.setValue(this.usuarioEditar.login)

      this.formulario.get('nome')?.setValue(this.usuarioEditar.nome)
      this.formulario.get('email')?.setValue(this.usuarioEditar.email)
      this.adminModel = response.body.admin;
      this.exibirDetalhes = false;
      this.exibirEditar = true;
      this.modalService.open('modalUsuario');
    });
  }

}


