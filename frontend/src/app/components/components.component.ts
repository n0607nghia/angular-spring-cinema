import { Component } from '@angular/core';

@Component({
  selector: 'app-components',
  templateUrl: './components.component.html',
  styleUrl: './components.component.css'
})
export class ComponentsComponent {
  readonly movies = [
    { title: 'Blade Runner 2099', time: '18:30', hall: 'Hall A', seats: 44 },
    { title: 'Dune: Part III', time: '20:15', hall: 'Hall C', seats: 31 },
    { title: 'Interstellar Reborn', time: '22:00', hall: 'Hall B', seats: 26 }
  ];
}
