import React from 'react';
import './ApplicantNav.scss';
import { Navbar, Container, Nav } from 'react-bootstrap';
import { Link } from 'react-router-dom';

const ApplicantNav = () => {
  return (
    <Navbar bg="dark" data-bs-theme="dark">
      <Container>
        <Navbar.Brand href="/">Applicant Navbar</Navbar.Brand>
        <Nav className="me-auto">
          <Link to="/" className="nav-link">Home</Link>
          <Link to="/registration-form" className="nav-link">Registration Form</Link>
          <Link to="/login-form" className="nav-link">Login Form</Link>
          <Link to="/applicant-register" className="nav-link">Applicant Register</Link>
          <Link to="/apply" className="nav-link">Apply</Link>
          <Link to="/notification" className="nav-link">Notification</Link>
          <Link to="/resource-upload" className="nav-link">Resource</Link>
          <Link to="/admitcard" className="nav-link">Admitcard</Link>
        </Nav>
      </Container>
    </Navbar>
  );
};

export default ApplicantNav;
