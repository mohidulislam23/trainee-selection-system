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

const App = () => {
  return (
    <div>

      <header>
        <h1>Welcome to My App</h1>
      </header>
      <main>
        <p>This is the main content of the app.</p>
      </main>

      <ShowAllApplicant />

      <Routes >
        <Route path="/circular" element={<CircularShow></CircularShow>}></Route>
        {/* <Route path="/home" element={<Home></Home>}></Route>
        <Route path="/createBatch" element={<BatchCreate></BatchCreate>}></Route>
        <Route path="/allBatch" element={<AllBatch></AllBatch>}></Route>
        <Route path="/traineeRegister" element={<Trainee></Trainee>}></Route>
        <Route path="/trainerRegister" element={<Trainer></Trainer>}></Route> */}
      </Routes>

      {/* <RegistrationForm />
      <LoginForm /> */}
      <Footer />


    </div>
  );
};

export default App;
