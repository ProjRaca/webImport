import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AutocompleteResponsavelComponent } from './autocomplete-responsavel.component';

describe('AutocompleteResponsavelComponent', () => {
  let component: AutocompleteResponsavelComponent;
  let fixture: ComponentFixture<AutocompleteResponsavelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AutocompleteResponsavelComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AutocompleteResponsavelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
