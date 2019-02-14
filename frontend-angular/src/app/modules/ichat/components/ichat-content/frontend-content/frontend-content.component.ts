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
  isServerValid = false;
  isDemandInfoListValid = true;
  selectedFrontend: Frontend = null;
  switchedItem: boolean;

  constructor(private store: Store<fromRoot.State>,
              private iChatService: IChatService) {

  }

  ngOnInit() {
    this.switchedItem = false;
    this._setFrontend();
  }


  ngOnChanges() {
    if (this.selectedFrontend === null || this.frontEnd.index !== this.selectedFrontend.index) {
      this.step = 0;
      this._setFrontend();
      this.switchedItem = true;
    } else {
      this.switchedItem = false;
    }
  }

  private _setFrontend() {
    this.selectedFrontend = JSON.parse(JSON.stringify(this.frontEnd));
    if (!this.selectedFrontend.demandInfo) {
      this.selectedFrontend.demandInfo = DefaultDemandInfo;
    }
  }

  prevStep() {
    this.step--;
  }

  nextStep() {
    this.step++;
  }


  demandInfoListChanged(rawValue) {
    this.selectedFrontend.demandInfo.demandInfoList = rawValue.data;
    this.isDemandInfoListValid = rawValue.isFormValid;
  }

  serverChanged(rawValue) {
    if (this.selectedFrontend.connectionType === ConnectionType.IAGENT_SERVER) {
      this.selectedFrontend.iAgentServer = JSON.parse(JSON.stringify(rawValue.data));
    }

    this.isServerValid = rawValue.isFormValid;
  }

  onResizeFirstCol(rawValue) {
    console.log(rawValue);
    const table = document.getElementById('frontend-settings') as HTMLTableElement;
    for (let i = 0; i < table.rows.length; i++) {
      const firstCol = table.rows[i].cells[0];
      firstCol.style.width = rawValue + 'px';
    }
  }

  connectionTypeChanged(newType) {
    this.selectedFrontend.connectionType = newType;
  }


  submitCurrent() {
    console.log('submit', this.selectedFrontend);
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
