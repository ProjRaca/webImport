import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ImportarArquivoComponent } from './importar-arquivo.component';

describe('ImportarArquivoComponent', () => {
  let component: ImportarArquivoComponent;
  let fixture: ComponentFixture<ImportarArquivoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ImportarArquivoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ImportarArquivoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
