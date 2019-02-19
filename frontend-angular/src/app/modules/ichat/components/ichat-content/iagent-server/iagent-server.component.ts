import {

  Component,
  EventEmitter,
  HostListener,
  Input,
  OnChanges,
  OnInit,
  Output
} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Subscription} from 'rxjs';
import {IAgentServerService} from 'core/services/iagent-server.service';

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
  @Output() sizeFirstCol = new EventEmitter();

  sub: Subscription;
  isReloadingForm = false;

  constructor(private iAgentService: IAgentServerService) {
  }

  ngOnInit() {
    this.isReloadingForm = false;
    this._reloadForm();
    this._subscribe();
    this.sizeFirstCol.emit(document.getElementsByClassName('first-col')[0].clientWidth);
  }

  ngOnChanges() {
    if (!this.isReloadingForm) {
      this._reloadForm();
      this._subscribe();
    }
  }

  @HostListener('window:resize', ['$event'])
  onResize(event) {
    this.sizeFirstCol.emit(document.getElementsByClassName('first-col')[0].clientWidth);

  }

  isFormValid() {
    return !this.formIAgentServer.invalid;
  }

  /*checkServer() {
    this.isClicked = true;
    this.connected = false;
    this.iAgentService.fetchServer(this.formIAgentServer.getRawValue()).subscribe(res => {
      this.isProcessed = true;
      if (res && res.hasOwnProperty('access_token')) {
        this.connected = true;
      }
    }, error1 => this.isProcessed = true);
  }*/

  private _reloadForm() {
    if (!this.isReloadingForm) {
      this.isReloadingForm = true;
      if (this.formIAgentServer) {
        this.sub.unsubscribe();
        this.formIAgentServer.reset();
      } else {
        this._initForm();
      }

      if (this.iAgentServer && this.iAgentServer.hasOwnProperty('address')) {

        Object.keys(this.iAgentServer).forEach(key => {
          if (this.formIAgentServer.get(key)) {
            this.formIAgentServer.controls[key].setValue(this.iAgentServer[key]);
          }
        });
      }
      this.onIAgentServerChanged.emit({
        data: this.formIAgentServer.getRawValue(),
        isFormValid: this.isFormValid()
      });
      this.isReloadingForm = false;
    }
  }

  private _initForm() {
    this.formIAgentServer = new FormGroup({
      address: new FormControl('', [Validators.required]),
      userAPI: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
      clientId: new FormControl('', [Validators.required]),
      secret: new FormControl('', [Validators.required]),
    });
  }

  private _subscribe() {
    this.sub = this.formIAgentServer.valueChanges.subscribe(data => {
      /*    this.isClicked = false;
          this.connected = false;
          this.isProcessed = false;*/
      if (this.isFormValid()) {
        this.onIAgentServerChanged.emit({
          data: data,
          isFormValid: this.isFormValid()
        });
      }
    });
  }


}
