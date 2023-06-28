import { Sizes } from './../../enums/sizes.enum';
import { DocumentoInclusao } from './../../entity/documento-inclusao.entity';
import { Component, OnInit } from '@angular/core';
import { ModalService } from 'src/app/service/modalService.service';

@Component({
  selector: 'app-documentos',
  templateUrl: './documentos.component.html',
  styleUrls: ['./documentos.component.css'],
})
export class DocumentosComponent implements OnInit {
  documento: DocumentoInclusao = {
    filial: '',
    data_documento: '',
    reponsavel: '',
    data_validade: '',
    tipo_tocumento: '',
    documento_pai: '',
    documento_restrito: false
  }

  displayedColumns: string[] = ['id', 'Filial', 'Resposável','Tp Documento','Dt Documento','Dt Validade', 'Doc Restrito','Ações'];

  documentos: DocumentoInclusao[] = [];
  constructor(protected modalService: ModalService) { }

  ngOnInit() {
  }

  pesquisar(){}

  getSizeModal(): string{
    return Sizes.Middle.toString();
  }

}
