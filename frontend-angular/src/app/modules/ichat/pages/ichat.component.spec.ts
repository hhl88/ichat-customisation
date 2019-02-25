import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IChatComponent } from './ichat.component';

describe('IChatComponent', () => {
  let component: IChatComponent;
  let fixture: ComponentFixture<IChatComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IChatComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IChatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
