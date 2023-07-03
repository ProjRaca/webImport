import { UsuarioComponent } from './componentes/usuario/usuario.component';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatMenuModule} from '@angular/material/menu';
import {MatButtonModule} from '@angular/material/button';
import {MatDialogModule} from '@angular/material/dialog';
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule, MAT_FORM_FIELD_DEFAULT_OPTIONS} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {MatDividerModule} from '@angular/material/divider';
import {MatSnackBarModule} from '@angular/material/snack-bar';

import { MenuComponent } from './componentes/menu/menu.component';
import { ModalComponent } from './componentes/modal/modal.component';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { ResponsavelComponent } from './componentes/responsavel/responsavel.component';
import { ConferenciaComponent } from './componentes/conferencia/conferencia.component';
import { DocumentosComponent } from './componentes/documentos/documentos.component';
import { ImportarArquivoComponent } from './componentes/importar-arquivo/importar-arquivo.component';
import { LoginComponent } from './layout/login/login.component';
import { PrincipalComponent } from './layout/principal/principal.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { UsuarioAutenticadoGuard } from './service/guard/usuario-autenticado.guard';
import { UsuarioNaoAutenticadoGuard } from './service/guard/usuario-nao-autenticado.guard';
import { MovimentacaoService } from './service/movimentacao-service.service';
import { UsuarioService } from './service/usuario.service';
import { CpfPipe } from './pipes/cpf.pipe';
import { CnpjPipe } from './pipes/cnpj.pipe';
import { MatriculaPipe } from './pipes/matricula.pipe';
import { MatSortModule } from '@angular/material/sort';
import { TokenInterceptor } from './service/interceptors/token.interceptor';
import { httpInterceptorProviders } from './service/interceptors';
import { ScackBarCustomComponent } from './componentes/scack-bar-custom/scack-bar-custom.component';


@NgModule({
  declarations: [
    AppComponent,
      MenuComponent,
      ModalComponent,
      UsuarioComponent,
      ResponsavelComponent,
      ConferenciaComponent,
      DocumentosComponent,
      ImportarArquivoComponent,
      LoginComponent,
      PrincipalComponent,
      CpfPipe,
      CnpjPipe,
      MatriculaPipe,
      ScackBarCustomComponent
   ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatMenuModule,
    MatButtonModule,
    MatDialogModule,
    MatCardModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    AppRoutingModule,
    MatDividerModule,
    FormsModule,
    ReactiveFormsModule,
    MatCheckboxModule,
    MatRadioModule,
    MatSelectModule,
    MatIconModule,
    MatTableModule,
    MatPaginatorModule,
    MatSnackBarModule,
    HttpClientModule,
    MatSortModule
  ],
  providers: [ httpInterceptorProviders,
    {provide: [MAT_FORM_FIELD_DEFAULT_OPTIONS, HTTP_INTERCEPTORS], useValue: {appearance: 'outline'},
      multi: true
  }, MovimentacaoService, UsuarioService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
