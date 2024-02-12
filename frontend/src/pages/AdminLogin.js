import { useState } from "react";
import { Col, Form, FormGroup } from "react-bootstrap";
import { Button, Container, Input, Label } from "reactstrap";
import { useNavigate } from "react-router-dom";

const AdminLogin = (props) => {
  const navigate = useNavigate();

  const [adminLogin, setAdminLogin] = useState({});

  const loginhandle = () => {
    // const uname = "admin";
    // const pass = "admin";

    console.log(adminLogin.userName);
    console.log(adminLogin.password);

    if (adminLogin.userName === "admin" && adminLogin.password === "admin") {
      // console.log("hello");
      navigate("dashboard");
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
        <h2>Admin Login</h2>
        <FormGroup>
          <Label>Username</Label>
          <Col>
            <Input
              type="text"
              name="userName"
              id="userName"
              placeholder="Enter Username"
              onChange={(e) => {
                setAdminLogin({ ...adminLogin, userName: e.target.value });
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
                setAdminLogin({ ...adminLogin, password: e.target.value });
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
export default AdminLogin;
