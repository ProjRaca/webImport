import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-conferencia',
  templateUrl: './conferencia.component.html',
  styleUrls: ['./conferencia.component.css']
})
export class ConferenciaComponent implements OnInit {

  displayedColumns: string[] = ['Matrícula', 'Colaborador', 'Cpf','Conta Débito','Valor','Histórico', 'Parceiro','Marca','Ações'];

  constructor() { }

  ngOnInit() {
  }

  pesquisar(){}

}
