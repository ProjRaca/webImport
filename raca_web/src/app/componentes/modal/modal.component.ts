import { Component, ElementRef, Input, OnDestroy, OnInit } from '@angular/core';
import { Sizes } from 'src/app/enums/sizes.enum';
import { ModalService } from 'src/app/service/modalService.service';

@Component({
  selector: 'modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css']
})
export class ModalComponent implements OnInit, OnDestroy {

  @Input() id?: string
  @Input() sizeType!: string;

  isOpen = false
  private element: any



  constructor(private modalService: ModalService, private el: ElementRef) {
      this.element = el.nativeElement
  }

  ngOnInit() {
      // add self (this modal instance) to the modal service so it can be opened from any component
      this.modalService.add(this)

      // move element to bottom of page (just before </body>) so it can be displayed above everything else
      document.body.appendChild(this.element)

      // close modal on background click
      this.element.addEventListener('click', (el: any) => {
          if (el.target.className === 'modal') {
              this.close()
          }
      })
  }

  ngOnDestroy() {
      // remove self from modal service
      this.modalService.remove(this)

      // remove modal element from html
      this.element.remove()
  }

  open() {
      this.element.style.display = 'block'
      document.body.classList.add('modal-open')
      this.isOpen = true;
  }

  close() {
      this.element.style.display = 'none'
      document.body.classList.remove('modal-open')
      this.isOpen = false
  }

  setSize(size: string): string {
      switch(size){
        case Sizes.Middle.toString():
          return this.getMiddleCss()
        case Sizes.Small.toString():
          return this.getSmallCss()
        default:
          return this.getSmallCss()
      }
  }

  getMiddleCss(): string {
    console.log('definiu como middle')
    return "modal-body-midlle"
  }

  getSmallCss(): string {
    console.log('definiu como small')
    return "modal-body-small"
  }

  getCssClass(): string {
    console.log('retornou o getCssClass')
    return this.setSize(this.sizeType);
  }
}
