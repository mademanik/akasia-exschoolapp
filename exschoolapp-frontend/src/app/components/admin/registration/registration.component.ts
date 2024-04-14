import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import {RegistrationService} from 'src/app/services/registration.service';
import {MatSort} from '@angular/material/sort';
import {Registration} from 'src/app/models/registration.model';
import {MatSnackBar} from '@angular/material/snack-bar';
import {AddRegistrationComponent} from "./add-registration/add-registration.component";
import {DatePipe} from "@angular/common";
import {ShowRegistrationComponent} from "./show-registration/show-registration.component";
import {EditRegistrationComponent} from "./edit-registration/edit-registration.component";
import {DeleteRegistrationComponent} from "./delete-registration/delete-registration.component";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements AfterViewInit, OnInit {
  displayedColumns: string[] = [
    'studentName',
    'extracurricularName',
    'startDate',
    'endDate',
    'grade',
    'action'
  ];

  dataSource = new MatTableDataSource<Registration>();
  ELEMENT_DATA = [];

  @ViewChild(MatPaginator) paginator: any = MatPaginator;
  @ViewChild(MatSort) sort: any = MatSort;

  constructor(
    public dialog: MatDialog,
    private registrationService: RegistrationService,
    private _snackBar: MatSnackBar,
    private datePipe: DatePipe
  ) {
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  ngOnInit(): void {
    this.findAllRegistrations();
  }

  findAllRegistrations() {
    this.registrationService.findAllRegistrations().subscribe({
      next: (res) => {
        this.dataSource.data = res.data!;
      },
      error: (err) => {
        alert(err);
      },
    });
  }

  addDialog() {
    const dialogRef = this.dialog.open(AddRegistrationComponent, {
      width: '50%',
      position: {top: '20px'},
    });

    dialogRef.afterClosed().subscribe((result) => {
      setTimeout(() => {
        this.findAllRegistrations();
      }, 1000);

      if (result?.message == 'success') {
        this.openSnackbarSuccess('Success', 'Data successfully created');
      } else if (result?.message == 'error') {
        this.openSnackbarError('Error', 'Data create Failed');
      } else if (result?.message == 'invalid') {
        this.openSnackbarError('Error', 'Form invalid');
      }
    });
  }

  openSnackbarSuccess(title: string, message: string) {
    this._snackBar.open(message, title, {
      horizontalPosition: 'right',
      verticalPosition: 'top',
      duration: 3000,
      panelClass: 'app-notification-success',
    });
  }

  openSnackbarError(title: string, message: string) {
    this._snackBar.open(message, title, {
      horizontalPosition: 'right',
      verticalPosition: 'top',
      duration: 3000,
      panelClass: 'app-notification-error',
    });
  }


  convertDate(date: string): string {
    const parsedDate = new Date(date);

    return this.datePipe.transform(parsedDate, 'dd-MM-yyyy HH-mm-ss') || '';
  }

  showDialog(id: String) {
    const dialogRef = this.dialog.open(ShowRegistrationComponent, {
      width: '50%',
      position: {top: '20px'},
      data: {id: id},
    });

    dialogRef.afterClosed().subscribe((result) => {
      setTimeout(() => {
        this.findAllRegistrations();
      }, 500);
    });
  }

  editDialog(id: String) {
    const dialogRef = this.dialog.open(EditRegistrationComponent, {
      width: '50%',
      position: {top: '20px'},
      data: {id: id},
    });

    dialogRef.afterClosed().subscribe((result) => {
      setTimeout(() => {
        this.findAllRegistrations();
      }, 1000);

      this.resultSnackBar(result, 'update');
    });
  }

  deleteDialog(id: String) {
    const dialogRef = this.dialog.open(DeleteRegistrationComponent, {
      width: '20%',
      position: {top: '20px'},
      data: {id: id},
    });

    dialogRef.afterClosed().subscribe((result) => {
      setTimeout(() => {
        this.findAllRegistrations();
      }, 500);

      this.resultSnackBar(result, 'delete');
    });
  }

  resultSnackBar(result: any, type: string) {
    if (result?.message == 'success') {
      this.openSnackbarSuccess('Success', `Data ${type} successfully`);
    } else if (result?.message == 'error') {
      this.openSnackbarError('Error', `Data ${type} Failed`);
    } else if (result?.message == 'invalid') {
      this.openSnackbarError('Error', `Form ${type} invalid`);
    }
  }

}


