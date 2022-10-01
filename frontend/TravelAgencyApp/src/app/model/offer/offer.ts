import {Rating} from "primeng/rating/rating";
import {NgModel} from "@angular/forms";

export interface Offer {
    businessKey: String;
    price: number;
    country: String;
    imageUrl: String;
    createDate: Date;
}
