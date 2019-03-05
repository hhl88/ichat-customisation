import {AfterContentChecked, Component, EventEmitter, Input, OnChanges, OnInit, Output, ViewChild} from '@angular/core';
import {Store} from '@ngrx/store';
import * as fromRoot from 'store/reducers';
import {Frontend} from 'core/interfaces/frontend.interface';
import {ConnectionType} from 'core/enum/connection-type.enum';
import {IChatService} from 'ichat/services/ichat.service';
import {DefaultDemandInfo} from 'core/interfaces/demand-info.interface';
import {SwalComponent} from '@toverux/ngx-sweetalert2';
import {CurrentItemSelectedAction, FrontEndListAddAction, FrontendListUpdateItemAction} from 'store/actions/ichat';

@Component({
  selector: 'app-frontend-content',
  templateUrl: './frontend-content.component.html',
  styleUrls: ['./frontend-content.component.scss']
})
export class FrontendContentComponent implements OnInit, OnChanges, AfterContentChecked {
  @Input() frontEnd: Frontend;
  step = 0;
  isServerValid = true;
  isDemandInfoListValid = true;
  isValid = false;
  selectedFrontend: Frontend = null;
  switchedItem: boolean;
  @Output() onUpdateFrontend = new EventEmitter();
  @ViewChild('frontendCreatedSwal') private frontendCreatedSwal: SwalComponent;
  @ViewChild('frontendUpdatedSwal') private frontendUpdatedSwal: SwalComponent;
  @ViewChild('frontendFailedSwal') private frontendFailedSwal: SwalComponent;

  constructor(private store: Store<fromRoot.State>,
              private iChatService: IChatService) {
  }

  ngOnInit() {
    this.switchedItem = false;
    this._setFrontend();
  }

  ngOnChanges() {
    if (this.selectedFrontend === null || this.frontEnd._uid !== this.selectedFrontend._uid) {
      this.step = 0;
      this._setFrontend();
      this.switchedItem = true;
    } else {
      this.switchedItem = false;
    }

  }

  ngAfterContentChecked(): void {
    this.isValid = this.isServerValid && this.isDemandInfoListValid;
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
    if (this.selectedFrontend.id) {
      this.iChatService.updateFrontEnd(this.selectedFrontend.id, this.selectedFrontend).subscribe(res => {
        console.log('update frontend', res);
        const newFrontend = {...this.selectedFrontend, ...JSON.parse(res.body)};
        this.store.dispatch(new FrontendListUpdateItemAction(newFrontend));
        this.store.dispatch(new CurrentItemSelectedAction(newFrontend));
        this.frontendUpdatedSwal.show().then();
      }, error1 => {
        this.frontendFailedSwal.show().then();
        console.log('error update', error1);
      });
    } else {
      this.iChatService.createFrontEnd(this.selectedFrontend).subscribe(res => {
        console.log('create Frontend', res);
        const newFrontend = {...this.selectedFrontend, ...JSON.parse(res.body)};
        this.store.dispatch(new FrontEndListAddAction(newFrontend));
        this.store.dispatch(new CurrentItemSelectedAction(newFrontend));
        this.frontendCreatedSwal.show().then();

      }, error1 => {
        this.frontendFailedSwal.show().then();
        console.log('error', error1);
      });
    }
  }

  cancelCurrent() {

  }

  setAsDefault() {
    if (this.selectedFrontend.id) {
      this.iChatService.setAsDefaultFrontend(this.selectedFrontend.id).subscribe(res => {
        // console.log('res', res);
      });
    }
  }


}
