import React, { useState } from 'react';
import axios from 'axios';
import './CreateCircular.scss'; 

const CreateCircular = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [successMessage, setSuccessMessage] = useState('');
  const [errorMessage, setErrorMessage] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();

    // Check if input fields are not blank
    if (!title || !description) {
      setErrorMessage('Please fill in all the fields.');
      setSuccessMessage('');
      return;
    }

    // Create the circular object with the entered data
    const circularData = {
      title,
      description,
    };

    // Send a POST request to create the circular
    axios.post('http://localhost:8080/circular/', circularData)
      .then(() => {
        setSuccessMessage('Circular created successfully.');
        setErrorMessage('');
        // Clear the form after successful submission
        setTitle('');
        setDescription('');
      })
      .catch((error) => {
        setErrorMessage('Error creating circular. Please try again.');
        setSuccessMessage('');
      });
  };

  return (
    <div className="create-circular">
      <h2>Create Circular</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="title">Training Title:</label>
          <input
            type="text"
            id="title"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            required
          />
        </div>
        <div>
          <label htmlFor="description">Description:</label>
          <textarea
            id="description"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            required
          />
        </div>
        <div>
          <button type="submit">Create</button>
        </div>
      </form>
      {successMessage && <p className="success-message">{successMessage}</p>}
      {errorMessage && <p className="error-message">{errorMessage}</p>}
    </div>
  );
};

export default CreateCircular;
