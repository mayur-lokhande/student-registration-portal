import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

export default function Home() {
  const [students, setStudents] = useState([]);

  useEffect(() => {
    loadStudents();
  }, []);

  const loadStudents = async () => {
    const result = await axios.get("http://localhost:8181/api/students");
    setStudents(result.data);
  };

  const deleteStudent = async (id) => {
    // alert("Student Deleted Successfully");
    toast.success(`Student Deleted Successfully`, {
      position: "top-right",
      autoClose: 5000,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
    });
    await axios.delete(`http://localhost:8181/api/students/${id}`);
    loadStudents();
  };

  return (
    <div>
      <div className="container">
        <Link
          style={{ marginLeft: "90%", marginTop: "2%" }}
          className="btn btn-danger btn-sm mr-5"
          to="/"
        >
          Logout
        </Link>
        <div className="py-4">
          <table className="table table-secondary table-bordered">
            <thead>
              <tr>
                <th scope="col">Id</th>
                <th scope="col">Name</th>
                <th scope="col">Mobile Number</th>
                {/* <th scope="col">Email Address</th>
                  <th scope="col">Address</th>
                  <th scope="col">Marks</th> */}
                <th scope="col">Status</th>
                <th scope="col">Comment</th>
                <th scope="col">Action</th>
              </tr>
            </thead>
            <tbody>
              {students.map((student, index) => (
                <tr>
                  {/* <th scope="row" key={index}>
                      {index + 1}
                    </th> */}
                  <td>{student.id}</td>
                  <td>{student.name}</td>
                  <td>{student.mobileNumber}</td>
                  {/* <td>{student.emailId}</td>
                    <td>{student.address}</td>
                    <td>{student.marks}</td> */}
                  <td>{student.status}</td>
                  <td>{student.comment}</td>
                  <td>
                    <Link
                      className="btn btn-success mx-2 btn-sm"
                      to={`/viewstudentbyid/${student.id}`}
                    >
                      View
                    </Link>
                    <Link
                      className="btn btn-primary mx-2 btn-sm"
                      to={`/editstudent/${student.id}`}
                    >
                      Respond
                    </Link>
                    <button
                      className="btn btn-danger mx-2 btn-sm"
                      onClick={() => deleteStudent(student.id)}
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
          <ToastContainer />
        </div>
      </div>
    </div>
  );
}
