import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NapsterService } from '../napster.service';
@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrls: ['./search-result.component.css']
})
export class SearchResultComponent implements OnInit {

  constructor(private napsterService: NapsterService) { }

  artists;
  ngOnInit(): void {
    this.fetchTopArtists();
  }

  fetchTopArtists() {
    this.napsterService
    .fetchTopArtists()
    .subscribe(
      res => this.artists = res, 
      err => err == HttpErrorResponse ? console.log(err): console.log("something went wrong")
      );    
  }

}
