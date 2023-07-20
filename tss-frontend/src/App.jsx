import './App.css';
import React from 'react';
// import { BrowserRouter as Router,Route } from 'react-router-dom'; // Import Router, Switch, and Route
import Footer from './components/footer/Footer';
import RegistrationForm from './pages/registration/RegistrationForm';
import LoginForm from './pages/login/LoginForm';
import CircularShow from './components/circular/CircularShow';
import ApplyPage from './pages/apply/ApplyPage';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import ShowAllApplicant from './pages/applicant/ShowAllApplicant';
import ShowAndSendMail from './components/mail/ShowAndSendMail';
import CreateMail from './components/mail/CreateMail';
import Result from './pages/result/Result';
import Result2 from './pages/result/Result2';
import CreateExam from './pages/exams/CreateExam';
import UploadMark from './pages/marks/UploadMark';
import ApplicantRegister from './pages/applicant/ApplicantRegister';

const App = () => {
  return (
    <div>

      <header>
        <h1>Welcome to My App</h1>
      </header>
      <main>
        <p>This is the main content of the app.</p>
      </main>

      <ApplicantRegister />

      {/* <UploadMark /> */}

      {/* <CreateExam /> */}

      {/* <Result /> */}
      
      {/* <CreateMail /> */}
      {/* <ShowAndSendMail /> */}

      {/* <ShowAllApplicant /> */}

      <Routes >
        <Route path="/circular" element={<CircularShow></CircularShow>}></Route>
      </Routes>

      {/* <RegistrationForm /> */}
      {/* <LoginForm /> */}
      <Footer />


    </div>
  );
};

export default App;
