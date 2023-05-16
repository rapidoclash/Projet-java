import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import AutreClass.Bordure;
import AutreClass.Camera;
import AutreClass.Enemy;
import AutreClass.Liche;
import AutreClass.Personnage;
import AutreClass.Zonne;

public class Niveau1 extends BasicGameState {
	private Personnage J1;
    private Camera camera;
    private float xcam;
	private Enemy e1;
	private Image fond;
	Enemy [] enemies = new Enemy[3];
	public Niveau1() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		//camera = new Camera(gc.getWidth(), gc.getHeight());
		fond = new Image("image/salleV2.png");
		J1 = Niveau0.getJ1() ;
		
		//Bordure bordure = new Bordure(0, 0, 1080, 720);
		//J1.ajouterBordure(bordure);

		int k = 10;
		String [] img ={"liche_gauche","fantome"};
		for (int i = 0;i<enemies.length;i++) {
			int nb = (int)(Math.random()*2);
			enemies[i] =  new Liche(10,10, 500+k, 520, 0, 0);
			k+=100;
		}
	}	

	@Override
	public void render(GameContainer gc, StateBasedGame st, Graphics g) throws SlickException {

		//g.translate(-camera.getX(), -0);
		
		
		fond.draw(0, 2,(int)(fond.getWidth()*0.55),(int)(fond.getHeight()*0.55));
		//e1.afficher(g);
		
		
		for (Enemy enemy : enemies) {
			enemy.afficher(g);
		}
		
		
		J1.dessiner(g);
		
		//g.translate(-camera.getX(), -0);
		//qg.translate(camera.getX(), 0); 
//	    g.setColor(Color.red);
//	    g.fillOval(e1.getX() - 6, e1.getY() - 6, 12, 12);
//	    g.fillOval(e1.getX()+(float)(e1.getImage().getWidth())-6,e1.getY()+(float)(e1.getImage().getHeight())-6, 12, 12);
//	    g.drawRect(e1.getX(), e1.getY(), (float)(e1.getImage().getWidth()),(float)(e1.getImage().getHeight()));
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

		//camera.centerOn(J1.getX(), gc.getWidth()/2);
		if (J1.getX()+J1.getImage().getWidth()>= gc.getWidth()) { // il faudra aussi tester si tout les ennemie sont mort pour passer
			sbg.enterState(2);
	        J1.setX(50);
			J1.setY(525);
			for (int i = 0; i < J1.getBoulesDeFeu().size(); i++) {
			    J1.getBoulesDeFeu().remove(i);
			    i--;
			    
			}
		}

		
		if(!J1.enVie()) {
            sbg.enterState(-1);
        }
		J1.update(gc, delta,510,enemies);
		//e1.update(gc, delta, delta, J1);
		for (Enemy enemy : enemies) {
			enemy.update(gc, delta, 520, J1);
		}

	}

	@Override
	public int getID() {
		return 1;
	}

}
