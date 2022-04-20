import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-head-page',
  templateUrl: './head-page.component.html',
  styleUrls: ['./head-page.component.scss']
})
export class HeadPageComponent implements OnInit {
  @Input() title: string | undefined;

  constructor() { }

  ngOnInit(): void {
  }

}
