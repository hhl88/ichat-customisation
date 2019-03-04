import {Component, EventEmitter, Input, OnChanges, OnInit, Output} from '@angular/core';
import {Info} from 'core/interfaces/info.interface';
import {FormArray, FormControl, FormGroup, Validators} from '@angular/forms';
import {DemandInfo} from 'core/interfaces/demand-info.interface';

@Component({
  selector: 'app-demand-info',
  templateUrl: './demand-info.component.html',
  styleUrls: ['./demand-info.component.scss']
})
export class DemandInfoComponent implements OnInit, OnChanges {
  @Input() demandInfo: DemandInfo;
  @Input() switchedItem: boolean;

  @Output() onDemandInfoListChanged = new EventEmitter<any>();
  @Output() onFinishedBuild = new EventEmitter<any>();

  form: FormGroup;

  constructor() {
  }

  ngOnInit() {
    this.reloadForm();
  }

  ngOnChanges(): void {
    if (this.switchedItem) {
      this.reloadForm();
    }
  }


  reloadForm() {
    this.initForm();

    if (this.demandInfo && this.demandInfo.demandInfoList.length > 0) {
      const demandList: Info[] = JSON.parse(JSON.stringify(this.demandInfo.demandInfoList));
      for (let i = 0; i < demandList.length; i++) {
        this.addDemandInfo();
        this.getFormGroup(i).controls['name'].setValue(demandList[i].name);
        this.getFormGroup(i).controls['example'].setValue(demandList[i].example);
        this.getFormGroup(i).controls['required'].setValue(demandList[i].required);

      }
    }
    this.form.controls['demandInfoList'].valueChanges.subscribe(data => {
        const isFormValid = this._isFormValid();
        //if (isFormValid) {
        this.onDemandInfoListChanged.emit({
          data: this.getList().getRawValue(),
          isFormValid: isFormValid
        });
        //}

      }
    );
  }

  initForm() {
    this.form = new FormGroup({
      demandInfoList: new FormArray([])
    });
  }

  addDemandInfo() {
    this.getList().push(this._initDemandInfo());
  }

  private _initDemandInfo() {
    return new FormGroup({
      name: new FormControl('', [Validators.required, Validators.minLength(1)]),
      example: new FormControl('', [Validators.required, Validators.minLength(1)]),
      required: new FormControl(false, [Validators.required])
    }, {
      updateOn: 'blur'
    });
  }

  removeDemandInfo(event, i: number) {
    const list = this.getList();
    if (list.length > 0) {
      this.getFormGroup(i).clearValidators();
      list.removeAt(i);
      event.stopPropagation();
    }
  }

  getList() {
    return <FormArray>this.form.get('demandInfoList');
  }

  getFormGroup(index: number) {
    return (<FormGroup>(<FormArray>this.form.get('demandInfoList')).controls[index]);
  }

  private _isFormValid() {
    const valid = this.getList().controls.findIndex(form => form.invalid);
    return valid === -1;
  }


}
