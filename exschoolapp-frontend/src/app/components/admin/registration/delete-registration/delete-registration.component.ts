import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {RegistrationService} from 'src/app/services/registration.service';

@Component({
  selector: 'app-delete-registration',
  templateUrl: './delete-registration.component.html',
  styleUrls: ['./delete-registration.component.scss']
})
export class DeleteRegistrationComponent {
  id: String = '';

  constructor(
    @Inject(MAT_DIALOG_DATA)
    public data: { id: any },
    private registrationService: RegistrationService,
    private dialogRef: MatDialogRef<DeleteRegistrationComponent>,
  ) {
  }

  deleteRegistration() {
    this.registrationService.deleteRegistrationById(this.data.id).subscribe({
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
  }
}


