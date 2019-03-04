import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ConnectionType} from 'core/enum/connection-type.enum';
import {IAgentServer} from 'core/interfaces/iagent-server.interface';
import {IAgentServerService} from 'ichat/services/iagent-server.service';

@Component({
  selector: 'app-server',
  templateUrl: './server.component.html',
  styleUrls: ['./server.component.scss'],
  providers: [IAgentServerService]

})
export class ServerComponent implements OnInit {
  @Input() iAgentServer: any;
  @Input() connectionType: ConnectionType;
  @Input() switchedItem: boolean;

  @Output() onServerChanged = new EventEmitter();
  @Output() onConnectionTypeChanged = new EventEmitter();
  @Output() sizeFirstCol = new EventEmitter();

  cloudType = ConnectionType.CLOUD;
  iAgentServerType = ConnectionType.IAGENT_SERVER;

  servers: any = {};

  isProcessed = false;
  connected = false;
  isClicked = false;

  currentIAgentServer: IAgentServer = null;

  constructor(private iAgentService: IAgentServerService) {
    this.servers[this.iAgentServerType] = {
      title: 'Eigenes iAgent System',
      checked: false
    };
    this.servers[this.cloudType] = {
      title: 'Cloud',
      checked: false
    };
  }

  ngOnInit() {
    if (this.connectionType === null || this.connectionType === undefined) {
      this.connectionType = ConnectionType.IAGENT_SERVER;
    }
    this.servers[this.connectionType].checked = true;
    // this.sizeFirstCol.emit()
  }

  onSelectConnectionType(event) {
    this.connectionType = event.value;
    if (event.value === this.cloudType) {
      this.servers[this.cloudType].checked = true;
      this.servers[this.iAgentServerType].checked = false;
    } else if (event.value === this.iAgentServerType) {
      this.servers[this.cloudType].checked = false;
      this.servers[this.iAgentServerType].checked = true;
    }
    this.onConnectionTypeChanged.emit(event.value);
  }

  serverChanged(rawValue) {
    // if (!this.switchedItem) {
    this.currentIAgentServer = JSON.parse(JSON.stringify(rawValue.data));
    this.onServerChanged.emit(rawValue);
    // }
  }

  onResizeFirstCol(rawValue) {
    this.sizeFirstCol.emit(rawValue);
  }

  checkServer() {
    this.isClicked = true;
    this.connected = false;
    this.isProcessed = false;
    if (this.currentIAgentServer) {
      this.iAgentService.fetchServer(this.currentIAgentServer).subscribe(res => {
        this.isProcessed = true;
        if (res && res.hasOwnProperty('access_token')) {
          this.connected = true;
        }
      }, error1 => this.isProcessed = true);
    }

  }


}
