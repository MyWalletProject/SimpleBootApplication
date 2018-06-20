package com.example.spring.req_domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Req_Role {

	public Integer roleId;

	@Pattern(regexp="[a-zA-Z]+",message="Role Name only alphabets")
	@Size(min=3,message="role Name must be atleast 3 characters !")
	@NotNull(message="role Name cannot be null")
	public String  roleName;

	public String  roleDescription;

	public Boolean isActive;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
