import {NgModule} from '@angular/core';

import {IChatRoutingModule} from './ichat-routing.module';
import {SharedModule} from 'shared/shared.module';
import {IChatComponent} from './pages/ichat.component';
import {SidebarComponent} from './components/sidebar/sidebar.component';
import {IChatContentComponent} from './components/ichat-content/ichat-content.component';
import {ItemListComponent} from './components/item-list/item-list.component';
import {FrontendContentComponent} from './components/ichat-content/frontend-content/frontend-content.component';
import {LayoutContentComponent} from './components/ichat-content/layout-content/layout-content.component';
import {ImageCropperModule} from 'ngx-image-cropper';
import { UserEditPasswordComponent } from './components/user-edit-password/user-edit-password.component';
import {UserService} from 'core/services/user.service';
import {IChatService} from 'ichat/services/ichat.service';
import { MessageBubbleComponent } from './components/ichat-content/layout-content/message-bubble/message-bubble.component';
import {DemandInfoComponent} from 'ichat/components/ichat-content/frontend-content/demand-info/demand-info.component';
import {ServerComponent} from 'ichat/components/ichat-content/frontend-content/server/server.component';
import {IAgentServerComponent} from 'ichat/components/ichat-content/frontend-content/iagent-server/iagent-server.component';

@NgModule({
  declarations: [
    IChatComponent,
    SidebarComponent,
    IChatContentComponent,
    ItemListComponent,
    FrontendContentComponent,
    LayoutContentComponent,
    IAgentServerComponent,
    ServerComponent,
    DemandInfoComponent,
    UserEditPasswordComponent,
    MessageBubbleComponent
  ],
  imports: [
    SharedModule,
    IChatRoutingModule,
    ImageCropperModule
  ],
  providers: [
    IChatService,
    UserService
  ]
})
export class IChatModule {
}
