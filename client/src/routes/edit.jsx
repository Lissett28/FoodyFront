import { Form, useLoaderData, redirect } from "react-router-dom";
import { updateRestaurant } from "../restaurants";

export default function EditRestaurant() {
  const restaurant = useLoaderData();

  return (
    <Form method="post" id="place-form">
      <p>
        {restaurant.name}
      </p>
      <label>
        <span>Notes</span>
        <textarea
          name="notes"
          defaultValue={restaurant.notes}
          rows={6}
        />
      </label>
      <p>
        <button type="submit">Save</button>
        <button type="button">Cancel</button>
      </p>
    </Form>
  );
}

export async function action ({ request, params }) {
  const formData = await request.formData();
  const updates = Object.fromEntries(formData);
  await updateRestaurant(params.placeId, updates);
  return redirect(`/places/${params.placeId}`)
}