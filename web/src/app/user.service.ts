import { User } from './user';
import { Service } from './service';
import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

@Injectable()
export class UserService extends Service {
  constructor(private h: Http) { super(h) }

  update(user: User): void {
    super.update(user, 'user', user.id)
  }
}