import {Component} from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';
import {
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';
import {MentorService} from 'src/app/services/mentor.service';

export enum Gender {
  MALE = 'MALE',
  FEMALE = 'FEMALE',
}

@Component({
  selector: 'app-add-mentor',
  templateUrl: './add-mentor.component.html',
  styleUrls: ['./add-mentor.component.scss']
})
export class AddMentorComponent {
  constructor(
    private formBuilder: FormBuilder,
    private mentorService: MentorService,
    public dialogRef: MatDialogRef<AddMentorComponent>
  ) {
  }

  genders = Object.values(Gender);
  selectedGender?: Gender;

  mentorForm: FormGroup = this.formBuilder.group({
    name: ['', Validators.required],
    email: ['', Validators.required],
    gender: ['', Validators.required],
    address: ['', Validators.required],
    phoneNumber: ['', Validators.required],
  });

  submit(): void {
    if (this.mentorForm.valid) {
      const jsonData = this.mentorForm.value;

      this.mentorService.createMentor(jsonData).subscribe({
        next: (res) => {
          this.dialogRef.close({
            message: 'success',
          });
        },
        error: (err) => {
          console.log(err);
          this.dialogRef.close({
            message: 'error',
          });
        },
      });
    } else {
      this.dialogRef.close({
        message: 'invalid',
      });
    }
  }
}

