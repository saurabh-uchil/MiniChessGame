package model;

public class User {
	private String userId;
	private String password;
	private int gameCount;
	private int winCount;
	private boolean isLoggedIn;
	
	public User(String userId, String password) {
		this.userId = userId;
		this.password = password;
		gameCount = 0;
		winCount = 0;
		isLoggedIn = false;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public boolean getIsLoggedIn() {
		return isLoggedIn;
	}
		
	public void setLoginStatus(boolean status) {
		isLoggedIn = status;
	}
	
	public boolean logout() {
		isLoggedIn = false;
		return true;
	}
	
	

}
