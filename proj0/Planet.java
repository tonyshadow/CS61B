public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
        /** Or:
        this(p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
        */
    }

    public double calcDistance(Planet p) {
        double dx = p.xxPos - xxPos;
        double dy = p.yyPos - yyPos;
        double r = Math.sqrt(dx * dx + dy * dy);
        return r;
    }

    public double calcForceExertedBy(Planet p) {
        double G = 6.67e-11;
        double r = calcDistance(p);
        double force = G * mass * p.mass / (r * r);
        return force;
    }

    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - xxPos;
        double r = calcDistance(p);
        double force = calcForceExertedBy(p);
        double forceX = force * dx / r;
        return forceX;
    }

    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - yyPos;
        double r = calcDistance(p);
        double force = calcForceExertedBy(p);
        double forceY = force * dy / r;
        return forceY;
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double netforceX = 0;
        for (int i = 0; i < planets.length; i++) {
            if (this.equals(planets[i])) {
            }else {
                netforceX += calcForceExertedByX(planets[i]);
            }
        }
        return netforceX;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double netforceY = 0;
        for (int i = 0; i < planets.length; i++) {
            if (this.equals(planets[i])) {
            }else {
                netforceY += calcForceExertedByY(planets[i]);
            }
        }
        return netforceY;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / mass;
        double aY = fY / mass;
        xxVel = xxVel + dt * aX;
        yyVel = yyVel + dt * aY;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    public void draw() {
        String planetToDraw = "./images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, planetToDraw);
    }
}
