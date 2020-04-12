package com.rjxy.model;

/*
 * 管理员类，对应表：t_admin
 * id 主键
 * username 用户名
 * password 密码
 * nickname 昵称
 * role 角色
 */
public class Admin {

	private int id;
	private String username;
	private String password;
	private String nickname;
	private int role;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
}
