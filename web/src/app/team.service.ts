import { Team } from './team';
import { Service } from './service';
import { DbResponse } from './record';
import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

@Injectable()
export class TeamService extends Service {
  constructor(private h: Http) { super(h) }

  get(): Promise<Team[]> {
    return super.getAll<Team>('team')
  }
}