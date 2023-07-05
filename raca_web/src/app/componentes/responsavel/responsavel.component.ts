import { Component, OnInit } from '@angular/core';
import { Responsavel } from 'src/app/entity/responsavel.entity';
import { Sizes } from 'src/app/enums/sizes.enum';
import { ModalService } from 'src/app/service/modalService.service';

@Component({
  selector: 'app-responsavel',
  templateUrl: './responsavel.component.html',
  styleUrls: ['./responsavel.component.css']
})
export class ResponsavelComponent implements OnInit {

  displayedColumns: string[] = ['id', 'Nome', 'Email','Cpf/Cnpj','Telefone','Ações'];


  responsaveis: Responsavel[] = [
    {id: 1, nome: 'Sophia Fernanda Baptista',  email: 'sophia-baptista89@nelsonalfredoimoveis.com.br',cpf_cnpj: '701.289.665-56',telefone:'(00)0000-0000'},
    {id: 2, nome: 'Sabrina e Beatriz Limpeza ME',  email: 'seguranca@sabrinaebeatrizlimpezame.com.br',cpf_cnpj: '89.348.764/0001-86',telefone:'(00)0000-0000'}
    ];

  constructor(protected modalService: ModalService) { }

  ngOnInit() {
  }

  pesquisar(){}

  getSizeModal(): string{
    return Sizes.Middle.toString();
  }

}
