import { Movimentacao } from './../../entity/movimentacao.entity';
import { MovimentacaoService } from 'src/app/service/movimentacao-service.service';
import { AfterViewInit, OnInit, ViewChild} from '@angular/core';

import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DocumentoDetalhes } from 'src/app/entity/documento-detalhes.entity';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { ScackBarCustomComponent } from '../scack-bar-custom/scack-bar-custom.component';
import { EmpresaEnum } from 'src/app/enums/empresaEnum.enum';
import { ContaService } from 'src/app/service/conta.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MovimentacaoDTO } from 'src/app/entity/movimentacaoDTO.entity';
import { DataUtils } from 'src/app/utils/data.utils';

@Component({
  selector: 'app-conferencia',
  templateUrl: './conferencia.component.html',
  styleUrls: ['./conferencia.component.css'],
  providers: [MatPaginator]
})
export class ConferenciaComponent extends ScackBarCustomComponent implements  OnInit, AfterViewInit {

  displayedColumns: string[] = ['Matrícula', 'Colaborador', 'Cpf','Conta Débito','Valor','Histórico', 'Parceiro','Marca'];
  formulario!: FormGroup;
  empresas = EmpresaEnum;
  dataSource = new MatTableDataSource<DocumentoDetalhes>;
  dataSourceSize: number = 0
  pageEvent!: PageEvent;
  listaSession =  Array()
  empresaSelectedValue: string = '';
  historicoSelectedValue: string = '';
  listaHistorico: string[] = [];
  listaMovimentacao: Movimentacao[] = [];
  movimentacaoDTOAtualizada!: MovimentacaoDTO;

  listaEmpresa = [{ id:1, value: "Raça Distribuidora" },
                  { id:2, value: "Casa de Carnes"}
                ];

  @ViewChild('paginator') paginator: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  date = new FormControl(new Date());

  constructor(
    private formBuilder: FormBuilder,
    private movimentacaoService: MovimentacaoService,
    private contaService: ContaService,
    protected paginador: MatPaginator,
    snackBar: MatSnackBar) {
      super(snackBar);
      this.paginator = paginador
     }

     ngOnInit() {
      this.criarFormulario();
    }

    ngAfterViewInit() {
      this.criarFormulario();
      this.getHistorico();
      if(this.isArquivoCarregado()){
        if(this.isListaImpostadaArquivo()){
          this.listaSession =  JSON.parse(localStorage.getItem('importados') || '{}' );
          const lista = this.convertToDocumentoList(this.listaSession);
          this.listaMovimentacao = this.listaSession;
          this.dataSource = new MatTableDataSource<DocumentoDetalhes>(lista);
          localStorage.removeItem('importados')
          localStorage.removeItem('cargaArquivo')
          localStorage.setItem('cargaArquivo', '0')
        }
      }else{
          this.getAll()
      }
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  criarFormulario(){
    this.formulario = this.formBuilder.group({
      competencia: ['', [ Validators.required]],
      nota: [''],
      vencimento: ['', [Validators.required]],
      empresaSelectedValue: ['', [Validators.required]],
      historicoSelectedValue: ['', [Validators.required]],
      descricaoHistorico: [''],
    })
  }


  atualizar(){
    let competencia = DataUtils.convertDataStringToPtBrFormat(this.formulario.value.competencia);
    let vencimento = DataUtils.convertDataStringToPtBrFormat(this.formulario.value.vencimento);
    let nota = DataUtils.convertNotaStringToPtBrFormat(this.formulario.value.nota)

    let objAtualizar: MovimentacaoDTO = {
      codigofilial: this.formulario.value.empresaSelectedValue,
      competencia: competencia,
      vencimento: vencimento,
      nota:nota,
      historico: this.formulario.value.historicoSelectedValue,
      historicoDescricao: this.formulario.value.descricaoHistorico,
      listMovimentacao: this.listaMovimentacao
    }

    this.movimentacaoService.atualizar(objAtualizar).then( response => {
      if (!response.ok) {
        this.exibirMensagemErro('Ocorreu um erro ao tentar atualizar movimentação', 'Verifique seus dados.')
      }
      this.exibirMensagemSucesso('Movimentação atualizada com sucesso.','')
      const lista = this.convertToDocumentoList(response.body.listMovimentacao);
      this.movimentacaoDTOAtualizada = response.body;
      this.dataSource = new MatTableDataSource<DocumentoDetalhes>(lista);
      this.dataSource.paginator = this.paginator;
      console.log('Retorno Atualizar ==> ', this.movimentacaoDTOAtualizada)
    })

  }

  transferir(){

    this.movimentacaoService.transferirMovimentacao(this.movimentacaoDTOAtualizada).then( response => {
      if (!response.ok) {
        this.exibirMensagemErro('Ocorreu um erro ao tentar transferir uma movimentação', 'Verifique seus dados.')
      }
      this.exibirMensagemSucesso('Movimentação transferida com sucesso com sucesso.','')
      const lista = this.convertToDocumentoList(response.body.listMovimentacao);
      this.movimentacaoDTOAtualizada = response.body;
      this.dataSource = new MatTableDataSource<DocumentoDetalhes>(lista);
      this.dataSource.paginator = this.paginator;
      console.log('Retorno transferida ==> ', this.movimentacaoDTOAtualizada)
    })

  }

  getAll(){
    this.movimentacaoService.getAll().then( response => {
      if (response.body.message) {
        this.exibirMensagemErro('Falha na autenticação', response.body.message)
      }

      const lista = this.convertToDocumentoList(response.body);
      this.dataSource = new MatTableDataSource<DocumentoDetalhes>(lista);
      this.dataSource.paginator = this.paginator;
    })
  }

  convertToDocumentoList(listaEntry: any[]): DocumentoDetalhes[]{
    let listaAux: any[] = [];
    if(listaEntry){
      listaAux = listaEntry.map(item =>{
        let doc: DocumentoDetalhes = {

         matricula: item.idfuncionario || ' -- ',
         colaborador: item.nomefuncionario,
         cpf: item.cpffuncionario,
         conta_debito: item.contacorrente+ ' - ' + item.contacorrentedv,
         valor: item.valor,
         historico: item.historico || ' -- ',
         parceiro: item.cnpjempresa,
         marca: ' -- '
         }
         return doc;
     });
     this.dataSourceSize = listaAux.length
    }
    return listaAux;
  }

  getHistorico(){
    this.contaService.findAllHistorico().then( response => {
      if (!response.ok) {
        this.exibirMensagemErro('Falha na autenticação', 'Usuário ou senha incorretos.')
      }
      this.listaHistorico = response.body;
    })
  }

  private isListaImpostadaArquivo() {
    return localStorage.hasOwnProperty('importados');
  }

  private isArquivoCarregado() {
    return localStorage.getItem('cargaArquivo') === '1';
  }

  preencherNota(){
   let compet: string = this.formulario.value.competencia
   this.formulario.get('nota')?.setValue( compet.substring(2,compet.length));

  }

}



