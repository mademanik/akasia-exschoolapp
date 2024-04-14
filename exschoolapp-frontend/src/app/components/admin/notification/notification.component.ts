import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import {NotificationService} from 'src/app/services/notification.service';
import {MatSort} from '@angular/material/sort';
import {Notification} from 'src/app/models/notification.model';
import {MatSnackBar} from '@angular/material/snack-bar';
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.scss']
})
export class NotificationComponent implements AfterViewInit, OnInit {
  displayedColumns: string[] = [
    'message',
    'toEmail',
    'createdAt',
  ];

  dataSource = new MatTableDataSource<Notification>();
  ELEMENT_DATA = [];

  @ViewChild(MatPaginator) paginator: any = MatPaginator;
  @ViewChild(MatSort) sort: any = MatSort;

  constructor(
    public dialog: MatDialog,
    private notificationService: NotificationService,
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
    this.findAllNotifications();
  }

  findAllNotifications() {
    this.notificationService.findAllNotifications().subscribe({
      next: (res) => {
        this.dataSource.data = res.data!;
      },
      error: (err) => {
        alert(err);
      },
    });
  }

  convertDate(date: string): string {
    const parsedDate = new Date(date);

    return this.datePipe.transform(parsedDate, 'dd-MM-yyyy HH-mm-ss') || '';
  }
}

