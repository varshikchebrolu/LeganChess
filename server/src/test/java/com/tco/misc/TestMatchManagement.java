package com.tco.misc;

import java.util.ArrayList;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestMatchManagement {
    static Match tempMatch;
    static int tempID;
    User player1 = new User("Bob", "Test", "bobtest", "bob@test.com", "12345", 10);
    User player2 = new User("fred","test", "fredtest","fred@test.com", "12346", 11);


    @Test
    @Order(1)
    @DisplayName("Verify Match creation")
    public void testCreateNewMatch() {
        try {
            tempMatch = MatchManagement.createNewMatch(player1,player2);
            tempID = tempMatch.getMatchID();
            System.out.println("tempID:" + tempID);
        } catch (MatchCreationException e) {
            //e.printStackTrace();
        }
        assert (tempID > 0);
        assert (MatchManagement.getActiveMatches().contains(tempMatch));
    }
    @Test
    @Order(2)
    @DisplayName("Verify Match saves")
    public void testSaveMatch(){
        tempMatch.setCurrentTurn(player2.getUserName());
        Boolean result = MatchManagement.saveMatch(tempMatch);

        assertTrue(result);
        assert(!MatchManagement.getActiveMatches().contains(tempMatch));
        tempMatch = null;
    }

    @Test
    @Order(3)
    @DisplayName("Verify Match retrieval")
    public void testGetSavedMatch(){
        //System.out.println("inside:" +tempID);
        tempMatch = MatchManagement.getSavedMatch(tempID);

        assertEquals (tempMatch.getMatchID(), tempID);
        assert (MatchManagement.getActiveMatches().contains(tempMatch));

    }
    @Test
    @Order(4)
    @DisplayName("Verify Match Deletion")
    public void testDeleteMatch(){
        Boolean result = MatchManagement.deleteMatch(tempMatch);

        assertTrue(result);
        assert (!MatchManagement.getActiveMatches().contains(tempMatch));
        assertFalse(MatchManagement.deleteMatch(tempMatch));
    }
}

