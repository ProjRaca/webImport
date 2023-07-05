import { MovimentacaoService } from 'src/app/service/movimentacao-service.service';
import { AfterViewInit, ViewChild} from '@angular/core';

import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DocumentoDetalhes } from 'src/app/entity/documento-detalhes.entity';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { ScackBarCustomComponent } from '../scack-bar-custom/scack-bar-custom.component';
import { EmpresaEnum } from 'src/app/enums/empresaEnum.enum';

@Component({
  selector: 'app-conferencia',
  templateUrl: './conferencia.component.html',
  styleUrls: ['./conferencia.component.css'],
  providers: [MatPaginator]
})
export class ConferenciaComponent extends ScackBarCustomComponent implements  AfterViewInit {

  displayedColumns: string[] = ['Matrícula', 'Colaborador', 'Cpf','Conta Débito','Valor','Histórico', 'Parceiro','Marca','Ações'];

  empresas = EmpresaEnum;
  dataSource = new MatTableDataSource<DocumentoDetalhes>;
  dataSourceSize: number = 0
  pageEvent!: PageEvent;
  listaSession =  Array()

  @ViewChild('paginator') paginator: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private movimentacaoService: MovimentacaoService,
    protected paginador: MatPaginator,
    snackBar: MatSnackBar) {
      super(snackBar);
      this.paginator = paginador
      console.log(this.empresas);
     }


    ngAfterViewInit() {
      if(localStorage.getItem('cargaArquivo') === '1'){
        if(localStorage.hasOwnProperty('importados')){
          this.listaSession =  JSON.parse(localStorage.getItem('importados') || '{}' );
          const lista = this.convertToDocumentoList(this.listaSession);
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

  pesquisar(){}

  getAll(){
    this.movimentacaoService.getAll().then( response => {
      if (!response.ok) {
        this.exibirMensagemErro('Falha na autenticação', 'Usuário ou senha incorretos.')
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

  listarItens(){
    let adicional;

  }
}



