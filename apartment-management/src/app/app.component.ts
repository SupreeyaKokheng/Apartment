
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common'; // ✅ ต้องเพิ่ม!
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule], // ✅ ใส่ CommonModule และ FormsModule
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'apartment-management';

  username: string = '';
  password: string = '';
  errorMessage: string = '';

  onLogin() {
    console.log('🔐 Logging in with:', this.username, this.password);

    if (this.username === 'admin' && this.password === 'password123') {
      alert('✅ Login Successful!');
    } else {
      this.errorMessage = '🚨 Invalid username or password!';
    }
  }
}
