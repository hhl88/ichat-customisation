import {enableProdMode, MissingTranslationStrategy} from '@angular/core';
import {platformBrowserDynamic} from '@angular/platform-browser-dynamic';

import {AppModule} from './app/app.module';
import {environment} from 'environments/environment';

if (environment.production) {
  enableProdMode();
}

// const translations = require(`raw-loader!./i18n/messages.${localStorage.getItem('locale')}.xlf`);


platformBrowserDynamic().bootstrapModule(AppModule, {
  missingTranslation: MissingTranslationStrategy.Error,
})
  .catch(err => console.log(err));
