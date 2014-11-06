package pl.edu.agh.eis.datasource;

/**
 * Class representing user profile.
 * TODO: Think how to represent conditions and Profile configuration
 *
 */
public class Profile {
	protected int iconResourceID;
	protected String name;
	protected boolean isActive;
	
	public Profile(int iconResourceID, String name) {
		this.iconResourceID = iconResourceID;
		this.name = name;
		isActive = false;
	}
	
	public boolean isActive(){
		return isActive;
	}
	
	public String getName() {
		return name;
	}
	
	public int getIconResourceID() {
		return iconResourceID;
	}
	

}
