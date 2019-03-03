import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-toggle-option',
  templateUrl: './toggle-option.component.html',
  styleUrls: ['./toggle-option.component.scss']
})
export class ToggleOptionComponent implements OnInit {
  @Input() isMultiple: boolean;
  @Input() options: any[];

  @Input() selectedIndexItems: number[];
  @Output() onOptionsChanged = new EventEmitter();

  constructor() {
  }

  ngOnInit() {
  }

  onChangeOption(event) {
    this.selectedIndexItems = event.value;
    this.onOptionsChanged.emit({selectedItems: event.value});
  }

}
