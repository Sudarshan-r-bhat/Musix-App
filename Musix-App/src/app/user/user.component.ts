import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
/*
  tcomments: boolean = true;
  tfavourites: boolean = false;
  trecommendation: boolean = false;
  tcountrySpecial: boolean = false;
*/
tprofile: boolean = true;
tfavourite: boolean = false;
trecommend: boolean = false;

userData ={
  img: '',
  name: 'ellen',
  emailId:'stack@gmail.com'
};
//changing profile photo
/*   url = "https://randomuser.me/api/portraits/women/43.jpg";

  onSelectFile(event) { // called each time file input changes
      if (event.target.files && event.target.files[0]) {
        var reader = new FileReader();

        reader.readAsDataURL(event.target.files[0]); // read file as data url

        reader.onload = (event:any) => { // called once readAsDataURL is completed
          this.url = event.target.result;
        }
      }
  } */

//Display user data
userName: String = this.userData.name;// get details from database
userEmail: String = this.userData.emailId;

  constructor() { }

  ngOnInit(): void {
  }

  toggle(str: string) {
    
    if(str == "profile")
    {
      this.tprofile = true;
      this.tfavourite = false;
      this.trecommend = false;
      return this.tprofile;
    }else if(str == "favourite")
    {
      this.tprofile = false;
      this.tfavourite = true;
      this.trecommend = false;
      return this.tfavourite;
    }
      this.tprofile = false;
      this.tfavourite = false;
      this.trecommend = true;
      return this.trecommend;
  }
  /*
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
  */
}
