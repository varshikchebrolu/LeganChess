# Protocol and API Documentaion

### Client Server Communication
All communication between the client and server will be in the form of JSON objects. The server will impliment a Restful API implimented via Spark.
Schema files will be used to validate data as it is transmitted. Since communications may include sensative information, TLS sessions will be established between the \
client and server to facilitate secure/encrypted communication.

### Client Requests <hr>

#### NewUser
  (see [Link](../server/src/main/resources/schemas/NewUserRequest.json) for full schema) \
  Purpose: Used when sending new user registration information to the server. 
  ```
  Request Type: "NewUser" 
  Required Fields/Types: "name":"string" 
                         "email":"string" 
                         "username":"string" 
                         "password":"string" 
  ```
  Note: Some fields have pattern or length restrictions.
  
#### GetUser
  (see [Link](../server/src/main/resources/schemas/GetUserRequest.json) for full schema) \
  Purpose: Used to lookup an existing user. Basic validation via username/password combination.
  ```
  Request Type: "GetUser"
  Required Fields/Types: "username":"string"
                         "password":"string"
  ```
  Note: Some fields have pattern or length restrictions.
  
#### Login
(see [Link](../server/src/main/resources/schemas/LoginRequest.json) for full schema) \
  Purpose: Used to login to system. Basic validation via Email/password combination.
  ```
  Request Type: "Login"
  Required Fields/Types: "email":"string"
                         "password":"string"
  ```
  Note: Some fields have pattern or length restrictions.

#### Logout  
  (see [Link](../server/src/main/resources/schemas/LogoutRequest.json) for full schema) \
    Purpose: Used to logout of system.  
   ```
    Request Type: "Logout"
    Required Fields/Types: "userID":"integer"                        
   ```
   
  
  
  ### Server Response <hr>
  
  #### NewUser
  (see [Link](../client/schemas/NewUserResponse.json) for full schema) \
  Purpose: Server response to a request to register a new user. Response either indicates success and returns a userID number \
  that can be used for future server communications, or a failure will return an error message indicating what was wrong \
  with the request. example: "username already exists"
  ```
  Request Type: "NewUser" 
  Required Fields/Types: "successful":"boolean" 
                         "userID":"integer" 
                         "errorMsg":"string" 
 ```
   
#### GetUser
  (see [Link](../client/schemas/GetUserResponse.json) for full schema) \
  Purpose: Server response to a FindUser request. Response either indicates success and returns the userID number of the user\
  for use in future server communications, or a failure will return an error message indicating what was wrong \
  with the request. example: "Incorrect Password" 
  ```
  Request Type: "FindUser"
  Required Fields/Types: "successful":"boolean" 
                         "userID":"integer" 
                         "errorMsg":"string"
  ```
   
#### Login  
   (see [Link](../client/schemas/LoginResponse.json) for full schema) \
    Purpose: Server response to a Login request. Response either indicates success and returns the userID, name, email, etc of the user  
    for use in future server communications, or a failure will return an error message indicating what was wrong   
    with the request. example: "Incorrect Password"  
```
    Request Type: "Login"
    Required Fields/Types: "successful":"boolean"   
                           "userID":"integer"   
                           "firstName":"string"  
                           "lastName":"string"  
                           "userName":"string"  
                           "userID":"integer"  
                           "email":"string"  
``` 
 #### Logout
   (see [Link](../client/schemas/LogoutResponse.json) for full schema) \
    Purpose: Server response to a Logout request. Response either indicates success or failure.
   ```
    Request Type: "Logout"  
    Required Fields/Types: "successful":"boolean"   
   ```
   
 ### WebSocket (2-Way) <hr>
 The Websocket interface can be used to send messages between the client and server in either direction. There are 2 defined   
 Websocket instances that are used in this application. The lobbysocket object is available in the Lobby.js component.  
 MatchSocket object is availiable from the HomePage.js component. Each socket object accepts a specific message format.  
   
```
    lobbySocket: Send any "text/string" as a message. Server Echos recieved messages back to all attached Clients.  
    MatchSocket: Send a JSON text object in the following format:   
                           "type":"string"   
                           "from":"string"  
                           "to": "string"  
                           "msg":"string"  
                           "user":"string"  
                          
``` 
