import { HttpEventType, HttpResponse } from '@angular/common/http';
import { MovimentacaoService } from './../../service/movimentacao-service.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-importar-arquivo',
  templateUrl: './importar-arquivo.component.html',
  styleUrls: ['./importar-arquivo.component.css']
})
export class ImportarArquivoComponent implements OnInit {

  selectedFiles?: FileList;
  currentFile?: File;
  progress = 0;
  message = '';

  fileInfos?: Observable<any>;


  constructor(private formBuilder: FormBuilder,
    private movimentacao: MovimentacaoService) { }

    formImport =  this.formBuilder.group({
      file: ['']
    });

    ngOnInit(): void {
  }

  selectFile(event: any): void {
    this.selectedFiles = event.target.files[0];
    if (event.target.files.length > 0 && this.formImport != undefined ) {
      this.currentFile = event.target.files[0];

    }
  }

  upload(): void {
    this.progress = 0;

    if (this.selectedFiles) {
       const file = this.currentFile;

        const formData = new FormData();
        formData.append('file', file ? file : '' );

        this.movimentacao.upload(formData).subscribe({
          next: (event: any) => {
            if (event.type === HttpEventType.UploadProgress) {
              this.progress = Math.round((100 * event.loaded) / event.total);
            } else if (event instanceof HttpResponse) {
              this.message = event.body.message;
              this.fileInfos = this.movimentacao.getFiles();
            }
          },
          error: (err: any) => {
            console.log(err);
            this.progress = 0;

            if (err.error && err.error.message) {
              this.message = err.error.message;
            } else {
              this.message = 'Não foi possível realizar upload do arquivo!';
            }

            this.currentFile = undefined;
          },
        });
      // }

      this.selectedFiles = undefined;
    }
  }

  //handleImport(event: any) {
   // this.selectedFiles = event.target.files;
   // this.test_selectedFiles = (<HTMLInputElement>event.target).files;
  //}

  // upload() {
  //   this.progress.percentage = 0;
  //   //this.currentFileUpload =  this.selectedFiles.item(0);
  //   let file = this.selectedFiles.item(0);
  //   this.movimentacaoService.pushFileToStorage(file).subscribe(event => {
  //     if (event.type === HttpEventType.UploadProgress) {
  //       this.progress.percentage = Math.round(100 * event.loaded / event.total);
  //     } else if (event instanceof HttpResponse) {
  //        alert('File Successfully Uploaded');
  //     }
  //     this.selectedFiles = undefined;
  //    }
  //   );
  // }


}
