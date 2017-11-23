import { Skill } from './skill';
import { Service } from './service';
import { DbResponse } from './record';
import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

@Injectable()
export class SkillService extends Service {
  constructor(private h: Http) { super(h) }

  get(): Promise<Skill[]> {
    return super.getAll<Skill>('skill')
  }
}