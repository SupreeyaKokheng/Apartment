// import { Routes } from '@angular/router';
// import { WaterMeterComponent } from './pages/water-meter/water-meter.component';
// import { ElectricMeterComponent } from './pages/electric-meter/electric-meter.component';
// import { SummaryComponent } from './pages/summary/summary.component';
// import { BillingComponent } from './pages/billing/billing.component';

// export const routes: Routes = [
//   { path: 'water-meter', component: WaterMeterComponent },
//   { path: 'electric-meter', component: ElectricMeterComponent },
//   { path: 'summary', component: SummaryComponent },
//   { path: 'billing', component: BillingComponent },
//   { path: '', redirectTo: 'water-meter', pathMatch: 'full' },
// ];

import { Routes } from '@angular/router';
import { WaterMeterComponent } from './pages/water-meter/water-meter.component';
import { ElectricMeterComponent } from './pages/electric-meter/electric-meter.component';
import { SummaryComponent } from './pages/summary/summary.component';
import { BillingComponent } from './pages/billing/billing.component';

export const routes: Routes = [
  { path: '', redirectTo: '/water-meter', pathMatch: 'full' },
  { path: 'water-meter', component: WaterMeterComponent },
  { path: 'electric-meter', component: ElectricMeterComponent },
  { path: 'summary', component: SummaryComponent },
  { path: 'billing', component: BillingComponent },
];
