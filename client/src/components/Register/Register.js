import {Col, Container, Row} from "reactstrap";

import React from "react";

export default function Register() {
    return (
        <Container id = "Register">
            <Row>
                <Col>
                    <h2>New user?</h2>
                    <form name="registerForm" action="" method="get">
                        <Row><Col><p>Please type your first name:</p></Col> <Col><input type="text" id="firstNamebox" ></input></Col></Row>
                        <Row><Col><p>Please type your last name:</p></Col> <Col><input type="text" id="lastNamebox" ></input></Col></Row>
                        <Row><Col><p>Please type your email:</p></Col> <Col><input type="text" id="emailbox"></input></Col></Row>
                        <Row><Col><p>Please type your username:</p></Col> <Col><input type="text" id="usernamebox" ></input></Col></Row>
                        <Row><Col><p>Please type your password:</p></Col> <Col><input type="password" id="passwordbox" ></input></Col></Row>
                        <Row><Col>Please re-enter your password:</Col> <Col><input type="password" id="passwordbox2" ></input></Col></Row>
                    </form>
                </Col>
            </Row>
        </Container>
    )
}

