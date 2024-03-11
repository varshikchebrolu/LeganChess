import React, {Component, useState} from 'react'
import HomeNavBar from "./HomeNavBar";
import Chessboard from 'chessboardjsx';

export default class HomePage extends Component{
    constructor(props) {
        super(props);
        const url = "ws://" + location.hostname + ":8000" + "/Match/" +this.props.userName ;
        const matchSocket = new WebSocket(url);

        this.msgHandler = this.msgHandler.bind(this);
        this.moveHandler = this.moveHandler.bind(this);
        this.sendMoveMessage = this.sendMoveMessage.bind(this);
        this.createMatch = this.createMatch.bind(this);
        this.saveMatch = this.saveMatch.bind(this);

        matchSocket.onmessage =this.msgHandler;

        this.state = {
            matchSocket: matchSocket,
            player1: this.props.userName,
            player2: this.props.userName,
            matchID: 0,
            initialState: ""    //"knbrp3/bqpp4/npp5/rp1p3P/p3P1PR/5NPP/4PPQB/3PRBNK"
            };

    };

    render() {
    return(
        <div className='container'>
            <div className="row justify-content-start">
            <div className="card col bg-light" style={{position:'relative',width:'30%',height:'20rem'}}>
                <div className="card-body">
                    <h5 className="card-title">Legan Chess</h5>
                    <p className="card-text">Its a type of chess</p>
                    <div className="btn-group" role="group" aria-label="game controls">
                    <button className='btn btn-primary' onClick={this.createMatch}>Create Game</button>
                    <button className='btn btn-danger' onClick={this.saveMatch}>Save Game</button>
                    </div>
                </div>
            </div>
            <div className="col" style={{position:'relative'}}>
                <Chessboard id="BasicBoard" boardWidth={510}
                            position={this.state.initialState}
                            onDrop={({sourceSquare,targetSquare,piece})=> {this.moveHandler(sourceSquare,targetSquare,piece)}}
                />
            </div>
                <div className="col">
                    <div className="card text-white bg-primary" style={{position:'relative',height:'48%'}}>
                        <div className="card-body">
                            <h5 className="card-title">Match 1</h5>
                            <p className="card-text">Details of the match</p>
                            <p>Player (Black) : {this.state.player2}</p>
                            <p>Player (White) : {this.state.player1} </p>
                            <p>Turn : {this.state.player1} </p>
                        </div>
                    </div>
                    <div className="card text-white bg-primary" style={{position:'relative',height:'48%',paddingTop:'2px'}}>
                        <div className="card-body">
                            <h5 className="card-title">Previous Matches</h5>

                        </div>
                    </div>
                </div>


            </div>

        </div>

    )}

    saveMatch(){
        let output = {type:"save", from:this.state.player1, to:this.state.player2, msg:this.state.matchID, user:this.props.userName}
        this.state.matchSocket.send(JSON.stringify(output));
    }

    moveHandler(s,t,p) {
        if(this.state.matchID > 0) {
            let from = s;
            let to = t;
            this.sendMoveMessage(from, to, this.state.matchID);
        }
    }
    createMatch(){
        let output = {type:"create", from:this.state.player1, to:this.state.player2, msg:"", user:this.props.userName}
        this.state.matchSocket.send(JSON.stringify(output));
        console.log(output);
    }
    sendMoveMessage(from, to, matchID) {
        let output = {type:"move", from:from, to:to, msg:matchID, user:this.props.userName}
        this.state.matchSocket.send(JSON.stringify(output));
        console.log("sent:" + JSON.stringify(output));

    }
    msgHandler(event){
    let msg = JSON.parse(event.data);
    console.log(msg);
    if (msg.type === "update") {
        console.log("Attempting to update msgpl: " + msg.msg)
        this.setState({initialState: msg.msg.split(",")[0]});
        if (msg.msg.split(",")[1] === "true") {
            if (confirm("CheckMate! Do you want to start another match?")) {
                this.createMatch()
            } else {
                return;
            }
        }
    }
    if (msg.type === "create") {
        this.setState({matchID: msg.msg , initialState:"knbrp3/bqpp4/npp5/rp1p3P/p3P1PR/5PPN/4PPQB/3PRBNK"});
    }
    if (msg.type === "invalid") {
        this.props.showmessage("Invalid Move: Please Try again!")
        console.error(msg);
    }
    if (msg.type === "save") {
        if(msg.msg === "success"){
            this.setState({initialState:"", matchID:0})
            this.props.showmessage("Match Saved!")
        }
        this.props.showmessage("Failed to save match! Please Try again!")
        console.error(msg);
    }else {
        console.error(msg);
    }
    }
 }

