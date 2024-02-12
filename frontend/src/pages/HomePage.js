import React from "react";
import { Link } from "react-router-dom";
import "../App.css";

const HomePage = () => {
  return (
    <div>
      <div
        className="card text-bg-dark mb-3 mx-auto my-5"
        style={{ maxWidth: "45rem" }}
      >
        <div className="card-body">
          <h5 className="card-title fw-bold">
            Welcome to the Student Registration Portal
          </h5>
          <p className="card-text">Please Login to portal</p>
          <Link className="btn btn-outline-primary mx-2 fw-bold" to="/admin">
            Admin Login
          </Link>
          <Link className="btn btn-outline-primary mx-2 fw-bold" to="/user">
            User Login
          </Link>
          <hr />
          <p className="card-text">Student Registration Form</p>
          <Link
            className="btn btn-outline-primary mx-2 fw-bold"
            to="/addstudent"
          >
            Student Registeration
          </Link>
        </div>
      </div>
    </div>
  );
};
export default HomePage;
