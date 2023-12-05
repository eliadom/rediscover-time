import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CuaMenuComponent } from './cua-menu.component';

describe('MainMenuComponent', () => {
  let component: CuaMenuComponent;
  let fixture: ComponentFixture<CuaMenuComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CuaMenuComponent]
    });
    fixture = TestBed.createComponent(CuaMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
