import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ConnectionType} from 'core/enum/connection-type.enum';
import {IAgentServerService} from 'ichat/services/iagent-server.service';

@Component({
  selector: 'app-iagent-server',
  templateUrl: './iagent-server.component.html',
  styleUrls: ['./iagent-server.component.scss'],
  providers: [IAgentServerService]
})
export class IAgentServerComponent implements OnInit {
  formIAgentServer: FormGroup;
  @Input() iAgentServer: any;

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
  isProcessed = false;
  connected = false;
  isClicked = false;

  constructor(private iAgentService: IAgentServerService) {
  }

  ngOnInit() {
    console.log('iAgentserver', this.iAgentServer);
    this.formIAgentServer = new FormGroup({
      address: new FormControl('', [Validators.required]),
      userAPI: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
      clientId: new FormControl('', [Validators.required]),
      secret: new FormControl('', [Validators.required]),
    });
    if (this.iAgentServer && this.iAgentServer.hasOwnProperty('address')) {

      Object.keys(this.iAgentServer).forEach(key => {
        if (this.formIAgentServer.get(key)) {
          this.formIAgentServer.controls[key].setValue(this.iAgentServer[key]);
        }
      });
    }
    this.formIAgentServer.valueChanges.subscribe(data => {
      this.isClicked = false;
      this.connected = false;
      this.isProcessed = false;

      this.iAgentServer = this.formIAgentServer.getRawValue();
      this.onIAgentServerChanged.emit({
        data: this.iAgentServer,
        isFormValid: !this.formIAgentServer.invalid
      });
    });

  }

  checkServer() {
    this.isClicked = true;
    this.connected = false;
    this.iAgentService.fetchServer(this.formIAgentServer.getRawValue()).subscribe(res => {
      this.isProcessed = true;
      if (res && res.hasOwnProperty('access_token')) {
        this.connected = true;
      }
      console.log('connected', this.connected);

    }, error1 => this.isProcessed = true);
  }


}
