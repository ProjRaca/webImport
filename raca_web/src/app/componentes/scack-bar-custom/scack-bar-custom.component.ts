import { Component } from '@angular/core';
import { MatSnackBar,
         MatSnackBarHorizontalPosition,
         MatSnackBarVerticalPosition
} from '@angular/material/snack-bar';

@Component({
  selector: 'app-scack-bar-custom',
  templateUrl: './scack-bar-custom.component.html',
  styleUrls: ['./scack-bar-custom.component.css']
})
export class ScackBarCustomComponent {

  horizontalPosition: MatSnackBarHorizontalPosition = 'end';
  verticalPosition: MatSnackBarVerticalPosition = 'top';


  constructor(private snackBar: MatSnackBar) { }

 exibirMensagemErro(TituloErro: string, msgErro: string){
    this.snackBar.open(TituloErro, msgErro, {
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
      duration: 5000,
      panelClass: ['mat-warn']
    });
  }

  exibirMensagemSucesso(TituloErro: string, msgErro: string){
    this.snackBar.open(TituloErro, msgErro, {
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
      duration: 5000,
      panelClass: ['mat-warn']
    });
  }

}
