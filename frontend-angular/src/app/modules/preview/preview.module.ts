import {NgModule} from '@angular/core';

import {PreviewRoutingModule} from './preview-routing.module';
import {PreviewComponent} from './pages/preview.component';
import {SharedModule} from 'shared/shared.module';
import {ChatComponent} from './components/chat/chat.component';
import {SingleMessageComponent} from './components/single-message/single-message.component';
import {FormChatComponent} from './components/form-chat/form-chat.component';
import {MessengerComponent} from './components/messenger/messenger.component';
import {ButtonGroupChatComponent} from './components/button-group-chat/button-group-chat.component';

@NgModule({
  declarations: [
    PreviewComponent,
    ChatComponent,
    SingleMessageComponent,
    FormChatComponent,
    MessengerComponent,
    ButtonGroupChatComponent]
  ,
  imports: [
    SharedModule,
    PreviewRoutingModule
  ],
  entryComponents: [
    MessengerComponent
  ]
})
export class PreviewModule { }
