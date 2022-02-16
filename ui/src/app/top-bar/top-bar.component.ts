import { Component, Inject } from '@angular/core';
import { MsalGuardConfiguration, MsalService, MSAL_GUARD_CONFIG } from '@azure/msal-angular';
import { RedirectRequest } from '@azure/msal-browser';


@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.css']
})
export class TopBarComponent {

  // Start Security 06
  // Remove comments when activating Security

  // title = 'hands-on-erfa-baseapp';
  // isIframe = false;
  // loginDisplay = this.authService.instance.getAllAccounts().length > 0;

  // constructor(private authService: MsalService, @Inject(MSAL_GUARD_CONFIG) private msalGuardConfig: MsalGuardConfiguration) { }

  // ngOnInit() {
  //   this.isIframe = window !== window.parent && !window.opener;
  // }

  // login() {
  //   if (this.msalGuardConfig.authRequest) {
  //     this.authService.loginRedirect({ ...this.msalGuardConfig.authRequest } as RedirectRequest);
  //   } else {
  //     this.authService.loginRedirect();
  //   }
  //   this.setLoginDisplay();
  // }

  // setLoginDisplay() {
  //   this.loginDisplay = this.authService.instance.getAllAccounts().length > 0;
  // }

  // End Security 06 

}
