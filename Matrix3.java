/**
 * Holds an intertia tensor, consisting of a 3x3 row major matrix.
 * This matrix is not padding to produce an aligned structure, since 
 * it is most commonly used with a mass (single real) and two damping coefficients
 * to make the 12-element characteristics array 
 * of a rigid body.
 */
public class Matrix3{
		  public double [] data = new double[9];

		  /**
			* Constructors
			*/

		  public Matrix3(double a0, double a1, double a2,double a3, double a4,double a5,double a6,double 
								a7, double a8){
					 data[0]=a0;
					 data[1]=a1;
					 data[2]=a2;
					 data[3]=a3;
					 data[4]=a4;
					 data[5]=a5;
					 data[6]=a6;
					 data[7]=a7;
					 data[8]=a8;
		  }

		  public Matrix3(){

		  }

		/**
		 * Returns a matrix that is this matrix multiplied by the given * other matrix.
		 */
		  public Matrix3 mult(Matrix3 o){
					return new Matrix3(
										data[0]*o.data[0] + data[1]*o.data[3] + data[2]*o.data[6],
										data[0]*o.data[1] + data[1]*o.data[4] + data[2]*o.data[7],
										data[0]*o.data[2] + data[1]*o.data[5] + data[2]*o.data[8],

										data[3]*o.data[0] + data[4]*o.data[3] + data[5]*o.data[6],
										data[3]*o.data[1] + data[4]*o.data[4] + data[5]*o.data[7],
										data[3]*o.data[2] + data[4]*o.data[5] + data[5]*o.data[8],

										data[6]*o.data[0] + data[7]*o.data[3] + data[8]*o.data[6],
										data[6]*o.data[1] + data[7]*o.data[4] + data[8]*o.data[7],
										data[6]*o.data[2] + data[7]*o.data[5] + data[8]*o.data[8]
										 );

		  }


    /**
     * Sets the matrix to be the inverse of the given matrix.
     * @param m The matrix to invert and use to set this.
     */
		  public void setInverse(Matrix3 m){
		 		 double t4 = m.data[0]*m.data[4];
       		 double t6 = m.data[0]*m.data[5];
       		 double t8 = m.data[1]*m.data[3];
       		 double t10 = m.data[2]*m.data[3];
       		 double t12 = m.data[1]*m.data[6];
       		 double t14 = m.data[2]*m.data[6];


				 // Calculate the determinant.
				 double t16 = (t4*m.data[8] - t6*m.data[7] - t8*m.data[8] +
                    t10*m.data[7] + t12*m.data[5] - t14*m.data[4]);
       		 // Make sure the determinant is non-zero.
       		 if (t16 == 0) return;
       		 double t17 = 1/t16;
       		 data[0] = (m.data[4]*m.data[8]-m.data[5]*m.data[7])*t17;
       		 data[1] = -(m.data[1]*m.data[8]-m.data[2]*m.data[7])*t17;
       		 data[2] = (m.data[1]*m.data[5]-m.data[2]*m.data[4])*t17;
       		 data[3] = -(m.data[3]*m.data[8]-m.data[5]*m.data[6])*t17;
       		 data[4] = (m.data[0]*m.data[8]-t14)*t17;
       		 data[5] = -(t6-t10)*t17;
       		 data[6] = (m.data[3]*m.data[7]-m.data[4]*m.data[6])*t17;
       		 data[7] = -(m.data[0]*m.data[7]-t12)*t17;
       		 data[8] = (t4-t8)*t17;
		  }

	/** Returns a new matrix containing 
	 * the inverse of this matrix.
	 */
			public Matrix3 inverse(){
			    Matrix3 result = new Matrix3();
			    result.setInverse(this);
				 return result;
			}
   /**
    * Inverts the matrix.
    */
			public void invert(){
             setInverse(this);
         }
   /**
     * Sets the matrix to be the transpose of the given matrix.
     *
     * @param m The matrix to transpose and use to set this.
     */
			public void setTranspose(Matrix3 m){
				 data[0] = m.data[0];
			    data[1] = m.data[3];
			    data[2] = m.data[6];
			    data[3] = m.data[1];
			    data[4] = m.data[4];
			    data[5] = m.data[7];
			    data[6] = m.data[2];
			    data[7] = m.data[5];
			    data[8] = m.data[8];
			}

	/** Returns a new matrix
	*containing the transpose of this matrix. 
	*/
		  public Matrix3 transpose(){
				 Matrix3 result=new Matrix3();
				 result.setTranspose(this);
			    return result;
		  }


/**
* Sets this matrix to be the rotation matrix corresponding to
* the given quaternion.
*/
        public void setOrientation(Quaternion q){
             data[0] = 1 - (2*q.j*q.j + 2*q.k*q.k);
             data[1] = 2*q.i*q.j + 2*q.k*q.r;
             data[2] = 2*q.i*q.k - 2*q.j*q.r;
             data[3] = 2*q.i*q.j - 2*q.k*q.r;
             data[4] = 1 - (2*q.i*q.i  + 2*q.k*q.k);
             data[5] = 2*q.j*q.k + 2*q.i*q.r;
             data[6] = 2*q.i*q.k + 2*q.j*q.r;
             data[7] = 2*q.j*q.k - 2*q.i*q.r;
             data[8] = 1 - (2*q.i*q.i  + 2*q.j*q.j);
       }
}
