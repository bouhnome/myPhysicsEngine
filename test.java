import java.util.*;
import java.awt.Color;
public class test{
	
	static ParticleForceRegistry registry = new ParticleForceRegistry();
	static LinkedList<Particle> listOfParticles = new LinkedList<Particle>();
	static LinkedList<Particle> structure = new LinkedList<Particle>();
	static ParticleGravity poids = new ParticleGravity(new Apoint(0,1,0));
	static ParticleGravity lancement = new ParticleGravity(Apoint.multByScalar(Apoint.normalize(new Apoint(1000,-0.8*800+40,0)),10));

		

	public static void simulate(double dt,int niter){

		if(niter==350){
			registry.clear();
		}
boolean replenish = true;
			for(int i =0;i<listOfParticles.size();i++){
				if(listOfParticles.get(i).position.y<0.80*800-35)
					replenish = false;
			}

			if(replenish){
				int u = 0;
		while(u<20)
		{		listOfParticles.add(new Particle(new Apoint(1000,800*0.80-30*1-u*30,0),10,new Apoint(0,0,0),Color.white,30));
				listOfParticles.add(new Particle(new Apoint(1000,800*0.80-30*3-30*(u),0),10,new Apoint(0,0,0),Color.orange,30));
				listOfParticles.add(new Particle(new Apoint(1000,800*0.80-30*5-30*(u),0),10,new Apoint(0,0,0),Color.red,30));
				listOfParticles.add(new Particle(new Apoint(1000,800*0.80-30*7-30*(u),0),10,new Apoint(0,0,0),Color.green,30));
				listOfParticles.add(new Particle(new Apoint(1000,800*0.80-30*9-30*(u),0),10,new Apoint(0,0,0),Color.yellow,30));
				
				u = u+10;	
		}			

			}

		
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

				
			}	//clear


			for(int i =1 ;i<listOfParticles.size();i++){
				for(int j =i+1;j<listOfParticles.size();j++){
					if(listOfParticles.get(i).position.y>=0.80*800-1200 && listOfParticles.get(j).position.y>=0.80*800-40&& Apoint.magnitude(listOfParticles.get(j).velocity)!=0 && Apoint.magnitude(listOfParticles.get(i).velocity)!=0 ){
					
						if(listOfParticles.get(i).position.x+listOfParticles.get(i).rayon>=listOfParticles.get(j).position.x-listOfParticles.get(j).rayon && listOfParticles.get(i).position.y+listOfParticles.get(i).rayon>=listOfParticles.get(j).position.y-listOfParticles.get(j).rayon && listOfParticles.get(i).position.y-listOfParticles.get(i).rayon<=listOfParticles.get(j).position.y+listOfParticles.get(j).rayon && listOfParticles.get(i).position.x-listOfParticles.get(i).rayon<=listOfParticles.get(j).position.x+listOfParticles.get(j).rayon){
							double  a=listOfParticles.get(i).position.x+listOfParticles.get(i).rayon;
							listOfParticles.get(i).position.x=listOfParticles.get(j).position.x-listOfParticles.get(j).rayon-listOfParticles.get(i).rayon;
							listOfParticles.get(j).position.x=a+listOfParticles.get(j).rayon;
							
								listOfParticles.get(i).myColor = Color.black;
								listOfParticles.get(j).myColor = Color.black;
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
		
		
		if(listOfParticles.get(0).position.x+listOfParticles.get(0).rayon>=1000-30&&(listOfParticles.get(0).position.x==1000000)){
			listOfParticles.get(0).position.x=1000-30-listOfParticles.get(0).rayon;
			listOfParticles.get(0).velocity.x=-listOfParticles.get(0).velocity.x;
			listOfParticles.get(5).velocity.x=40;
			listOfParticles.get(0).rebound=0.2;
			listOfParticles.get(5).rebound=0.2;
			registry.add(listOfParticles.get(0),poids);
			registry.add(listOfParticles.get(5),poids);
		}
		if(listOfParticles.get(5).position.x+listOfParticles.get(5).rayon>=1000+60&&(listOfParticles.get(0).position.x==-1000000)){
			for(int i = 6;i<listOfParticles.size();i++){
				registry.add(listOfParticles.get(i),poids);
				listOfParticles.get(i).rebound = 0.5;
			}
		}
		
if(listOfParticles.size()>=29){
	registry.clear();
	
}

		

		registry.updateForces(dt);
		for(Particle b : listOfParticles)
			b.integrate(dt);
	}

	public static void main(String [] args){
		listOfParticles.add(new Particle(new Apoint(0,0.8*800,0),0.01,new Apoint(),Color.black,6));
		registry.add(listOfParticles.get(0),lancement);

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