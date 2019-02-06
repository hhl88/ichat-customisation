import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ConnectionType} from 'core/enum/connection-type.enum';

@Component({
  selector: 'app-iagent-server',
  templateUrl: './iagent-server.component.html',
  styleUrls: ['./iagent-server.component.scss']
})
export class IAgentServerComponent implements OnInit {
  formIAgentServer: FormGroup;
  @Input() iAgentServer: any;
  @Input() connectionType: ConnectionType;

  @Output() onIAgentServerChanged = new EventEmitter();
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
    this.formIAgentServer = new FormGroup({
      address: new FormControl('', [Validators.required]),
      userAPI: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
      clientId: new FormControl('', [Validators.required]),
      secret: new FormControl('', [Validators.required]),
    });
    if (this.iAgentServer && this.iAgentServer.hasOwnProperty('address')) {
      Object.keys(this.iAgentServer).forEach(key => this.formIAgentServer.controls[key].setValue(this.iAgentServer[key]));
    }
    this.formIAgentServer.valueChanges.subscribe(data => {
      this.iAgentServer = this.formIAgentServer.getRawValue();
      this.onIAgentServerChanged.emit({
        data: this.iAgentServer,
        isFormValid: !this.formIAgentServer.invalid
      });
    });

  }

  checkServer() {

  }

  onSelectConnectionType(event) {
    this.connectionType = event.value;
    this.servers.forEach(server => {
      server.checked = server.id === this.connectionType;
    });

  }

}
