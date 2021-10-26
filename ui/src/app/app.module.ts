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

// Remove when activating Security
import {RouterModule} from '@angular/router';
// Done remove

@NgModule({
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,

// Remove when activating Security
    RouterModule.forRoot([
      {path: '', component: FormComponent}
    ])
// Done remove

// Start Security
/*
    AppRoutingModule,
    MsalModule.forRoot( new PublicClientApplication({
      auth: {
        clientId: 'tbd client id from ui',
        authority: 'tbd https://login.microsoftonline.com/ipt-tenant-id',
        redirectUri: 'tbd redirect url from ui'
      },
      cache: {
        cacheLocation: 'localStorage'
      }
    }), {
      interactionType: InteractionType.Redirect, // MSAL Guard Configuration
      authRequest: {
        scopes: ['tbd scope from bff']
      }
    }, {
      interactionType: InteractionType.Redirect, // MSAL Interceptor Configuration
      protectedResourceMap: new Map([ 
          ['tbd service url from bff', ['tbd scope from bff']]
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
*/
// End Security
  ],
  declarations: [
    AppComponent,
    TopBarComponent,
    FormComponent,

// Start Security
/*
    LoginComponent
*/
// End Security

  ],
  bootstrap: [
    AppComponent,

// Start Security
/*
    MsalRedirectComponent
*/
// End Security

  ]
})
export class AppModule {
}
