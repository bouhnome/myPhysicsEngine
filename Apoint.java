
public class Apoint {//Class for 3D points/vectors
    
    public double x;
    public double y;
    public double z;
    //coordinates
    
    public Apoint(){ //default constructor creates 0 vector 
        this(0.0,0.0,0.0);
    }
    
    public Apoint(Apoint r){//constructs an instance from another instance
        this.x = r.x;
        this.y = r.y;
        this.z = r.z;
    }
    
    public Apoint(double x, double y, double z){ //explicit constructor creates vector from 3 doubles
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public static double magnitude(Apoint a){//returns the magnitude of the argument vector
        return Math.sqrt(a.x*a.x+a.y*a.y+a.z*a.z);
    }
    
    public static double magnitudeSquare(Apoint a){//returns the magnitude suqared of the argument vector
        return a.x*a.x+a.y*a.y+a.z*a.z;
    }
    
    public static Apoint add(Apoint a, Apoint b){// returns the sum  of two vectors
        return new Apoint(a.x+b.x,a.y+b.y,a.z+b.z);
    }
    
    public static Apoint multByScalar(Apoint r ,double a){//returns argument vector multiplied by a scalar "a"
        return new Apoint(r.x*a,r.y*a,r.z*a);
    }
    
    public static Apoint opposite(Apoint a){// returns the opposite of the argument vector
        return multByScalar(a,-1);
    }
    
    public static Apoint substract(Apoint a , Apoint b){//substract vector b to vector a 
        return add(a,opposite(b));
    }
    
    public static double distance( Apoint a , Apoint b) {//computes the distance between two points
        return magnitude(substract(a,b)) ;
    }
    
    
    
    public static Apoint normalize(Apoint a){//returns unit vector
        return multByScalar(a,1/magnitude(a));
    }
    
    public static Apoint componentProduct(Apoint a, Apoint b){
        return new Apoint(a.x*b.x,a.y*b.y,a.z*b.z);
    }
    
    public static double dotProduct(Apoint a, Apoint b){
        double s = a.x*b.x+a.y*b.y+a.z*b.z;
        return s;
    }
    
    public static Apoint crossProduct(Apoint a, Apoint b){
        return new Apoint(   a.y*b.z-a.z*b.y   ,   a.z*b.x-a.x*b.z   ,   a.x*b.y-a.y*b.x  );
    }
    
    public String toString() {
        return  "[" +  x  + "," + y  + ","+z+"]";
    }    
    
}
