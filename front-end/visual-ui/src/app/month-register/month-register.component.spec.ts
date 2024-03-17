import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MonthRegisterComponent } from './month-register.component';

describe('MonthRegisterComponent', () => {
  let component: MonthRegisterComponent;
  let fixture: ComponentFixture<MonthRegisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MonthRegisterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MonthRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
