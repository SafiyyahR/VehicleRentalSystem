import { Vehicle } from './vehicle';
import { Schedule } from './schedule';

export class Booking {
	_id: string;
	schedule: Schedule;
	vehicle: Vehicle;
	driverFirstName: string;
	driverLastName: string;
	dateOfBirth: string;
	driverEmail: string;
	driverTelNo: string;
	totalRentalPrice: number;

	constructor() {
	}

}
