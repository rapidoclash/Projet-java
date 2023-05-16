package AutreClass;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Enemy {
	private float HP,HPmax,degats; // Stats en jeu
	private Image Image ,flippedImage; // images des monstres
	private float x,sk,y, vx; // Coord des monstre
	private List<Bordure> bordures;
	private float timeSinceLastAttack;
    public Enemy( float HP,float dgt, float x, float y, float vx, float vy) throws SlickException {
    	Image = new Image("image/plot.png");
    	flippedImage = Image.getFlippedCopy(true, false);
        this.HP = 1000;
        HPmax = 1000;
        degats = 0;
        this.x = x;
        this.y = 520;
        this.vx=0;
        timeSinceLastAttack = 4000;
    }

    

    public void afficher(Graphics g) {
    	if (this.enVie()) {
	        Image.draw(x, y, (int) (Image.getWidth() ), (int) (Image.getHeight()));
			g.setColor(Color.black);
			g.fillRect(x, y,(int)(Image.getWidth()) ,7);
			g.setColor(Color.green);
			g.fillRect(x+2, y+2,Math.max((((int)(Image.getWidth())-4)*HP)/HPmax, 0) ,3);
			
    	}

    }

        

    public void attack(Personnage joueur, int delta) {
        if (timeSinceLastAttack >= 1000) { // si le délai de 2 secondes est écoulé
            joueur.setHP(joueur.getHP() - (degats * (1 / joueur.getResistance())));
            timeSinceLastAttack = 0; // réinitialiser le temps depuis la dernière attaque
        } else {
            timeSinceLastAttack += delta; // incrémenter le temps écoulé depuis la dernière attaque
        }
    }

    public void update(GameContainer gc, int delta, int sol, Personnage joueur) throws SlickException {
    	if (this.enVie()) {
	        float distance = Math.abs(joueur.getX() - x);
	        if (HP >= HPmax * 0.5) { // Si l'ennemi a plus de la moitié de sa vie, il attaque le joueur
	            if (joueur.getBounds().intersect(this.getBounds())) {
	                attack(joueur, delta);
	            } else {
	                if (joueur.getX() < x) {
	                    x -= vx * delta / 1000f;
	                    Image = flippedImage.copy().getFlippedCopy(true, false);
	                }
	                if (joueur.getX() > x) {
	                    x += vx * delta / 1000f;
	                    Image = flippedImage.copy();
	                }
	            }
	        } else { // Si l'ennemi a moins de la moitié de sa vie, il fuit le joueur
	            if (distance < joueur.getImage().getWidth() * 4.0) {
	                if (joueur.getX() < x) {
	                    x += vx * delta / 1000f;
	                }
	                if (joueur.getX() > x) {
	                    x -= vx * delta / 1000f;
	                }
	            }
	            
	        }
    	}
//    	else {
//    		if (png == "liche_gauche") {
//    			Image = new Image("image/"+"skull"+".png");
//    			sk = y+ joueur.getImage().getHeight()-Image.getHeight();
//    		}
//    	}
        
    }
    
    
	public boolean enVie() {
		return HP>0;
	}

    public Zonne getBounds() {
        Zonne zonne = new Zonne(x, y, x + Image.getWidth(), y + Image.getHeight());
        return zonne;
    }

    public void ajouterBordure(Bordure bordure) {
        bordures.add(bordure);
    }

    public void supprimerBordure(Bordure bordure) {
        bordures.remove(bordure);
    }

    public float getY() {
        // TODO Auto-generated method stub
        return y;
    }

    public float getX() {
        // TODO Auto-generated method stub
        return x;
    }

    public void setY(float y) {
        // TODO Auto-generated method stub
        this.y = y;
    }

    public void setX(float x) {
        // TODO Auto-generated method stub
        this.x = x;
    }

	public Image getImage() {
		return Image;
	}



	public float getHP() {
		return HP;
	}



	public void setHP(float hp) {
		this.HP = hp;
		
	}
    
}
