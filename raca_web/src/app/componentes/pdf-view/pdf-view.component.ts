import { Component, Input, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { Sizes } from 'src/app/enums/sizes.enum';
import { ModalService } from 'src/app/service/modalService.service';

@Component({
  selector: 'pdf-view',
  templateUrl: './pdf-view.component.html',
  styleUrls: ['./pdf-view.component.css']
})
export class PdfViewComponent{

  public base64 = new Subject<string>();

  @Input()
  set base64File(texto: any){
    if(texto != undefined && texto.length > 0){
      this.setBase64File(texto);
    }
  }

  constructor(protected modalService: ModalService) { }

  getImagem(): string{
    return this.base64 as any;
  }
  setBase64File(value: any){
    this.base64 =  value ;
    this.modalService.open('modalPdf');
  }

  getSizeModal(): string{
    return Sizes.Middle.toString();
  }
}
