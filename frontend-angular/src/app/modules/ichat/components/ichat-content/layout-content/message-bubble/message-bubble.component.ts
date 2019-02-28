import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Bubble} from 'core/interfaces/bubble.interface';
import {BubbleStyle} from 'core/interfaces/bubble-style.interface';

@Component({
  selector: 'app-message-bubble',
  templateUrl: './message-bubble.component.html',
  styleUrls: ['./message-bubble.component.scss']
})
export class MessageBubbleComponent implements OnInit {
  form: FormGroup;
  @Input() id: string;
  @Input() subHeader: string;
  @Input() bubbleStyle: any;
  @Output() onBubbleStyleChanged = new EventEmitter();

  constructor() {
  }

  ngOnInit() {
    console.log('bubbleStyle', this.bubbleStyle)
    this.form = new FormGroup({
      backgroundColor: new FormControl(''),
      borderColor: new FormControl(''),
      borderRadius: new FormControl(''),
      fontColor: new FormControl('')
    });

    // this.form.valueChanges.subscribe(data => {
    //   const ele = document.getElementById(this.id);
    //
    //   ele.style.backgroundColor = data.backgroundColor;
    //   ele.style.borderRadius = data.borderRadius;
    //   ele.style.borderColor = data.borderColor;
    //   ele.style.color = data.fontColor;
    //   this.onBubbleStyleChanged.emit({data: data});
    // });
  }

}
