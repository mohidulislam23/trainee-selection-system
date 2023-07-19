// ShowAllApplicant.jsx
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './ShowAllApplicant.scss';

const ShowAllApplicant = () => {
  const [applicantData, setApplicantData] = useState([]);
  const [sortField, setSortField] = useState('');
  const [sortDirection, setSortDirection] = useState('asc');

  useEffect(() => {
    fetchApplicants();
  }, []);

  const fetchApplicants = async () => {
    try {
      const response = await axios.get('http://localhost:8080/applicant/');
      setApplicantData(response.data);
    } catch (error) {
      console.error('Error fetching applicants', error);
    }
  };

  const handleSort = (field) => {
    if (sortField === field) {
      setSortDirection(sortDirection === 'asc' ? 'desc' : 'asc');
    } else {
      setSortField(field);
      setSortDirection('asc');
    }
  };

  const sortedApplicants = applicantData.sort((a, b) => {
    if (sortDirection === 'asc') {
      return a[sortField] < b[sortField] ? -1 : 1;
    } else {
      return a[sortField] > b[sortField] ? -1 : 1;
    }
  });

  return (
    <div className="show-all-applicant">
      <h2>All Applicants</h2>
      <table className="applicant-table">
        <thead>
          <tr>
            <th>Name</th>
            <th onClick={() => handleSort('passingYear')}>Passing Year</th>
            <th onClick={() => handleSort('cgpa')}>CGPA</th>
            <th onClick={() => handleSort('gender')}>Gender</th>
            <th onClick={() => handleSort('degreeName')}>Degree</th>
            <th onClick={() => handleSort('email')}>Email</th>
            <th onClick={() => handleSort('contactNumber')}>Contact Number</th>
            <th onClick={() => handleSort('educationalInstitute')}>Educational Institute</th>
            <th onClick={() => handleSort('dob')}>Date of Birth</th>
            <th >Present Address</th>
            <th>Approve</th>
          </tr>
        </thead>
        <tbody>
          {sortedApplicants.map((applicant) => (
            <tr key={applicant.applicantId}>
              <td>{applicant.firstName} {applicant.lastName}</td>
              <td>{applicant.passingYear}</td>
              <td>{applicant.cgpa}</td>
              <td>{applicant.gender}</td>
              <td>{applicant.degreeName}</td>
              <td>{applicant.email}</td>
              <td>{applicant.contactNumber}</td>
              <td>{applicant.educationalInstitute}</td>
              <td>{applicant.dob.substring(0,10)}</td>
              <td>{applicant.presentAddress}</td>
              <td>
                <button className="button-click-effect">Approve</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ShowAllApplicant;