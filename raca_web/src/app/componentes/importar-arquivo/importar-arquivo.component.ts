import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-importar-arquivo',
  templateUrl: './importar-arquivo.component.html',
  styleUrls: ['./importar-arquivo.component.css']
})
export class ImportarArquivoComponent implements OnInit {

  title = 'File-Upload-Save';
  selectedFiles!: FileList;
  test_selectedFiles!: FileList;
  private currentFileUpload = new File([], 'send')
  progress: { percentage: number } = { percentage: 0 };
  selectedFile = null;
  changeImage = false;


  constructor() { }

  ngOnInit(): void {
  }

  handleImport(event: any) {
    this.selectedFiles = event.target.files;
   // this.test_selectedFiles = (<HTMLInputElement>event.target).files;
  }

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
