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


@NgModule({
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,

// Start Security

    AppRoutingModule,
    MsalModule.forRoot( new PublicClientApplication({
      auth: {
        clientId: 'c74fd85a-cb4f-410b-9b3d-bb81ed1c971d',
        authority: 'https://login.microsoftonline.com/a9080dcf-8589-4cb6-a2e2-21398dc6c671',
        redirectUri: 'https://csa-baseapp-ui.azurewebsites.net/redirect'
      },
      cache: {
        cacheLocation: 'localStorage'
      }
    }), {
      interactionType: InteractionType.Redirect, // MSAL Guard Configuration
      authRequest: {
        scopes: ['api://35834eab-1ffd-45d3-90a7-0f2a4948740e/Call.Calculate']
      }
    }, {
      interactionType: InteractionType.Redirect, // MSAL Interceptor Configuration
      protectedResourceMap: new Map([ 
          ['https://csa-apim.azure-api.net/csa-baseapp-bff', ['api://35834eab-1ffd-45d3-90a7-0f2a4948740e/Call.Calculate']]
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
  ],

// End Security

  declarations: [
    AppComponent,
    TopBarComponent,
    FormComponent,

// Start Security

    LoginComponent

// End Security

  ],
  bootstrap: [
    AppComponent,

// Start Security

    MsalRedirectComponent

// End Security

  ]
})
export class AppModule {
}
