import { UsuarioComponent } from './componentes/usuario/usuario.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ResponsavelComponent } from './componentes/responsavel/responsavel.component';
import { ConferenciaComponent } from './componentes/conferencia/conferencia.component';
import { DocumentosComponent } from './componentes/documentos/documentos.component';


const routes: Routes = [
  {
    path: '',
    redirectTo: '',
    pathMatch: 'full'
  },
  {path: 'usuario',component: UsuarioComponent},
  {path: 'responsavel',component: ResponsavelComponent},
  {path: 'conferencia',component: ConferenciaComponent},
  {path: 'documentos',component: DocumentosComponent}
]
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
