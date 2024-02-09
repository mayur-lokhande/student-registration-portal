import React from "react";
import "./App.css";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Navbar from "./layout/Navbar";

import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AddStudent from "./components/AddStudent";
import EditStudent from "./components/EditStudent";
import ViewStudentById from "./components/ViewStudentById";
import ViewStatus from "./components/ViewStatus";
import AdminDashboard from "./pages/AdminDashboard";
import HomePage from "./pages/HomePage";
import AdminLogin from "./pages/AdminLogin";
import UserLogin from "./pages/UserLogin";
import "react-toastify/dist/ReactToastify.css";

function App() {
  return (
    <main className="App">
      <Router>
        <Navbar />
        <Routes>
          <Route exact path="/" element={<HomePage />} />
          <Route exact path="/admin" element={<AdminLogin />} />
          <Route exact path="/user" element={<UserLogin />} />

          <Route exact path="/admin/dashboard" element={<AdminDashboard />} />
          <Route exact path="/addstudent" element={<AddStudent />} />
          <Route exact path="/editstudent/:id" element={<EditStudent />} />
          <Route
            exact
            path="/viewstudentbyid/:id"
            element={<ViewStudentById />}
          />

          <Route exact path="/user/viewstatus/:id" element={<ViewStatus />} />
        </Routes>
      </Router>
    </main>
  );
}

export default App;
