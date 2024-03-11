## User Stories and Tasks

1.  As a User I would like to Register an account on the game platform.
    

	a.  Create a database to hold user registration information

	b.  Create UI components that allows the user to register

	c.  Create API to verify user input and send data to database
    

2.  As a User I would like to login into my registered account.
    

	a.  Create UI components that allow an existing user to log into their account

	b.  Add API to transfer data 

	c.  Add back-end verification for user input, possibly with JavaDAO/Java PO classes
    

3.  As a User, I would like to see a list of potential opponents for a new match.
    

	a.  Add UI section to home page where user can see who's currently playing or search for other users

	b.  Create "Users Currently Logged In" database table

	c.  Create API to populate front-end with users who are currently logged in
    

4.  As a user, I would like to be able to invite other players.
    

	a.  Add UI ability to select a user from user story #3.

	b.  Add popover with UI component to invite other user to a new match

	c.  Add "Invitation" API

	d.  Create database table for invitations


5.  As a user, I would like to be able to accept/reject invitations sent to me.


	a.  Create UI components for invitation management

	b.  Add API to send data so a user can accept or reject an invitation

	c.  Update Invitations table in DB with invitation status (if needed, i.e. wasn't included in story #4)
    

6.  As a user, I would like to see a history of the matches I have played.
    

	a.  Create a log of game history (time stamps, final score, opponents, etc.)

	b.  Create "Match History" DB table

	c.  Add UI components to User Profile page to display match history information
    

7.  As a user, I would like to be able to start a new match.
    

	a.  Create a UI button on home page that allows the user to start a new game

	b.  Create UI components for a new popover, which will display after clicking button in 7a

	c.  Starting a new match will add a new entry to "Matches in Progress" DB table

	d.  Create new API that will handle data needed for new match

	e.  Create new JavaDAO/Java PO classes to create new board from back-end specs. and update relevant DB tables


8.  As a user, I would like to be able to join an existing game.  


	a.  Create UI section on home page displaying matches in progress that need another player

	b.  Create new DB table to hold in-progress matches where one player has left but the other wants to continue

	c.  Add API or JavaDAO/PO classes to transfer data of new player to existing match


9.  As a user, I would like the game to enforce the ruleset for the given game.
    

	a.  Create API to communicate between board and rules listed in back-end classes for each piece

	b.  Add UI feature that shows a player a proposed move is invalid

	c.  Create front-end code to move a piece only if the move is valid (9b and 9c may be one issue, not two)
    

10.  As a user, I would like the game to keep track of each player’s turn.
    

	a.  Add column to Match Progress DB table which will have data showing current player's turn

	b.  Add API or JavaDAO/PO classes to communicate turn status between DB and UI page with board
    
	c.  Add UI component to page which displays the board showing whose turn it is
        

11.  As a user, I would like the system to notify players when a match has ended.

    a.  Add Java DAO class and Java PO class to initiate process of announcing a match has ended

	b.  Java PO class updates Match Progress table 

	c.  Add new API to send UI notifications to both players when the current game has ended

	d.  Create UI components representing match-ended notification


12.  As a user, I can save a match and resume match at a later time.
    

	a.  Add UI button to Save match status before a user navigates away from page with game board
    
	b.  Add UI button to a user's match list on home page allowing the user to resume an in-progress game

	c.  Add back-end logic (Java DAO, Java PO classes) to change a match status in DB from On Hold to Active and vice versa

	d.  Create API that is called when a user clicks Save button

