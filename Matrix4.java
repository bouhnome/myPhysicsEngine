/**
 * Holds the transform matrix, consisting of a rotation matrix and
 * a position The matrix has 12 elements
 * It is assumed that the 
 * remaining four are (0,0,0,1) producing a homogenous matrix
 */
public class Matrix4{


		  public double [] data = new double[12];


		  /**
			* Constructors
			*/
		  public Matrix4(double a0, double a1, double a2,double a3, double a4,double a5,double a6,double 
								a7, double a8, double a9, double a10,double a11){
					 data[0]=a0;
					 data[1]=a1;
					 data[2]=a2;
					 data[3]=a3;
					 data[4]=a4;
					 data[5]=a5;
					 data[6]=a6;
					 data[7]=a7;
					 data[8]=a8;
					 data[9]=a9;
					 data[10]=a10;
					 data[11]=a11;
		  }

		  public Matrix4(){

		  }
		  /**
			* Transfor the given vector by this matrix.
			*
			* @param vector the vector to transform
			*/
		  public Apoint trans( Apoint vector ){
					 return new Apoint( vector.getX()*data[0]+
										 vector.getY()*data[1]+
										 vector.getZ()*data[2]+data[3],

										 vector.getX()*data[4]+
									 	 vector.getY()*data[5]+
									    vector.getZ()*data[6]+data[7], 
										
										 vector.getX()*data[8]+
									 	 vector.getY()*data[9]+
									    vector.getZ()*data[10]+data[11] 
										 );

		  }

		  /**
			* Returns a matrix that is is this matric multiplied 
			* by the given matrix argument
			*/
		  public Matrix4 mult(Matrix4 o){
					 Matrix4 result = new Matrix4();
					 result.data[0] = (o.data[0]*data[0]) + (o.data[4]*data[1])+
							          	(o.data[8]*data[2]);
					 result.data[4] = (o.data[0]*data[4]) + (o.data[4]*data[5])+
							 			 	(o.data[8]*data[6]);
					 result.data[8] = (o.data[0]*data[8]) + (o.data[4]*data[9])+ 
											(o.data[8]*data[10]);

					 result.data[1] = (o.data[1]*data[0]) + (o.data[5]*data[1])+ 
											(o.data[9]*data[2]);
					 result.data[5] = (o.data[1]*data[4]) + (o.data[5]*data[5])+ 
											(o.data[9]*data[6]);
					 result.data[9] = (o.data[1]*data[8]) + (o.data[5]*data[9])+ 
											(o.data[9]*data[10]);

					 result.data[2] = (o.data[2]*data[0]) + (o.data[6]*data[1])+
							         	(o.data[10]*data[2]);
					 result.data[6] = (o.data[2]*data[4]) + (o.data[6]*data[5])+ 
											(o.data[10]*data[6]);
					 result.data[10] =(o.data[2]*data[8]) + (o.data[6]*data[9])+
											(o.data[10]*data[10]);

					 result.data[3] = (o.data[3]*data[0]) + (o.data[7]*data[1]) +
											(o.data[11]*data[2])+data[3];
					 result.data[7] = (o.data[3]*data[4]) + (o.data[7]*data[5]) +
											(o.data[11]*data[6])+data[7];
					 result.data[11] =(o.data[3]*data[8]) + (o.data[7]*data[9])+
											(o.data[11]*data[10])+data[11];

					 return result;
		  }

			/**
			* Returns the determinant of the matrix.
			*/
	   	public double getDeterminant(){
		 		 return data[8]*data[5]*data[2]+
       		 data[4]*data[9]*data[2]+
       		 data[8]*data[1]*data[6]-
       		 data[0]*data[9]*data[6]-
       		 data[4]*data[1]*data[10]+
       		 data[0]*data[5]*data[10];
			}

			/**
			* Sets the matrix to be the inverse of the given matrix.
			*
			* @param m The matrix to invert and use to set this.
			*/
			public void setInverse(Matrix4 m){
					// Make sure the determinant is non-zero.
					double det = getDeterminant();
					if (det == 0) return;
					det = 1/det;
					data[0] = (-m.data[9]*m.data[6]+m.data[5]*m.data[10])*det;
					data[4] = (m.data[8]*m.data[6]-m.data[4]*m.data[10])*det;
					data[8] = (-m.data[8]*m.data[5]+m.data[4]*m.data[9]* m.data[15])*det;
					data[1] = (m.data[9]*m.data[2]-m.data[1]*m.data[10])*det;
					data[5] = (-m.data[8]*m.data[2]+m.data[0]*m.data[10])*det;
					data[9] = (m.data[8]*m.data[1]-m.data[0]*m.data[9]* m.data[15])*det;
					data[2] = (-m.data[5]*m.data[2]+m.data[1]*m.data[6]* m.data[15])*det;
					data[6] = (+m.data[4]*m.data[2]-m.data[0]*m.data[6]* m.data[15])*det;
					data[10] = (-m.data[4]*m.data[1]+m.data[0]*m.data[5]* m.data[15])*det;
					data[3] = (m.data[9]*m.data[6]*m.data[3]
					-m.data[5]*m.data[10]*m.data[3]
					-m.data[9]*m.data[2]*m.data[7]
					+m.data[1]*m.data[10]*m.data[7]
					+m.data[5]*m.data[2]*m.data[11]
					-m.data[1]*m.data[6]*m.data[11])*det;
					data[7] = (-m.data[8]*m.data[6]*m.data[3]
					+m.data[4]*m.data[10]*m.data[3]
					+m.data[8]*m.data[2]*m.data[7]
					-m.data[0]*m.data[10]*m.data[7]
					-m.data[4]*m.data[2]*m.data[11]
					+m.data[0]*m.data[6]*m.data[11])*det;
					data[11] =(m.data[8]*m.data[5]*m.data[3]
					-m.data[4]*m.data[9]*m.data[3]
					-m.data[8]*m.data[1]*m.data[7]
					+m.data[0]*m.data[9]*m.data[7]
					+m.data[4]*m.data[1]*m.data[11]
					-m.data[0]*m.data[5]*m.data[11])*det;
			}

			/** Returns a new matrix containing the inverse of this matrix. */
			
			public Matrix4 inverse(){
					Matrix4 result= new Matrix4();
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
			* Sets this matrix to be the rotation matrix corresponding to
			* the given quaternion.
			*/
		   public void setOrientationAndPos(Quaternion q, Apoint pos) {
					data[0] = 1 - (2*q.j*q.j + 2*q.k*q.k);
					data[1] = 2*q.i*q.j + 2*q.k*q.r;
					data[2] = 2*q.i*q.k - 2*q.j*q.r;
					data[3] = pos.getX();
					data[4] = 2*q.i*q.j - 2*q.k*q.r;
					data[5] = 1 - (2*q.i*q.i  + 2*q.k*q.k);
					data[6] = 2*q.j*q.k + 2*q.i*q.r;
					data[7] = pos.getY();
					data[8] = 2*q.i*q.k + 2*q.j*q.r;
					data[9] = 2*q.j*q.k - 2*q.i*q.r;
					data[10] = 1 - (2*q.i*q.i  + 2*q.j*q.j);
					data[11] = pos.getZ();
         }
			public Apoint localToWorld(Apoint local, Matrix4 transform) {
    				return transform.trans(local);
			}
			public Apoint worldToLocal( Apoint world, Matrix4 transform) {
   				 Matrix4 inverseTransform =new Matrix4();
   				 inverseTransform.setInverse(transform);
   				 return inverseTransform.trans(world);
         }

			/**
			* Transform the given vector by the transformational inverse 
			* of this matrix.
			*/
			public Apoint transformInverse(Apoint vector){
					Apoint tmp = new Apoint(Apoint.substract(vector,new Apoint(data[3],data[7],data[11])));
					return new Apoint(
					tmp.getX() * data[0] +
					tmp.getY() * data[4] +
					tmp.getZ() * data[8],
					tmp.getX() * data[1] +
					tmp.getY() * data[5] +
					tmp.getZ() * data[9],
					tmp.getX() * data[2] +
					tmp.getY() * data[6] +
					tmp.getZ() * data[10]
					);
			}

			 /**
          * Transform the given direction vector by this matrix.
          */
			public Apoint transformDirection(Apoint vector){
					return new Apoint(
					vector.getX() * data[0] +
					vector.getY() * data[1] +
					vector.getZ() * data[2],
					vector.getX() * data[4] +
					vector.getY() * data[5] +
					vector.getZ() * data[6],
					vector.getX() * data[8] +
					vector.getY() * data[9] +
					vector.getZ() * data[10]
					); 
			}

			/**
			* Transform the given direction vector by the
			* transformational inverse of this matrix.
			*/
			public Apoint transformInverseDirection(Apoint vector){
					return new Apoint(
					vector.getX() * data[0] +
					vector.getY() * data[4] +
					vector.getZ() * data[8],
					vector.getX() * data[1] +
					vector.getY() * data[5] +
					vector.getZ() * data[9],
					vector.getX() * data[2] +
					vector.getY() * data[6] +
					vector.getZ() * data[10]
					);
			}

			public Apoint localToWorldDirn(Apoint local, Matrix4 transform) {
					return transform.transformDirection(local);
			}

			public Apoint worldToLocalDirn(Apoint world,Matrix4 transform) {
					return transform.transformInverseDirection(world);
			}
}
