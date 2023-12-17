import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SmallViewClientComponent } from './small-view-client.component';

describe('MainMenuComponent', () => {
  let component: SmallViewClientComponent;
  let fixture: ComponentFixture<SmallViewClientComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SmallViewClientComponent]
    });
    fixture = TestBed.createComponent(SmallViewClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
