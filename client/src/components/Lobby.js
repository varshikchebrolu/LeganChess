import React from 'react';
import {Component} from "react";

import {Container} from "reactstrap";
import {Button, Card, CardContent} from "@material-ui/core";
import CardActions from "@material-ui/core/CardActions";
import Input from "@material-ui/core/Input";
import {getOriginalServerUrl} from "../utils/restfulAPI"
import TextareaAutosize from "@material-ui/core/TextareaAutosize";


export default class Lobby extends Component {
    constructor(props) {
        super(props);
        const url = "ws://"+ location.hostname + ":8000" +"/Lobby/" +props.userID ;
        //const url = getOriginalServerUrl() + "" +props.userID;
        const lobbySocket = new WebSocket(url);

       lobbySocket.onmessage = function (event) {
           let msg = JSON.parse(event.data);
           document.getElementById("messageBody").append("\n");
            document.getElementById("messageBody").append(msg.from + ": " +msg.msg);
        };

        this.sendMessage = this.sendMessage.bind(this);
        this.state = {
            lobbySocket: lobbySocket
        };
    }

    render() {

        return (
            <Container>
                <Card>
                    <CardContent>
                        <div>
                            <h4>Welcome to the Lobby</h4>
                        </div>
                        <TextareaAutosize
                                aria-label="minimum height"
                                minRows={10}
                                placeholder="Lobby Channel"
                                style={{ width: 400 }}
                                id="messageBody"
                           />

                    </CardContent>
                    <CardActions>
                        <Input placeholder="type message here"
                               id="chatInput" style={{width:"75%"}}
                               autoFocus={true}/>
                        <Button color="primary"
                                size="small"
                                onClick={()=> {this.sendMessage(document.getElementById("chatInput").value)}}>Send</Button>
                    </CardActions>
                </Card>
                <Button onClick={()=>{this.props.toggle(); this.props.home();}}>Start a Match</Button>
            </Container>
        )
    }

    sendMessage(msg) {
        let output = {type:"chat", from:this.props.userName, to:"all", msg:msg}
        this.state.lobbySocket.send(JSON.stringify(output));
        //console.log(document.getElementById("chatInput").value);
    }
    getUserList(){
        let output = {type:"list", from:this.props.userName, to:"", msg:""};
        this.state.lobbySocket.send(JSON.stringify(output));
    }


}
