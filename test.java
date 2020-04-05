import java.util.*;
import java.awt.Color;
public class test{
	//we declare the data structure that will hold the informations on the link between the particles and the force generators
	static ParticleForceRegistry registry = new ParticleForceRegistry();
	//we declare a linkedlist for the particles
	static LinkedList<Particle> listOfParticles = new LinkedList<Particle>();
	//we declare all the force we might need
	static ParticleGravity poids = new ParticleGravity(new Apoint(0,9,0));

		

	public static void simulate(double dt,int niter){


		
			for(int j = 1;j<listOfParticles.size();j++)
			{if(listOfParticles.get(0).position.x+listOfParticles.get(0).rayon>=listOfParticles.get(j).position.x-listOfParticles.get(j).rayon && listOfParticles.get(0).position.y+listOfParticles.get(0).rayon>=listOfParticles.get(j).position.y-listOfParticles.get(j).rayon && listOfParticles.get(0).position.y-listOfParticles.get(0).rayon<=listOfParticles.get(j).position.y+listOfParticles.get(j).rayon && listOfParticles.get(0).position.x-listOfParticles.get(0).rayon<=listOfParticles.get(j).position.x+listOfParticles.get(j).rayon){
							double  a=listOfParticles.get(0).position.x+listOfParticles.get(0).rayon;
							listOfParticles.get(0).position.x=listOfParticles.get(j).position.x-listOfParticles.get(j).rayon-listOfParticles.get(0).rayon;
							listOfParticles.get(j).position.x=a+listOfParticles.get(j).rayon;
							
			
							listOfParticles.get(0).velocity.x = -listOfParticles.get(0).velocity.x;
							listOfParticles.get(j).velocity.x = -0.1*listOfParticles.get(0).velocity.x;
			
						}}

			for(int i =1;i<listOfParticles.size();i++){
				if(listOfParticles.get(i).velocity.x !=0 && (listOfParticles.get(i).position.x+listOfParticles.get(i).rayon<=1000-30 || listOfParticles.get(i).position.x-listOfParticles.get(i).rayon>=1000+30)){
					registry.add(listOfParticles.get(i),poids);
					listOfParticles.get(i).rebound = 0;
				}

				
			}	

			for(int i =1 ;i<listOfParticles.size();i++){
				for(int j =i+1;j<listOfParticles.size();j++){
					if(listOfParticles.get(i).position.y>=0.80*800-1200 && listOfParticles.get(j).position.y>=0.80*800-40&& Apoint.magnitude(listOfParticles.get(j).velocity)!=0 && Apoint.magnitude(listOfParticles.get(i).velocity)!=0 ){
						listOfParticles.get(i).myColor = Color.white;
						if(listOfParticles.get(i).position.x+listOfParticles.get(i).rayon>=listOfParticles.get(j).position.x-listOfParticles.get(j).rayon && listOfParticles.get(i).position.y+listOfParticles.get(i).rayon>=listOfParticles.get(j).position.y-listOfParticles.get(j).rayon && listOfParticles.get(i).position.y-listOfParticles.get(i).rayon<=listOfParticles.get(j).position.y+listOfParticles.get(j).rayon && listOfParticles.get(i).position.x-listOfParticles.get(i).rayon<=listOfParticles.get(j).position.x+listOfParticles.get(j).rayon){
							double  a=listOfParticles.get(i).position.x+listOfParticles.get(i).rayon;
							listOfParticles.get(i).position.x=listOfParticles.get(j).position.x-listOfParticles.get(j).rayon-listOfParticles.get(i).rayon;
							listOfParticles.get(j).position.x=a+listOfParticles.get(j).rayon;
							
								listOfParticles.get(i).myColor = Color.white;
								listOfParticles.get(j).myColor = Color.white;
								listOfParticles.get(i).damping = 0.95;
								listOfParticles.get(j).damping = 0.95;
							listOfParticles.get(i).velocity.x = -listOfParticles.get(i).velocity.x;
							listOfParticles.get(j).velocity.x = -listOfParticles.get(i).velocity.x;
			
						}
					}
				}

			}	

			for(int i = 1; i<listOfParticles.size();i++)	
{			if(listOfParticles.get(i).position.y>=0.80*800-40 && Apoint.magnitude(listOfParticles.get(i).velocity)!=0 && (listOfParticles.get(i).position.x+listOfParticles.get(i).rayon>=1000-30 &&listOfParticles.get(i).position.x-listOfParticles.get(i).rayon<=1000+30 )){
				if(listOfParticles.get(i).position.x+listOfParticles.get(i).rayon>=1000-30 ){
						listOfParticles.get(i).position.x=1000-30-listOfParticles.get(i).rayon;
						listOfParticles.get(i).velocity.x=-30;
				}
	
			}}
		
		/*
		if(listOfParticles.get(0).position.x+listOfParticles.get(0).rayon>=1000-30){
			listOfParticles.get(0).position.x=1000-30-listOfParticles.get(0).rayon;
			listOfParticles.get(0).velocity.x=-listOfParticles.get(0).velocity.x;
			listOfParticles.get(5).velocity.x=40;
			listOfParticles.get(0).rebound=0.2;
			listOfParticles.get(5).rebound=0.2;
			registry.add(listOfParticles.get(0),poids);
			registry.add(listOfParticles.get(5),poids);
		}
		if(listOfParticles.get(5).position.x+listOfParticles.get(5).rayon>=1000+60){
			for(int i = 6;i<listOfParticles.size();i++){
				registry.add(listOfParticles.get(i),poids);
				listOfParticles.get(i).rebound = 0.5;
			}
		}
		*/



		//the registry updates the forces of all the particles
		registry.updateForces(dt);
		//we update the velocity and position of all the particles + un test de print
		for(Particle b : listOfParticles)
			b.integrate(dt);
	}

	public static void main(String [] args){
		//the particle linked list if filled with one particle
		listOfParticles.add(new Particle(new Apoint(200,100,0),0.01,new Apoint(42,42,0),Color.black,6));

		int u = 0;
		while(u<20)
		{		listOfParticles.add(new Particle(new Apoint(1000,800*0.80-30*1-u*30,0),0.01,new Apoint(0,0,0),Color.white,30));
				listOfParticles.add(new Particle(new Apoint(1000,800*0.80-30*3-30*(u),0),0.01,new Apoint(0,0,0),Color.orange,30));
				listOfParticles.add(new Particle(new Apoint(1000,800*0.80-30*5-30*(u),0),0.01,new Apoint(0,0,0),Color.red,30));
				listOfParticles.add(new Particle(new Apoint(1000,800*0.80-30*7-30*(u),0),0.01,new Apoint(0,0,0),Color.green,30));
				listOfParticles.add(new Particle(new Apoint(1000,800*0.80-30*9-30*(u),0),0.01,new Apoint(0,0,0),Color.yellow,30));
				
				u = u+10;	
		}			

		FenetrePlotCourbe fenetre = new FenetrePlotCourbe(listOfParticles);
		fenetre.lancement();
	}
}