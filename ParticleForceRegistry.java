import java.util.*;
class ParticleForceRegistration{
  public Particle a;
  public ParticleForceGenerator fg;
  public ParticleForceRegistration(Particle a , ParticleForceGenerator fg){//the ParticleForRegistration merely points to the corresponding objects
    this.a = a;
    this.fg = fg;
    
  }
}


public class ParticleForceRegistry{

LinkedList<ParticleForceRegistration> registrations = new LinkedList<ParticleForceRegistration>();

public ParticleForceRegistry(){//the constructor won't be used
}

public void add(Particle a, ParticleForceGenerator fg){//adds the pair to the linked list
	registrations.add(new ParticleForceRegistration(a,fg));
}

public void remove(Particle a , ParticleForceGenerator fg){//removes the pair from the linked list and does nothing if the pair is not present
  registrations.remove(new ParticleForceRegistration(a,fg));
}

public void clear(){//empties the registrations of  all connections
  registrations.clear();
}

public void updateForces(double dt){// calls all the forceGenerators to update the forces of their corresponding particles
  for(ParticleForceRegistration b : registrations ){
  b.fg.updateForce(b.a,dt);
  }
}

}
