import {Component, EventEmitter, HostListener, Input, OnChanges, OnDestroy, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Subscription} from 'rxjs';
import {IAgentServerService} from 'ichat/services/iagent-server.service';

@Component({
  selector: 'app-iagent-server',
  templateUrl: './iagent-server.component.html',
  styleUrls: ['./iagent-server.component.scss'],
  providers: [IAgentServerService]
})
export class IAgentServerComponent implements OnInit, OnChanges, OnDestroy {
  formIAgentServer: FormGroup;
  @Input() iAgentServer: any;
  @Input() oldIAgentServer: any;
  @Input() switchedItem: boolean;


  @Output() onIAgentServerChanged = new EventEmitter();
  @Output() sizeFirstCol = new EventEmitter();

  sub: Subscription;

  constructor() {
  }

  ngOnInit() {
    this._reloadForm();
    this.sizeFirstCol.emit(document.getElementsByClassName('first-col')[0].clientWidth);
  }

  ngOnChanges() {
    if (!!this.oldIAgentServer && !!this.iAgentServer) {
      const t = Object.keys(this.oldIAgentServer).filter(key => this.oldIAgentServer[key] !== this.iAgentServer[key]);
      if (Object.keys(this.oldIAgentServer).filter(key => this.iAgentServer[key] !== this.oldIAgentServer[key]).length > 0) {
        this._reloadForm();
      }
    }
  }

  ngOnDestroy(): void {
    if (this.sub) {
      this.sub.unsubscribe();
    }
  }

  @HostListener('window:resize', ['$event'])
  onResize(event) {
    this.sizeFirstCol.emit(document.getElementsByClassName('first-col')[0].clientWidth);
  }

  isFormValid() {
    return !this.formIAgentServer.invalid;
  }

  private _reloadForm() {
    if (this.formIAgentServer) {
      this.sub.unsubscribe();
    }
    this.formIAgentServer = new FormGroup({});
    if (!!this.iAgentServer) {
      this.oldIAgentServer = JSON.parse(JSON.stringify(this.iAgentServer));
      Object.keys(this.iAgentServer).forEach(key => {
        this.formIAgentServer.addControl(key, new FormControl(this.iAgentServer[key]));
      });

    }
    this.sub = this.formIAgentServer.valueChanges.subscribe(data => {
      this.onIAgentServerChanged.emit({
        data: data,
        isFormValid: this.isFormValid()
      });
    });
  }
}
