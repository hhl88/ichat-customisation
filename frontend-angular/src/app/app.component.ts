import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, CanActivate, Router, RoutesRecognized} from '@angular/router';
import {DomSanitizer} from '@angular/platform-browser';
import {MatIconRegistry} from '@angular/material';
import {AuthGuard} from 'core/guard/auth.guard';
import {AuthService} from 'core/authentication/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  animations: []
})
export class AppComponent implements OnInit {
  title = 'IChat Customisation';

  isOnHomepage: boolean;

  constructor(private matIconRegistry: MatIconRegistry,
              private authService: AuthService,
              private authGuard: AuthGuard,
              public router: Router,
              private route: ActivatedRoute) {

  }

  ngOnInit(): void {
    if(this.authService.getToken()) {
      this.router.events.subscribe(event => {
        if (event instanceof RoutesRecognized) {
          console.log(event)
          this.guardRoute(event);
        }
      });
    } else {
      this.router.navigate(['']);
    }


  }

  private guardRoute(event: RoutesRecognized): void {
    if (this.isPublic(event)) {
      console.log('public');
      return;
    }

    if (!this.callCanActivate(event, this.authGuard)) {
      console.log('callCanActivate');
      return;
    }

  }

  private callCanActivate(event: RoutesRecognized, guard: CanActivate) {
    console.log('snapshot', this.route.snapshot);
    console.log('event', event);
    return guard.canActivate(this.route.snapshot, event.state);
  }

  private isPublic(event: RoutesRecognized) {
    return event.state.root.firstChild.data.isPublic;
  }
}
