import { MovimentacaoService } from './../../service/movimentacao-service.service';
import { Component } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { FormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ScackBarCustomComponent } from '../scack-bar-custom/scack-bar-custom.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-importar-arquivo',
  templateUrl: './importar-arquivo.component.html',
  styleUrls: ['./importar-arquivo.component.css']
})
export class ImportarArquivoComponent extends ScackBarCustomComponent {


  currentFile?: File;
  progress = 0;
  message = '';

  fileInfos?: Observable<any>;

  constructor(private formBuilder: FormBuilder,
    snackBar: MatSnackBar,
    private router: Router,
    private movimentacao: MovimentacaoService) {
      super(snackBar);
     }

    formImport =  this.formBuilder.group({
      file: ['']
    });

  selectFile(event: any): void {
    if (event.target.files.length > 0 && this.formImport != undefined ) {
      this.currentFile = event.target.files[0];
    }
  }

  upload(): void {
    this.progress = 0;

    if (this.currentFile) {
        const formData = new FormData();
        formData.append('xls_file', this.currentFile, this.currentFile.name);

        this.movimentacao.upload(formData).then(response => {
          if (!response.ok) {
            this.exibirMensagemErro('Ocorreu um problema ao tentar fazer upload do arquivo','');
          }else{
            this.exibirMensagemSucesso('Importação do arquivo realizada com sucesso!','')
            localStorage.setItem('importados', JSON.stringify( response.body.listMovimentacao));
            localStorage.setItem('cargaArquivo', '1');
            this.router.navigateByUrl('/conferencia');
          }
        });
    }
  }
}
