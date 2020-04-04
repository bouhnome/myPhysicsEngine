public class ParticleGravity implements ParticleForceGenerator{

Apoint g;//holds the acceleration created by the gravitational force

public ParticleGravity(Apoint g){//creates a Gravity forceGenerator with a custom g-value
this.g = new Apoint(g); 
}

public void updateForce(Particle a, double dt){//implementation of the updateForce method
  //we first check that we don't have infinit mass 
  if(!a.hasFiniteMass())
    return;
  //we add the force to the particle's accumulator
  a.addForce( Apoint.multByScalar(g,a.getMass() ));
}

}