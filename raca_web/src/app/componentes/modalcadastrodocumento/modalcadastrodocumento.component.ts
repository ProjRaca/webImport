import { DocumentoService } from './../../service/documento.service';
import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
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

  formularioModal!: FormGroup;
  filteredOptions!: Observable<Responsavel[]>;
  empresaSelectedValue: string = '';

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

  constructor(
    @Inject(FormBuilder) public formBuilder: FormBuilder,
    private responsavelService: ResponsavelService,
    private serviceDocumento : DocumentoService,
    snackBar: MatSnackBar) {
      super(snackBar);
    }

  ngOnInit(): void {
    this.criarFormulario();
    this.getAllResponsaveis();
    this.filteredOptions = this.formularioModal.get('nomeResponsavel')!.valueChanges.pipe(
      startWith(''),
      map(value => ( value ? this._filterReponsavel(value || '') : this.responsaveis.slice())),
    );
    this.getDocumentos();
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
      nomeDocumento: ['']
    });
    this.formularioModal.get('docRestrito')?.setValue(false)
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
    this.serviceDocumento.findAll().then(response => {
      this.documentos = response.body;
      console.log('documentos => ', this.documentos)
    })
  }

}
