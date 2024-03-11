import {Container, Row, Col, Button, Input} from 'reactstrap';
import {getOriginalServerUrl, sendAPIRequest} from "../../utils/restfulAPI";
import {buildLoginRequest} from "../../utils/buildRequests";
import React, {Component, useState} from 'react';
import RegisterModal from "./RegisterModal"
import {useToggle} from "../../hooks/useToggle";



export default function Login(props) {

        return (
        <Container id="Login">
            <Row>
                <Col>
                    <h1>Welcome to Legan Chess</h1>
                    <h4>An exciting twist on the classic game of chess.</h4>
                    <Row>&nbsp;&nbsp;&nbsp;</Row>
                </Col>
            </Row>
            <Row>
                <Col>
                    <h2>Returning user?</h2>
                    <form name="loginForm" action="" method="get">
                        <Row><p>Email:</p>&nbsp;&nbsp;&nbsp;<Input type="text" defaultValue=""
                                                                      id="userInputbox"/></Row>
                        <Row><p>Password:</p>&nbsp;&nbsp;&nbsp;<Input type="password" defaultValue=""
                                                                      id="passInputbox"/></Row>
                        <Row>&nbsp;&nbsp;&nbsp;</Row>
                        <Row><Col><Button name="submitbutton" onClick={()=> {loginHandler(document.getElementById("userInputbox").value,
                                                                            document.getElementById("passInputbox").value, props.closePage, props.user, props.userID, props.showMessage)}}>Login</Button></Col></Row>
                        <Row>&nbsp;&nbsp;&nbsp;</Row>
                    </form>
                    <h4>New User? Please Register for an account.</h4><Button onClick={()=> {props.togglereg(true)}}>Register</Button>
                    <RegisterModal closepage={props.closePage} handle={props.togglereg} open={props.showreg} user={props.setuser} userID={props.userID}/>
                </Col>
            </Row>
        </Container>
    )
}


    function loginHandler(u,p, toggle, uname, userid, showMsg) {

        const request = buildLoginRequest(u, p)  // sample userdata to test with: "test@test.com","test");
        sendAPIRequest(request, getOriginalServerUrl())
            .then(response => {
                if (response.successful === true) {
                    console.log("username being set to: " + response.userName)
                    uname(response.userName);
                   processLoginSuccess(response,toggle, uname, userid);
                } else {
                    console.error("error");
                    showMsg("Invalid Email or Password, please try again.", "error")
                }
            });
}

    // decide what to do with the info you receive here
    // you can read the individual fields as response.field ...or convert the whole object if you
    // have something to store it as.
    function processLoginSuccess(response,toggle, uname, userid) {
        uname(response.userName);
        userid(response.userID);
        toggle();
        console.log("requestType:" + response.requestType + "  successful:" + response.successful + response.userName);

}


