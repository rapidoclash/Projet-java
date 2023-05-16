package AutreClass;


public class Zonne {
	private float x1,y1,x2,y2;
	
	public Zonne(float x,float y,float d,float e) {
		this.x1 = x;
		this.x2 = d;
		this.y1 = y;
		this.y2 = e;
	}

	public boolean intersects(Zonne z) {
	    float[] b1 = {x1, y1};
	    float[] b2 = {x2, y1};
	    float[] b3 = {x2, y2};
	    float[] b4 = {x1, y2};

	    float[] z1 = {z.x1, z.y1};
	    float[] z2 = {z.x2, z.y1};
	    float[] z3 = {z.x2, z.y2};
	    float[] z4 = {z.x1, z.y2};

	    boolean insideZ = (this.x1 <= z1[0] && z1[0] <= this.x2 && this.y1 <= z1[1] && z1[1] <= this.y2) ||
	            (this.x1 <= z2[0] && z2[0] <= this.x2 && this.y1 <= z2[1] && z2[1] <= this.y2) ||
	            (this.x1 <= z3[0] && z3[0] <= this.x2 && this.y1 <= z3[1] && z3[1] <= this.y2) ||
	            (this.x1 <= z4[0] && z4[0] <= this.x2 && this.y1 <= z4[1] && z4[1] <= this.y2);

	    boolean insideThis = (z.x1 <= b1[0] && b1[0] <= z.x2 && z.y1 <= b1[1] && b1[1] <= z.y2) ||
	            (z.x1 <= b2[0] && b2[0] <= z.x2 && z.y1 <= b2[1] && b2[1] <= z.y2) ||
	            (z.x1 <= b3[0] && b3[0] <= z.x2 && z.y1 <= b3[1] && b3[1] <= z.y2) ||
	            (z.x1 <= b4[0] && b4[0] <= z.x2 && z.y1 <= b4[1] && b4[1] <= z.y2);


	    boolean overlap = !((z.x1 > x2) || (z.x2 < x1) || (z.y1 > y2) || (z.y2 < y1));

	    return insideZ || insideThis || overlap;
	}
	public boolean intersect(Zonne z) {
	    float xLeft = Math.max(x1, z.x1);
	    float yTop = Math.max(y1, z.y1);
	    float xRight = Math.min(x2, z.x2);
	    float yBottom = Math.min(y2, z.y2);

	    if (xLeft >= xRight || yTop >= yBottom) {
	        return false;
	    }

	    return true;
	}
	
//	public boolean intersects(Zonne Z) {
//		
//		return !(x1 > Z.x2 || x2 < Z.x1 || y1 > Z.y2 || y2 < Z.y1);
//		}
//	public boolean intersects(Zonne z) {
//		boolean bo2 = (this.x1<z.x1 && z.x1<this.x2) || (this.x1<z.x2 && z.x2< this.x2) || (z.x1<this.x1 && this.x2 < z.x2)  ;
//		return bo2;
//	}
	
}
