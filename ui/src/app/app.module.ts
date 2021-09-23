import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {RouterModule} from '@angular/router';
import {HttpClientModule} from '@angular/common/http';
import {ReactiveFormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {TopBarComponent} from './top-bar/top-bar.component';
import {FormComponent} from './form/form.component';


@NgModule({
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule.forRoot([
      {path: '', component: FormComponent}
    ])
  ],
  declarations: [
    AppComponent,
    TopBarComponent,
    FormComponent
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule {
}
