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



## Part II: System
### What type of database would you choose? Why?
In this case, I use mySQL because it is a widely-used relational database management system that is well-suited for handling structured data.
  
Besides, given the requirement for sub-millisecond latency, I would also consider using an extra in-memory database like Redis or Memcached to achieve faster speed in storing and retrieving data.

### Assume your target user audience are all math students in the US. How would you size your database accordingly? What strategies would you consider to prevent overloading a single database instance with requests?
1. To size the database, we could consider the following factors:
    * the expected number of users
    * the amount of data being 
    * the frequency of database access
2. Strategies considered to prevent overloading
    * Database sharidng: We could split a large database into smaller, more manageable parts, with each part being hosted on a separate server. This allows for more efficient database access and reduces the risk of overload on a single instance.
    * load balancer: Load balancer can monitor the performance of each instance and route requests to the most available and efficient instance. This can help ensure that the database can handle a large number of requests without being overwhelmed.
    * cache: We could use cache to store frequently accessed data, to reduce the number of requests made to the database and improve performance.

### Did you set up your database locally or on cloud infrastructure?
I set the mySQL database locally

## Part IV: Conclusion and Futher Todo
Hi, this is Kexuan and I am thrilled to apply for the SDE intern position at Shelly Xu Design. I am happy to participate in the technical exercise part and have done my best to meet the requirements. However, due to time constraints, I was unable to present a detailed javadoc in part 3 and there are many rooms for improvement.Moving forward, I have a few ideas for how to further enhance the project.

1. Using Redis to realize high query speed and prevent overloading
2. Writing front end UI and integated front end and backend

Thank you for taking the time to review my work.






