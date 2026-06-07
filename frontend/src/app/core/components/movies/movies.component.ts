import {Component, OnInit} from '@angular/core';
import {CinemaService} from '../../../services/cinema.service';
import {Router} from '@angular/router';
import { ScreeningModel } from '../../../models/screening-model';

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrl: './movies.component.css'
})
export class MoviesComponent implements OnInit {
  screening: ScreeningModel[] = [];

  constructor(private cinemaService: CinemaService,
              private router: Router) {
  }

  ngOnInit() {
    this.loadScreenings();
  }

  readonly movies = [
    {title: 'Blade Runner 2099', time: '18:30', hall: 'Hall A', seats: 44},
    {title: 'Dune: Part III', time: '20:15', hall: 'Hall C', seats: 31},
    {title: 'Interstellar Reborn', time: '22:00', hall: 'Hall B', seats: 26}
  ];

  private loadScreenings() {
    this.cinemaService.fetchScreens().subscribe({
      next: screen => this.screening = screen,
      error: err => console.error('Error loading screenings', err),
      complete: () => console.info('Loaded screenings')
    })
  }
}
