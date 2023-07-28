import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalcadastrodocumentoComponent } from './modalcadastrodocumento.component';

describe('ModalcadastrodocumentoComponent', () => {
  let component: ModalcadastrodocumentoComponent;
  let fixture: ComponentFixture<ModalcadastrodocumentoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalcadastrodocumentoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalcadastrodocumentoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
