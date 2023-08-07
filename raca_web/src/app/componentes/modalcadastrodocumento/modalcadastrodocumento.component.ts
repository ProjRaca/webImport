import { DocumentoService } from './../../service/documento.service';
import { Component, EventEmitter, Inject, OnInit, Output } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { Observable, startWith, map } from 'rxjs';
import { DocumentoDTO } from 'src/app/entity/documento-dto.entity';
import { Responsavel } from 'src/app/entity/responsavel.entity';
import { Sizes } from 'src/app/enums/sizes.enum';
import { ResponsavelService } from 'src/app/service/responsavel.service';
import { DataUtils } from 'src/app/utils/data.utils';
import { ScackBarCustomComponent } from '../scack-bar-custom/scack-bar-custom.component';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-modalcadastrodocumento',
  templateUrl: './modalcadastrodocumento.component.html',
  styleUrls: ['./modalcadastrodocumento.component.css']
})
export class ModalcadastrodocumentoComponent extends ScackBarCustomComponent implements OnInit {

  @Output() documentBase64Emitter = new EventEmitter<string[]>();

  formularioModal!: FormGroup;
  filteredOptions!: Observable<Responsavel[]>;
  empresaSelectedValue: string = '';
  selectedFile: any = null;
  formData = new FormData();
  nomeEmpresa: string = '';
  nomeDocumentoPai: string = '';
  nomeResponsavel: string = '';
  dataValidade: string = '';
  dataDocumento: string = '';
  tipoDocumentoDesc: string = '';
  documentoRestritoDesc: string = '';
  documentoBase64: string[] = [];

  listaEmpresa  = [ { id:1, value: "Raça Distribuidora"}, { id:2, value: "Casa de Carnes" } ]
  responsaveis: Responsavel[] = [];
  documentos: DocumentoDTO[] = [];
  documento!: DocumentoDTO;
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

  currentFile?: File;
  base64File: string[] = [];
  detalhes: boolean = false;

  constructor(
    @Inject(FormBuilder) public formBuilder: FormBuilder,
    private responsavelService: ResponsavelService,
    private serviceDocumento : DocumentoService,
    snackBar: MatSnackBar) {
      super(snackBar);
    }

  ngOnInit(): void {
    this.getAllResponsaveis();
    if(this.detalhes === false){
      this.getDocumentos();
      this.criarFormulario();
      this.filteredOptions = this.formularioModal.get('nomeResponsavel')!.valueChanges.pipe(
        startWith(''),
        map(value => ( value ? this._filterReponsavel(value || '') : this.responsaveis.slice())),
      );
    }else{
      this.carregarDadosDetalhes();
    }
  }

  private carregarDadosDetalhes() {

    sleep(5000);
    this.criarFormularioDetalhes(this.documento);
    console.log('Documento Destalhes ==> ', this.documento);
    this.nomeEmpresa = this.listaEmpresa.filter(emp => emp.id === Number(this.documento.filial))[0].value;
    this.dataDocumento = DataUtils.formatarData(this.documento.datadocumentesc as string);
    this.dataValidade = DataUtils.formatarData(this.documento.datavalidade as string);
    this.tipoDocumentoDesc = this.tipoDocumento.filter(tp => tp.id === Number(this.documento.tipodocumento))[0].value;
    this.documentoRestritoDesc = this.documento.restrito === true ? 'Sim' : 'Não';
    this.documentoBase64 = this.documento.documento || [];
    this.serviceDocumento.findAll()
      .pipe()
      .toPromise()
      .then(response => {
      this.documentos = response.body;
      console.log('Documentos =>', this.documentos);
      this.nomeDocumentoPai = this.documentos.filter(doc => doc.id == this.documento.iddocpai)[0].nome || 'teste';
    })
    .catch((error) => {
      // Lida com erros, se necessário.
      console.error('Erro na chamada:', error);
      this.exibirMensagemErro('Ocorreu um erro ao realizar chamada', error.body.message)
    })

  }

  criarFormulario(){
      this.formularioModal = this.formBuilder.group({
      empresa: [''],
      dtDocumento: [''],
      dtValidade: [''],
      nomeResponsavel: '',
      tpDocumento: [''],
      docRestrito: [false],
      docPai: [''],
      nomeDocumento: [''],
      file: ['']
    });
    this.formularioModal.get('docRestrito')?.setValue(false)
  }

  criarFormularioDetalhes(documento: DocumentoDTO){
    this.formularioModal = this.formBuilder.group({
    empresa: new FormControl({value: documento.filial, disabled: true} ),
    dtDocumento: new FormControl({value: documento.datadocumentesc, disabled: true}),
    dtValidade: new FormControl({value: documento.datavalidade, disabled: true}),
    nomeResponsavel: new FormControl({value: documento.emissor, disabled: true}),
    tpDocumento: new FormControl({value: documento.tipodocumento, disabled: true}),
    docRestrito: new FormControl({value: documento.restrito, disabled: true}),
    docPai: new FormControl({value: documento.iddocpai, disabled: true}),
    nomeDocumento: new FormControl({value: documento.nome, disabled: true}),
    file: new FormControl({value: documento.documento, disabled: true}),
  });
  //this.formularioModal.get('docRestrito')?.setValue(false)
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

  getSizeModal(): string{
    return Sizes.Middle.toString();
  }

  salvar(){
    let dataDocumento = DataUtils.convertDataStringToPtBrFormat( this.formularioModal.value?.dtDocumento );
    let dataValidade = DataUtils.convertDataStringToPtBrFormat( this.formularioModal.value?.dtValidade );

    if(this.formularioModal.status == 'INVALID') return;
    let documentoInclusao = {
      iddocpai: this.formularioModal.value?.docPai,
      filial: this.formularioModal.value?.empresa,
      datadocumentesc: dataDocumento,
      datavalidade: dataValidade,
      emissor: this.formularioModal.value?.nomeResponsavel,
      tipodocumento: this.formularioModal.value?.tpDocumento,
      documento: this.base64File,
      restrito: this.formularioModal.value?.docRestrito,
      nome: this.formularioModal.value?.nomeDocumento
    };
    this.serviceDocumento.save(documentoInclusao).then( response => {
      if (!response.ok) {
        this.exibirMensagemErro('Ocorreu um erro ao tentar atualizar movimentação', 'Verifique seus dados.')
      }else
      this.documento = response.body;

    })
  }
  getDocumento(){
    return this.documento;
  }

  getDocumentos(){
    this.serviceDocumento.findAll()
      .pipe()
      .toPromise()
      .then(response => {
      this.documentos = response.body;
      console.log('Documentos', this.documentos)
    })
    .catch((error) => {
       // Lida com erros, se necessário.
       console.error('Erro na chamada:', error);
       this.exibirMensagemErro('Ocorreu um erro ao realizar chamada', error.body.message)
    })
  }

  onFileChange(event: any): void {
    let myfilename = '';

    if (event.target.files.length > 0 && this.formularioModal != undefined ) {
      this.currentFile = event.target.files[0];
    }
      Array.from(event.target.files).forEach((file: any) => {
        console.log(file);
        myfilename += file.name + ',';
      });
      const file = event.target.files[0];
      const reader = new FileReader();
      reader.onload = (e: any) => {

       const base64String = e.target.result
                .replace('data:', '')
                .replace(/^.+,/, '');
      this.base64File = base64String;
      }
      reader.readAsDataURL(file);



  }

  selectChange(event: Event): void {
    const inputElement = event.target as HTMLInputElement;
    const file = inputElement.files?.[0];

    if (file) {
      const reader = new FileReader();

      reader.onload = (e) => {
        const fileContent = e.target?.result as string;
        // Exiba o resultado para o usuário (opcional)
        this.exibirMensagemSucesso('Arquivo PDF carregado com sucesso!', 'Fechar');
        // Agora você pode salvar 'fileContent' (base64) no banco de dados.
        console.log(fileContent);
      };

      reader.readAsDataURL(file);
    }
  }

  visualizarDocumento(base64: any){
    this.documentBase64Emitter.emit(base64);
  }

  getBase64DocumentoCode(): string{
    return this.documentoBase64 as any
  }

}

export const toBase64 = (file: File) =>
  new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result);
    reader.onerror = (error) => reject(error);
  });

  async function sleep(arg0: number) {
      await sleep(arg0);
  }

