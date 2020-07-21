package org.psu.edu.sweng.capstone.backend.security;

import org.psu.edu.sweng.capstone.backend.dao.DecisionDAO;
import org.psu.edu.sweng.capstone.backend.dao.UserDAO;
import org.psu.edu.sweng.capstone.backend.enumeration.RoleEnum;
import org.psu.edu.sweng.capstone.backend.exception.EntityNotFoundException;
import org.psu.edu.sweng.capstone.backend.model.Decision;
import org.psu.edu.sweng.capstone.backend.model.DecisionUser;
import org.psu.edu.sweng.capstone.backend.model.User;
import org.psu.edu.sweng.capstone.backend.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("authCheck")
public class AuthCheck {
		
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private DecisionDAO decisionDao;
	
	/** Determines whether the authenticated user can access the requested user. 
	 *
	 * @param requestedUser - The user that is trying to be accessed
	 * @return true if the authenticated user has access, false if not
	 */
	public boolean hasUserAccess(String requestedUser) {
		final String authUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		return authUsername.equals(requestedUser);
	}
	
	/** Returns whether the authenticated user has Administrator role access.
	 * 
	 * @return True if authenticated User is an Administrator, false if not.
	 * @throws EntityNotFoundException If the User doesn't exist in the database.
	 */
	public boolean isAdmin() throws EntityNotFoundException {
		final String authUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		final User authUser = userDao.findByUserName(authUsername).orElseThrow(
				() -> new EntityNotFoundException("User " + authUsername));
		
		for (UserRole ur : authUser.getRoles()) {
			if (RoleEnum.ADMIN.getDescription().equals(ur.getRole().getName())) {
				return true;
			}
		}
		
		return false;
	}
	
	/** Determines whether the authenticated user is the Decision Owner.
	 * 
	 * @param decisionId - The Unique Identifier of the Decision
	 * @return True if the authenticated user is the Decision Owner, false if not
	 * @throws EntityNotFoundException If the Decision doesn't exist in the database.
	 */
	public boolean isDecisionOwner(final Long decisionId) throws EntityNotFoundException {
		final String authUsername = SecurityContextHolder.getContext().getAuthentication().getName();

		final Decision decision = decisionDao.findById(decisionId).orElseThrow(
				() -> new EntityNotFoundException("Decision " + decisionId.toString()));
		
		return authUsername.equals(decision.getOwnerId().getUserName());
	}
	
	/** Determines whether the authenticated user is on the Decision User list of the given Decision
	 * 
	 * @param decisionId - The Unique Identifier of the Decision
	 * @return True if authenticated user is on the Decision User list of the given Decision, false if not.
	 * @throws EntityNotFoundException If the Decision doesn't exist in the database.
	 */
	public boolean onDecisionUserList(final Long decisionId) throws EntityNotFoundException {
		final String authUsername = SecurityContextHolder.getContext().getAuthentication().getName();

		final Decision decision = decisionDao.findById(decisionId).orElseThrow(
				() -> new EntityNotFoundException("Decision " + decisionId.toString()));
		
		boolean isDecisionUser = false;
		for (DecisionUser du : decision.getDecisionUsers()) {
			if (authUsername.equals(du.getUser().getUserName())) {
				isDecisionUser = true;
			}
		}
		
		return isDecisionUser;
	}
}
