import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import {ExtracurricularService} from 'src/app/services/extracurricular.service';
import {MatSort} from '@angular/material/sort';
import {Extracurricular} from 'src/app/models/extracurricular.model';
import {MatSnackBar} from '@angular/material/snack-bar';
import {AddExtracurricularComponent} from "./add-extracurricular/add-extracurricular.component";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-extracurricular',
  templateUrl: './extracurricular.component.html',
  styleUrls: ['./extracurricular.component.scss']
})
export class ExtracurricularComponent implements AfterViewInit, OnInit {
  displayedColumns: string[] = [
    'name',
    'startDate',
    'endDate',
    'registrationStartDate',
    'registrationEndDate',
    'quota',
    'action'
  ];

  dataSource = new MatTableDataSource<Extracurricular>();
  ELEMENT_DATA = [];

  @ViewChild(MatPaginator) paginator: any = MatPaginator;
  @ViewChild(MatSort) sort: any = MatSort;

  constructor(
    public dialog: MatDialog,
    private extracurricularService: ExtracurricularService,
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
    this.findAllExtracurriculars();
  }

  findAllExtracurriculars() {
    this.extracurricularService.findAllExtracurriculars().subscribe({
      next: (res) => {
        this.dataSource.data = res.data!;
      },
      error: (err) => {
        alert(err);
      },
    });
  }

  addDialog() {
    const dialogRef = this.dialog.open(AddExtracurricularComponent, {
      width: '50%',
      position: {top: '20px'},
    });

    dialogRef.afterClosed().subscribe((result) => {
      setTimeout(() => {
        this.findAllExtracurriculars();
      }, 500);

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
  }

  editDialog(id: String) {
  }

  deleteDialog(id: String) {
  }

}

