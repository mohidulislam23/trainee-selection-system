import React, { useState } from 'react';
import axios from 'axios';
import jsPDF from 'jspdf';
import 'jspdf-autotable';
import './GetAdmitCard.scss';

const GetAdmitCard = () => {
  const [applicantId, setApplicantId] = useState('');
  const [admitCardData, setAdmitCardData] = useState(null);
  const [error, setError] = useState('');

  const handleApplicantIdChange = (e) => {
    setApplicantId(e.target.value);
  };

  const fetchAdmitCardData = () => {
    setError('');

    if (!applicantId) {
      setError('Please enter a valid Applicant ID.');
      return;
    }

    const decodedApplicantId = atob(applicantId);

    // Get the token from localStorage
    const token = localStorage.getItem('token');

    axios
      .get(`http://localhost:8080/admitcard/${decodedApplicantId}`, {
        headers: {
          Authorization: `Bearer ${token}`, // Include the token in the Authorization header
        },
      })
      .then((response) => {
        setAdmitCardData(response.data);
      })
      .catch((error) => {
        setError('Your admit card is not ready yet.');
        console.error('Error fetching admit card data:', error);
      });
  };

  const handleDownloadAdmitCard = () => {
    setError('');

    if (!admitCardData) {
      setError('Admit card data not found.');
      return;
    }

    // Get the token from localStorage
    const token = localStorage.getItem('token');
    const decodedApplicantId = atob(applicantId);

    axios
    .get(`http://localhost:8080/admitcard/${decodedApplicantId}/pdf`, {
      responseType: 'arraybuffer', // Ensure response is treated as binary data
      headers: {
        Authorization: `Bearer ${token}`, // Include the token in the Authorization header
      },
    })
      .then((response) => {
        // Simulate downloading the PDF
        const pdfData = new Uint8Array(response.data);
        const blob = new Blob([pdfData], { type: 'application/pdf' });
        const url = URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = `admit_card_${admitCardData.serialNumber}.pdf`;
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
      })
      .catch((error) => {
        setError('Error downloading admit card.');
        console.error('Error downloading admit card:', error);
      });
  };

  
  return (
    <div className="get-admit-card">
      <h2>Get Admit Card</h2>
      <div className="input-container">
        <label htmlFor="applicantId">Enter Your Applicant ID:</label>
        <input
          type="text"
          id="applicantId"
          value={applicantId}
          onChange={handleApplicantIdChange}
        />
        <button onClick={fetchAdmitCardData}>Fetch Admit Card</button>
      </div>

      {error && <p className="error-message">{error}</p>}

      {admitCardData ? (
        <div className="admit-card-details">
          <h3>Admit Card Details</h3>
          <table>
            <tbody>
              <tr>
                <td>Exam Name:</td>
                <td>{admitCardData.candidateId.circular.title}</td>
              </tr>
              <tr>
                <td>Candidate Name:</td>
                <td>
                  {`${admitCardData.candidateId.applicant.firstName} ${admitCardData.candidateId.applicant.lastName}`}
                </td>
              </tr>
              <tr>
                <td>Serial Number:</td>
                <td>{admitCardData.serialNumber}</td>
              </tr>
            </tbody>
          </table>
          <button onClick={handleDownloadAdmitCard}>Download Admit Card</button>
        </div>
      ) : null}
    </div>
  );
};

export default GetAdmitCard;
