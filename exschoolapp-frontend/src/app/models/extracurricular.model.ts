import {Mentor} from "./mentor.model";

export class Extracurricular {
  id?: any;
  createdDate?: any;
  lastUpdatedDate?: any;
  name?: string;
  startDate?: any;
  endDate?: any;
  registrationStartDate?: any;
  registrationEndDate?: any;
  location?: string;
  description?: string;
  quota?: number;
  mentor?: Mentor;
}
