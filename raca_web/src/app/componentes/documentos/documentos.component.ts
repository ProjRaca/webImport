import { Sizes } from './../../enums/sizes.enum';
import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { ModalService } from 'src/app/service/modalService.service';
import { ResponsavelService } from 'src/app/service/responsavel.service';
import { Responsavel } from 'src/app/entity/responsavel.entity';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Observable, map, startWith } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { ModalcadastrodocumentoComponent } from '../modalcadastrodocumento/modalcadastrodocumento.component';
import { DocumentoService } from 'src/app/service/documento.service';
import { DocumentoDTO } from 'src/app/entity/documento-dto.entity';
import { DialogDeleteComponent } from '../dialogDelete/dialog-delete.component';
import { ScackBarCustomComponent } from '../scack-bar-custom/scack-bar-custom.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DataUtils } from 'src/app/utils/data.utils';
import { UsuarioService } from 'src/app/service/usuario.service';
import { TipoDocumento } from 'src/app/entity/tipo-documento.entity';

@Component({
  selector: 'app-documentos',
  templateUrl: './documentos.component.html',
  styleUrls: ['./documentos.component.css'],
})
export class DocumentosComponent extends ScackBarCustomComponent  implements OnInit {

  @Output() documentBase64Emitter = new EventEmitter<string[]>();

  panelOpenState = false;
  formulario!: FormGroup;
  formularioPesquisa!: FormGroup;
  docBase64: string[] = [];

  responsavel!: string;

  displayedColumns: string[] = ['id','Nº Documento','Nome', 'Filial', 'Responsável','Tp Documento','Dt Documento','Dt Validade', 'Doc Restrito','Ações'];
  responsaveis: Responsavel[] = [];
  filiais: Responsavel[] = [];
  listaEmpresa  = [ { id:1, value: "Raça Distribuidora"}, { id:2, value: "Casa de Carnes" } ]
  listTipoDocumento: TipoDocumento[] = [];
  documentos: DocumentoDTO[] = [];
  listaDocumentoPai: DocumentoDTO[] = [];

  filteredOptions!: Observable<Responsavel[]>;
  filteredOptionsDocumentoPai!: Observable<DocumentoDTO[]>;

  empresaSelectedValue: string = '';
  isAdmin: boolean = false;
  page = 5;
  spreadMode: "off" | "even" | "odd" = "off";

  constructor(
    protected modalService: ModalService,
    private formBuilder: FormBuilder,
    public dialog: MatDialog,
    snackBar: MatSnackBar,
    private responsavelService: ResponsavelService,
    private serviceDocumento : DocumentoService,
    public usuarioService: UsuarioService) {
      super(snackBar);
    }

  ngOnInit() {
        this.criarFormularioPesquisa();
        this.getAllResponsaveis();
        this.getAllFiliais();
        this.getDocumentoPai();
        this.getTipoDocumentoList();
        this.filterResponsavelAutocomplete();
        this.filterDocumentoPaiAutocomplete();
        this.getDocumentos();
        this.isAdmin = this.usuarioService.isUsuarioAdmin;
  }

  private filterResponsavelAutocomplete() {
    this.filteredOptions = this.formulario.get('responsavel')!.valueChanges.pipe(
      startWith(''),
      map(value => (value ? this._filterReponsavel(value || '') : this.responsaveis.slice()))
    );
  }

  private filterDocumentoPaiAutocomplete() {
    this.filteredOptionsDocumentoPai = this.formulario.get('docPai')!.valueChanges.pipe(
      startWith(''),
      map(value => (value ? this._filterDocumentoPai(value || '') : this.listaDocumentoPai.slice()))
    );
  }

  pesquisar(){

   if(this.formulario.status == 'INVALID') return;
   let dtDocumento = this.formulario.get('dtDocumento')?.value != '' && this.formulario.get('dtDocumento')?.value != undefined ? DataUtils.formatarDatetoUsFormat(this.formulario.get('dtDocumento')?.value) : '';
   let dtFim = this.formulario.get('dtDocumentoFinal')?.value != '' && this.formulario.get('dtDocumentoFinal')?.value != undefined ? DataUtils.formatarDatetoUsFormat(this.formulario.get('dtDocumentoFinal')?.value) : '';
   let dtValidade = this.formulario.get('dtValidade')?.value != '' && this.formulario.get('dtValidade')?.value != undefined ? DataUtils.formatarDatetoUsFormat(this.formulario.get('dtValidade')?.value) : '';
   let dtFimValidade = this.formulario.get('dtValidadeFinal')?.value != '' && this.formulario.get('dtValidadeFinal')?.value != undefined ? DataUtils.formatarDatetoUsFormat(this.formulario.get('dtValidadeFinal')?.value) : '';
   let nomeResponsavel = this.formulario.get('responsavel')?.value || undefined ;
   let idResponsavel = nomeResponsavel != undefined ? this.responsaveis.filter(respo => respo.nome === nomeResponsavel )[0].nome : ''
   let documentoPaiNome = this.formulario.get('docPai')?.value || undefined;
   let documentoPai = this.formulario.get('docPai')?.value != "" && this.formulario.get('docPai')?.value != null  ? this.listaDocumentoPai.filter(respo => respo.nome?.toLocaleUpperCase() === documentoPaiNome.toLocaleUpperCase() ) : undefined;

   let filter: DocumentoDTO = {
      datadocumentesc: dtDocumento || undefined,
      datavalidade: dtValidade  || undefined,
      emissor: idResponsavel?.toString() ,
      empresa : this.formulario.get('empresa')?.value  || undefined,
      iddocpai:  documentoPai != undefined ?  documentoPai[0].id : undefined,
      tipodocumento: this.formulario.get('tpDocumento')?.value || undefined,
      restrito: this.formulario.get('docRegistro')?.value || false,
      numerodocumento: this.formulario.get('numeroDocumento')?.value || undefined,
      datafimvalidade: dtFimValidade,
      datafim: dtFim,
      responsavel: idResponsavel?.toString() ,
      filial: this.formulario.get('filial')?.value || undefined
    }

    this.serviceDocumento.findByFilter(filter)
    .pipe()
    .toPromise()
    .then((resposta) => {
      this.documentos = resposta.body
    })
    .catch((error) => {
      // Lida com erros, se necessário.
      console.error('Erro na chamada:', error);
      this.exibirMensagemErro('Falha na autenticação', error.body.message)
    });
  }

  getSizeModal(): string{
    return Sizes.Large.toString();
  }

  criarFormulario(){
      this.formulario = this.formBuilder.group({
      empresa: [''],
      dtDocumento: [''],
      dtDocumentoFinal: [''],
      dtValidade: [''],
      dtValidadeFinal: [''],
      responsavel: '',
      tpDocumento: [''],
      docRegistro: [false],
      numeroDocumento:[''],
      filial:[''],
      docPai: ['']
    })
    this.formulario.get('docRestrito')?.setValue(false)
  }

  getAllResponsaveis(){
    this.responsavelService.findAll().then(response => {
      this.responsaveis = response.body;
    })
  }

  getAllFiliais(){
    this.responsavelService.findAllFiliais().then(response => {
      this.filiais = response.body;
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

  private _filterDocumentoPai(value: string): any[] {
    return this.listaDocumentoPai
      .filter(resp => {
        return resp.nome?.toString().toLocaleLowerCase().includes(value.toLocaleLowerCase())
      });
  }

  openNovo(){
    const dialogRef = this.dialog.open(ModalcadastrodocumentoComponent,
      {
        height: '75%',
        width: '70%',
      });

      dialogRef.afterClosed().subscribe(result => {
        if(result === true){
          this.getDocumentos();
          this.exibirMensagemSucesso('O registro foi incluído com sucesso','');
        }
      });
  }

  getDocumentos(){
    this.serviceDocumento.findAll()
        .pipe()
        .toPromise()
        .then(response => {
        this.documentos = response.body;
      })
      .catch((error) => {
        // Lida com erros, se necessário.
        console.error('Erro na chamada:', error);
        this.exibirMensagemErro('Ocorreu um erro ao realizar chamada', error.body.message)
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

    var doc: DocumentoDTO = {
      id: id,
      restrito: false
    }
    this.serviceDocumento.findByFilter(doc)
    .pipe()
    .toPromise()
    .then((resposta) => {
      if (resposta.body.length > 0){

        doc = resposta.body[0]
        this.callDialogDetalhes(doc);
      }
    })
    .catch((error) => {
      // Lida com erros, se necessário.
      console.error('Erro na chamada:', error);
      this.exibirMensagemErro('Falha na autenticação', error.body.message)
    });


  }

  private callDialogDetalhes(doc: DocumentoDTO) {
    const dialogRef = this.dialog.open(ModalcadastrodocumentoComponent,
      {
        height: '75%',
        width: '70%',
      });
    dialogRef.componentInstance.detalhes = true;
    dialogRef.componentInstance.update = false;
    dialogRef.componentInstance.documento = doc;
  }

  editar(id: number){
    var doc: DocumentoDTO = {
      id: id,
      restrito: false
    };

    this.serviceDocumento.findByFilter(doc)
    .pipe()
    .toPromise()
    .then((resposta) => {
      if (resposta.body.length > 0){
        doc = resposta.body[0]
        this.callDialogEditar(doc);
      }
    })
    .catch((error) => {
      // Lida com erros, se necessário.
      console.error('Erro na chamada:', error);
      this.exibirMensagemErro('Falha na autenticação', error.body.message)
    });
  }

  private callDialogEditar(doc: DocumentoDTO) {
    const dialogRef = this.dialog.open(ModalcadastrodocumentoComponent,
      {
        height: '75%',
        width: '70%',
      });
    dialogRef.componentInstance.detalhes = false;
    dialogRef.componentInstance.update = true;
    dialogRef.componentInstance.documento = doc;

    dialogRef.afterClosed().subscribe(result => {
      if(result === true){
        this.getDocumentos();
        this.exibirMensagemSucesso('O registro foi alterado com sucesso','');
      }
    });
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

  getDocumentoPai(){
    this.serviceDocumento.findAllDOcumentoPai()
        .pipe()
        .toPromise()
        .then(response => {
        this.listaDocumentoPai = response.body;
      })
      .catch((error) => {
        // Lida com erros, se necessário.
        console.error('Erro na chamada:', error);
        this.exibirMensagemErro('Ocorreu um erro ao realizar chamada', error.body.message)
      })
  }

  print(){
    if (window.print) {
      window.print();
    } else {
      console.error('Seu navegador não suporta a função de impressão.');
    }
  }

  getTipoDocumentoList(){
    this.serviceDocumento.findAllTipoDOcumento()
        .pipe()
        .toPromise()
        .then(response => {
        this.listTipoDocumento = response.body;
      })
      .catch((error) => {
        // Lida com erros, se necessário.
        console.error('Erro na chamada:', error);
        this.exibirMensagemErro('Ocorreu um erro ao realizar chamada', error.body.message)
      })
  }

  criarFormularioPesquisa(){
    this.formulario = new FormGroup({
      empresa: new FormControl(),
      dtDocumento:new FormControl(),
      dtDocumentoFinal: new FormControl(),
      dtValidade: new FormControl(),
      dtValidadeFinal: new FormControl(),
      responsavel: new FormControl(),
      tpDocumento: new FormControl(),
      docRegistro: new FormControl(),
      numeroDocumento:new FormControl(),
      filial:new FormControl(),
      docPai: new FormControl()
    });

    this.formulario.get('docRestrito')?.setValue(false)
    this.formulario.get('docRegistro')?.setValue(false)
  }

  resetFormulario(){
    this.formulario.reset();
    this.getDocumentos();
  }

}
