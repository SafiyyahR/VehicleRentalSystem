export class Vehicle {

   _id:string;
   type:string;
   plateNumber:string;
   make:string;
   model:string;
   colour:string;
   regYear:number;
   rentalPerHour:number;
   manual:boolean;
   mileage:number;
   fuel:number;
   passengers:number;
   engineDisplacement:number;
   extraFeatures: any;
   vehicleImage: any;
   //available, damaged, not in use
   status:string;

   constructor(){

   }

}
