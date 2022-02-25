import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import {ReactiveFormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {TopBarComponent} from './top-bar/top-bar.component';
import {FormComponent} from './form/form.component';
import { LoginComponent } from './login/login.component';
import { AppRoutingModule } from './app-routing.module';
import { MsalGuard, MsalInterceptor, MsalRedirectComponent, MsalModule } from '@azure/msal-angular';
import { InteractionType, PublicClientApplication } from '@azure/msal-browser';

// Start Security 01
// Remove when activating Security

//import {RouterModule} from '@angular/router';

// End Security 01

@NgModule({
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,

// Start Security 02
// Remove when activating Security

//     RouterModule.forRoot([
//       {path: '', component: FormComponent}
//     ])

// End Security 02

// Start Security 03
// Remove comments when activating Security


   AppRoutingModule,
   MsalModule.forRoot( new PublicClientApplication({
     auth: {
       clientId: 'bae89acd-224f-40b8-a0cf-9904bda64849',
       authority: 'https://login.microsoftonline.com/a9080dcf-8589-4cb6-a2e2-21398dc6c671',
       redirectUri: 'https://tpe-ui-app.azurewebsites.net'
     },
     cache: {
       cacheLocation: 'localStorage'
     }
   }), {
     interactionType: InteractionType.Redirect, // MSAL Guard Configuration
     authRequest: {
       scopes: ['write.all']
     }
   }, {
     interactionType: InteractionType.Redirect, // MSAL Interceptor Configuration
     protectedResourceMap: new Map([
         ['https://graph.microsoft.com/v1.0/me', ['user.read']],
         ['https://tpe-bff-app.azurewebsites.net', ['api://1e7dc472-262c-42a1-9b4f-83b1a98e3d0e/Call.Calculate']]
     ])
   })
 ],
 providers: [
   {
     provide: HTTP_INTERCEPTORS,
     useClass: MsalInterceptor,
     multi: true
   },
   MsalGuard

// End Security 03

  ],
  declarations: [
    AppComponent,
    TopBarComponent,
    FormComponent,

// Start Security 04
// Remove comments when activating Security

    LoginComponent

// End Security 04

  ],
  bootstrap: [
    AppComponent,

// Start Security 05
// Remove comments when activating Security

    MsalRedirectComponent

// End Security 05

  ]
})
export class AppModule {
}
