import { Category } from "./category";
import { Locationmap } from "./locationmap";
import { Transaction } from "./transaction";


export class Business {
    id: String;
    alias: String;
    name: String;
    image_url: String;
    is_closed: Boolean;
    url:String;
    review_count:Number;
    categories:Category[];
    rating:Number;
    transactions:Transaction[];
    price:String;
    location:Locationmap;
    phone:String;
    distance:Number;
}
