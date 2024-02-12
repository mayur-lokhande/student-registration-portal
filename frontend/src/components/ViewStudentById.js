// import axios from "axios";
// import React, { useEffect, useState } from "react";
// import { Link, useParams } from "react-router-dom";

// export default function ViewStudentById() {
//   const [student, setStudent] = useState({
//     name: "",
//     emailId: "",
//     mobileNumber: "",
//     address: "",
//     marks: "",
//   });

//   const { id } = useParams();

//   useEffect(() => {
//     loadUser();
//   }, []);

//   const loadUser = async () => {
//     const result = await axios.get(`http://localhost:8181/api/students/${id}`);
//     setStudent(result.data);
//   };

//   return (
//     <div>
//       <div className="container pt-5">
//         <div className="row">
//           <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
//             <h2 className="text-center m-4 text-white">Student Details</h2>
//             <div className="card">
//               <div className="card-header">
//                 <h4 className="text-info fw-bold">
//                   Student Registration Id: {student.id}
//                 </h4>
//                 <ul className="list-group list-group-flush">
//                   <li className="list-group-item">
//                     <b>Name: </b>
//                     {student.name}
//                   </li>
//                   <li className="list-group-item">
//                     <b>Email Id: </b>
//                     {student.emailId}
//                   </li>
//                   <li className="list-group-item">
//                     <b>Mobile Number: </b>
//                     {student.mobileNumber}
//                   </li>
//                   <li className="list-group-item">
//                     <b>Address: </b>
//                     {student.address}
//                   </li>
//                   <li className="list-group-item">
//                     <b>Marks in percentage: </b>
//                     {student.marks}
//                   </li>
//                   <li className="list-group-item">
//                     <b>Status: </b>
//                     {student.status}
//                   </li>
//                   <li className="list-group-item">
//                     <b>Faculty Comment: </b>
//                     {student.comment}
//                   </li>
//                 </ul>
//               </div>
//             </div>
//             <Link className="btn btn-primary my-2" to={"/admin/dashboard"}>
//               Back to Home
//             </Link>
//           </div>
//         </div>
//       </div>
//     </div>
//   );
// }

import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";

export default function ViewStudentById() {
  const [student, setStudent] = useState({
    name: "",
    emailId: "",
    mobileNumber: "",
    address: "",
    marks: "",
  });

  const [document, setDocument] = useState(null);

  const { id } = useParams();

  useEffect(() => {
    loadStudent();
    loadDocument();
  }, []);

  const loadStudent = async () => {
    const result = await axios.get(`http://localhost:8181/api/students/${id}`);
    setStudent(result.data);
  };

  const loadDocument = async () => {
    const result = await axios.get(
      `http://localhost:8181/api/documents/student/${id}`
    );
    setDocument(result.data[0]); // Assuming there is only one document per student
  };

  const viewDocument = () => {
    window.open(`http://localhost:8181/api/documents/student/${id}`, "_blank");
  };

  return (
    <div>
      <div className="container pt-5">
        <div className="row">
          <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
            <h2 className="text-center m-4 text-white">Student Details</h2>
            <div className="card">
              <div className="card-header">
                <h4 className="text-info fw-bold">
                  Student Registration Id: {student.id}
                </h4>
                <ul className="list-group list-group-flush">
                  <li className="list-group-item">
                    <b>Name: </b>
                    {student.name}
                  </li>
                  <li className="list-group-item">
                    <b>Email: </b>
                    {student.emailId}
                  </li>
                  <li className="list-group-item">
                    <b>Mobile Number: </b>
                    {student.mobileNumber}
                  </li>
                  <li className="list-group-item">
                    <b>Address: </b>
                    {student.address}
                  </li>
                  <li className="list-group-item">
                    <b>Marks: </b>
                    {student.marks}
                  </li>
                  <li className="list-group-item">
                    <b>Status: </b>
                    {student.status}
                  </li>
                  <li className="list-group-item">
                    <b>Faculty Comment: </b>
                    {student.comment}
                  </li>
                  <li>
                    {document && (
                      <button
                        className="btn btn-outline-primary my-2"
                        onClick={viewDocument}
                      >
                        View Documents
                      </button>
                    )}
                  </li>
                </ul>
              </div>
            </div>
            <Link className="btn btn-success my-2" to={"/admin/dashboard"}>
              Back to Home
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
}
