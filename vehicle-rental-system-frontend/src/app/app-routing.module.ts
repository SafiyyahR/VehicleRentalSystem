import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {IndexPage} from './pages/index/index.page';


const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component:IndexPage},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
