import React, { useState } from 'react';
import axios from 'axios';
import './UploadWrittenMark.scss';

const UploadWrittenMark = () => {
  const [selectedFile, setSelectedFile] = useState(null);
  const [uploadStatus, setUploadStatus] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const [hiddenCode, setHiddenCode] = useState('');
  const [mark, setMark] = useState('');

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

      const token = localStorage.getItem('token');
      await axios.put('http://localhost:8080/written/upload', formData, {
        headers: {
          Authorization: `Bearer ${token}`,
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

  const handleManualUpload = async (event) => {
    event.preventDefault();

    if (!hiddenCode || !mark) {
      setUploadStatus('Please enter both hidden code and mark.');
      return;
    }

    try {
      setIsLoading(true);
      setUploadStatus('');

      const token = localStorage.getItem('token');
      await axios.put(`http://localhost:8080/written/update/${hiddenCode}`, { mark }, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });

      setUploadStatus('Manual mark upload successful!');
    } catch (error) {
      console.error('Error uploading manual mark:', error);
      setUploadStatus('Error uploading manual mark. Please try again.');
    } finally {
      setIsLoading(false);
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

      <div className="manual-upload-form">
        <h3>Manual Mark Upload</h3>
        <form onSubmit={handleManualUpload}>
          <div className="form-group">
            <label htmlFor="hiddenCodeInput">Hidden Code:</label>
            <input
              type="number"
              id="hiddenCodeInput"
              value={hiddenCode}
              onChange={(e) => setHiddenCode(e.target.value)}
            />
          </div>
          <div className="form-group">
            <label htmlFor="markInput">Mark:</label>
            <input
              type="number"
              id="markInput"
              value={mark}
              onChange={(e) => setMark(e.target.value)}
            />
          </div>
          <button type="submit">Upload Manual Mark</button>
        </form>
      </div>
    </div>
  );
};

export default UploadWrittenMark;