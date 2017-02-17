import { NgModule, ErrorHandler } from '@angular/core';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { MyApp } from './app.component';
import { IstMap } from '../pages/istmap/istmap';
import { Schedule } from '../pages/schedule/schedule';
import { Announcements } from '../pages/announcements/announcements';
import { Credits } from '../pages/credits/credits';
import { Sponsors } from '../pages/sponsors/sponsors';

@NgModule({
  declarations: [
    MyApp,
    IstMap,
    Schedule,
    Announcements,
    Credits,
    Sponsors
  ],
  imports: [
    IonicModule.forRoot(MyApp)
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    IstMap,
    Schedule,
    Announcements,
    Credits,
    Sponsors
  ],
  providers: [{provide: ErrorHandler, useClass: IonicErrorHandler}]
})
export class AppModule {}
