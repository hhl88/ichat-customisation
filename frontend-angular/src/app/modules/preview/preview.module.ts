import { NgModule } from '@angular/core';

import { PreviewRoutingModule } from './preview-routing.module';
import { PreviewComponent } from './pages/preview.component';
import {SharedModule} from 'shared/shared.module';
import { ChatComponent } from './components/chat/chat.component';
import { SingleMessageComponent } from './components/single-message/single-message.component';

@NgModule({
  declarations: [
    PreviewComponent,
    ChatComponent,
    SingleMessageComponent]
  ,
  imports: [
    SharedModule,
    PreviewRoutingModule
  ]
})
export class PreviewModule { }
