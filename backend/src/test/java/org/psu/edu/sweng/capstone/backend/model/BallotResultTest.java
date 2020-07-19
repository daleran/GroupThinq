package org.psu.edu.sweng.capstone.backend.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BallotResultTest {

    private static final User USER = new User("mboyer87", "password", "Boyer", "Matt", "mboyer87@gmail.com", new Date());
    private Ballot ballot = new Ballot();
    private BallotOption ballotOption = new BallotOption("BK Lounge", "get those cheeze borger", ballot, USER);

    private BallotResult testResult = new BallotResult(ballot, ballotOption, USER);

    @BeforeEach
    void setUp() {
        testResult.setVoteUpdatedDate(new Date(321L));
    }

    @Test
    void constructor_worksProperly() {

        assertNotNull(testResult.getVoteDate());
        assertEquals(ballot, testResult.getBallot());
        assertEquals(USER, testResult.getUser());
        assertEquals(new Date(321L), testResult.getVoteUpdatedDate());
        assertEquals(ballotOption, testResult.getBallotOption());
    }

    @Test
    void getters_setters_workProperly() {
        User USER = new User("zzzzzach", "dorwssap", "Beimford", "Zach", "email@fake.com", new Date());
        Ballot ballot = new Ballot();
        BallotOption ballotOption = new BallotOption("BK Lounge", "get those cheeze borger", ballot, USER);


        testResult.setBallotOption(ballotOption);
        testResult.setVoteDate(new Date(3333L));
        testResult.setVoteUpdatedDate(new Date(4444L));
        testResult.setUser(USER);
        testResult.setBallot(ballot);

        assertEquals(ballotOption, testResult.getBallotOption());
        assertEquals(new Date(3333L), testResult.getVoteDate());
        assertEquals(new Date(4444L), testResult.getVoteUpdatedDate());
        assertEquals(ballot, testResult.getBallot());
        assertEquals(USER, testResult.getUser());
    }

    @Test
    void defaultConstructor_worksProperly(){
        BallotResult ballotResult = new BallotResult();

        assertNull(ballotResult.getBallot());
        assertNull(ballotResult.getBallotOption());
        assertNull(ballotResult.getVoteUpdatedDate());
        assertNull(ballotResult.getUser());
    }


}