import { Vehicle } from './vehicle';
import { Schedule } from './schedule';

export class Booking {
	_id:string;
    //Mr., Ms., Mrs., Miss
    title:string;
    schedule:Schedule;
    vehicleId:string;
    firstName:string;
    lastName:string;
    dateOfBirth:string;
    email:string;
    telNo:string;
	total:number;	
    //confirmed, request to delete, cancelled
    status:string;

	constructor() {
	}

}
