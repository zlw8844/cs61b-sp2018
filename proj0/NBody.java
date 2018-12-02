
public class NBody{
	public static String backgroundImg = "images/starfield.jpg";
	//note: this should be a staic method.
	public static double readRadius(String file){
		In in = new In(file);
		double radius;
		in.readInt();
		radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String file){
		In in = new In(file);
		int planetCount = in.readInt();
		Planet[] allPlanets = new Planet[planetCount];
		in.readDouble();
		int i = 0;
		while(i < planetCount){
			double xPos = in.readDouble();
			double yPos = in.readDouble();
			double xVel = in.readDouble();
			double yVel = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			Planet planet = new Planet(xPos, yPos, xVel, yVel, m, img);
			allPlanets[i] = planet;
			i += 1;
		}
		return allPlanets;
	}

	public static void drawBackgroud(Double universeRadius){
		StdDraw.setScale(-universeRadius, universeRadius);
		StdDraw.clear();
		StdDraw.picture(0, 0, backgroundImg);
		StdDraw.show();
	}

	public static void drawAllPlanets(Planet[] allPlanets){
			for (Planet planet: allPlanets){
			planet.draw();
		}

	}
	public static void main(String[] args){
		Double T = Double.parseDouble(args[0]);
		Double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Double universeRadius = readRadius(filename);
		Planet[] allPlanets = readPlanets(filename);
		//System.out.println(allPlanets.length);

		//draw backGround;
		drawBackgroud(universeRadius);

		//draw allPlanets;
		drawAllPlanets(allPlanets);

		//start simulation;
		StdDraw.enableDoubleBuffering();
		double t = 0;
		int LEN = allPlanets.length;
		
		while (t<=T){
			double[] xForces = new double[LEN];
			double[] yForces = new double[LEN];

			for (int i=0; i<LEN; i++){
				xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
				yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
			}

			for (int i=0; i<LEN; i++){
				allPlanets[i].update(dt, xForces[i],yForces[i]);
			}

			drawBackgroud(universeRadius);

			drawAllPlanets(allPlanets);

			StdDraw.show();
			StdDraw.pause(10);
			t += dt;
		}
		
		StdOut.printf("%d\n", allPlanets.length);
		StdOut.printf("%.2e\n", universeRadius);
		for (int i = 0; i < allPlanets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
            allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);   
		}
	}
}