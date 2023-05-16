import org.newdawn.slick.AppGameContainer;

import org.newdawn.slick.SlickException;

public class Main {

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new MonJeu("light souls"));
		
	    app.setIcons(new String[] {"src/icon48.gif"});	    
		//app.setTargetFrameRate(120);
		app.setDisplayMode(1024,720,false); 
		app.start();
	}

}