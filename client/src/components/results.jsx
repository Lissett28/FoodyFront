import { useEffect, useState } from 'react';

const Results = props => {
  const { result } = props; 
  const [results, setResults] = useState(null);

  useEffect(() => {
    setResults(result);
  }, [result]);

  return (
    <>
      {results ? (
        <div>
          <h2>{results.name}</h2>
          <p>
            View on <a href={results.url} target="_blank" rel="noreferrer">Yelp</a>
          </p>
          <p>Categories:</p>
          <ul>
            {results.categories.map((cat, i) => (
              <li key={i}>{cat.title}</li>
            ))}
          </ul>
          <p>
            Find directions on{" "}
            <a
              href={`https://www.google.com/maps/search/${results.location.display_address.join(
                " "
              )}`}
            >
              Google Maps
            </a>
          </p>
        </div>
      ) : (
        <div></div>
      )}
    </>
  );
};


export default Results;
