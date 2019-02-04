import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {LOGIN_PAGE, SIGN_UP_PAGE} from 'core/constants/routing.constants';

@Component({
  selector: 'app-entry',
  templateUrl: './entry.component.html',
  styleUrls: ['./entry.component.scss']
})
export class EntryComponent implements OnInit {
  constructor(private route: Router) { }

  ngOnInit() {
    console.log('entry')
    this.navigateToSignIn();
  }

 private navigateToSignIn() {
    this.route.navigate([LOGIN_PAGE]);
 }

  private navigateToSignUp() {
  this.route.navigate([SIGN_UP_PAGE]);
  }
}
