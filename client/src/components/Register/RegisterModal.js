
import {Container, ModalBody, Modal, ModalHeader, Col, Row} from "reactstrap";
import React, {Component, useState} from "react";
import {ModalFooter} from "reactstrap";
import {Button} from "reactstrap";
import Register from "./Register";
import {buildNewUserRequest} from "../../utils/buildRequests";
import {getOriginalServerUrl, sendAPIRequest} from "../../utils/restfulAPI";

export default function RegisterModal(props) {


        return (
            <Container>
                <Modal myid="myModal" isOpen={props.open}>
                    <ModalHeader><Button onClick={() => {
                        props.handle(false)
                    }}>Close</Button></ModalHeader>
                    <Register/>
                    <ModalFooter>
                        <Button id="subButton1" color="secondary" onClick={()=>{registerHandler(props.user,props.userID,props.handle,props.closepage);}}>Submit</Button>
                        <Button id="cancelButton1" color="secondary" onClick={() => {
                            props.handle(false)
                        }}>Cancel</Button>
                    </ModalFooter>
                </Modal>
            </Container>
        );
    }


   function registerHandler(setUser,setID,toggle,close ) {
        var firstName = document.getElementById("firstNamebox");
        var lastName = document.getElementById("lastNamebox");
        var email = document.getElementById("emailbox");
        var userName = document.getElementById("usernamebox");
        var password = document.getElementById("passwordbox");
        //var reTypePassword = document.getElementById("passwordbox2");

        //add code to check if password and retypepassword match before sending request.

        var jsonRequest = buildNewUserRequest(firstName.value, lastName.value, userName.value, email.value, password.value);

        sendNewUserRequest(jsonRequest,setUser,setID,toggle, userName,close);


    }


    function sendNewUserRequest(request,u,id,toggle,username,close) {

        sendAPIRequest(request, getOriginalServerUrl())
            .then(response => {
                if (response) {
                    processNewUserSuccess(response,u,id,toggle,username,close);
                } else {
                    console.error("error");
                }
            });
    }


    function processNewUserSuccess(response,u,id,toggle,username,close) {

        console.log("requestType:" + response.requestType);
        if (response.successful === true) {
            console.log("Login:" + response.successful);
            u(username);
            id(response.userID);
            toggle();
            close();
        }

    }


