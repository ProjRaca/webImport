import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Observable, startWith, map } from 'rxjs';
import { Responsavel } from 'src/app/entity/responsavel.entity';
import { ResponsavelService } from 'src/app/service/responsavel.service';

@Component({
  selector: 'app-autocomplete-responsavel',
  templateUrl: './autocomplete-responsavel.component.html',
  styleUrls: ['./autocomplete-responsavel.component.css']
})
export class AutocompleteResponsavelComponent implements OnInit {

  @Input() formulario: any;

  form!: FormGroup;
  filteredOptions!: Observable<Responsavel[]>;
  responsaveis: Responsavel[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private responsavelService: ResponsavelService) {

    }

  ngOnInit(): void {
    this.form = this.formulario
    this.getAllResponsaveis();
    // this.filteredOptions = this.formulario.get('nomeResponsavelGroup')!.valueChanges.pipe(
    //   startWith(''),
    //   map(value => ( value ? this._filterReponsavel(value || '') : this.responsaveis.slice())),
    // );
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
}
