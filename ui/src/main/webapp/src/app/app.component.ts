import { Component } from '@angular/core';
import { StockService } from './stock.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [StockService]
})
export class AppComponent {
  constructor(private stockService: StockService) { }

  save(value: number): void {
  	this.stockService.update({"code":"prod1", "price":0, "stock":value})
  		.then(() => null);
  }
}
