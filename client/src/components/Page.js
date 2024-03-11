import React, { useEffect, useState } from 'react';
import { Collapse } from 'reactstrap';
import Footer from './Margins/Footer';
import { useToggle } from '../hooks/useToggle';

import Login from "./Register/Login";
import HomePage from "./Home/HomePage";
import HomeNavBar from "./Home/HomeNavBar";
import Lobby from "./Lobby";


export default function Page(props) {
	const [showLogin, toggleLogin] = useToggle(true);
	const [showLobby, toggleLobby] = useToggle(false);
	const [showHome, toggleHome] = useToggle(false);
	const [showReg, toggleReg] = useToggle(false);
	const [firstName, setFirstName] = useState("Malachy");
	const [lastName, setLastName] = useState("Swonger");
	const [userName, setUserName] = useState("mswonger");
	const [userID, setUserID] = useState(1);
	const [email, setEmail] = useState("mswonger@colostate.edu");


	return (
		<>
			<HomeNavBar toggleLogin = {props.toggleLogin}/>
			<div className="body">
				<Collapse isOpen={showLobby} toggle={toggleLobby}>
					<Lobby toggle={toggleLobby} user={userID} userName={userName} home={toggleHome}/>
				</Collapse>
				<Collapse isOpen={showHome} toggle={toggleHome}>
					<HomePage userName={userName} user={userID} showmessage={props.showMessage}/>
				</Collapse>
				<Collapse isOpen={showLogin} >
					<Login  showreg={showReg} togglereg={toggleReg} showMessage={props.showMessage} closePage={()=>{toggleLogin(); toggleLobby();}} fname={setFirstName} lname={setLastName} user={setUserName} userID={setUserID} email={setEmail}/>
				</Collapse>
			</div>

			<Footer/>
		</>
	)
}



