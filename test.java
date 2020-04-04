import java.util.*;
public class test{
	public static void main(String [] args){
		double t_init =0;//time of start of the simulation
		double dt = 0.001;//time step
		double t_fin  =100;//time of ending of the simulation
		double n = (int)((t_fin-t_init)/dt)+1;//number of iterations of the simulation
		




		ParticleForceRegistry registry = new ParticleForceRegistry();//we declare the data structure that will hold the informations on the link between the particles and the force generators


		//we declare all the force we might need
		ParticleGravity poids = new ParticleGravity(new Apoint(0,0,-9.8));
		ParticleGravity sidewaysPoids = new ParticleGravity(new Apoint(0,9.8,0));



		//we declare a linkedlist for the particles
		LinkedList<Particle> listOfParticles = new LinkedList<Particle>();



		//the particle linked list if filled with some particles
		listOfParticles.add(new Particle(new Apoint(0,0,10),0.01,0.5));
		listOfParticles.add(new Particle(new Apoint(0,1,2),0.01,new Apoint(1,1,1)));
		listOfParticles.add(new Particle(new Apoint(1,0,-10),0.01,new Apoint(0,9,0),0.3));
		listOfParticles.add(new Particle(new Apoint(1,1,1),0.01));


		

		//simulation loop
		for(int i=1;i<=n;i++){
			//we give weight to every particle from time t=0
			if(i==1){
				for(Particle b : listOfParticles)
					registry.add(b,poids);
			}
			//we attach another force generator to the first particle in the list when i=500
			if(i==500)
				registry.add(listOfParticles.get(1),sidewaysPoids);

			//the registry updates the forces of all the particles
			registry.updateForces(dt);

			//we update the velocity and position of all the particles
			for(Particle b : listOfParticles)
				b.integrate(dt);

			//draw(); //we draw the frame 

			//un print test pour voir si tout fonctionne bien
			for(Particle b : listOfParticles)
				System.out.println(b.position);
			System.out.println();
			System.out.println();
		}
	}
}