import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ButtonGroupChatComponent} from './button-group-chat.component';

describe('ButtonGroupChatComponent', () => {
  let component: ButtonGroupChatComponent;
  let fixture: ComponentFixture<ButtonGroupChatComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ButtonGroupChatComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ButtonGroupChatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
