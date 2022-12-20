package factory.admin;

import javax.inject.Inject;
import javax.inject.Singleton;

import action.BaseAction;
import action.user.CreateUserAction;
import action.user.DeleteUserAction;
import action.user.EditUserAction;
import action.user.GetAllUsersAction;
import action.user.GetUserAction;
import config.ActionConfig;

@Singleton
public class UserActionFactory {
	
	private final CreateUserAction createUserAction;
	private final EditUserAction editUserAction;
	private final GetUserAction getUserAction;
	private final DeleteUserAction deleteUserAction;
	private final GetAllUsersAction getAllUsersAction;
	
	@Inject
	public UserActionFactory(
			CreateUserAction createUser,
			EditUserAction editUserAction,
			GetUserAction getUserAction,
			DeleteUserAction deleteUserAction,
			GetAllUsersAction getAllUsersAction) {
		this.createUserAction  = createUser;
		this.editUserAction    = editUserAction;
		this.getUserAction     = getUserAction;
		this.deleteUserAction  = deleteUserAction;
		this.getAllUsersAction = getAllUsersAction;
	}
	
	public BaseAction create(String action) {
		
		if(action.equals(ActionConfig.CREATE_USER)) {
			return createUserAction;
		} else if(action.equals(ActionConfig.EDIT_USER)) {
			return editUserAction;
		} else if(action.equals(ActionConfig.GET_USER)) {
			return getUserAction;
		} else if(action.equals(ActionConfig.DELETE_USER)) {
			return deleteUserAction;
		} else if(action.equals(ActionConfig.GET_ALL_USERS)) {
			return getAllUsersAction;
		} else {
			throw new RuntimeException("Action " + action + " not present.");
		}
		
	}
	

}
