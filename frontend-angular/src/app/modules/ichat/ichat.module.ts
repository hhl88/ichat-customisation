import {NgModule} from '@angular/core';

import {IChatRoutingModule} from './ichat-routing.module';
import {SharedModule} from 'shared/shared.module';
import {IChatComponent} from './pages/ichat.component';
import {SidebarComponent} from './components/sidebar/sidebar.component';
import {IChatContentComponent} from './components/ichat-content/ichat-content.component';
import {ItemListComponent} from './components/item-list/item-list.component';
import {FrontendContentComponent} from './components/ichat-content/frontend-content/frontend-content.component';
import {LayoutContentComponent} from './components/ichat-content/layout-content/layout-content.component';
import {IAgentServerComponent} from 'ichat/components/ichat-content/iagent-server/iagent-server.component';
import { ServerComponent } from './components/ichat-content/server/server.component';
import { DemandInfoComponent } from './components/ichat-content/demand-info/demand-info.component';
import {ImageCropperModule} from 'ngx-image-cropper';
import { UserEditPasswordComponent } from './components/user-edit-password/user-edit-password.component';
import {UserService} from 'core/services/user.service';
import {IChatService} from 'ichat/services/ichat.service';

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
    UserEditPasswordComponent
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
