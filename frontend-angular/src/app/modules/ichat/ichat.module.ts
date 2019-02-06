import {NgModule} from '@angular/core';

import {IChatRoutingModule} from './ichat-routing.module';
import {SharedModule} from 'shared/shared.module';
import {IChatComponent} from './pages/ichat.component';
import {SidebarComponent} from './components/sidebar/sidebar.component';
import {IChatContentComponent} from './components/ichat-content/ichat-content.component';
import {ItemListComponent} from './components/item-list/item-list.component';
import {IChatService} from 'ichat/services/ichat.service';
import {FrontendContentComponent} from './components/ichat-content/frontend-content/frontend-content.component';
import {LayoutContentComponent} from './components/ichat-content/layout-content/layout-content.component';
import {IAgentServerComponent} from 'ichat/components/ichat-content/iagent-server/iagent-server.component';

@NgModule({
  declarations: [
    IChatComponent,
    SidebarComponent,
    IChatContentComponent,
    ItemListComponent,
    FrontendContentComponent,
    LayoutContentComponent,
    IAgentServerComponent
  ],
  imports: [
    SharedModule,
    IChatRoutingModule
  ],
  providers: [
    IChatService
  ]
})
export class IChatModule {
}
