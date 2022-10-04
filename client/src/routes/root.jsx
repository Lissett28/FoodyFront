import { Outlet, NavLink, Form, useLoaderData, useNavigation, redirect } from "react-router-dom";
import { getRestaurants, createRestaurant } from "../restaurants"

export default function Root() {
  const { restaurants } = useLoaderData();
  const navigation = useNavigation();

  return (
    <>
      <div id="sidebar">
        <h1>Foody</h1>
        <div>
          <form id="search-form" role="search">
            <input
              id="q"
              aria-label="Search for restaurants"
              placeholder="Search"
              type="search"
              name="q"
            />
            <div
              id="search-spinner"
              aria-hidden
              hidden={true}
            />
            <div
              className="sr-only"
              aria-live="polite"
            ></div>
          </form>
          <Form method="post">
            <button type="submit">New</button>
          </Form>
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

export async function action() {
  const place = await createRestaurant();
  return redirect(`/places/${place.id}/edit`);
}