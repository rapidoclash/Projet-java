import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import AutreClass.Bordure;
import AutreClass.Enemy;
import AutreClass.Personnage;
import AutreClass.Zonne;

public class Niveau0 extends BasicGameState {
	private static Personnage J1;
	private Image fond;
	Enemy [] enemies = new Enemy[0];
	
	public Niveau0() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		fond = new Image("image/fonddef.png");
		J1 = new  Personnage(gc,900,550);
		Bordure bordure = new Bordure(300, 300, 1080-(int)(J1.getImage().getWidth()*1.5), 720);
		J1.ajouterBordure(bordure);
	}
    public void enter(GameContainer gc, StateBasedGame game) throws SlickException {
        // Définir l'icône de la fenêtre
    	
    }

	@Override
	public void render(GameContainer arg0, StateBasedGame st, Graphics g) throws SlickException {
		fond.draw(0, 2,(int)(fond.getWidth()*0.7),(int)(fond.getHeight()*0.7));
		J1.dessiner(g);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		J1.update(gc, delta,550,enemies);
		Input input = gc.getInput();
		Zonne z1 = new Zonne(400,540,440,580);
		Zonne zJ= J1.getBounds();
		

		if (input.isKeyPressed(Input.KEY_E)&& z1.intersect(zJ) && J1.enVie()) {
	        //fond = new Image("images/salleV2.png");
	        sbg.enterState(1); // passage niveau 
	        J1.supprimerBordure(0);
	        J1.setX(50);
			J1.setY(525);
			for (int i = 0; i < J1.getBoulesDeFeu().size(); i++) {
			    J1.getBoulesDeFeu().remove(i);
			    i--;
			    
			}
		}
	}


	@Override
	public int getID() {

		return 0;
	}

	public static Personnage getJ1() {
		return J1;
	}

	public static void setJ1(Personnage j1) {
		J1 = j1;
	}

	
}
