import React, { useState } from 'react';
import './ResourceUpload.scss'; // Import the SCSS file

const ResourceUpload = ({ applicantId }) => {
  const [photo, setPhoto] = useState(null);
  const [cv, setCv] = useState(null);
  const [uploadStatus, setUploadStatus] = useState(null);

  const handlePhotoChange = (e) => {
    setPhoto(e.target.files[0]);
  };

  const handleCvChange = (e) => {
    setCv(e.target.files[0]);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append('photo', photo);
    formData.append('cv', cv);

    const token = localStorage.getItem('token');
    const requestOptions = {
      method: 'POST',
      headers: {
        Authorization: `Bearer ${token}`,
      },
      body: formData,
    };
    fetch(`http://localhost:8080/resource/applicant/${applicantId}`, requestOptions)
      .then((response) => response.json())
      .then((data) => {
        // Handle the response data if needed
        console.log(data);
        // Show success message and clear input fields
        setUploadStatus('success');
        setPhoto(null);
        setCv(null);
      })
      .catch((error) => {
        console.error('Error uploading resources:', error);
        // Show error message if needed
        setUploadStatus('error');
      });
  };

  return (
    <div className="resource-upload">
      {uploadStatus === 'success' && <div className="success-message">Upload successful!</div>}
      {uploadStatus === 'error' && <div className="error-message">Error uploading resources. Please try again.</div>}
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="photo">Upload Photo:</label>
          <input type="file" id="photo" name="photo" onChange={handlePhotoChange} />
        </div>
        <div>
          <label htmlFor="cv">Upload CV:</label>
          <input type="file" id="cv" name="cv" onChange={handleCvChange} />
        </div>
        <button type="submit">Upload</button>
      </form>
    </div>
  );
};

export default ResourceUpload;
