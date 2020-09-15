import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-playbar',
  templateUrl: './playbar.component.html',
  styleUrls: ['./playbar.component.css']
})
export class PlaybarComponent implements OnInit {
  duration: number = 100;
  currentTime: number = 50;
  readCurrentTime: String ='00:00';
  readDuration: String ='00:00';
  // volume = 0.5;

  isPlay: boolean = false;
  isClicked: boolean = false;
  isShuffle: boolean = false;
  isMute: boolean = true;

  mydata = {
              img: '',
              label: '',
              author:''
            };



  constructor() { }

  ngOnInit(): void {
  }

    
//  setVolume(value){
//    console.log(value);
//  }
 setSeek(value){
  console.log(value);
  }
  
  isStart(){
    return false;
  }

  previous(){

  }
  next(){

  }
  play(){}

  pause(){}


  isEnd(){
    return false;
  }

  mute(){}

}
