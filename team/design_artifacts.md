## CRC Cards

| Platform | |
|------------|-------------|
| Authenticates users | Authentication |
| Host matches | Game |
| Record match history | History |
| Allow users to find each other | User Communication |

| New User | |
|------------|-----------|
| Registration | Authentication | 

| Existing User | |
|------------|-------------|
| Login | Authentication |
| Create a match | Game |
| Invite player to a match | User Communication |
| Accept/reject invitation to a match | User Communication |
| Participate in multiple matches simultaneously | User Profile |
| Quit a match | Game |
| Update match status | Game Status |
| User can unregister | User Profile | 

| Game | |
|------------|-------------|
| Displays gameboard | |
| Wait for another player to join | Rules |
| Prohibit too many players from joining | Rules | 
| Keep track of whose turn it is/ Save match progress | Match Status |
| Ensure game move is legal | Rules |
| Notify players of winner | Match Status |
| Upload final statistics after match completion | History |

| User Communication | |
|------------|-------------|
| Manage game invitations | |
| Allow users to search for one another | |

| History | |
|------------|-------------|
| Track match history | Game |
| Calculate match statistics | |

| User Profile | |
|------------|-------------|
| Display user credentials | |
| Allow user to unregister | |
| Display match history | History |
| Provides access to matches in progress | Game |

| Rules | |
|------------|-------------|
| Contain ruleset for given game | Game |
| Ensure correct number of players before match start | Game |

| Match Status | |
|------------|-------------|
| Track each match's status | Game |
| Track all game moves | History |
## Class Diagram
![Class Diagram](../images/UpdatedClassDiagram.png)
## Alt Class Diagram
![Class Diagram](../images/ClassDiagramAlt.png)
## Component Hierarachy
![Component](../images/Component_H.jpeg)
