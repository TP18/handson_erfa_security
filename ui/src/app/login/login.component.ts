import { Component, OnInit } from '@angular/core';
import { MsalBroadcastService, MsalService } from '@azure/msal-angular';
import { EventMessage, EventType, InteractionStatus } from '@azure/msal-browser';
import { Subject } from 'rxjs';
import { filter, takeUntil } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';

// Start Security 08
// Remove comments when activating Security

// type ProfileType = {
//   givenName?: string,
//   surname?: string,
//   userPrincipalName?: string,
//   id?: string
// }

// End Security 08

// Only for Security

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  // Start Security 09
  // Remove when activating Security

  ngOnInit(): void {
      
  }

  // End Security 09

  // Start Security 10 
  // Remove comments when activating Security

  // loginDisplay = this.authService.instance.getAllAccounts().length > 0;
  // profile!: ProfileType
  // private readonly _destroying$ = new Subject<void>()

  // constructor(private broadcastService: MsalBroadcastService, private authService: MsalService, private http: HttpClient) { }

  // ngOnInit(): void {

  //   this.broadcastService.msalSubject$
  //     .pipe(
  //       filter((msg: EventMessage) => msg.eventType === EventType.LOGIN_SUCCESS),
  //     )
  //     .subscribe((result: EventMessage) => {
  //       console.log(result);
  //     });

  //   this.broadcastService.inProgress$
  //     .pipe(
  //       filter((status: InteractionStatus) => status === InteractionStatus.None),
  //       takeUntil(this._destroying$)
  //     )
  //     .subscribe(() => {
  //       this.setLoginDisplay();
  //     })

  // }

  // setLoginDisplay() {
  //   this.loginDisplay = this.authService.instance.getAllAccounts().length > 0;
  //   if (this.loginDisplay) {
  //     this.getProfile()
  //   }
  // }

  // getProfile() {
  //   this.http.get('https://graph.microsoft.com/v1.0/me')
  //     .subscribe(profile => {
  //       this.profile = profile
  //     })
  // }

  // End Security 10 
}
