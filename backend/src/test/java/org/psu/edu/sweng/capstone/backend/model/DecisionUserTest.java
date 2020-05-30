package org.psu.edu.sweng.capstone.backend.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DecisionUserTest {
	
	private DecisionUser decisionUser = new DecisionUser();
	
	@BeforeEach
	void setUp() {
		decisionUser.setDecision(new Decision(1L, "Test Decision"));
		decisionUser.setUser(new User("TestUser", "fakepw", "User", "Test", "TestUser@gmail.com", new Date(1337L), new Date()));
	}
	
	@Test
	void constructor_worksProperly() {
		// given
		Decision newDecision = new Decision(2L, "New Decision");
		User newUser = new User("TReyob", "fakepw", "Reyob", "Ttam", "TtamReyob@gmail.com", new Date(1337L), new Date());
		
		// when
		DecisionUser newDecisionUser = new DecisionUser(newDecision, newUser);
		
		// then
		assertEquals(2L, newDecisionUser.getDecision().getId());
		assertEquals("New Decision", newDecisionUser.getDecision().getName());
		assertEquals("TReyob", newDecisionUser.getUser().getUserName());
		assertEquals("Reyob", newDecisionUser.getUser().getLastName());
		assertEquals("Ttam", newDecisionUser.getUser().getFirstName());
		assertEquals("TtamReyob@gmail.com", newDecisionUser.getUser().getEmailAddress());
	}
	
	@Test
	void getters_workProperly() {
		assertEquals(1L, decisionUser.getDecision().getId());
		assertEquals("Test Decision", decisionUser.getDecision().getName());
		assertEquals("TestUser", decisionUser.getUser().getUserName());
		assertEquals("User", decisionUser.getUser().getLastName());
		assertEquals("Test", decisionUser.getUser().getFirstName());
		assertEquals("TestUser@gmail.com", decisionUser.getUser().getEmailAddress());
	}
	
	@Test
	void setters_workProperly() {
		// given
		Decision newTestDecision = new Decision(1337L, "New Test Decision");
		User newTestUser = new User("jsmith", "fakepw", "Smith", "John", "JohnSmith@gmail.com", new Date(1337L), new Date());
		
		// when
		decisionUser.setDecision(newTestDecision);
		decisionUser.setUser(newTestUser);
		
		// then
		assertEquals(1337L, decisionUser.getDecision().getId());
		assertEquals("New Test Decision", decisionUser.getDecision().getName());
		assertEquals("jsmith", decisionUser.getUser().getUserName());
		assertEquals("Smith", decisionUser.getUser().getLastName());
		assertEquals("John", decisionUser.getUser().getFirstName());
		assertEquals("JohnSmith@gmail.com", decisionUser.getUser().getEmailAddress());
	}
}
