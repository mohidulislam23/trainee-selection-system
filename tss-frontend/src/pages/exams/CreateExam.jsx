import React, { useState } from 'react';
import axios from 'axios';
import './CreateExam.scss'; // Import the SCSS file

const CreateExam = () => {
  const [examName, setExamName] = useState('');
  const [examCode, setExamCode] = useState('');
  const [successMessage, setSuccessMessage] = useState('');
  const [errorMessage, setErrorMessage] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();

    // Check if the examName and examCode fields are not empty
    if (!examName.trim() || !examCode.trim()) {
      setErrorMessage('Exam Name and Exam Code cannot be blank.');
      return;
    }

    // Create the exam object with the entered data
    const examData = {
      examName: examName,
      examCode: examCode,
    };

    // Send a POST request to create the exam
    axios.post('http://localhost:8080/exam/', examData)
      .then((response) => {
        setSuccessMessage('Exam created successfully.');
        setErrorMessage('');
        // Clear the form after successful submission
        setExamName('');
        setExamCode('');
      })
      .catch((error) => {
        setErrorMessage('Error creating exam. Please try again.');
        setSuccessMessage('');
      });
  };

  return (
    <div className="create-exam">
      <h2>Create Exam</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="examName">Exam Name:</label>
          <input
            type="text"
            id="examName"
            value={examName}
            onChange={(e) => setExamName(e.target.value)}
            required // Make the field required
          />
        </div>
        <div>
          <label htmlFor="examCode">Exam Code:</label>
          <input
            type="text"
            id="examCode"
            value={examCode}
            onChange={(e) => setExamCode(e.target.value)}
            required // Make the field required
          />
        </div>
        <div>
          <button type="submit">Create Exam</button>
        </div>
      </form>
      {successMessage && <p className="success-message">{successMessage}</p>}
      {errorMessage && <p className="error-message">{errorMessage}</p>}
    </div>
  );
};

export default CreateExam;
