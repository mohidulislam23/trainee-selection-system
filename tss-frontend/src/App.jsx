import './App.css';
import React from 'react';
// import { BrowserRouter as Router,Route } from 'react-router-dom'; // Import Router, Switch, and Route
import Footer from './components/footer/Footer';
import RegistrationForm from './pages/registration/RegistrationForm';
import LoginForm from './pages/login/LoginForm';
import CircularShow from './pages/circular/CircularShow';
import ApplyPage from './pages/apply/ApplyPage';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import ShowAllApplicant from './pages/applicant/ShowAllApplicant';
import ShowAndSendMail from './pages/mail/ShowAndSendMail';
import CreateMail from './pages/mail/CreateMail';
import Result from './pages/result/Result';
import Result2 from './pages/result/Result2';
import CreateExam from './pages/exams/CreateExam';
import UploadMark from './pages/marks/UploadMark';
import ApplicantRegister from './pages/applicant/ApplicantRegister';
import CreateCircular from './pages/circular/CreateCircular';
import Apply from './pages/apply/Apply';
import Apply2 from './pages/apply/Apply';
import GetAdmitCard from './pages/admitcard/GetAdmitCard';
import NotificationProp from './pages/notification/NotificationProp';
import Approve from './pages/approve/Approve';
import UploadWrittenMark from './pages/marks/UploadWrittenMark';

const App = () => {
  return (
    <div>

      <header>
        <h1>Trainee Selection System</h1>
      </header>
      <main>
        <p>This is the main content of the app.</p>
      </main>


      {/* Admin work */}
      {/* ---------------------------------------- */}
      {/* <CreateCircular /> */}
      {/* <CircularShow /> */}

      {/* <UploadMark /> */}

      {/* <CreateExam /> */}

      {/* <Result /> */}

      {/* <CreateMail /> */}
      {/* <ShowAndSendMail /> */}

      {/* <ShowAllApplicant /> */}

      {/* <Approve /> */}



      {/* Applicant work */}
      {/* ---------------------------------------- */}

      {/* <RegistrationForm /> */}

      {/* <LoginForm /> */}

      {/* <ApplicantRegister /> */}

      {/* <Apply /> */}

      {/* <NotificationProp applicantId={2}/> */}
      {/* <NotificationProp applicantId={5} /> */}
      

      {/* <GetAdmitCard /> */}


      {/* Evaluator work */}
      {/* ---------------------------------------- */}

      <UploadWrittenMark />


      <Routes >
        <Route path="/circular" element={<CircularShow></CircularShow>}></Route>
      </Routes>

      
      <Footer />


    </div>
  );
};

export default App;
