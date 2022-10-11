import { Category } from "./category";
import { Locationmap } from "./locationmap";
import { Transaction } from "./transaction";


export class Business {
    id: string;
    alias: string;
    name: string;
    image_url: string;
    is_closed: boolean;
    url:string;
    review_count:number;
    categories:Category[];
    rating:number;
    transactions:Transaction[];
    price:string;
    location:Locationmap;
    phone:string;
    distance:number;
}
