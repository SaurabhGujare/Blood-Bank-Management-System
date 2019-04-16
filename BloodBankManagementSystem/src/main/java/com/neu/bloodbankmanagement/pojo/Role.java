package com.neu.bloodbankmanagement.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Role")
public class Role {
	
	private long id;
	private String userName;
	private String password;
	private String role;
	
	public Role() {
		
	}
	

	public Role(long id, String userName, String password, String role) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "Role [id=" + id + ", userName=" + userName + ", password=" + password + ", role=" + role + "]";
	}
	
	
	

}
