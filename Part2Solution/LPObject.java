package Part2Solution;

import java.util.ArrayList;

/**
 * Class representing linear programming object
 */
public class LPObject {
    /**
     * in the object function, the coefficient for x1
     */
    public double x1Coefficient;
    /**
     * in the object function, the coefficient for x2
     */
    public double x2Coefficient;
    /**
     * represents the type of objective function
     * the type should be "MIN" or "MAX"
     */
    public String type;
    /**
     * An ArrayList containing all Constraint instances associated to this Model.
     */
    public ArrayList<constraint> constraints;
    public static final String MIN = "MIN";
    public static final String MAX = "MAX";
    /**
    * * Creates a new Model instance with given parameters.
    * @param xCoefficient double x coefficient for this model's objective function.
    * @param yCoefficient double y coefficient for this model's objective function.
    * @param allowsNegatives boolean for negative values allowance in this Model instance.
    * @param type Objective function type for this Model instance.
    */
	public LPObject(double x1Coefficient, double x2Coefficient, String type) {
		super();
		this.x1Coefficient = x1Coefficient;
		this.x2Coefficient = x2Coefficient;
		this.type = type;
		this.constraints = new ArrayList<constraint>();
	}
    /**
    * Method used to add a new constraint instance to this Model.
    * @param c Constraint to be added.
    */
	public void addConstraint(constraint c) {
		this.constraints.add(c);
	}
    /**
    * Evaluates the objective function value of the given Point instance.
    * @param p Point to be evaluated.
    * @return A double value, the evaluation of the Point in the function of this Model.
    */
	public double evaluateObjetive(Point p) {
		return this.x1Coefficient*p.x + this.x2Coefficient*p.y;
	}

    /**
    * Gives an ArrayList containing all Points that are axis intersections for each Constraint in this model.
    * @return An ArrayList of Points.
    */
    public ArrayList<Point>giveAxisIntersections(){
        ArrayList<Point>points=new ArrayList<>();
        for (int i = 0; i < constraints.size(); i++) {
		points.addAll(constraints.get(i).pointsOfIntersectionWithAxes());
	}
        return points;
    }

    /**
    * Gives an ArrayList containing all Points that are feasible intersection Points for this Model.
    * @return An ArrayList of Points.
    */
	public ArrayList<Point>giveAllFeasiblePoints(){
		ArrayList<Point>points=new ArrayList<Point>();
		points.add(new Point(0,0));
		for (int i = 0; i < constraints.size(); i++) {
			points.addAll(constraints.get(i).pointsOfIntersectionWithAxes());
		}
		
		for (int i = 0; i < constraints.size(); i++) {
			for (int j = i+1; j < constraints.size(); j++) {
				Point p=constraints.get(i).intersectionWith(constraints.get(j));
				if(p!=null) {
					points.add(p);
				}else{
                    System.out.println("Interseccion nula");
                }
			}
		}
		
		// because we have x1 >= 0, x2 >= 0
		for (int i = 0; i < points.size(); i++) {
				if(points.get(i).x<0 || points.get(i).y<0) {
					points.remove(i);
					i--;
				}
		}
		
		for (int i = 0; i < points.size(); i++) {
			boolean feasible=true;
			for (int j = 0; j < constraints.size() && feasible; j++) {
				if(!constraints.get(j).isSatisfiedBy(points.get(i))) {
					feasible=false;
					System.out.println("Discarding point "+points.get(i).toString()+" because of constraint "+constraints.get(j).toString());
					points.remove(i);
					i--;
				}
			}
		}
		System.out.println("Feasible points:");
		for (int i = 0; i < points.size(); i++) {
			System.out.println(points.get(i).toString());
		}
		return points;
	}

    /**
     * Gives a Point representing the optimum Point found within all feasible Points for this Model.
     * @return A Point instance. Can be null.
     */
    public Point giveOptimumPoint() {
        ArrayList<Point>feasiblePoints=this.giveAllFeasiblePoints();
        Point op=null;
        if(this.type.equals(this.MAX)) {
            System.out.println("Maximum:");
            double max=Double.NEGATIVE_INFINITY;
            for (int i = 0; i < feasiblePoints.size(); i++) {
                if(evaluateObjetive(feasiblePoints.get(i))>max) {
                    max=evaluateObjetive(feasiblePoints.get(i));
                    op=feasiblePoints.get(i);
                }
            }
        }else {
            System.out.println("Minimum:");
            double min=Double.POSITIVE_INFINITY;
            for (int i = 0; i < feasiblePoints.size(); i++) {
                if(evaluateObjetive(feasiblePoints.get(i))<min) {
                    min=evaluateObjetive(feasiblePoints.get(i));
                    op=feasiblePoints.get(i);
                }
            }
        }
        return op;
    }

    /**
    * Overriden toString().
    * @return String representation of this Model instance.
    */
	public String toString() {
		String t="";
		String separator="";
		if(this.x2Coefficient>=0) {
			separator="+";
		}
		if(this.type.equals(this.MAX)) {
			t="MAXIMIZE";
		}else {
			t="MINIMIZE";
		}
		String st=t+" "+this.x1Coefficient+"X"+separator+""+this.x2Coefficient+"Y SUBJECT TO:"+"\n";
		st+="X,Y>=0"+"\n";
		for (int i = 0; i < constraints.size(); i++) {
			st+=constraints.get(i).toString()+"\n";
		}
		return st;
	}
}
