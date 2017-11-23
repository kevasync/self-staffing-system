import { Location } from './location';
import { Service } from './service';
import { DbResponse } from './record';
import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

@Injectable()
export class LocationService extends Service {
  constructor(private h: Http) { super(h) }

  get(): Promise<Location[]> {
    return super.getAll('location')
  }
}