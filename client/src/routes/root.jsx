import axios from "axios";
import { Outlet, NavLink, useSubmit, useLoaderData, useNavigation, redirect } from "react-router-dom";
import { getRestaurants, createRestaurant } from "../restaurants"

export default function Root() {
  const { restaurants } = useLoaderData();
  const navigation = useNavigation();

  const handleYelpSearch = event => {
    event.preventDefault();
    const form = event.target
    console.log(event.target);
    const query = `${form.address.value}&${form.radius.value}`;
    axios.get('http://localhost:8080/api/locate/find/' + query)
    .then(res => {
      createRestaurant(res.data.businesses[0]);
      return redirect('/');
    })
    .catch(err => { console.log(err)});
    }
  

  return (
    <>
      <div id="sidebar">
        <h1>Foody</h1>
        <div>
          <form id="search-form" onSubmit={handleYelpSearch} role="search">
            <label>Your address: <input
              id="address"
              aria-label="Enter your address"
              placeholder="Enter your address"
              type="search"
              name="address"
            /></label>
            <label>Radius to search (meters) <input
              id="radius"
              aria-label="Search radius"
              placeholder="1"
              type="number"
              name="radius"
            /></label>
            <div
              id="search-spinner"
              aria-hidden
              hidden={true}
            />
            <div
              className="sr-only"
              aria-live="polite"
            ></div>
            <button type="submit">Search</button>
          </form>

            
          
        </div>
        
        <nav>
          {restaurants.length ? (
            <ul>
              {restaurants.map(place => (
                <li key={ place.id }>
                  <NavLink
                    to={`places/${place.id}`}
                    className={({ isActive, isPending }) => 
                      isActive ?
                      "active" : 
                      isPending ? 
                      "pending" :
                      ""
                    }
                  >
                    {place.name ? place.name : 
                    (
                      <i>No name provided</i>
                    )}
                    {place.favorite && <span>*</span>}
                  </NavLink>
                </li>
              ))}
            </ul>
          ): (
            <p>No restaurants saved</p>
          )}
        </nav>
      </div>
      <div
      id="detail"
      className={
        navigation.state === "loading" ? "loading" : ""
      }
      >
        <Outlet />
      </div>
    </>
  );
}

export async function loader() {
  const restaurants = await getRestaurants();
  return { restaurants };
}

export async function action( { request }) {
  const formData = await request.formData();
  const placeData = Object.fromEntries(formData);
  const place = await createRestaurant(placeData);
  return redirect(`/places/${place.id}/`);
}