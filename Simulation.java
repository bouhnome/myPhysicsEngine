import java.util.*;
public class Simulation{
	static LinkedList<Particle> listOfParticles;
	static ParticleForceRegistry registry;
	public Simulation(LinkedList<Particle> listOfParticles,ParticleForceRegistry registry){
	this.listOfParticles = listOfParticles;
	this.registry = registry;
	}
	public void simulate(double dt){
			//the registry updates the forces of all the particles
 			registry.updateForces(dt);


 			//we update the velocity and position of all the particles + un test de print
 			for(Particle b : listOfParticles)
 				{b.integrate(dt);}
	}
}