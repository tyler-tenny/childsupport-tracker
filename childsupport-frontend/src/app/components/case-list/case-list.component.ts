import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { CaseService, Case } from '../../services/case.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-case-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './case-list.component.html',
  styleUrl: './case-list.component.css'
})

export class CaseListComponent implements OnInit {
  cases: Case[] = [];
  loading = true;

  constructor(
    private caseService: CaseService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
    this.caseService.getAllCases().subscribe({
      next: (data) => { this.cases = data; this.loading = false; },
      error: () => { this.loading = false; }
    });
  }

  viewCase(id: number) {
    this.router.navigate(['/cases', id]);
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
