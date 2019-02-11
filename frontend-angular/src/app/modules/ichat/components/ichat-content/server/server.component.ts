import {Component, EventEmitter, Input, OnChanges, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ConnectionType} from 'core/enum/connection-type.enum';

@Component({
  selector: 'app-server',
  templateUrl: './server.component.html',
  styleUrls: ['./server.component.scss']
})
export class ServerComponent implements OnInit {
  @Input() iAgentServer: any;
  @Input() connectionType: ConnectionType;
  @Input() switchedItem: boolean;

  @Output() onServerChanged = new EventEmitter();
  @Output() onConnectionTypeChanged = new EventEmitter();
  @Output() onFinishedBuild = new EventEmitter<any>();

  cloudType = ConnectionType.CLOUD;
  iAgentServerType = ConnectionType.IAGENT_SERVER;

  servers: any = {};

  constructor() {
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
    console.log('servers', this.servers);
    if (this.connectionType === null || this.connectionType === undefined) {
      this.connectionType = ConnectionType.IAGENT_SERVER;
    }
    this.servers[this.connectionType].checked = true;

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
    this.onServerChanged.emit(rawValue);
    // }
  }

  finishedBuild(event) {
    this.onFinishedBuild.emit();

  }


}
