import React from "react";
import { Link } from "react-router-dom";
import "./Navbar.css";
export default function Navbar() {

  

  return (
    <div>
      <nav className="navbar navbar-expand-lg custom-bg">
        <div className="container-fluid">
          <Link className="navbar-brand text-white" to="/">
            Student Registration Portal
          </Link>
          <div>
            
            <button
              className="navbar-toggler"
              type="button"
              data-bs-toggle="collapse"
              data-bs-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span className="navbar-toggler-icon"></span>
            </button>
            {/* <Link className="btn btn-outline-light" to="/addstudent">
              Register Student
            </Link> */}
          
          </div>
        </div>
      </nav>
    </div>
  );
}
