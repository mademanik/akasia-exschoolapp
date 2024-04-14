import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {MentorService} from 'src/app/services/mentor.service';
import {
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';

export enum Gender {
  MALE = 'MALE',
  FEMALE = 'FEMALE',
}

@Component({
  selector: 'app-edit-mentor',
  templateUrl: './edit-mentor.component.html',
  styleUrls: ['./edit-mentor.component.scss']
})
export class EditMentorComponent implements OnInit {
  mentorForm: FormGroup = this.formBuilder.group({
    id: ['', Validators.required],
    name: ['', Validators.required],
    email: ['', Validators.required],
    gender: ['', Validators.required],
    address: ['', Validators.required],
    phoneNumber: ['', Validators.required],
  });

  constructor(
    @Inject(MAT_DIALOG_DATA)
    public data: { id: any },
    private formBuilder: FormBuilder,
    private mentorService: MentorService,
    private dialogRef: MatDialogRef<EditMentorComponent>
  ) {
  }

  genders = Object.values(Gender);
  selectedGender?: Gender;

  ngOnInit(): void {
    this.findMentorById(this.data.id);
  }

  findMentorById(id: String) {
    this.mentorService.findMentorById(id).subscribe({
      next: (res) => {
        this.mentorForm.setValue({
          id: res.data!.id,
          name: res.data!.name,
          email: res.data!.email,
          gender: res.data!.gender,
          address: res.data!.address,
          phoneNumber: res.data!.phoneNumber,
        });
      },
      error: (err) => {
        alert(err);
      },
    });
  }

  submit(): void {
    if (this.mentorForm.valid) {

      const id = this.mentorForm.get('id')?.value;
      const jsonData = this.mentorForm.value;

      this.mentorService.updateMentor(id, jsonData).subscribe({
        next: (res) => {
          this.dialogRef.close({
            message: 'success',
          });
        },
        error: (err) => {
          this.dialogRef.close({
            message: 'error',
          });
          console.log(err);
        },
      });
    } else {
      this.dialogRef.close({
        message: 'invalid',
      });
    }
  }
}
