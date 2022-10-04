import { Form, useLoaderData } from "react-router-dom";
import { getRestaurant } from "../restaurants";

export default function Restaurant() {
  const place = useLoaderData();

  return (
    <div id="place">
      <div>
        <img
          key={place.avatar}
          src={place.avatar || null}
          alt="Placeholder"
        />
      </div>

      <div>
        <h1>
          {place.name ? (
            <>
              { place.name }
            </>
          ) : (
            <i>No Name Provided</i>
          )}{" "}
          <Favorite place={ place } />
        </h1>

        {place.website && (
          <p>
            <a
              target="_blank"
              href={ place.website }
              rel="noreferrer"
            >
              { place.website }
            </a>
          </p>
        )}

        {place.notes && <p>{place.notes}</p>}

        <div>
          <Form action="edit">
            <button type="submit">Edit</button>
          </Form>
          <Form
            method="post"
            action="destroy"
            onSubmit={(event) => {
              
              if (// eslint-disable-next-line no-restricted-globals
                !confirm(
                  "Please confirm you want to delete this record."
                )
              ) {
                event.preventDefault();
              }
            }}
          >
            <button type="submit">Delete</button>
          </Form>
        </div>
      </div>
    </div>
  );
}

function Favorite({ place }) {
  // yes, this is a `let` for later
  let favorite = place.favorite;
  return (
    <Form method="post">
      <button
        name="favorite"
        value={favorite ? "false" : "true"}
        aria-label={
          favorite
            ? "Remove from favorites"
            : "Add to favorites"
        }
      >
        {favorite ? "★" : "☆"}
      </button>
    </Form>
  );
}

export async function loader({ params }) {
  return getRestaurant(params.placeId);
}
