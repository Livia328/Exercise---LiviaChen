# Exercise---Livia
## Part I: Math
### Question:
>Consider the following problem:
>
>             (a) Min Z = –3X1 + X2
>
>Subject to the following:
>
>             (b) X1 + X2 ≤ 5
>
>             (c) 2X1 + X2 ≤ 8  
>
>             (d) X1 ≥ 0, X2 ≥ 0 
>
>What is the optimal solution?
### Solution:
In this problem, I will use graphical method to achieve. 
  
First, we could plotting the region satisfies all the constraints, shown as quadrilateral ABCD.

![This is an image](/asserts/partI.pic.jpg)
  
We could change equation (a) to this format:
  
  
             (a) Z = -3X1 + X2 
           
          -> (e) X2 = 3X1 + Z
  
The coefficient of this equation is 3 and the intersection of this function(e) and X2-axis is the value of Z.
  
Moving fuction(e) inside the the constrained area, when X1 = 4, X2 = 0, Z reaches it's MIN value, which is -12.
  
Therefore, the optimal solution is :
  
X1 = 4,  X2 = 0, Z = -12

## Part II: Programming

In this problem, we only have two variables, X1 and X2, so we can solve the linear problem by calculating all feasible points and finding the minimum or maximum.

More specifically, the feasible points are:

1. The intersection of constraints and axes.
2. The intersection of two constraints.

Therefore, the steps are:

1. Generate the objective function with given coefficients and type. For example, `MAX 3X1 + 4X2` has a type of `MAX`, an `x1coefficient` of `3`, and an `x2coefficient` of `4`.
2. Add constraints. For example, `15X1 + 10X2 <= 300` and `2.5X1 + 5X2 <= 110`.
3. Find all insertion points of constraint and axes, constraint and constraint. For example, in this question, we will have the points:
    * (0, 0)
    * (0, 30)
    * (20, 0)
    * (0, 22)
    * (44, 0)
    * (8, 18)

4. Delete points obtained from step 3 that do not meet all constraints. For example, in this question, we will discard (0, 30) and (44, 0) because (0, 30) does not meet the constraint `2.5X1 + 5X2 <= 110`, and (44, 0) does not meet the constraint `15X1 + 10X2 <= 300`.
5. Get the optimum point from the feasible points.

**Note:** Please make sure to use proper markdown syntax for headings, lists, and text formatting.






