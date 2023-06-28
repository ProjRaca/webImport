import { Component, ViewEncapsulation } from '@angular/core';
import { ModalService } from 'src/app/service/modalService.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],
  encapsulation: ViewEncapsulation.None
 })
export class MenuComponent  {

  constructor(protected modalService: ModalService) {}

  ngOnInit(): void {
  }

  csvInputChange(fileInputEvent: any) {

  }
}
