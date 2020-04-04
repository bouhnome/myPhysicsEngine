import java.util.*;
public class test{

	static ParticleForceRegistry registry = new ParticleForceRegistry();//we declare the data structure that will hold the informations on the link between the particles and the force generators
	//we declare a linkedlist for the particles
	static LinkedList<Particle> listOfParticles = new LinkedList<Particle>();




	public static void simulate(double dt){
			//the registry updates the forces of all the particles
			registry.updateForces(dt);

			
			//we update the velocity and position of all the particles + un test de print
			for(Particle b : listOfParticles)
				b.integrate(dt);

			
	}






	public static void main(String [] args){

		


		
		//we declare all the force we might need
		ParticleGravity poids = new ParticleGravity(new Apoint(0,9.8,0));
		ParticleGravity sidewaysPoids = new ParticleGravity(new Apoint(9,0,0));







		//the particle linked list if filled with some particles
		listOfParticles.add(new Particle(new Apoint(100,100,0),0.01,0.9));
		listOfParticles.add(new Particle(new Apoint(110,110,0),0.01,new Apoint(1,1,1),0.9));
		listOfParticles.add(new Particle(new Apoint(90,90,0),0.01,new Apoint(0,9,0),0.9));
		listOfParticles.add(new Particle(new Apoint(80,80,0),0.01,0.9));



		//on attache certaines forces
		for(Particle b : listOfParticles)
			registry.add(b,poids);
		registry.add(listOfParticles.get(1),sidewaysPoids);


		FenetrePlotCourbe fenetre = new FenetrePlotCourbe(listOfParticles);
		fenetre.lancement();




	}
}