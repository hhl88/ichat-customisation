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
  @Output() onDemandInfoListChanged = new EventEmitter<any>();

  form: FormGroup;

  constructor() {
  }

  ngOnInit() {
    this.reloadForm();
  }

  ngOnChanges(): void {
    this.reloadForm();
  }

  reloadForm() {
    console.log('demandIf', this.demandInfo)
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
        this.onDemandInfoListChanged.emit({
          data: this.getList().getRawValue(),
          isFormValid: this.isFormValid()
        });
      }
    );
  }

  initForm() {
    this.form = new FormGroup({
      demandInfoList: new FormArray([])
    });
  }

  addDemandInfo() {
    console.log('213123', this.form.getRawValue());
    this.getList().push(this.initDemandInfo());
  }

  initDemandInfo() {
    return new FormGroup({
      name: new FormControl('', [Validators.required, Validators.minLength(1)]),
      example: new FormControl('', [Validators.required, Validators.minLength(1)]),
      required: new FormControl(false)
    }, {
      updateOn: 'blur'
    });
  }

  removeDemandInfo(event, i: number) {
    const list = this.getList();
    if (list.length > 1) {
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

  isFormValid() {
    const valid = this.getList().controls.findIndex(form => form.invalid);
    return valid === -1;
  }


}
