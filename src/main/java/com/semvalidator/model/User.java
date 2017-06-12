package com.semvalidator.model;

import com.semvalidator.enums.UserProfile;
import com.semvalidator.util.AuthoritiesUtils;

import javax.persistence.*;
import java.util.List;

/**
 * Created by comp-dev on 4/13/17.
 *
 */
@Entity
@Table(name="tb_user")
public class User extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String login;

	private String password;

	private String firstName;

	private String lastName;

	@Enumerated(value = EnumType.STRING)
	private UserProfile profile;

	public User() {
	}

	public List<String> getAuthorities(){
		if( profile != null ){
			return AuthoritiesUtils.get(profile);
		}
		return null;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public UserProfile getProfile() {
		return profile;
	}

	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}
}
