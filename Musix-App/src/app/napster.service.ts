import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class NapsterService {

  apiKey = '';
  constructor(private http: HttpClient) { }

  fetchTopArtists() {
    // api key:    
    // api secret:    
    // callback url:   musix-repo
    return this.http.get<any>("http://api.napster.com/v2.2/artists/top?apikey=" + this.apiKey);
  }

}
