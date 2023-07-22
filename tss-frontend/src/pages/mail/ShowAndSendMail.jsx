import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './ShowAndSendMail.scss';

const ShowAndSendMail = () => {
  const [mails, setMails] = useState([]);
  const [selectedMailId, setSelectedMailId] = useState(null);
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [showForm, setShowForm] = useState(false);

  useEffect(() => {
    // Fetch data from the API endpoint
    axios.get('http://localhost:8080/mail/')
      .then((response) => {
        setMails(response.data);
      })
      .catch((error) => {
        console.error('Error fetching mails:', error);
      });
  }, []);

  const handleSendToApproved = (mailId) => {
    setSelectedMailId(mailId);
    setShowForm(true);
  };

  const handleSendData = () => {
    // Validate if Username and Password are not blank
    if (username.trim() === '' || password.trim() === '') {
      console.error('Username and Password cannot be blank.');
      return;
    }

    // Prepare the JSON data to send
    const jsonData = {
      username,
      password,
    };

    // Send data to the API endpoint
    axios.post(`http://localhost:8080/mail/sendToApplicants/${selectedMailId}`, jsonData)
      .then((response) => {
        // Handle successful response if needed
        console.log('Data sent successfully:', response.data);
      })
      .catch((error) => {
        // Handle error if needed
        console.error('Error sending data:', error);
      });

    // Reset form and selected mail
    setShowForm(false);
    setSelectedMailId(null);
    setUsername('');
    setPassword('');
  };

  const handleCloseForm = () => {
    setShowForm(false);
    setSelectedMailId(null);
    setUsername('');
    setPassword('');
  };

  return (
    <div className="mail-list">
      {mails.map((mail) => (
        <div className="mail-card" key={mail.mailId}>
          <h3>{mail.subject}</h3>
          <p>{mail.body}</p>
          <p className="timestamp">{new Date(mail.timestamp).toLocaleString()}</p>
          <button onClick={() => handleSendToApproved(mail.mailId)}>Send to Approved</button>
        </div>
      ))}
      {showForm && (
        <div className="form">
          <h3>Provide Admin/Sender Email</h3>
          <input
            type="text"
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <button onClick={handleSendData}>Send</button>
          <button className="close-button" onClick={handleCloseForm}>X</button>
        </div>
      )}
    </div>
  );
};

export default ShowAndSendMail;