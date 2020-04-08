/*=================This class contains the informations relative to a contact between two objects==============*/
public class ParticleContact{
	//holds the adress of the two particles that are involved in the contact. The second adresse can be NULL if the particle is pointing to the scenery
	Particle particles[] = new Particle[2];
	//restitution coeffecient of the collision 
	double restitution;
	//the contact normal vector from the point of view of the first particle in the array
	Apoint contactNormal;
    //Holds the depth of penetration at the contact 
    double penetration;

	//constructor
	public ParticleContact(Particle particles[],double restitution, Apoint contactNormal){
		this.particles = particles;
		this.restitution = restitution;
		this.contactNormal = new Apoint(contactNormal);
		this.penetration = penetration;
	}

	/*
	 * Resolves this contact, for both velocity and interpenetration.
	*/
	public void resolve(double dt){
		resolveVelocity(dt);
		resolveInterpenetration(dt);
	}


	/*
	 * Calculates the separating velocity at this contcat
	*/
	public double calculateSeparatingVelocity(){
		Apoint relativeVelocity = particles[0].getVel();
		if(particles[1]!=null)relativeVelocity = Apoint.substract(relativeVelocity,particles[1].getVel());
		return Apoint.dotProduct(relativeVelocity,contactNormal);
	}

	/*
	 *Handles the impulse calculations for this collision
	*/
	private void resolveVelocity(double dt){

		//Find the velocity in the direction of the contact.
		double separatingVelocity = calculateSeparatingVelocity();
		//Check wether it needs to be resolved.

		if(separatingVelocity>0){
			//the contact is either separatig or stationnary - there's no impulse required
			return;
		}

		//calculate the new separating velocity 
		double newSepVelocity = -separatingVelocity*restitution;

		double deltaVelocity = newSepVelocity - separatingVelocity;

		//we apply the change  in velocity to each object in proportion to 
		//its inverse mass(i.e., those with lower inverse mass [higher actual mass] get less change in velocity).
		double totalInverseMass = particles[0].getInverseMass();

		if(particles[1]!=null) totalInverseMass +=particles[1].getInverseMass();

		//if all particles have infinite mass, then impulses have no effect.
		if(totalInverseMass<=0) return;

		//calculate the impulse to apply.
		double impulse = deltaVelocity/totalInverseMass;

		//Find the amount of impulse per unit of mass.
		Apoint impulsePerIMass = Apoint.multByScalar(contactNormal,impulse);

		//apply impulses : they are applied in the direction of the contact,
		//and are proportional to the inverse mass.
		particles[0].setVel(Apoint.add(particles[0].getVel(),Apoint.multByScalar(impulsePerIMass,particles[0].getInverseMass()) ));

		if(particles[1]!=null){
			//Particle 1 goes in the opposite direction.
			particles[1].setVel(Apoint.add(particles[1].getVel(),Apoint.multByScalar(impulsePerIMass,-particles[1].getInverseMass())));
		}
	}

	/*
	 *Handles the interpenetration resolution for this contact
	 */
	void resolveInterpenetration(double dt){
		//if we don't have interpenetration, skip this step
		if(penetration<=0) return;

		//the movement of each object is based on its inverse mass, so total that
		double totalInverseMass = particles[0].getInverseMass();
		if(particles[1]!=null) totalInverseMass+=particles[1].getInverseMass();

		//if all particles have infinite mass, then do nothing
		if(totalInverseMass<=0) return;

		//find the amount of penetration resolution per unit of mass
		Apoint movePerIMass = Apoint.multByScalar(contactNormal,(-penetration/totalInverseMass));

		//apply the penetration resolution
		particles[0].setPos(Apoint.add(particles[0].getPos(),Apoint.multByScalar(movePerIMass,particles[0].getInverseMass())));
		if(particles[1]!=null){
			particles[1].setPos(Apoint.add(particles[1].getPos(),Apoint.multByScalar(movePerIMass,particles[1].getInverseMass())));
		}

	}
}
