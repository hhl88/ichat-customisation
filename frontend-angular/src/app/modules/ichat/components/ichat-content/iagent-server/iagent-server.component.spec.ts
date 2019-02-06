import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IagentServerComponent } from './iagent-server.component';

describe('IagentServerComponent', () => {
  let component: IagentServerComponent;
  let fixture: ComponentFixture<IagentServerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IagentServerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IagentServerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
