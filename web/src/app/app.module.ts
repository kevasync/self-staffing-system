import { NgModule }       from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { HttpModule } from '@angular/http';

import { AppComponent }         from './app.component';
import { DashboardComponent }   from './dashboard.component';
import { SkillService }          from './skill.service';
import { UserService }          from './user.service';
import { LocationService }          from './location.service';
import { TeamService }          from './team.service';

import { AppRoutingModule }     from './app-routing.module';

@NgModule({
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpModule,
  ],
  declarations: [
    AppComponent,
    DashboardComponent
  ],
  providers: [ 
    SkillService,
    UserService,
    LocationService,
    TeamService
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
