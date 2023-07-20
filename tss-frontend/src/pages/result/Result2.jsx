import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './Result.scss';

const Result2 = () => {
  const [results, setResults] = useState([]);
  const [sortDirection, setSortDirection] = useState('asc');
  const [isButtonClicked, setIsButtonClicked] = useState(false);

  useEffect(() => {
    // Fetch data from the API endpoint
    axios.get('http://localhost:8080/result/all')
      .then((response) => {
        setResults(response.data);
      })
      .catch((error) => {
        console.error('Error fetching results:', error);
      });
  }, []);

  useEffect(() => {
    // Fetch applicant details and update the results array
    const fetchApplicantDetails = async () => {
      const updatedResults = [];
      for (const result of results) {
        try {
          const response = await axios.get(
            `http://localhost:8080/applicant/${result.applicantId}`
          );
          const { firstName, lastName } = response.data;
          updatedResults.push({ ...result, firstName, lastName });
        } catch (error) {
          console.error(
            `Error fetching applicant details for ID ${result.applicantId}:`,
            error
          );
        }
      }
      setResults(updatedResults);
    };

    // Fetch applicant details only when results change
    fetchApplicantDetails();
  }, [results]);

  const handleShowScore = () => {
    if (!isButtonClicked) {
      // Send a blank POST request to generate scores
      axios.post('http://localhost:8080/result/generate', {})
        .then(() => {
          // Set the isButtonClicked state to true to hide the button
          setIsButtonClicked(true);
          window.alert(
            "Refresh the page please to show the results."
          );
        })
        .catch((error) => {
          console.error('Error generating scores:', error);
        });
    }
  };

  const handleSortByScore = () => {
    // Toggle sorting direction between 'asc' and 'desc'
    const newSortDirection = sortDirection === 'asc' ? 'desc' : 'asc';
    setSortDirection(newSortDirection);

    // Sort the results array by the score in the selected direction
    const sortedResults = [...results].sort((a, b) => {
      if (newSortDirection === 'asc') {
        return a.score - b.score;
      } else {
        return b.score - a.score;
      }
    });

    setResults(sortedResults);
  };

  const getSortIcon = () => {
    return sortDirection === 'asc' ? '▲' : '▼';
  };

  return (
    <div className="result">
      {!isButtonClicked && (
        <button onClick={handleShowScore}>Generate Score</button>
      )}
      <table>
        <thead>
          <tr>
            <th>Applicant ID</th>
            <th onClick={handleSortByScore}>
              Circular Title {getSortIcon()}{' '}
            </th>
            <th onClick={handleSortByScore}>
              Score {getSortIcon()}
            </th>
            <th>First Name</th>
            <th>Last Name</th>
          </tr>
        </thead>
        <tbody>
          {results.map((result) => (
            <tr key={result.resultId}>
              <td>{result.applicantId}</td>
              <td>{result.circularTitle}</td>
              <td>{result.score}</td>
              <td>{result.firstName}</td>
              <td>{result.lastName}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Result2;
