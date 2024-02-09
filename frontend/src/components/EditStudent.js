import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

export default function EditStudent() {
  let navigate = useNavigate();

  const { id } = useParams();

  const [student, setStudent] = useState({
    // name: "",
    // emailId: "",
    // mobileNumber: "",
    // address: "",
    // marks: "",
    status:"",
    comment:"",
  });

  const { /*name, emailId, mobileNumber, address, marks,*/ status,comment } = student;

  const onInputChange = (event) => {
    setStudent({ ...student, [event.target.name]: event.target.value });
  };

  useEffect(() => {
    loadStudent();
  }, []);

  const onSubmit = async (event) => {
    event.preventDefault();
    await axios.put(`http://localhost:8181/api/students/${id}`, student);
    navigate("/admin/dashboard");
  };

  const loadStudent = async () => {
    const result = await axios.get(`http://localhost:8181/api/students/${id}`);
    setStudent(result.data);
  };

  return (
    <div>
        <div className="container pt-4 pb-4">
          <div className="row">
            <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
              <h2 className="text-center text-white">Respond to student application</h2>
              <form onSubmit={onSubmit}>
                {/* <div className="mb-3">
                  <label htmlFor="Name" className="form-label text-white">
                    Name
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
                    Email
                  </label>
                  <input
                    type={"text"}
                    className="form-control"
                    placeholder="Enter your email"
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
                    placeholder="Enter your mobile number"
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
                    Marks
                  </label>
                  <input
                    type={"text"}
                    className="form-control"
                    placeholder="Enter your marks"
                    name="marks"
                    value={marks}
                    onChange={onInputChange}
                  />
                </div> */}

                <div className="mb-3">
                  <label htmlFor="Name" className="form-label text-white">
                    Update Status
                  </label>
                  <select
                    className="form-control"
                    name="status"
                    value={status}
                    onChange={onInputChange}>

                    <option value={"Select Status"} disabled selected>Select Status</option>
                    <option value={"Selected"}>Selected</option>
                    <option value={"Rejected"}>Rejected</option>
                  </select>
                </div>
                <div className="mb-3">
                  <label htmlFor="Name" className="form-label text-white">
                    Comment
                  </label>
                  <input
                    type={"text"}
                    className="form-control"
                    placeholder="Add comment"
                    name="comment"
                    value={comment}
                    onChange={onInputChange}
                  />
                </div>
                <button type="submit" className="btn btn-outline-primary">
                  Submit
                </button>
                <Link className="btn btn-outline-danger mx-2" to="/admin/dashboard">
                  Cancel
                </Link>
              </form>
            </div>
          </div>
        </div>
    </div>
  );
}
