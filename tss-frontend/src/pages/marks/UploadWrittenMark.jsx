import React, { useState } from 'react';
import axios from 'axios';
import './UploadWrittenMark.scss';

const UploadWrittenMark = () => {
  const [selectedFile, setSelectedFile] = useState(null);
  const [uploadStatus, setUploadStatus] = useState('');
  const [isLoading, setIsLoading] = useState(false);

  const handleFileChange = (event) => {
    setSelectedFile(event.target.files[0]);
  };

  const handleUpload = async () => {
    if (!selectedFile) {
      setUploadStatus('Please select a file first.');
      return;
    }

    try {
      const formData = new FormData();
      formData.append('file', selectedFile);

      setIsLoading(true); 
      setUploadStatus(''); 

      await axios.put('http://localhost:8080/written/upload', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });

      setUploadStatus('File uploaded successfully!');
    } catch (error) {
      console.error('Error uploading file:', error);
      setUploadStatus('Error uploading file. Please try again.');
    } finally {
      setIsLoading(false); // Hide loading spinner
    }
  };

  return (
    <div className={`upload-written-mark ${isLoading ? 'loading' : ''}`}>
      <h2>Upload Written Marks</h2>
      <div className="file-input-container">
        <input type="file" id="fileInput" onChange={handleFileChange} /> 
        <label htmlFor="fileInput">Choose File</label>
        
        <button onClick={handleUpload}>Upload</button>
        <div className="loading-spinner"></div>
      </div>
      {uploadStatus && <p className={`upload-status ${uploadStatus ? 'show' : ''}`}>{uploadStatus}</p>}
    </div>
  );
};

export default UploadWrittenMark;
