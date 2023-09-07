package edu.kh.test.user.model;

public class UserDTO {
	int userNo;
	String userId;
	String userName;
	int UserAge;
	
	public UserDTO() {}
	public UserDTO(int userNo, String userId, String userName, int userAge) {
		this.userNo = userNo;
		this.userId = userId;
		this.userName = userName;
		UserAge = userAge;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserAge() {
		return UserAge;
	}
	public void setUserAge(int userAge) {
		UserAge = userAge;
	}
	@Override
	public String toString() {
		return "UserDTO [userNo=" + userNo + ", userId=" + userId + ", userName=" + userName + ", UserAge=" + UserAge
				+ "]";
	}
	
	
}
