import java.awt.Color;
import java.awt.Graphics;
public class Particle{

  private double inverseMass; 
  /* it is more convinient to handle the inverse of the mass of the particle
   * for instance immovable objects would have 0 inverse mass that is infinit mass
  */
  private Apoint position;
  private Apoint velocity;
  private Apoint acceleration;
  private Apoint forceAccum;
  private double damping; // parameter that allows us to simulate simple drag forces 
  private double rebound;
  private int rayon;//rayon de la particule





  //constructors
  public Particle(Apoint position,double inverseMass, Apoint velocity ,double damping,int rayon,double rebound){
    this.position = new Apoint(position);
    this.velocity = new Apoint(velocity);
    this.acceleration = new Apoint();
    this.forceAccum = new Apoint();
    this.inverseMass = inverseMass;
    this.damping = damping;
    this.rayon = rayon;
    this.rebound = rebound;
  }

  public Particle(Apoint position, double inverseMass,int rayon,double rebound){
    this(position,inverseMass,new Apoint(),1,rayon,rebound);
  }

    public Particle(Apoint position, double inverseMass,Apoint velocity,int rayon,double rebound){
    this(position,inverseMass,velocity,1,rayon,rebound);
  }

    public Particle(Apoint position, double inverseMass,double damping,int rayon,double rebound){
    this(position,inverseMass,new Apoint(),damping,rayon,rebound);
  }




//getters and setters
  public Apoint getPos(){
    return this.position;
  }
  public Apoint getVel(){
    return this.velocity;
  }
  public Apoint getAcc(){
    return this.acceleration;
  }
  public Apoint getFoAcu(){
    return this.forceAccum;
  }
  public double getInverseMass(){
    double b = this.inverseMass;
    return b;
  }
  public double getDamp(){
    return this.damping;
  }
  public int getRayon(){
    return this.rayon;
  }
  public double getRebound(){
    return this.rebound;
  }


  public void setPos(Apoint a){
    this.position = new Apoint(a);
  }
  public void setVel(Apoint a){
    this.velocity = new Apoint(a);
  }
  public void setAcc(Apoint a){
    this.acceleration = new Apoint(a);
  }
  public void setFoAcu(Apoint a){
    this.forceAccum = new Apoint(a);
  }
  public void setInverseMass(double m){
    this.inverseMass = m;
  }
  public void setDamp(double m){
    this.damping = m;
  }
  public void setRayon(int r){
    this.rayon = r;
  }
  public void setRebound(double re){
    this.rebound = re;
  }




  public boolean hasFiniteMass(){
    if(inverseMass==0)
      return false;
    else
      return true;
  }
  

    /**
    * Pour dessiner la figure courante
    * @param l'objet graphique où dessiner
    */ 
   public void dessine(Graphics g){
         // Appel à la méthode de l'ancêtre
         g.setColor(Color.BLACK);
         // Pour dessiner une particule(on déssine un cercle de petit rayon)
         g.fillOval((int)(position.getX())-getRayon(),(int)(position.getY())-getRayon(),2*rayon,2*rayon);
   }


  //integration method that integrates velocity and position uing Euler-explicit
  
  public void integrate(double dt){
  
    dt = Math.abs(dt);
    position = Apoint.add(position,Apoint.multByScalar(velocity,dt));//updating the position
    
    Apoint resultingAcc = acceleration;
    resultingAcc = Apoint.add(resultingAcc,Apoint.multByScalar(forceAccum,inverseMass));//obtaining acceleration from newton's second law
    
    velocity = Apoint.add(velocity,Apoint.multByScalar(resultingAcc,dt));//updating velocity
    
    velocity = Apoint.multByScalar(velocity,Math.pow(damping,dt));//we implement damping at each second
    
    forceAccum = new Apoint();//clearing out the force accumulator by zeroing it out 


    /*======================Detection and Gestion of colisions with the world's border==============*/

    if(position.getY()>800*FenetrePlotCourbe.limite_sol-rayon){
        position = new Apoint(position.getX(),800*FenetrePlotCourbe.limite_sol-rayon,position.getZ());
        velocity=new Apoint(velocity.getX(),-rebound*velocity.getY(),velocity.getZ()); 
    }
    if(position.getY()<rayon+22){//the value 22 is a constant and corresponds to the thickness of the band at the top of the window
        position = new Apoint(position.getX(),rayon+22,position.getZ());
        velocity=new Apoint(velocity.getX(),-rebound*velocity.getY(),velocity.getZ());
        
        
    }
    if(position.getX()<rayon){
        position = new Apoint(rayon,position.getY(),position.getZ());
        velocity=new Apoint(-rebound*velocity.getX(),velocity.getY(),velocity.getZ());
        
    }
    if(position.getX()>1400-rayon){
        position = new Apoint(1400-rayon,position.getY(),position.getZ());
        velocity=new Apoint(-rebound*velocity.getX(),velocity.getY(),velocity.getZ());
        
    }


    /*===============================================================================*/
  
  }
  
  public void addForce(Apoint force){//function adding a force to the force accumulator
   forceAccum = Apoint.add(force,forceAccum);
  }
  

}