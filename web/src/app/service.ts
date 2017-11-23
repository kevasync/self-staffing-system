import { Headers } from '@angular/http'
import { DbResponse } from './record'
import { Http } from '@angular/http'
import 'rxjs/add/operator/toPromise'

export class Service {
private baseUrl = 'http://localhost:5984'
private headers = new Headers({'Content-Type': 'application/json'})
 
constructor(private http: Http) { }  
  get<T>(resource: string, id: string): Promise<T> {
    return this.http.get(`${this.baseUrl}/${resource}/${id}`)
      .toPromise()
      .then(response => {
        return response.json() as T
      })
      .catch(this.handleError)
  }

  getAll<T>(resource: string): Promise<T[]> {
    return this.http.get(`${this.baseUrl}/${resource}/_all_docs?include_docs=true`)
      .toPromise()
      .then(response => {
        let r = response.json() as DbResponse<T>
        return r.rows.map(r => r.doc)
      })
      .catch(this.handleError)
  }

  update<T>(item: T, resource: string, id: string): void {
    this.http.put(`${this.baseUrl}/${resource}/${id}`,
    				JSON.stringify(item),
    				{ headers: this.headers })
      .toPromise()
      .then(() => item)
      .catch(this.handleError)
  }

  protected handleError(error: any): Promise<any> {
    console.error('an error occured', error)
    return Promise.reject(error.message || error)
  }
}