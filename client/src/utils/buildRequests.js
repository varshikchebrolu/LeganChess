import {getOriginalServerUrl, sendAPIRequest} from "./restfulAPI";


export function buildLoginRequest(email, password){
    const loginRequest ={
        "requestType": "Login",
        "email": email,
        "password": password
    }
    return loginRequest;
}

export function buildLogoutRequest(userID){
    const logoutRequest = {
        "requestType":"Logout",
        "userID": userID
    }
    return logoutRequest;
}

export function buildNewUserRequest(first,last,username,email,password){
    const newUserRequest = {
        "requestType":"NewUser",
        "firstName": first,
        "lastName": last,
        "userName": username,
        "email": email,
        "password": password
    }
    return newUserRequest;
}

//export async function sendLoginRequest(requestBody) {
//    console.log("entered sendLoginRequest");
//    const response = await sendAPIRequest(requestBody, getOriginalServerUrl());
//    //const response = await sendAPIRequest(requestBody, "/api/NewUser");
//    console.log("response: " + response);

//    if (response) {
//        processLoginSuccess(response);
//    } else {
//        showMessage(response.errorMsg);
//    }
//}

