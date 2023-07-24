import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './Apply.scss';

const Apply = () => {
    const [circularData, setCircularData] = useState([]);
    const [showForm, setShowForm] = useState(false);
    const [applicantId, setApplicantId] = useState('');
    const [selectedCircularId, setSelectedCircularId] = useState('');
    const [errorMessages, setErrorMessages] = useState({
        applicantId: '',
    });
    const [appliedCirculars, setAppliedCirculars] = useState([]);

    // Fetch circular data from the server
    const fetchCircularData = async () => {
        try {
            const token = localStorage.getItem('token');
            const response = await axios.get('http://localhost:8080/circular/', {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            setCircularData(response.data);
        } catch (error) {
            console.error('Error fetching circular data:', error);
        }
    };

    // Fetch the list of circulars the user has already applied for on component mount
    useEffect(() => {
        // For demonstration purposes, we assume the API endpoint '/applied-circulars/' exists
        // and returns an array of circular IDs the user has applied for.
        const fetchAppliedCirculars = async () => {
            try {
                const token = localStorage.getItem('token');
                const response = await axios.get('http://localhost:8080/applied-circulars/', {
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                });
                setAppliedCirculars(response.data);
            } catch (error) {
                console.error('Error fetching applied circulars:', error);
            }
        };

        fetchCircularData();
        fetchAppliedCirculars();
    }, []);

    // Handle apply button click and show the form
    const handleApplyClick = (circularId) => {
        if (!appliedCirculars.includes(circularId)) {
            setShowForm(true);
            setSelectedCircularId(circularId);
        }
    };

    // Handle form submission
    const handleSubmit = async (event) => {
        event.preventDefault();
        setErrorMessages({ applicantId: '' });

        // Validate input fields
        let hasError = false;

        if (!applicantId.trim()) {
            setErrorMessages((prevState) => ({
                ...prevState,
                applicantId: 'Applicant ID cannot be blank',
            }));
            hasError = true;
        }

        if (hasError) {
            return;
        }

        // Make the API call to submit the application
        const data = {
            applicant: {
                applicantId: parseInt(applicantId),
            },
            circular: {
                circularId: selectedCircularId,
            },
            approved: false,
        };

        try {
            const token = localStorage.getItem('token');
            await axios.post('http://localhost:8080/approval/', data, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            console.log('Application submitted successfully:', data);
        } catch (error) {
            console.error('Error submitting application:', error.response?.data?.message || error.message);
            setErrorMessages((prevState) => ({
                ...prevState,
                applicantId: error.response?.data?.message || 'An error occurred while submitting the application.',
            }));
        }

        // Reset form and hide it
        setShowForm(false);
        setApplicantId('');
        setSelectedCircularId('');
    };

    // Handle cancel button click to hide the form
    const handleCancelClick = () => {
        setShowForm(false);
        setApplicantId('');
        setSelectedCircularId('');
        setErrorMessages({ applicantId: '' });
    };

    // Function to handle decoding and update applicantId state
    const handleApplicantIdChange = (encodedApplicantId) => {
        try {
            const decodedApplicantId = atob(encodedApplicantId);
            setApplicantId(decodedApplicantId);
        } catch (error) {
            console.error('Error decoding applicantId:', error);
        }
    };

    return (
        <div className="apply-container">
            <h2>Available Circulars</h2>
            <div className="card-container">
                {circularData.map((circular) => (
                    <div key={circular.circularId} className="circular-card">
                        <h3>{circular.title}</h3>
                        <p>{circular.description}</p>
                        <button
                            onClick={() => handleApplyClick(circular.circularId)}
                            disabled={appliedCirculars.includes(circular.circularId)}
                        >
                            {appliedCirculars.includes(circular.circularId) ? 'Applied' : 'Apply'}
                        </button>
                    </div>
                ))}
            </div>

            {showForm && (
                <div className="apply-form">
                    <span className="close-btn" onClick={handleCancelClick}>
                        &times;
                    </span>
                    <form onSubmit={handleSubmit}>
                        <label htmlFor="applicantId">Applicant ID:</label>
                        <input
                            type="text"
                            id="applicantId"
                            //value={applicantId} do not use it, rather delete
                            onChange={(e) => handleApplicantIdChange(e.target.value)}
                        />
                        {errorMessages.applicantId && <p className="error-message">{errorMessages.applicantId}</p>}
                        <button type="submit">Apply</button>
                    </form>
                </div>
            )}
        </div>
    );
};

export default Apply;
