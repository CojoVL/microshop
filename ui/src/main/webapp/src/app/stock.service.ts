import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

@Injectable()
export class StockService {

  private apiUrl = '/api/inventory/products/';
  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http) { }

  update(hero: any): Promise<any> {
	  return this.http
	    .post(this.apiUrl, JSON.stringify(hero), {headers: this.headers})
	    .toPromise()
	    .then(() => hero)
	    .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
  	console.error('An error ocured', error)
  	return Promise.reject(error.message || error);
  }

}
