import {Component, EventEmitter, Input, OnChanges, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ConnectionType} from 'core/enum/connection-type.enum';

@Component({
  selector: 'app-server',
  templateUrl: './server.component.html',
  styleUrls: ['./server.component.scss']
})
export class ServerComponent implements OnInit, OnChanges {
  @Input() iAgentServer: any;
  @Input() connectionType: ConnectionType;

  @Output() onServerChanged = new EventEmitter();
  @Output() onConnectionTypeChanged = new EventEmitter();

  servers = [
    {
      id: 0,
      title: 'Eigenes iAgent System',
      checked: false,
    },
    {
      id: 1,
      title: 'Cloud',
      checked: false
    }
  ];

  constructor() {
  }

  ngOnInit() {
    if (this.connectionType === null || this.connectionType === undefined) {
      this.connectionType = ConnectionType.IAGENT_SERVER;
    }
    this.servers[this.connectionType].checked = true;

  }

  onSelectConnectionType(event) {
    this.connectionType = event.value;
    this.servers.forEach(server => {
      server.checked = server.id === this.connectionType;
    });
    this.onConnectionTypeChanged.emit(event.value);

  }

  serverChanged(rawValue) {
    this.onServerChanged.emit(rawValue);
  }

  ngOnChanges(): void {
    console.log('changes', this.iAgentServer);
  }


}
