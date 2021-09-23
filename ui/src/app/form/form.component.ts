import {Component, OnInit} from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {CalculateResponse} from "../dto/CalculateResponse";

import { environment } from '../../environments/environment';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  serviceUrl = environment.url;

  result: CalculateResponse | undefined;
  info: String | undefined;

  checkoutForm = this.formBuilder.group({
    customerId: '88'
  });

  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient) {
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    this.callService();
    this.checkoutForm.reset();
  }

  callService() {
    let customerId = this.checkoutForm.value.customerId;
    //todo check if nummeric
    if (customerId <= 0 || customerId > 100) {
      alert("Bitte gib eine Kundennummer zwischen 1 und 100 ein");
    }
    console.log("calling service for risk calculation for customerId", customerId);

    this.http.post<CalculateResponse>(this.serviceUrl, customerId)
      .subscribe(data => {
        console.log("result", data);
        this.result = {...data};
        this.produceNiceInformation(data.risk);
      });
  }

  produceNiceInformation(risk: string) {
    let riskAsNumber = Number(risk);
    if (riskAsNumber <= 20) {
      this.info = "YOLO";
    } else if (riskAsNumber <= 50) {
      this.info = "Ab sofort keine Hugs & Munzini mehr!";
    } else if (riskAsNumber <= 70) {
      this.info = "<a href='https://youtu.be/yGX4u5XfzVs' target='_blank'>Bleiben Sie zuhause. >>Link<<</a>";
    } else if (riskAsNumber <= 100) {
      this.info = "<a href='https://www.google.ch/search?q=notar' target='_blank'>Notar gefällig? Klick hier</a>";
    }
  }
}
