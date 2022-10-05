import React from "react";
import ReactDOM from "react-dom/client";
import {
  createBrowserRouter,
  RouterProvider,
  Route,
} from "react-router-dom";
import "./index.css";

import Root, { loader as rootLoader, action as rootAction } from './routes/root';
import ErrorPage from "./routes/error";
import Restaurant, { loader as placeLoader} from "./routes/restaurant";
import EditRestaurant, { action as editAction } from "./routes/edit";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Root />,
    errorElement: <ErrorPage />,
    loader: rootLoader,
    action: rootAction,
    children: [
      {
        path: "places/:placeId",
        element: <Restaurant />,
        loader: placeLoader,
      },
      {
        path: "places/:placeId/edit",
        element: <EditRestaurant />,
        loader: placeLoader,
        action: editAction,
      }
    ]
  },
]);

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);