import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Info} from 'core/interfaces/info.interface';
import {FormArray, FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-demand-info',
  templateUrl: './demand-info.component.html',
  styleUrls: ['./demand-info.component.scss']
})
export class DemandInfoComponent implements OnInit {
  @Input() demandInfoList: Info[];
  @Output() onDemandInfoListChanged = new EventEmitter<any>();

  form: FormGroup;

  constructor() {
  }

  ngOnInit() {
    this.form = new FormGroup({
      demandInfoList: new FormArray([])
    });

    if (this.demandInfoList.length > 0) {
      for (let i = 0; i < this.demandInfoList.length; i++) {
        this.addDemandInfo();
        this.getFormGroup(i).controls['name'].setValue(this.demandInfoList[i].name);
        this.getFormGroup(i).controls['example'].setValue(this.demandInfoList[i].example);
        this.getFormGroup(i).controls['required'].setValue(this.demandInfoList[i].required);

      }
    }

    this.form.controls['demandInfoList'].valueChanges.subscribe(data => {
        this.demandInfoList = this.getList().getRawValue();
        this.onDemandInfoListChanged.emit({
          data: this.demandInfoList,
          isFormValid: this.isFormValid()
        });
      }
    );
  }

  addDemandInfo() {
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
    return <FormArray>this.form.controls.demandInfoList;
  }

  getFormGroup(index: number) {
    return (<FormGroup>(<FormArray>this.form.controls.demandInfoList).controls[index]);
  }

  isFormValid() {
    const valid = this.getList().controls.findIndex(form => form.invalid);
    return valid === -1;
  }
}
