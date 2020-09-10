import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionMeetupsComponent } from './gestion-meetups.component';

describe('GestionMeetupsComponent', () => {
  let component: GestionMeetupsComponent;
  let fixture: ComponentFixture<GestionMeetupsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GestionMeetupsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GestionMeetupsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
