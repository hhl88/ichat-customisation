import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {DomSanitizer} from '@angular/platform-browser';
import {MatIconRegistry} from '@angular/material';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  animations: []
})
export class AppComponent implements OnInit {
  title = 'IChat Customisation';

  isOnHomepage: boolean;

  constructor(
              private matIconRegistry: MatIconRegistry,
              private domSanitizer: DomSanitizer,
              public router: Router) {

  }

  ngOnInit(): void {
    this.router.events.subscribe(val => {
      this.isOnHomepage = this.router.url === '/';
    });
    this.matIconRegistry.addSvgIcon(
      'item-cart',
      this.domSanitizer.bypassSecurityTrustResourceUrl('assets/img/svg/packaging-box.svg')
    );
  }

  closeModal() {
  }
}
