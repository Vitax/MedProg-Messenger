package de.sb.messenger.persistence;


public class Group {

	private Group _ADMIN;

	private Group _USER;
	
	
	public Group() 
	{
		super();
	}
	
	public Group(Group group){
		this._ADMIN = group._ADMIN;
		this._USER = group._USER;
	}

}