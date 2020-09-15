import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-songs',
  templateUrl: './songs.component.html',
  styleUrls: ['./songs.component.css']
})
export class SongsComponent implements OnInit {

  tartist: boolean = true;
  talbum: boolean = false;
  tvideo: boolean = false;

  albums = [
    {
      title:  "Imagine (Armin van Buuren album)",
      description: "You gonna fall in love!"
    },
    {
      title:  "Imagine (Armin van Buuren album)",
      description: "You gonna fall in love!"
    },
    {
      title:  "Imagine (Armin van Buuren album)",
      description: "You gonna fall in love!"
    },
    {
      title:  "Imagine (Armin van Buuren album)",
      description: "You gonna fall in love!"
    }
  ];
  constructor() { }

  ngOnInit(): void {
  }

  toggle(str: string) {
    if(str == "artist") {
      this.tartist = true;
      this.talbum = false;
      this.tvideo = false;
      return this.tartist;
    } else if(str == "album") {
      this.tartist = false;
      this.tvideo = false;
      this.talbum = true;
      return this.talbum;
    }
    
    this.tartist = false;
    this.talbum = false; 
    this.tvideo = true;
    return this.tvideo;
  }

}
