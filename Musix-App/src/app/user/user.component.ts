import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  tcomments: boolean = true;
  tfavourites: boolean = false;
  trecommendation: boolean = false;
  tcountrySpecial: boolean = false;
  constructor() { }

  ngOnInit(): void {
  }
  toggle(str: string) {
    if(str == "comments") {
      this.tcomments = true;
      this.tfavourites = false;
      this.trecommendation = false;
      this.tcountrySpecial = false;

      return this.tcomments;

    } else if(str == "favourites") {
      this.tcomments = false;
      this.tfavourites = true;
      this.trecommendation = false;
      this.tcountrySpecial = false;

      return this.tfavourites;

    } else if(str == 'recommendation') {
      this.tcomments = false;
      this.tfavourites = false;
      this.trecommendation = true;
      this.tcountrySpecial = false;

      return this.trecommendation;
    }
    
    this.tcomments = false;
    this.tfavourites = false;
    this.trecommendation = false;
    this.tcountrySpecial = true;

    return this.tcountrySpecial;
  }
}
