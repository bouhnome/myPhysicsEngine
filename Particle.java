import java.awt.Color;
import java.awt.Graphics;
public class Particle{

  private double inverseMass; 
  /* it is more convinient to handle the inverse of the particle
   * for instance immovable objects such as the ground would have 0 inverse mass that is infinit mass
  */
  public Apoint position;
  public Apoint velocity;
  public Apoint acceleration;
  public Apoint forceAccum;
  public double damping; // parameter that allows us to simulate simple drag forces 
  public int rayon=20;//rayon de la particule
  public double rebound =1;
  public Color myColor;





  //constructors
  public Particle(Apoint position,double inverseMass, Apoint velocity ,double damping,Color myColor,int rayon){
    this.position = new Apoint(position);
    this.velocity = new Apoint(velocity);
    this.acceleration = new Apoint();
    this.forceAccum = new Apoint();
    this.inverseMass = inverseMass;
    this.damping = damping;
    this.myColor = myColor;
    this.rayon = rayon;
  }

  public Particle(Apoint position, double inverseMass,Color myColor,int rayon){
    this(position,inverseMass,new Apoint(),1,myColor,rayon);
  }

    public Particle(Apoint position, double inverseMass,Apoint velocity,Color myColor,int rayon){
    this(position,inverseMass,velocity,1,myColor,rayon);
  }

    public Particle(Apoint position, double inverseMass,double damping,Color myColor,int rayon){
    this(position,inverseMass,new Apoint(),damping,myColor,rayon);
  }










  public boolean hasFiniteMass(){
    if(inverseMass==0)
      return false;
    else
      return true;
  }
  
  public double getMass(){
     double b = 1/this.inverseMass;
    return b;
  }

  /**
   * Pour dessiner la figure courante
   * @param l'objet graphique où dessiner
   */ 
  public void dessine(Graphics g){
        // Appel à la méthode de l'ancêtre
        g.setColor(myColor);
        // Pour dessiner une particule(on déssine un cercle de petit rayon)
        g.fillRect((int)(position.x)-rayon,(int)(position.y)-rayon,2*rayon,2*rayon);
  }

  //integration method that integrates velocity and position uing Euler-explicit
  
  public void integrate(double dt){
  
    dt = Math.abs(dt);
    position = Apoint.add(position,Apoint.multByScalar(velocity,dt));//updating the position
    
    Apoint resultingAcc = acceleration;
    resultingAcc = Apoint.add(resultingAcc,Apoint.multByScalar(forceAccum,inverseMass));//obtaining acceleration from newton's second law
    
    velocity = Apoint.add(velocity,Apoint.multByScalar(resultingAcc,dt));//updating velocity
    
    velocity = Apoint.multByScalar(velocity,Math.pow(damping,dt));//we implement damping at each second

    //we detect collisions
    if(position.y>800*0.80-rayon){
        position.y = 800*0.80-rayon;
        velocity.y = -rebound*velocity.y; 
    }
    if(position.y<rayon+22){
        position.y = rayon+22;
        velocity.y = -rebound*velocity.y; 
    }
    if(position.x<rayon){
        position.x = rayon;
        velocity.x = -rebound*velocity.x; 
    }
    if(position.x>1400-rayon){
        position.x = 1400-rayon;
        velocity.x = -rebound*velocity.x; 
    }
    forceAccum = new Apoint();//clearing out the force accumulator by zeroing it out 
  
  }
  
  public void addForce(Apoint force){//function adding a force to the force accumulator
   forceAccum = Apoint.add(force,forceAccum);
  }
  

}