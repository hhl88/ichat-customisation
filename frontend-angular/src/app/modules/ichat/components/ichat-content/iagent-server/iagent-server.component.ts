import {AfterContentInit, AfterViewInit, ChangeDetectorRef, Component, EventEmitter, Input, OnChanges, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ConnectionType} from 'core/enum/connection-type.enum';
import {IAgentServerService} from 'ichat/services/iagent-server.service';

@Component({
  selector: 'app-iagent-server',
  templateUrl: './iagent-server.component.html',
  styleUrls: ['./iagent-server.component.scss'],
  providers: [IAgentServerService]
})
export class IAgentServerComponent implements OnInit, OnChanges {
  formIAgentServer: FormGroup;
  @Input() iAgentServer: any;
  @Input() switchedItem: boolean;

  @Output() onIAgentServerChanged = new EventEmitter();
  @Output() onFinishedBuild = new EventEmitter<any>();

  isProcessed = false;
  connected = false;
  isClicked = false;

  constructor(private iAgentService: IAgentServerService) {
  }

  ngOnInit() {
    this.reloadForm();
    this.formIAgentServer.valueChanges.subscribe(data => {
      this.isClicked = false;
      this.connected = false;
      this.isProcessed = false;

      this.onIAgentServerChanged.emit({
        data: data,
        isFormValid: !this.formIAgentServer.invalid
      });
    });
    this.onFinishedBuild.emit();

  }

  ngOnChanges() {
    this.reloadForm();
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

  reloadForm() {
    if (this.formIAgentServer) {
      this.formIAgentServer.reset();
    } else {
      this.initForm();
    }

    if (this.iAgentServer && this.iAgentServer.hasOwnProperty('address')) {

      Object.keys(this.iAgentServer).forEach(key => {
        if (this.formIAgentServer.get(key)) {
          this.formIAgentServer.controls[key].setValue(this.iAgentServer[key]);
        }
      });
    }
  }

  initForm() {
    this.formIAgentServer = new FormGroup({
      address: new FormControl('', [Validators.required]),
      userAPI: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
      clientId: new FormControl('', [Validators.required]),
      secret: new FormControl('', [Validators.required]),
    });
  }

}
