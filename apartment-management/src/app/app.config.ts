
import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';
//import { provideHttpClient } from '@angular/common/http';
import { provideHttpClient, withFetch } from '@angular/common/http';
import { routes } from './app.routes';

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes), // เชื่อม Routing เข้ากับไฟล์ app.routes.ts
    //provideHttpClient()    // เพิ่ม HTTP Client สำหรับการเรียก API
    provideHttpClient(withFetch())
  ]
};
