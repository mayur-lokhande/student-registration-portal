import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

export default function AddStudent() {
  let navigate = useNavigate();

  const [student, setStudent] = useState({
    name: "",
    emailId: "",
    mobileNumber: "",
    address: "",
    marks: "",
    status: "Pending",
    comment: "NA",
  });

  // Add state to hold the selected file
  const [file, setFile] = useState(null);

  const { name, emailId, mobileNumber, address, marks, status, comment } =
    student;

  const onInputChange = (event) => {
    if (event.target.name === "file") {
      // Update file state
      setFile(event.target.files);
    } else {
      // Update other input fields
      setStudent({ ...student, [event.target.name]: event.target.value });
    }
  };

  const onSubmit = async (e) => {
    e.preventDefault();

    try {
      const formData = new FormData();
      for (let i = 0; i < file.length; i++) {
        formData.append(`files`, file[i]);
      }
      formData.append("name", name);
      formData.append("emailId", emailId);
      formData.append("mobileNumber", mobileNumber);
      formData.append("address", address);
      formData.append("marks", marks);
      formData.append("status", status);
      formData.append("comment", comment);

      const response = await axios.post(
        "http://localhost:8181/api/students",
        formData,
        {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        }
      );

      alert(
        `Your Registration Id: ${response.data.id} Please Remember this id for further updates.`
      );
      navigate("/");
    } catch (error) {
      console.error("Error:", error);
    }
  };

  return (
    <div>
      <div className="container pt-4 pb-4">
        <div className="row">
          <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
            <h2 className="text-center text-white">
              Student Registration Form
            </h2>
            <form onSubmit={onSubmit}>
              <div className="mb-3">
                <label htmlFor="name" className="form-label text-white">
                  Student Name
                </label>
                <input
                  type="text"
                  className="form-control"
                  placeholder="Enter your name"
                  name="name"
                  value={name}
                  required
                  onChange={onInputChange}
                />
              </div>
              <div className="mb-3">
                <label htmlFor="emailId" className="form-label text-white">
                  Email Id
                </label>
                <input
                  type="email"
                  className="form-control"
                  placeholder="Enter your email id"
                  name="emailId"
                  value={emailId}
                  required
                  onChange={onInputChange}
                />
              </div>
              <div className="mb-3">
                <label htmlFor="mobileNumber" className="form-label text-white">
                  Mobile Number
                </label>
                <input
                  type="text"
                  className="form-control"
                  placeholder="Enter your mobile number"
                  name="mobileNumber"
                  value={mobileNumber}
                  required
                  onChange={onInputChange}
                />
              </div>
              <div className="mb-3">
                <label htmlFor="address" className="form-label text-white">
                  Address
                </label>
                <input
                  type="text"
                  className="form-control"
                  placeholder="Enter your address"
                  name="address"
                  required
                  value={address}
                  onChange={onInputChange}
                />
              </div>
              <div className="mb-3">
                <label htmlFor="marks" className="form-label text-white">
                  Total Marks
                </label>
                <input
                  type="text"
                  className="form-control"
                  placeholder="Enter your marks"
                  name="marks"
                  value={marks}
                  required
                  onChange={onInputChange}
                />
              </div>

              {/* Add file input field */}
              <div className="mb-3">
                <label htmlFor="file" className="form-label text-white">
                  Upload File
                </label>
                <input
                  type="file"
                  className="form-control"
                  name="file"
                  required
                  multiple
                  onChange={onInputChange}
                />
              </div>
              {/* End of file input field */}
              <button type="submit" className="btn btn-outline-success">
                Submit
              </button>
              <Link className="btn btn-outline-danger mx-2" to="/">
                Cancel
              </Link>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}
