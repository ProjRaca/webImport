import { Component, OnInit } from '@angular/core';
import { FormBuilder,  FormControl,  FormGroup,  Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Responsavel } from 'src/app/entity/responsavel.entity';
import { Sizes } from 'src/app/enums/sizes.enum';
import { ModalService } from 'src/app/service/modalService.service';
import { ResponsavelService } from 'src/app/service/responsavel.service';
import { ScackBarCustomComponent } from '../scack-bar-custom/scack-bar-custom.component';
import { MatDialog } from '@angular/material/dialog';
import { DialogDeleteComponent } from '../dialogDelete/dialog-delete.component';
import { UsuarioService } from 'src/app/service/usuario.service';
import { Observable } from 'rxjs/internal/Observable';
import { startWith } from 'rxjs/internal/operators/startWith';
import { map } from 'rxjs/internal/operators/map';

@Component({
  selector: 'app-responsavel',
  templateUrl: './responsavel.component.html',
  styleUrls: ['./responsavel.component.css']
})
export class ResponsavelComponent extends ScackBarCustomComponent implements OnInit {

  displayedColumns: string[] = ['id','Nome','Email', 'Cpf/Cnpj','Filial','Telefone','Ações'];

  responsaveis: Responsavel[] = [];
  exibirDetalhes: boolean = false;
  formulario!: FormGroup;
  nomePesquisa: string = '';
  filialPesquisa: boolean = false;

  responsavelDetalhes!: Responsavel;
  responsavelEditar!: Responsavel;
  idRemocao: string = '';
  isAdmin: boolean = false;
  exibirEditar: boolean = false;

  filteredOptions!: Observable<Responsavel[]>;

  constructor(
    private formBuilder: FormBuilder,
    protected modalService: ModalService,
    private responsavelService: ResponsavelService,
    private usuarioService: UsuarioService,
    snackBar: MatSnackBar,
    public dialog: MatDialog ) {
      super(snackBar);
    }

  ngOnInit() {
    //this.criarFormulario();
    this.criarFormularioPesquisa();
    this.getAll();
    this.isAdmin = this.usuarioService.isUsuarioAdmin;
    this.filteredOptions = this.formulario.get('nomePesquisa')!.valueChanges.pipe(
      startWith(''),
      map(value => ( value ? this._filterReponsavel(value || '') : this.responsaveis.slice())),
    );
  }

  criarFormulario(){
    this.formulario = this.formBuilder.group({
      nome: ['', [Validators.required]],
      email: ['', [Validators.email]],
      cpfCnpj: ['', [Validators.required]],
      filial:[false],
      telefone: [''],
      nomePesquisa: ['']
    });
  }

  pesquisar(){
    if (this.formulario.get('nomePesquisa') == undefined && this.filialPesquisa == undefined){
      this.exibirMensagemErro('Falha na pesquisa','É necessário informar o nome ou filial para pesquisar.');
      return;
    }

    let filter = {
      nome: this.formulario.get('nomePesquisa')?.value != '' ? this.formulario.get('nomePesquisa')?.value : '',
      filial: this.filialPesquisa
    }
    this.responsavelService.findByFilter(filter)
    .pipe()
    .toPromise()
    .then( response => {
      if (!response.ok) {
        this.exibirMensagemErro('Falha na autenticação','Usuário ou senha incorretos.');
      }
      this.responsaveis = response.body;

    });
  return [];
  }

  getSizeModal(): string{
    return Sizes.Middle.toString();
  }

  getAll(){
    this.responsavelService.findAll().then( response => {
      if (!response.ok) {
        this.exibirMensagemErro('Falha na autenticação','Usuário ou senha incorretos.');
      }
      this.responsaveis = response.body;

    });
  return [];
  }

  openModalNovo(){
    this.formulario.reset();
    this.exibirDetalhes = false;
    this.exibirEditar = false;
    this.modalService.open('modalResponsavel');
    console.log('isAdmin :>>', this.isAdmin);
  }

  save(){
    if(this.formulario.status == 'INVALID') return;
    let responsavelAux: Responsavel = {
      nome: this.formulario?.value.nome,
      cpfcnpj: this.formulario?.value.cpfCnpj,
      telefone: this.formulario?.value.telefone,
      email: this.formulario?.value.email,
      filial: this.formulario?.value.filial
    };

    if(this.responsavelEditar != undefined && this.responsavelEditar.id != undefined && this.responsavelEditar.id){
      Object.assign(responsavelAux,{id:this.responsavelEditar.id });
      this.responsavelService.update(responsavelAux).then( response => {
        if (!response.ok) {
          this.exibirMensagemErro('Ocorreu um problema ao tentar atualizar o responsável','Verifique os dados informados.');
        }
        responsavelAux = response;
        this.modalService.close();
        this.exibirMensagemSucesso('Registro Alterdo com Sucesso','');
        this.getAll();
      });
    }else{
      this.responsavelService.save(responsavelAux).then( response => {
        if (!response.ok) {
          this.exibirMensagemErro('Ocorreu um problema ao tentar salvar o responsável','Verifique os dados informados.');
        }
        responsavelAux = response;
        this.modalService.close();
        this.exibirMensagemSucesso('Registro Incluído com Sucesso','');
        this.getAll();
      });
    }
  }


  detalhes(id: any){
    this.responsavelService.findById(id).then( response => {
      if (!response.ok) {
        this.exibirMensagemErro('Ocorreu um erro na requisição','Verifique os dados informados.');
      }
      this.responsavelDetalhes = response.body;
      this.exibirDetalhes = true;
      this.exibirEditar = false;
      this.formulario.get('nome')?.setValue(this.responsavelDetalhes.nome)
      this.formulario.get('email')?.setValue(this.responsavelDetalhes.email)
      this.formulario.get('cpfCnpj')?.setValue(this.responsavelDetalhes.cpfcnpj)
      this.formulario.get('telefone')?.setValue(this.responsavelDetalhes.telefone)

      this.modalService.open('modalResponsavel');
    });
  }

  confirmDelete(id: any){
    const dialogRef = this.dialog.open(DialogDeleteComponent);

    dialogRef.afterClosed().subscribe(result => {
      if(result === true){
        this.remover(id)
      }
    });
  }

  remover(id: any){
    this.responsavelService.deleteById(id).then( response => {
      if (!response.ok) {
        this.exibirMensagemErro('Ocorreu um erro ao tentar apagar um registro','Verifique os dados informados.');
        return;
      } else{
        this.getAll();
        this.exibirMensagemSucesso('O registro foi apagado com sucesso','');
      }

    });
  }

  editar(id: any){
    this.responsavelService.findById(id).then( response => {
      if (!response.ok) {
        this.exibirMensagemErro('Ocorreu um erro na requisição','Verifique os dados informados.');
      }
      this.responsavelEditar = response.body;
      this.formulario.get('nome')?.setValue(this.responsavelEditar.nome)
      this.formulario.get('email')?.setValue(this.responsavelEditar.email)
      this.formulario.get('cpfCnpj')?.setValue(this.responsavelEditar.cpfcnpj)
      this.formulario.get('telefone')?.setValue(this.responsavelEditar.telefone)

      this.exibirDetalhes = false;
      this.exibirEditar = true;
      this.modalService.open('modalResponsavel');
    });
  }

  private _filterReponsavel(value: string): any[] {
    return this.responsaveis
      .filter(resp => {
        return resp.nome.toString().toLocaleLowerCase().includes(value.toLocaleLowerCase())
      });
  }

  criarFormularioPesquisa(){
    this.formulario = new FormGroup({
      nome: new FormControl(),
      email: new FormControl([Validators.email]),
      cpfCnpj: new FormControl() ,
      filial: new FormControl(false),
      telefone: new FormControl(),
      nomePesquisa: new FormControl()
    });
  }

}
