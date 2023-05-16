package AutreClass;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Personnage {

	private Image image ,flippedImage;
	private float x, y;
	private float vx, vy;
	private float HP ,HPmax , Armure, Resistance;
	private boolean enSaut;
	private List<Bordure> bordures;
	private List<BouleDeFeu> boulesDeFeu;
	private float timeSinceLastBDF ;
	

	public Personnage(GameContainer gc, int x, int y) throws SlickException {
		image = new Image("image/sorcier_flamme.png");
		flippedImage = image.getFlippedCopy(true, false);
		this.x = x;
		this.y = y;
		vx = 400;
		vy = 0;
		enSaut = false;
		bordures = new ArrayList<>();
		HP = 20;
		HPmax = 20;
		Armure = 10;
		Resistance = 12;
		boulesDeFeu = new ArrayList<>();
		timeSinceLastBDF = 1000;
	}
	
	public Personnage(GameContainer gc, String nomimage ,int x, int y ,int vx,float HP,float armure,float resistance) throws SlickException {
		image = new Image("image/"+nomimage+".png");
		flippedImage = image.getFlippedCopy(true, false);
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.HP = HP;
		this.HPmax = HP;
		Armure = armure;
		Resistance = resistance ;
		vy = 0;
		enSaut = false;
		bordures = new ArrayList<>();
		boulesDeFeu = new ArrayList<>();
		
	}

	public void dessiner(Graphics g) throws SlickException {
		image.draw(x, y, (int) (image.getWidth()), (int) (image.getHeight() ));
		g.setColor(Color.black);
		g.fillRect(x, y,(int)(image.getWidth()) ,7);
		g.setColor(Color.green);
		g.fillRect(x+2, y+2,Math.max((((int)(image.getWidth())-4)*HP)/HPmax, 0) ,3);

		for(BouleDeFeu bdf : boulesDeFeu){
		    bdf.render(g);
		}
		float coef = 0.25f; 
		int l = 100;
		int m = 30;
		int n = 700;
		Image bdf = new Image("image/flamme1.png");;
		bdf.draw(m-(int) (image.getWidth()* coef), n-(int) (image.getWidth()* coef), (int) (image.getWidth()* coef), (int) (image.getHeight() * coef));
		g.setColor(Color.white);
		g.fillRect(m, n,l ,7);
		g.setColor(Color.gray);
		g.fillRect(m+2, n+2, Math.min(((l-4)*timeSinceLastBDF)/1000, l-4) ,3);


	}
	
	public void update(GameContainer gc, int delta, int sol,Enemy[] ennemis) throws SlickException {
		
		if (this.enVie()) {
			Input input = gc.getInput();
			
			tirerBouleDeFeu(gc,delta);
			for(BouleDeFeu bdf : boulesDeFeu){
			    bdf.update(gc, delta);
			    for(Enemy e :ennemis ) {
			    	if (e.enVie())
			    		bdf.hit(e);
			    	
			    }
			    	
			}
			
			
			if (input.isKeyDown(Input.KEY_Q)) {
				image = new Image("image/sorcier_flamme.png");
				// System.out.println("gauche");
				x -= vx * delta / 1000f;
			}
	
			if (input.isKeyDown(Input.KEY_D)) {
				image = flippedImage.copy();
				// System.out.println("droite");
				x += vx * delta / 1000f;
			}
	
			if (input.isKeyPressed(Input.KEY_SPACE) && !enSaut) {
				// System.out.println("saut");
				vy = -800;
				enSaut = true;
			}
		}
			// gravite
	
		if (enSaut) {
			y += vy * delta / 1000f;
			vy += 5000 * delta / 1000f;

			if (y >= sol) {
				y = sol;
				enSaut = false;
			}
		} else {
			y = sol;
		}

		// Restreint le personnage à chaque bordure
		for (Bordure bordure : bordures) {
			bordure.restreindre(this);
		}
		
	}
	public boolean enVie() {
		return HP>0;
	}
	
	public void tirerBouleDeFeu(GameContainer gc,int delta) throws SlickException {
	    Input input = gc.getInput();

	        if (timeSinceLastBDF >= 2000 && input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) { 
	        	BouleDeFeu bDF = new BouleDeFeu(x+(int)((image.getWidth())/2), y+(int)((image.getHeight())/2), 300,"flamme1" , input);
		        this.boulesDeFeu.add(bDF);
		        timeSinceLastBDF = 0;
	        } else {
	        	timeSinceLastBDF += delta; 
  
	    }
	    
	    
	    
		for (int i = 0; i < boulesDeFeu.size(); i++) {
		    if (!boulesDeFeu.get(i).isAlive()) {
		    	boulesDeFeu.remove(i);
		        i--;
		    }
		}
		
	}
	
	
	
	
	public List<BouleDeFeu> getBoulesDeFeu() {
		return boulesDeFeu;
	}

	public Zonne getBounds() {
		Zonne zonne = new Zonne(x, y, (float)(x + image.getWidth()),(float)( y + image.getHeight()));
		return zonne;
	}

	public void ajouterBordure(Bordure bordure) {
		bordures.add(bordure);
	}

	public void supprimerBordure(int nb) {
		bordures.remove(nb);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getHP() {
		return HP;
	}
	public float getHPmax() {
		return HPmax;
	}

	public void setHP(float f) {
		HP = f;
	}

	public float getResistance() {
	        return Resistance;
	}
	public float getArmure() {
        return Armure;
}

}
