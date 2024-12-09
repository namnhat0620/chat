import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { UserService } from '../services/user/user.service';
import { debounceTime, distinctUntilChanged, Subject, switchMap } from 'rxjs';

@Component({
  selector: 'app-list-conversation',
  templateUrl: './list-conversation.component.html',
  styleUrls: ['./list-conversation.component.scss'],
})
export class ListConversationComponent {
  value = '';
  private valueChanged = new Subject<string>();

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.valueChanged
      .pipe(
        debounceTime(2000),
        switchMap((value: string) => this.userService.getUserByUsername(value)) // Call API
      )
      .subscribe((data) => console.log(data));
  }

  onValueChange(): void {
    this.valueChanged.next(this.value);  // Emit the current value to trigger the API call
  }
}
