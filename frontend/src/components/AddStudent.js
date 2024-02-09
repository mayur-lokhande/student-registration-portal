import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
// import { Toast } from "react-toastify/dist/components";

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

  // const [generatedId, setGeneratedId] = useState(null);

  const { name, emailId, mobileNumber, address, marks, status, comment } =
    student;

  const onInputChange = (event) => {
    setStudent({ ...student, [event.target.name]: event.target.value });
  };

  // const onSubmit = async (event) => {
  //   event.preventDefault();
  //   await axios.post("http://localhost:8181/api/students", student);
  //   navigate("/");
  // };

  const onSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        "http://localhost:8181/api/students",
        student
      );
      // setGeneratedId(response.data.id); // Assuming the backend returns the generated ID
      // console.log(response);
      alert(
        `Your Registration Id: ${response.data.id} Please Remember this id for further updates.`
      );

      // toast.success(`Generated Id:${response.data.id}`, {
      //   position: "top-right",
      //   autoClose: 5000,
      //   hideProgressBar: false,
      //   closeOnClick: true,
      //   pauseOnHover: true,
      //   draggable: true,
      //   progress: undefined,
      // });
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
                <label htmlFor="Name" className="form-label text-white">
                  Student Name
                </label>
                <input
                  type={"text"}
                  className="form-control"
                  placeholder="Enter your name"
                  name="name"
                  value={name}
                  onChange={onInputChange}
                />
              </div>
              <div className="mb-3">
                <label htmlFor="Name" className="form-label text-white">
                  EmailId
                </label>
                <input
                  type={"text"}
                  className="form-control"
                  placeholder="Enter your emailId"
                  name="emailId"
                  value={emailId}
                  onChange={onInputChange}
                />
              </div>
              <div className="mb-3">
                <label htmlFor="Name" className="form-label text-white">
                  Mobile Number
                </label>
                <input
                  type={"number"}
                  className="form-control"
                  placeholder="Enter your Mobile Number"
                  name="mobileNumber"
                  value={mobileNumber}
                  onChange={onInputChange}
                />
              </div>
              <div className="mb-3">
                <label htmlFor="Name" className="form-label text-white">
                  Address
                </label>
                <input
                  type={"text"}
                  className="form-control"
                  placeholder="Enter your address"
                  name="address"
                  value={address}
                  onChange={onInputChange}
                />
              </div>
              <div className="mb-3">
                <label htmlFor="Name" className="form-label text-white">
                  Total Marks
                </label>
                <input
                  type={"number"}
                  className="form-control"
                  placeholder="Enter your Marks in percentage"
                  name="marks"
                  value={marks}
                  onChange={onInputChange}
                />
              </div>

              <button type="submit" className="btn btn-outline-success">
                Submit
              </button>
              <Link className="btn btn-outline-danger mx-2" to="/">
                Cancel
              </Link>
            </form>
            {/* <ToastContainer /> */}
          </div>
        </div>
      </div>
    </div>
  );
}
