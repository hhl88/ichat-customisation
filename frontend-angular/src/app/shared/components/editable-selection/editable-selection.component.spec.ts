import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditableSelectionComponent } from './editable-selection.component';

describe('EditableSelectionComponent', () => {
  let component: EditableSelectionComponent;
  let fixture: ComponentFixture<EditableSelectionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditableSelectionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditableSelectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
