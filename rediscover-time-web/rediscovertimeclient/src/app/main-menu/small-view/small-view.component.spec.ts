import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SmallViewComponent } from './small-view.component';

describe('MainMenuComponent', () => {
  let component: SmallViewComponent;
  let fixture: ComponentFixture<SmallViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SmallViewComponent]
    });
    fixture = TestBed.createComponent(SmallViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
