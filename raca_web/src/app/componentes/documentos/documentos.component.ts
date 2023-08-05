import { Sizes } from './../../enums/sizes.enum';
import { DocumentoInclusao } from './../../entity/documento-inclusao.entity';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ModalService } from 'src/app/service/modalService.service';
import { ResponsavelService } from 'src/app/service/responsavel.service';
import { Responsavel } from 'src/app/entity/responsavel.entity';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Observable, map, startWith } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { ModalcadastrodocumentoComponent } from '../modalcadastrodocumento/modalcadastrodocumento.component';
import { DocumentoService } from 'src/app/service/documento.service';
import { DocumentoDTO } from 'src/app/entity/documento-dto.entity';
import { DialogDeleteComponent } from '../dialogDelete/dialog-delete.component';
import { ScackBarCustomComponent } from '../scack-bar-custom/scack-bar-custom.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DataUtils } from 'src/app/utils/data.utils';

@Component({
  selector: 'app-documentos',
  templateUrl: './documentos.component.html',
  styleUrls: ['./documentos.component.css'],
})
export class DocumentosComponent extends ScackBarCustomComponent  implements OnInit {

  @Output() documentBase64Emitter = new EventEmitter<string[]>();

  formulario!: FormGroup;
  docBase64: string[] = [];

  responsavel!: string;

  displayedColumns: string[] = ['id','Nome', 'Filial', 'Responsável','Tp Documento','Dt Documento','Dt Validade', 'Doc Restrito','Ações'];
  responsaveis: Responsavel[] = [];

  documentos: DocumentoDTO[] = [];
  filteredOptions!: Observable<Responsavel[]>;
  empresaSelectedValue: string = '';

  listaEmpresa  = [ { id:1, value: "Raça Distribuidora"}, { id:2, value: "Casa de Carnes" } ]


  tipoDocumento = [
    { id: 1, value: 'Boleto'},
    { id: 2, value: 'Bordero'},
    { id: 3, value: 'Carregamento'},
    { id: 4, value: 'Cte'},
    { id: 5, value: 'Contrato de Aluguel'},
    { id: 6, value: 'Contrato de Servico'},
    { id: 7, value: 'Mdfe'},
    { id: 8, value: 'NF-e'},
    { id: 9, value: 'NFS_SE'}
    ]

    page = 5;
    spreadMode: "off" | "even" | "odd" = "off";

  constructor(
    protected modalService: ModalService,
    private formBuilder: FormBuilder,
    public dialog: MatDialog,
    snackBar: MatSnackBar,
    private responsavelService: ResponsavelService,
    private serviceDocumento : DocumentoService) {
      super(snackBar);
    }

  ngOnInit() {
    this.criarFormulario();
    this.getAllResponsaveis();
    this.filteredOptions = this.formulario.get('responsavel')!.valueChanges.pipe(
      startWith(''),
      map(value => ( value ? this._filterReponsavel(value || '') : this.responsaveis.slice())),
    );
    this.getDocumentos();
  }

  pesquisar(){

   if(this.formulario.status == 'INVALID') return;
    let dtDocumento = this.formulario.get('dtDocumento')?.value != '' ? DataUtils.convertDataStringToPtBrFormat(this.formulario.get('dtDocumento')?.value) : '';
    let dtValidade = this.formulario.get('dtValidade')?.value != '' ? DataUtils.convertDataStringToPtBrFormat(this.formulario.get('dtValidade')?.value) : '';
    let nomeResponsavel = this.formulario.get('responsavel')?.value ;
    let idResponsavel = this.responsaveis.filter(respo => respo.nome === nomeResponsavel )[0].id

    let filter: DocumentoDTO = {
      datadocumentesc: dtDocumento || '',
      datavalidade: dtValidade  || '',
      emissor: idResponsavel?.toString() || '' ,
      filial : this.formulario.get('empresa')?.value  || '',
      iddocpai: undefined,
    }


    this.serviceDocumento.findByFilter(filter).then( response => {
      if (!response.ok) {
        this.exibirMensagemErro('Falha na autenticação','Usuário ou senha incorretos.');
      }
      this.documentos = response.body;

    });

    this.formulario.reset();
  }

  getSizeModal(): string{
    return Sizes.Middle.toString();
  }

  criarFormulario(){
      this.formulario = this.formBuilder.group({
      empresa: [''],
      dtDocumento: [''],
      dtValidade: [''],
      responsavel: '',
      tpDocumento: [''],
      docRegistro: [false]
    })
    this.formulario.get('docRestrito')?.setValue(false)
  }

  getAllResponsaveis(){
    this.responsavelService.findAll().then(response => {
      this.responsaveis = response.body;
    })
  }

  findByName(nome: string){
    this.responsavelService.findByName(nome).then(response => {
      this.responsaveis = response.body;
    })
  }

  private _filterReponsavel(value: string): any[] {
    return this.responsaveis
      .filter(resp => {
        return resp.nome.toString().toLocaleLowerCase().includes(value.toLocaleLowerCase())
      });
  }

  openNovo(){
    const dialogRef = this.dialog.open(ModalcadastrodocumentoComponent,
      {
        height: '60%',
        width: '60%',
      });

      dialogRef.afterClosed().subscribe(result => {
        if(result === true){
          this.getDocumentos();
          this.exibirMensagemSucesso('O registro foi incluído com sucesso','');
        }
      });
  }

  getDocumentos(){
    this.serviceDocumento.findAll().then(response => {
      this.documentos = response.body;
    })
  }

  remover(id: any){
    this.serviceDocumento.delete(id).then( response => {
      if (!response.ok) {
        this.exibirMensagemErro('Ocorreu um erro ao tentar apagar um registro','Verifique os dados informados.');
        return;
      } else{
        this.getDocumentos();
        this.exibirMensagemSucesso('O registro foi apagado com sucesso','');
      }

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

  detalhes(id: number){

    var doc = this.documentos
    .filter(documento => documento.id == id )[0]

    const dialogRef = this.dialog.open(ModalcadastrodocumentoComponent,
      {
        height: '60%',
        width: '60%',
      });
    dialogRef.componentInstance.detalhes = true;
    dialogRef.componentInstance.documento = doc;

  }

  visualizarDocumento(id: number){

    var docs = this.documentos
    .filter(documento => documento.id == id )
     this.docBase64 = docs[0].documento!;

    this.documentBase64Emitter.emit(this.docBase64);
  }

  getBase64DocumentoCode(): string{
    return this.docBase64 as any
  }

}
