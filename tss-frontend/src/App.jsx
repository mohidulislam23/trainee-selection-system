import './App.css';
import React from 'react';
// import { BrowserRouter as Router,Route } from 'react-router-dom'; // Import Router, Switch, and Route
// import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Footer from './components/footer/Footer';
import RegistrationForm from './pages/registration/RegistrationForm';
import LoginForm from './pages/login/LoginForm';
import CircularShow from './pages/circular/CircularShow';
import ApplyPage from './pages/apply/ApplyPage';
import { BrowserRouter, Route, Router, Routes } from 'react-router-dom';
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
import Header from './components/header/Header';
import AdminNav from './components/navbar/AdminNav';
import Home from './pages/home/Home';
import ApplicantNav from './components/navbar/ApplicantNav';

const App = () => {
  return (
    <div>



      <header>
        <Header />
        <AdminNav />
        <ApplicantNav />




      </header>



      <main style={{
        backgroundColor: '', padding: 80
        
      }}>

        <Routes>
          <Route exact path="/" element={<Home />} />
          <Route path="/create-circular" element={<CreateCircular />} />
          <Route path="/circular-show" element={<CircularShow />} />
          <Route path="/upload-mark" element={<UploadMark />} />
          <Route path="/create-exam" element={<CreateExam />} />
          <Route path="/result" element={<Result />} />
          <Route path="/create-mail" element={<CreateMail />} />
          <Route path="/show-send-mail" element={<ShowAndSendMail />} />
          <Route path="/show-all-applicant" element={<ShowAllApplicant />} />
          <Route path="/approve" element={<Approve />} />

          <Route path="/" element={<Home />} />
          <Route path="/registration-form" element={<RegistrationForm />} />
          <Route path="/login-form" element={<LoginForm />} />
          <Route path="/applicant-register" element={<ApplicantRegister />} />
          <Route path="/apply" element={<Apply />} />
          <Route path="/notification" element={<NotificationProp applicantId={3} />} />
        </Routes>


        {/* <p>This is the main content of the app.</p> */}
      </main>
      <footer><Footer /></footer>


      {/* Admin work */}
      {/* ---------------------------------------- */}
      {/* <CreateCircular />
      <CircularShow />

      <UploadMark />

      <CreateExam />

      <Result />

      <CreateMail />
      <ShowAndSendMail />

      <ShowAllApplicant />

      <Approve /> */}



      {/* Applicant work */}
      {/* ---------------------------------------- */}

      {/* <RegistrationForm />


      <ApplicantRegister />

      <Apply />

      <NotificationProp applicantId={2}/> */}
      {/* <NotificationProp applicantId={5} /> */}


      {/* <GetAdmitCard /> */}


      {/* Evaluator work */}
      {/* ---------------------------------------- */}

      {/* <UploadWrittenMark /> */}


      {/* <Routes >
        <Route path="/circular" element={<CircularShow></CircularShow>}></Route>
      </Routes> */}





    </div>
  );
};

export default App;
