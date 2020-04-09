/**
* The contact resolution routine for particle contacts. One 
* resolver instance can be shared for the whole simulation. 
*/
public class ParticleContactResolver{
    /*
     * Holds the number of iterations allowed.
     */
    public int iterations;

     /*
      * This is a performance tracking value - we keep a record
      * of the actual number of iterations used.
      */
     public int iterationsUsed;

     /*
      * Creates a new contact resolver.
      */
     public ParticleContactResolver(int iterations){
        this.iterations = iterations;
     }

     /*
      * Sets the number of iterations that can be used.
      */
     public void setIterations(int iterations){
        this.iterations = iterations;
     }


      /*
       * Resolves a set of particle contacts for both penetration
       * and velocity.
       */
      public void resolveContacts(ParticleContact [] contactArray, int numContacts,double dt){
        iterationsUsed = 0;
        while(iterationsUsed < iterations){

            // Find the contact with the largest closing velocity;
            double max = 0;
            int maxIndex = numContacts;

            for (int i = 0; i < numContacts; i++){
                double sepVel = contactArray[i].calculateSeparatingVelocity();
                if (sepVel < max){
                    max = sepVel;
                    maxIndex = i;
                }   
            }   

            // Resolve this contact.
            contactArray[maxIndex].resolve(dt);
            iterationsUsed++;
        }

    }



}