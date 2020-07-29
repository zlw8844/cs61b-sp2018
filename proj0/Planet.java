public class Planet{
	public double xxPos; //Its current x position
	public double yyPos; //Its current y position
	public double xxVel; //Its current velocity in the x direction
	public double yyVel; //Its current velocity in the y direction
	public double mass; //Its mass
	public String imgFileName; //The name of the file that corresponds to the image that depicts the planet (for example, jupiter.gif)
	private static final double G = 6.67e-11;
	//constructor
	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;

	}
	//constructor
	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass  = p.mass;
		imgFileName = p.imgFileName;
	}

	//calcuate distance between two Planet objects
	public double calcDistance(Planet other){	
		double disSqu = (this.xxPos - other.xxPos)*(this.xxPos - other.xxPos)
				 + (this.yyPos - other.yyPos)*(this.yyPos - other.yyPos);
		return Math.sqrt(disSqu);
	}

	//calcuate the force exerted by another Planet objects
	public double calcForceExertedBy(Planet other){
		double distance = this.calcDistance(other);
		double force = Planet.G*this.mass*other.mass/(distance*distance);
		// or double force = G*mass*other.mass/(distance*distance);
		return force;
	}

	public double calcForceExertedByX(Planet other){
		double force = this.calcForceExertedBy(other);
		double distance = this.calcDistance(other);
		double forceX = force*(other.xxPos-this.xxPos)/distance;
		return forceX;
	}

	public double calcForceExertedByY(Planet other){
		double force = this.calcForceExertedBy(other);
		double distance = this.calcDistance(other);
		double forceY = force*(other.yyPos-this.yyPos)/distance;
		return forceY;
	}

	public double calcNetForceExertedByX(Planet[] allPlanets){
		double force = 0;
		/*
		int len = allPlanets.length;
		for (int i=0; i<len; i++){
			if (this.equals(allPlanets[i])){
				continue;
			}
			force += this.calcForceExertedByX(allPlanets[i]);
		}
		*/
		for (Planet planet: allPlanets){
			if (this.equals(planet)){
				continue;
			}
			force += this.calcForceExertedByX(planet);
		}
		return force;
	}

	public double calcNetForceExertedByY(Planet[] allPlanets){
		double force = 0;
		/*
		int len = allPlanets.length;
		for (int i=0; i<len; i++){
			if (this.equals(allPlanets[i])){
				continue;
			}
			force += this.calcForceExertedByY(allPlanets[i]);
		}
		*/
		for (Planet planet: allPlanets){
			if (this.equals(planet)){
				continue;
			}
			force += this.calcForceExertedByY(planet);
		}
		return force;
	}

	public void update(double dt, double fX, double fY){
		/*
		double ax = fX/this.mass;
		double ay = fY/this.mass;
		this.xxVel += ax*dt;
		this.yyVel += ay*dt;
		this.xxPos += this.xxVel*dt;
		this.yyPos += this.yyVel*dt;
		*/

		double ax = fX/mass;
		double ay = fY/mass;
		xxVel += ax*dt;
		yyVel += ay*dt;
		xxPos += xxVel*dt;
		yyPos += yyVel*dt;
	}

	public void draw(){
		String img = "images/" + imgFileName;
		StdDraw.picture(xxPos, yyPos, img);
		StdDraw.show();
	}

}