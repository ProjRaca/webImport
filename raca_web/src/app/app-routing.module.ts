import { UsuarioComponent } from './componentes/usuario/usuario.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ResponsavelComponent } from './componentes/responsavel/responsavel.component';
import { ConferenciaComponent } from './componentes/conferencia/conferencia.component';
import { DocumentosComponent } from './componentes/documentos/documentos.component';
import { ImportarArquivoComponent } from './componentes/importar-arquivo/importar-arquivo.component';
import { UsuarioNaoAutenticadoGuard } from './service/guard/usuario-nao-autenticado.guard';
import { LoginComponent } from './layout/login/login.component';
import { UsuarioAutenticadoGuard } from './service/guard/usuario-autenticado.guard';
import { PrincipalComponent } from './layout/principal/principal.component';


const routes: Routes = [
  { path: 'login', component: LoginComponent, canActivate: [UsuarioNaoAutenticadoGuard]},
  {
    path: '', component: PrincipalComponent, canActivate: [UsuarioAutenticadoGuard],
    children: [
      // { path: '', component: AppComponent },
      {path: 'usuario',component: UsuarioComponent},
      {path: 'responsavel',component: ResponsavelComponent},
      {path: 'conferencia',component: ConferenciaComponent},
      {path: 'documentos',component: DocumentosComponent},
      {path: 'importarArquivo',component: ImportarArquivoComponent}
    ],
  }
]
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
