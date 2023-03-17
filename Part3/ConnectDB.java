package Part3;

import Part2Solution.LPObject;
import Part2Solution.ResultPoint;
import Part2Solution.constraint;
import org.junit.After;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ConnectDB {

    public static final String URL = "jdbc:mysql://127.0.0.1:3306/exercise?serverTimezone=UTC";
    public static final String USERNAME = "root";
    public static final String PASSWORD = ""; // Set SQL password here
    private Connection connection;
    public final DecimalFormat decimalFormat = new DecimalFormat("###################.###########");


    @After
    public void over() throws SQLException {
        connection.close();
    }

    /**
     * link database
     *
     * @throws SQLException
     */
    public void initDB() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ResultPoint queryHistory(ArrayList<constraint> constraintArrayList) {
        ArrayList<Integer> constraintDBIdList = constraintArrayList(constraintArrayList);
        if (constraintDBIdList == null || constraintDBIdList.size() < 2) {
            System.out.println("Can not get data from database, now calculating.");
            return null;
        }
        return queryPointRes(constraintDBIdList);
    }


    /**
     * get point according to constraint 
     *
     * @param constraintDBIdList
     * @return
     */
    private ResultPoint queryPointRes(ArrayList<Integer> constraintDBIdList) {

        try {
            String querySql = "select * from result where constraintCompose = ?";
            String constraintCompose = constraintDBIdList.get(0).toString() + ":" + constraintDBIdList.get(1).toString();
            PreparedStatement preparedStatement = connection.prepareStatement(querySql);
            preparedStatement.setString(1, constraintCompose);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                System.out.println("Get result from database successfully.");
                double x = resultSet.getDouble("x");
                double y = resultSet.getDouble("y");
                double op = resultSet.getDouble("op");
                ResultPoint point = new ResultPoint(x, y, op);
                return point;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * check whether LPObject exists
     *
     * @param test1
     * @return
     */
    public boolean queryLPObjectHistory(LPObject test1) {
        Boolean result = false;
        try {
            String queryLPObjectHistorySql = "select * from LPObject where x1Coefficient = " + test1.x1Coefficient + " and x2Coefficient =" + test1.x2Coefficient + " and type = '" + test1.type + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(queryLPObjectHistorySql);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                result = true;
            } else {
                result = false;
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * insert LPObject
     * @param test1
     */
    public void insertLPObjectHistory(LPObject test1) {
        try {
            String insertLPObjectSQl = "insert LPObject (`x1Coefficient` , `x2Coefficient`,`type`) VALUEs(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertLPObjectSQl);
            preparedStatement.setDouble(1, test1.x1Coefficient);
            preparedStatement.setDouble(2, test1.x2Coefficient);
            preparedStatement.setString(3, test1.type);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            System.out.println("LPObject Table added successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * insert Constraint
     * @param constraints
     */
    public void insertConstraint(ArrayList<constraint> constraints) {
        String insertCOnstrain = "insert `constraint` (`x1Coefficient` , `x2Coefficient`,`constantValue`,`type`) VALUES(?,?,?,?)";
        try {
            for (constraint constraint : constraints) {
                PreparedStatement preparedStatement = connection.prepareStatement(insertCOnstrain);
                preparedStatement.setDouble(1, constraint.x1Coefficient);
                preparedStatement.setDouble(2, constraint.x2Coefficient);
                preparedStatement.setDouble(3, constraint.constantValue);
                preparedStatement.setString(4, constraint.type);
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getResultSet();
            }
            System.out.println("constraints Table added successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * insert result
     * @param resultPoint
     * @param constraintIdLIst
     */
    public void insertResult(ResultPoint resultPoint, ArrayList<Integer> constraintIdLIst) {
        try {
            String insertResult = "insert result (`constraintCompose`,`x`,`y`,`op`)VALUES(?,?,?,?)";
            String constraintCompose = constraintIdLIst.get(0).toString() + ":" + constraintIdLIst.get(1).toString();
            PreparedStatement preparedStatement = connection.prepareStatement(insertResult);
            preparedStatement.setString(1, constraintCompose);
            preparedStatement.setDouble(2, resultPoint.x);
            preparedStatement.setDouble(3, resultPoint.y);
            preparedStatement.setDouble(4, resultPoint.op);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * get constraint-id
     * @param constraintArrayList
     * @return
     */
    public ArrayList<Integer> constraintArrayList(ArrayList<constraint> constraintArrayList) {
        ArrayList<Integer> constraintDBIdList = new ArrayList<>();
        try {
            String querySql = "select * from `constraint` where x1Coefficient = ? and x2Coefficient = ? and constantValue = ? and type = ? ";
            for (constraint constraint : constraintArrayList) {
                PreparedStatement preparedStatement = connection.prepareStatement(querySql);
                preparedStatement.setDouble(1, constraint.x1Coefficient);
                preparedStatement.setDouble(2, constraint.x2Coefficient);
                preparedStatement.setDouble(3, constraint.constantValue);
                preparedStatement.setString(4, constraint.type);
                preparedStatement.executeQuery();
                ResultSet resultSet = preparedStatement.getResultSet();
                while (resultSet.next()) {
                    Integer id = resultSet.getInt(1);
                    constraintDBIdList.add(id);
                }
            }
            return constraintDBIdList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

