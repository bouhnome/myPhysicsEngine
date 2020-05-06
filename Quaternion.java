/**
 * Holds a three degree of freedom orientation.
*/
public class Quaternion{

		/**
		* Holds the real component of the quaternion.
		*/
		public double r;
		/**
		* Holds the first complex component of the quaternion.
		*/
		public double i;
		/**
		* Holds the second complex component of the quaternion.
		*/
		public double j;
		
		/**
		 * Holds the third complex component of the quaternion.
		 */
		double k;
		/**
       * Holds the quaternion data in array form.
       */
      public double [] data = new double[4];

		/**
		 * Constructors
		 */
		public Quaternion(double r, double i, double j, double k){
				  this.r=r;
				  this.i=i;
				  this.j=j;
				  this.k=k;

				  data[0]=r;
				  data[1]=i;
				  data[2]=j;
				  data[3]=k;
		}
		public Quaternion(double [] data){
				  this.data[0]=data[0];
				  this.data[1]=data[1];
				  this.data[2]=data[2];
				  this.data[3]=data[3];

				  this.r=data[0];
				  this.i=data[1];
				  this.j=data[2];
				  this.k=data[3];
		}
		/**
		 * Normalizes the quaternion to unit length, making it a valid * orientation quaternion.
       */
		public void normalize(){
				double d = r*r+i*i+j*j+k*k;
				// Check for zero length quaternion, and use the no-rotation
				// quaternion in that case.
				if (d == 0){
					r = 1;
					return;
				}
				d = 1/Math.sqrt(d); 
				r *= d;
				i *= d;
				j *= d;
				k *= d;
		}

		/**
     * Multiplies the quaternion by the given quaternion.
     *
     * @param multiplier The quaternion by which to multiply.
     */
		public void mult (Quaternion multiplier){
				Quaternion q = this;
				r = q.r*multiplier.r - q.i*multiplier.i -
				q.j*multiplier.j - q.k*multiplier.k;
				i = q.r*multiplier.i + q.i*multiplier.r +
				q.j*multiplier.k - q.k*multiplier.j;
				j = q.r*multiplier.j + q.j*multiplier.r +
				q.k*multiplier.i - q.i*multiplier.k;
				k = q.r*multiplier.k + q.k*multiplier.r +
				q.i*multiplier.j - q.j*multiplier.i;
		}

		public void rotateByVector(Apoint vector, double scale){
				Quaternion q = new Quaternion( 0, vector.getX()*scale, vector.getY()*scale, vector.getZ()*scale);
				this.mult(q);
		}

		/**
		* Adds the given vector to this, scaled by the given amount.
		* This is used to update the orientation quaternion by a rotation * and time.
		*
		* @param vector The vector to add.
		*
		* @param scale The amount of the vector to add.
		*/
		public void addScaledVector(Apoint vector, double scale) {
				Quaternion q = new Quaternion(0,vector.getX() * scale,vector.getY() * scale,vector.getZ() * scale);
				q.mult(this);
				r += q.r *0.5;
				i += q.i *0.5;
				j += q.j *0.5;
				k += q.k *0.5;
		}
}
