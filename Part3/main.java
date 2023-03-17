package Part3;

import Part2Solution.LPObject;
import Part2Solution.Point;
import Part2Solution.ResultPoint;
import Part2Solution.constraint;

import java.sql.SQLException;
import java.util.ArrayList;

public class main {


    /**
     * Set calculated object parameters
     * @return
     */
    private static LPObject LPParam() {
        LPObject test1 = new LPObject(3, 4, "MAX");
        constraint c1 = new constraint(1, 15, 10, 300, "<=");
        constraint c2 = new constraint(2, 2.5, 5, 110, "<=");
        test1.addConstraint(c1);
        test1.addConstraint(c2);
        return test1;
    }

    public static void main(String[] args) throws SQLException {
        ConnectDB connectDB = new ConnectDB();
        connectDB.initDB();
        ResultPoint res;
        LPObject test1 = LPParam();
        //1. Query whether the LPObject record exists
        if (connectDB.queryLPObjectHistory(test1)) {
            //2. Query whether the result of the two constraint records exist. If yes, return directly
            res = connectDB.queryHistory(test1.constraints);
            if (res == null) {
                res = extracted(connectDB, test1);
            }
        } else {
            res = extracted(connectDB, test1);
        }
        System.out.println("x1 = " + res.x);
        System.out.println("x2 = " + res.y);
        System.out.println("x2 = " + res.op);
    }


    private static ResultPoint extracted(ConnectDB connectDB, LPObject test1) {
        System.out.println("---------------optimal solution----------------");
        Point res = test1.giveOptimumPoint();
        double op = test1.x1Coefficient * res.x + test1.x2Coefficient * res.y;
        ResultPoint resultPoint = new ResultPoint(res.x, res.y, op);

        connectDB.insertLPObjectHistory(test1);
        connectDB.insertConstraint(test1.constraints);
        ArrayList<Integer> constraintIdLIst = connectDB.constraintArrayList(test1.constraints);
        connectDB.insertResult(resultPoint, constraintIdLIst);
        System.out.println("---------------finish solution----------------");
        return resultPoint;
    }
}
