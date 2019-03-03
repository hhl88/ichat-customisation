import {AfterViewInit, Component, EventEmitter, Input, OnChanges, OnDestroy, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Bubble} from 'core/interfaces/bubble.interface';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-message-bubble',
  templateUrl: './message-bubble.component.html',
  styleUrls: ['./message-bubble.component.scss']
})
export class MessageBubbleComponent implements OnInit, AfterViewInit, OnChanges, OnDestroy {
  form: FormGroup;
  @Input() id: string;
  @Input() subHeader: string;
  @Input() bubbleStyle: Bubble;
  @Output() onBubbleStyleChanged = new EventEmitter();
  oldBubbleStyle: Bubble;

  keys: string[] = [];
  sub: Subscription;

  constructor() {
  }

  ngOnInit() {
    this._reloadForm();
  }

  private _setBubbleSample(data) {
    const ele = document.getElementById(this.id);
    Object.keys(data).forEach(key => {
      ele.style[key] = data[key];
    });
  }

  ngAfterViewInit(): void {
    this._setBubbleSample(JSON.parse(JSON.stringify(this.bubbleStyle)));
  }

  ngOnChanges(): void {
    if (!!this.oldBubbleStyle && !!this.bubbleStyle) {
      const t = Object.keys(this.oldBubbleStyle).filter(key => this.bubbleStyle[key] !== this.oldBubbleStyle[key]);
      console.log('t', t);
      if (Object.keys(this.oldBubbleStyle).filter(key => this.bubbleStyle[key] !== this.oldBubbleStyle[key]).length > 0) {
        this._setBubbleSample(JSON.parse(JSON.stringify(this.bubbleStyle)));
        this._reloadForm();

      }
    }
  }

  ngOnDestroy(): void {
    if (this.sub) {
      this.sub.unsubscribe();

    }
  }

  private _reloadForm() {
    if (this.form) {
      this.sub.unsubscribe();
    }
    this.form = new FormGroup({});
    this.keys = [];
    if (!!this.bubbleStyle) {
      this.oldBubbleStyle = JSON.parse(JSON.stringify(this.bubbleStyle));
      Object.keys(this.bubbleStyle).forEach(key => {
        this.form.addControl(key, new FormControl(this.bubbleStyle[key]));
        this.keys.push(key);
      });

    }
    this.sub = this.form.valueChanges.subscribe(data => {
      this._setBubbleSample(data);
      this.onBubbleStyleChanged.emit({data: data});
    });
  }

}
