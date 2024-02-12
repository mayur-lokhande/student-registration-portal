import { useState } from "react";
import { Col, Form, FormGroup } from "react-bootstrap";
import { Button, Container, Input, Label } from "reactstrap";
import { useNavigate } from "react-router-dom";

const UserLogin = (props) => {
  const navigate = useNavigate();

  const [userLogin, setUserLogin] = useState({});

  const loginhandle = () => {
    const id = 0;
    console.log(userLogin.userName);
    console.log(userLogin.password);

    if (userLogin.userName === "student" && userLogin.password === "student") {
      // console.log("hello");
      let studentId = userLogin.id;
      let newStudent = "viewstatus/" + `${studentId}`;
      navigate(newStudent);
    } else {
      alert("Please Enter currect Username or Password");
    }
  };

  return (
    <div
      className="card mx-auto mt-3 p-5 border bg-light"
      style={{ width: "50%" }}
    >
      <Form>
        <h2>Student Login</h2>
        <FormGroup>
          <Label>Student Id:</Label>
          <Col>
            <Input
              type="text"
              name="id"
              id="id"
              placeholder="Enter Student Id"
              onChange={(e) => {
                setUserLogin({ ...userLogin, id: e.target.value });
              }}
            />
          </Col>
        </FormGroup>
        <FormGroup>
          <Label>Username</Label>
          <Col>
            <Input
              type="text"
              name="userName"
              id="userName"
              placeholder="Enter Username"
              onChange={(e) => {
                setUserLogin({ ...userLogin, userName: e.target.value });
              }}
            />
          </Col>
        </FormGroup>
        <FormGroup>
          <Label>Password</Label>
          <Col>
            <Input
              type="password"
              name="password"
              id="passwoed"
              placeholder="Enter Password"
              onChange={(e) => {
                setUserLogin({ ...userLogin, password: e.target.value });
              }}
            />
          </Col>
        </FormGroup>
        <Container className="mt-3 ">
          <Button
            type="submit"
            className="mx-4"
            color="success"
            onClick={() => {
              loginhandle();
              // props.setShow(true);
            }}
          >
            Login
          </Button>
          <Button type="reset" color="danger">
            Clear
          </Button>
        </Container>
      </Form>
    </div>
  );
};
export default UserLogin;
