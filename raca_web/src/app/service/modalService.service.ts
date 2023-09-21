import { Injectable } from '@angular/core';
import { ModalComponent } from '../componentes/modal/modal.component';

@Injectable({
  providedIn: 'root'
})
export class ModalService {

  private modals: ModalComponent[] = [];

  add(modal: ModalComponent) {
      // ensure component has a unique id attribute
      if (this.modals.length == 0 || this.modals.find(x => x.id !== modal.id)) {
          this.modals.push(modal);
      }
  }

  remove(modal: ModalComponent) {
      // remove modal from array of active modals
      this.modals = this.modals.filter(x => x === modal);
  }

  open(id: string) {
      // open modal specified by id
      const modal = this.modals.find(x => x.id === id);

      if (!modal) {
          throw new Error(`modal '${id}' not found`);
      }

      modal.open();
  }

  close() {
      // close the modal that is currently open
      const modal = this.modals.find(x => x.isOpen);
      modal?.close();
  }

}
