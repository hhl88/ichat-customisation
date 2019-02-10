import {AfterViewInit, Component, Input, NgZone, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {Store} from '@ngrx/store';
import * as fromRoot from 'store/reducers';
import {Frontend} from 'core/interfaces/frontend.interface';
import {ConnectionType} from 'core/enum/connection-type.enum';
import {IChatService} from 'ichat/services/ichat.service';
import {DefaultDemandInfo} from 'core/interfaces/demand-info.interface';

@Component({
  selector: 'app-frontend-content',
  templateUrl: './frontend-content.component.html',
  styleUrls: ['./frontend-content.component.scss']
})
export class FrontendContentComponent implements OnInit, OnChanges {
  @Input() frontEnd: Frontend;
  step = 0;
  isServerValid: boolean;
  isDemandInfoListValid: boolean;
  selectedFrontend: Frontend = null;

  constructor(private store: Store<fromRoot.State>,
              private iChatService: IChatService,
              private zone: NgZone) {

  }

  ngOnInit() {
    this._setFrontend();
  }


  ngOnChanges() {
    if (this.selectedFrontend === null || this.frontEnd.index !== this.selectedFrontend.index) {
      this.step = 0;
      this._setFrontend();
    }

  }

  private _setFrontend() {
    this.selectedFrontend = JSON.parse(JSON.stringify(this.frontEnd));
    if (!this.selectedFrontend.demandInfo) {
      this.selectedFrontend.demandInfo = DefaultDemandInfo;
    }
  }

  prevStep() {
    this.zone.run(() => {
      this.step--;
    });
  }

  nextStep() {
    this.step++;
  }


  demandInfoListChanged(rawValue) {
    console.log('demandInfo', rawValue);
    this.selectedFrontend.demandInfo.demandInfoList = rawValue.data;
    this.isDemandInfoListValid = rawValue.isFormValid;
  }

  serverChanged(rawValue) {
    if (this.selectedFrontend.connectionType === ConnectionType.IAGENT_SERVER) {
      this.selectedFrontend.iAgentServer = rawValue.data;
    }
    this.isServerValid = rawValue.isFormValid;
  }

  connectionTypeChanged(newType) {
    this.selectedFrontend.connectionType = newType;
  }


  submitCurrent() {
    if (this.selectedFrontend.id) {
      this.iChatService.updateFrontEnd(this.selectedFrontend.id, this.selectedFrontend).subscribe(res => {
        console.log('update frontend', res);
      }, error1 => {
        console.log('error update', error1);
      });
    } else {
      this.iChatService.createFrontEnd(this.selectedFrontend).subscribe(res => {
        console.log('create Frontend', res);
      }, error1 => {
        console.log('error', error1);
      });
    }
  }

  cancelCurrent() {

  }


}
