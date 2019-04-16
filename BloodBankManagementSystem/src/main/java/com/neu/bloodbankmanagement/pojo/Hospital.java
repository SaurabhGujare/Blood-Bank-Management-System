package com.neu.bloodbankmanagement.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="hospital")
public class Hospital {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id", unique = true, nullable = false)
	private long id;
	
	@Column(name="Name")
	@NotNull(message="is required")
	private String name;
	
	@Column(name="Email")
	@Pattern(regexp="[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+", message="Invalid Email id")
	@NotNull(message="is required")
	private String email;
	
	@Column(name="Phone")
	@Pattern(regexp="\\d{3}-\\d{3}-\\d{4}", message="Invalid Phone number (xxx-xxx-xxxx)")
	@NotNull(message="is required")
	private String phone;
	
	
	@Column(name="Username")
	@NotNull(message="is required")
	@Size(min=5, max=8, message="Minimum 5 and Maximum 8 characters required")
	private String userName;
	
	@Column(name="Password")
	@NotNull(message="is required")
	@Size(min=5, message="Minimum 5 characters required")
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="Role_Fk")
	private Role role;
	
	public Hospital() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Hospital [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + "]\n";
	}
	
	
	
	
}
