import { Component, OnInit } from '@angular/core';
import { FormBuilder,  FormGroup,  Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Responsavel } from 'src/app/entity/responsavel.entity';
import { Sizes } from 'src/app/enums/sizes.enum';
import { ModalService } from 'src/app/service/modalService.service';
import { ResponsavelService } from 'src/app/service/responsavel.service';
import { ScackBarCustomComponent } from '../scack-bar-custom/scack-bar-custom.component';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { DialogDeleteComponent } from '../dialogDelete/dialog-delete.component';

@Component({
  selector: 'app-responsavel',
  templateUrl: './responsavel.component.html',
  styleUrls: ['./responsavel.component.css']
})
export class ResponsavelComponent extends ScackBarCustomComponent implements OnInit {

  displayedColumns: string[] = ['id', 'Nome','Email', 'Cpf/Cnpj','Telefone','Ações'];

  responsaveis: Responsavel[] = [];
  exibirDetalhes: boolean = false;
  formulario!: FormGroup;
  nomePesquisa: string = '';

  responsavelDetalhes!: Responsavel;
  idRemocao: string = '';

  constructor(
    private formBuilder: FormBuilder,
    protected modalService: ModalService,
    private responsavelService: ResponsavelService,
    snackBar: MatSnackBar,
    public dialog: MatDialog ) {
      super(snackBar);
    }

  ngOnInit() {
    this.criarFormulario();
    this.getAll();
  }

  criarFormulario(){
    this.formulario = this.formBuilder.group({
      nome: ['', [Validators.required]],
      email: ['', [ Validators.email]],
      cpfCnpj: ['', [Validators.required]],
      telefone: [''],
    });
  }

  pesquisar(){
    if (this.nomePesquisa.length == 0){
      this.exibirMensagemErro('Falha na pesquisa','É necessário informar o nome para pesquisar.');
      return;
    }
    this.responsavelService.findByName(this.nomePesquisa).then( response => {
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
    this.exibirDetalhes = false;
    this.modalService.open('modalResponsavel');
  }

  save(){
    if(this.formulario.status == 'INVALID') return;
    let responsavelInclusao: Responsavel = {
      nome: this.formulario?.value.nome,
      cpfcnpj: this.formulario?.value.cpfCnpj,
      telefone: this.formulario?.value.telefone,
      email: this.formulario?.value.email
    };

    this.responsavelService.save(responsavelInclusao).then( response => {
      if (!response.ok) {
        this.exibirMensagemErro('Ocorreu um problema ao tentar salvar o usuário','Verifique os dados informados.');
      }
      responsavelInclusao = response;
      this.modalService.close();
      this.exibirMensagemSucesso('Registro Incluído com Sucesso','');
      this.getAll();
    });
  }


  detalhes(id: any){
    this.responsavelService.findById(id).then( response => {
      if (!response.ok) {
        this.exibirMensagemErro('Ocorreu um erro na requisição','Verifique os dados informados.');
      }
      this.responsavelDetalhes = response.body;
      this.exibirDetalhes = true;
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
      console.log('The dialog was closed', result);
      if(result === true){
        this.remover(id)
      }
    });
  }

  // deleteRegistro(isDeleteItem: string){
  //   console.log(isDeleteItem)
  //   if(isDeleteItem === 'true'){
  //     this.remover(this.idRemocao)
  //   }
  // }

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

}
