import { Component } from '@angular/core';

import { NavController, NavParams } from 'ionic-angular';

@Component({
  selector: 'page-schedule',
  templateUrl: 'schedule.html'
})
export class Schedule {
  selectedItem: any;
  // quick structure for each event
  // title: obvious usage
  // time: the time the event is set to take place
  // desc: short description of the event
  // extdesc: extended description of the event
  items: Array<{title: string, time: string, desc: string, extdesc: string}>;

  constructor(public navCtrl: NavController, public navParams: NavParams) {
    // If we navigated to this page, we will have an item available as a nav param
    this.selectedItem = navParams.get('item');

    this.items = [];

    // first event (check-in?)
    this.items.push({
        title: 'First Event',
        time: 'null o\' clock',
        desc: 'This is event number one',
        extdesc: 'This is an extended description of event number one.'
      });
    // event two
    this.items.push({
        title: 'Second Event',
        time: 'half-past null',
        desc: 'This is event number two',
        extdesc: 'This is an extended description of event number two.'
      });
    // event three
    this.items.push({
        title: 'Third Event',
        time: 'null-thirty am',
        desc: 'This is event number three',
        extdesc: 'This is an extended description of event number three.'
      });
    // adding new 'events' should be pretty easy
    }

  // not sure what sorcery happens here, just kinda leave it alone pls
  itemTapped(event, item, time, extdesc) {
    this.navCtrl.push(Schedule, {
      item: item,
      time: time,
      extdesc: extdesc
    });
  }
}
