import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MsalGuard } from '@azure/msal-angular';
import { FormComponent } from './form/form.component';
import { LoginComponent } from './login/login.component';

// Only for Security

const routes: Routes = [
  {
    path: 'form',
    component: FormComponent,
    canActivate: [MsalGuard]
  },
  {
    path: '',
    component: LoginComponent
  },
];

const isIframe = window !== window.parent && !window.opener;

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    initialNavigation: !isIframe ? 'enabled' : 'disabled' // Don't perform initial navigation in iframes
  })],
  exports: [RouterModule]
})
export class AppRoutingModule { }