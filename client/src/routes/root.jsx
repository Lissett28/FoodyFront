import axios from "axios";
import { useState } from "react";
import { useNavigation } from "react-router-dom";
import Results from "../components/results";

export default function Root() {
  const [results, setResults] = useState();
  const [query, setQuery] = useState({address: "", radius: 0});
  const navigation = useNavigation();

  const handleQuerySubmit = (event) => {
    event.preventDefault();
    axios.get(`http://localhost:8080/api/locate/find/${query.address}&${query.radius}`)
    .then(res => {
      setResults(res.data.businesses[0]);
    });
  };

  const handleAddressChange = event => {
    setQuery({...query, address: event.target.value});
  }

  const handleRadiusChange = event => {
    setQuery({...query, radius: event.target.value});
  }

  return (
    <>
      <div
        id="detail"
        className={navigation.state === "loading" ? "loading" : ""}
      >
        <h1>Foody</h1>
        <div>
          <form id="search-form" onSubmit={handleQuerySubmit} role="search">
            <label>
              Your address:{" "}
              <input
                id="address"
                onChange={handleAddressChange}
                value={query.address}
                aria-label="Enter your address"
                type="search"
                name="address"
              />
            </label>
            <label>
              Radius to search (meters){" "}
              <input
                id="radius"
                onChange={handleRadiusChange}
                value={query.radius}
                aria-label="Search radius"
                type="number"
                name="radius"
              />
            </label>
            <div id="search-spinner" aria-hidden hidden={true} />
            <div className="sr-only" aria-live="polite"></div>
            <button type="submit">Search</button>
          </form>
        </div>

        <Results result={ results } />
      </div>
    </>
  );
}
