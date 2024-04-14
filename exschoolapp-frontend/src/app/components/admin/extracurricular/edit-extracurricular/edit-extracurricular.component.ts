import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {ExtracurricularService} from 'src/app/services/extracurricular.service';
import {
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';
import {MentorService} from 'src/app/services/mentor.service';
import {Mentor} from "../../../../models/mentor.model";

@Component({
  selector: 'app-edit-extracurricular',
  templateUrl: './edit-extracurricular.component.html',
  styleUrls: ['./edit-extracurricular.component.scss']
})
export class EditExtracurricularComponent implements OnInit {
  extracurricularForm: FormGroup = this.formBuilder.group({
    id: ['', Validators.required],
    name: ['', Validators.required],
    startDate: ['', Validators.required],
    endDate: ['', Validators.required],
    registrationStartDate: ['', Validators.required],
    registrationEndDate: ['', Validators.required],
    location: ['', Validators.required],
    description: ['', Validators.required],
    quota: ['', Validators.required],
    mentorId: ['', Validators.required],
  });

  constructor(
    @Inject(MAT_DIALOG_DATA)
    public data: { id: any },
    private formBuilder: FormBuilder,
    private extracurricularService: ExtracurricularService,
    private mentorService: MentorService,
    private dialogRef: MatDialogRef<EditExtracurricularComponent>
  ) {
  }

  mentors?: Mentor[];
  selectedMentor?: Mentor;

  ngOnInit(): void {
    this.findExtracurricularById(this.data.id);
    this.findAllMentors();
  }

  findExtracurricularById(id: String) {
    this.extracurricularService.findExtracurricularById(id).subscribe({
      next: (res) => {
        this.extracurricularForm.setValue({
          id: res.data!.id,
          name: res.data!.name,
          startDate: res.data!.startDate,
          endDate: res.data!.endDate,
          registrationStartDate: res.data!.registrationStartDate,
          registrationEndDate: res.data!.registrationEndDate,
          location: res.data!.location,
          description: res.data!.description,
          quota: res.data!.quota,
          mentorId: res.data!.mentor!.id,
        });
      },
      error: (err) => {
        alert(err);
      },
    });
  }

  findAllMentors() {
    this.mentorService.findAllMentors().subscribe({
      next: (res) => {
        this.mentors = res.data!;
      },
      error: (err) => {
        alert(err);
      },
    });
  }

  submit(): void {
    if (this.extracurricularForm.valid) {

      const id = this.extracurricularForm.get('id')?.value;
      const jsonData = this.extracurricularForm.value;

      this.extracurricularService.updateExtracurricular(id, jsonData).subscribe({
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
