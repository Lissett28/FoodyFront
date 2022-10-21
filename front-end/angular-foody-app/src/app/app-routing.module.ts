import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from './component/about/about.component';
import { ContactComponent } from './component/contact/contact.component';
import { HomepageComponent } from './component/homepage/homepage.component';
import { LoginComponent } from './component/login/login.component';
import { AuthenticationGuard } from './guard/authentication.guard';

const routes: Routes = [
  // now lets set the guard at the begining 
  {path:'',canActivate:[AuthenticationGuard],children:[

  ]},
  // by defualt we should be route to homepage
  {path: 'login', component: LoginComponent},
  {path: 'explore', component: HomepageComponent},
  {path: 'contact', component: ContactComponent},
  {path: 'about', component: AboutComponent},
  {path: '**', component: HomepageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
