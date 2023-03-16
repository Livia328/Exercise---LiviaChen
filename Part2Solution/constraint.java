package Part2Solution;

import java.util.*;

/**
 * class representing for constraints in a linear programming problem
 * the constraints would only consists 2 variable x1 and x2
 */
public class constraint {
    /**
     * the id of constraint
     */
    public int id;
    /**
     * the x1 coefficient of the constraint
     */
    public double x1Coefficient;
    /**
     * the x2 coefficient of the constraint
     */
    public double x2Coefficient;
    /**
     * the constant value of the constraint
     */
    public double constantValue;
    /**
     * the type of the constraint
     * it would be LESS_OR_EQUAL, MORE_OR_EQUAL, EQUAL
     * 
     */
    public String type;

    public static final String LESS_OR_EQUAL = "<=";
    public static final String MORE_OR_EQUAL = ">=";
    public static final String EQUAL = "=";
    public static final double DELTA = 0.00001;

    public constraint(int id, double x1Coefficient, double x2Coefficient, double constantValue, String type) {
        this.id = id;
        // if((x1Coefficient==0 && x2Coefficient==0) ) {
		// 	throw new IllegalArgumentException("Inconsistent constraint specifications at constraint "+id+".");
		// }
		
		this.x1Coefficient = x1Coefficient;
		this.x2Coefficient = x2Coefficient;
		this.constantValue = constantValue;
		this.type = type;
	}
    /**
    * Gives the value of evaluating the parameter Point with this constraint coefficients.
     * @param p A Point instance.
     * @return double value, indicating the linear combination of the Point coordinates with this constraint coefficients.
     */
	public double evaluate(Point p) {
        return (p.x * x1Coefficient)+(p.y * x2Coefficient);
    }

    /**
    * 
    * @param x The X coordinate to be evaluated in this Constraint.
    * @return The value of this Constraint in the form of y=f(x).
    * @throws Exception Exception thrown when the y value of the Constraint is 0.
    */
    public double giveYof(double x)throws Exception{
        if(this.x2Coefficient==0){
            throw new Exception("Cannot calculate x2 value of a function with x2 coefficient 0.");
        }else{
            return (this.constantValue-(this.x1Coefficient * x))/this.x2Coefficient;
        }
    }

    /**
    * Indicates if this Constraint instance is satisfied by the Point parameter.
    * @param p Point to be evaluated.
    * @return A boolean value, true if the Point satisfies this Constraint, false otherwise.
    */
    public boolean isSatisfiedBy(Point p) {
        double evaluation=evaluate(p);
        if(this.type.equals(LESS_OR_EQUAL)) {
            return (evaluation <= this.constantValue+DELTA);
        }else if(this.type.equals(MORE_OR_EQUAL)) {
            return (evaluation >= this.constantValue-DELTA);
        }else {
            return (evaluation >= this.constantValue-DELTA && evaluation <= this.constantValue+DELTA);
        }
    }

    /**
    * Returns an ArrayList containing all intersections of this Constraint with the coordinate axes.
    * @return ArrayList of type Point.
    */
    public ArrayList<Point> pointsOfIntersectionWithAxes(){
        ArrayList<Point> cuts = new ArrayList<Point>();
        double c1=this.constantValue / this.x2Coefficient;
         
        if (!Double.isInfinite(c1) && !Double.isNaN(c1)){
            cuts.add(new Point(0,c1));
        }
        double c2=this.constantValue/this.x1Coefficient;
        
        if(!Double.isInfinite(c2) && !Double.isNaN(c2)){
            cuts.add(new Point(c2,0));
        }
  
        return cuts;
    }

    /**
    * Gives the intersection of this Constraint wtih the given parameter Constraint.
    * @param c Another Constraint instance.
    * @return A Point, representing the intersection between both Constraints. Can be null.
    */
    public Point intersectionWith(constraint c) {
        double topPart=(this.constantValue*c.x2Coefficient) - (c.constantValue*this.x2Coefficient);
        double bottomPart=(this.x1Coefficient*c.x2Coefficient)-(c.x1Coefficient*this.x2Coefficient);
        double x=topPart/bottomPart;
        double y;
        if(this.x2Coefficient!=0){
            y=(this.constantValue-(this.x1Coefficient*x))/this.x2Coefficient;
        }else{
            y=(c.constantValue-(c.x1Coefficient*x))/c.x2Coefficient;
        }
        if(!Double.isNaN(x)  && !Double.isNaN(y)) {
            return new Point(x,y);
        }else {
            return null;
        }
    }
}
