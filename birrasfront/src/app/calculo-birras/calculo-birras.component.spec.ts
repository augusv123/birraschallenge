import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CalculoBirrasComponent } from './calculo-birras.component';

describe('CalculoBirrasComponent', () => {
  let component: CalculoBirrasComponent;
  let fixture: ComponentFixture<CalculoBirrasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CalculoBirrasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CalculoBirrasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
