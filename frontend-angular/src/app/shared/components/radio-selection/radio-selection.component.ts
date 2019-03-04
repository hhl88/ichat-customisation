import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-radio-selection',
  templateUrl: './radio-selection.component.html',
  styleUrls: ['./radio-selection.component.scss']
})
export class RadioSelectionComponent implements OnInit {
  @Input() options: any[];
  @Input() selectedItemIndex: number;
  @Input() displayAsColumn: boolean;
  @Output() onSelectionChanged = new EventEmitter();


  constructor() {
  }

  ngOnInit() {
    if (!this.selectedItemIndex && this.options.length > 0) {
      this.selectedItemIndex = 0;
    }

  }

  onChangeSelection(event) {
    this.selectedItemIndex = event.value;
    this.onSelectionChanged.emit({newSelection: event.value});
  }

}
