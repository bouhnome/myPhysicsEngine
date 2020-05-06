import java.util.*;
public class test{
	static ParticleForceRegistry registry = new ParticleForceRegistry();//we declare the data structure that will hold the informations on the link between the particles and the force generators
 	//we declare a linkedlist for the particles
 	static LinkedList<Particle> listOfParticles = new LinkedList<Particle>();

 	static Simulation simulator = new Simulation(listOfParticles,registry);


 	public static void main(String [] args){

		//we declare all the forces we might need
		ParticleGravity poids = new ParticleGravity(new Apoint(0,9.8,0));
 		
		//the particle linked list if filled with some particles
		listOfParticles.add(new Particle(new Apoint(100,100,0)/*initial position*/,0.01/*inverse mass*/,new Apoint(100,0,0)/*initial Velocity*/,1/*damping*/,10/*radius*/,1/*rebound*/));
 		
		//we attach some forces
 		for(Particle b : listOfParticles)
 			registry.add(b,poids);
 		

 		FenetrePlotCourbe fenetre = new FenetrePlotCourbe(listOfParticles,simulator);
 		//we launch the window and the timer
 		fenetre.lancement();
	}
}
