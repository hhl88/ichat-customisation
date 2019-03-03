import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-editable-selection',
  templateUrl: './editable-selection.component.html',
  styleUrls: ['./editable-selection.component.scss']
})
export class EditableSelectionComponent implements OnInit {
  @Input() options: any[];
  @Input() selectedOption: string;
  @Output() onSelectionChanged = new EventEmitter();

  constructor() {
  }

  ngOnInit() {
  }

  onChangeSelection(event) {
    this.selectedOption = event.target.value;
    this.onSelectionChanged.emit({newSelection: event.target.value});
  }

  onTyping(event) {
    this.selectedOption = event.target.value;
    this.onSelectionChanged.emit({newSelection: event.target.value});
  }

}
