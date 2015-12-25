package net.sleek.CDStore.webService.test;

public class UserInfo {
	public UserInfo(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public UserInfo(){}
	public void setId(int id){
		this.id = id;
	}
	public void setName(String name)
	{this.name = name;}
	int id;
	String name;
}
