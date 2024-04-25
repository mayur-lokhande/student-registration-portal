import React from "react";
import "../App.css";

const HomePage = () => {
  return (
    <div>
      <div
        className="card text-bg-dark mb-3 mx-auto my-5"
        style={{ maxWidth: "45rem" }}
      >
        <div className="card-body">
          
          <marquee><h4 className="card-title fw-bold">
            Welcome to the ABC College Website
          </h4></marquee>
        </div>
      </div>
    </div>
  );
};
export default HomePage;
