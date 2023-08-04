import { Component, Input, OnInit } from '@angular/core';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-pdf-view',
  templateUrl: './pdf-view.component.html',
  styleUrls: ['./pdf-view.component.css']
})
export class PdfViewComponent{

  public base64 = new Subject<string>();

  @Input()
  set base64File(texto: Subject<string>){
    this.base64 =  texto ;
  }

  constructor() { }

  getImagem(): string{
    return this.base64 as any;
  }
}
