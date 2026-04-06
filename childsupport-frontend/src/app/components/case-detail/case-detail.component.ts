import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CaseService, Case, Payment } from '../../services/case.service';

@Component({
  selector: 'app-case-detail',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './case-detail.component.html',
  styleUrl: './case-detail.component.css'
})
export class CaseDetailComponent implements OnInit {
  case: Case | null = null;
  payments: Payment[] = [];
  loading = true;

  newPayment: Payment = {
    amount: 0,
    paymentDate: '',
    status: 'RECEIVED'
  };

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private caseService: CaseService
  ) {}

  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.caseService.getCaseById(id).subscribe(c => {
      this.case = c;
      this.loading = false;
    });
    this.caseService.getPayments(id).subscribe(p => {
      this.payments = p;
    });
  }

  logPayment() {
    if (!this.case?.id) return;
    this.caseService.createPayment(this.case.id, this.newPayment).subscribe(p => {
      this.payments.push(p);
      this.newPayment = { amount: 0, paymentDate: '', status: 'RECEIVED' };
    });
  }

  goBack() {
    this.router.navigate(['/cases']);
  }
}
