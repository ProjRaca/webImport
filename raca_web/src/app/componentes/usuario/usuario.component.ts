import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {FloatLabelType, MatFormFieldModule} from '@angular/material/form-field';
import { Usuario } from 'src/app/entity/usuario.entity';
import { Sizes } from 'src/app/enums/sizes.enum';
import { ModalService } from 'src/app/service/modalService.service';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css'],

})
export class UsuarioComponent  {

  hideRequiredControl = new FormControl(false);
  floatLabelControl = new FormControl('auto' as FloatLabelType);


  usuarios: Usuario[] = [
    {id: 1, nome: 'John',  email: 'john@gmail.com',login: 'jmacedo123' ,senha: ''},
    {id: 2, nome: 'Mike',  email: 'mike@gmail.com',login: 'mtavares123', senha: ''},
    ];

  displayedColumns: string[] = ['id','Login', 'Nome', 'Email','Ações'];


  constructor(protected modalService: ModalService) {}



  pesquisar(){
    console.log('pesquisar');
  }

  getSizeModal(): string{
    return Sizes.Middle.toString();
  }
}


