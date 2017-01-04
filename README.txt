CS542 Design Patterns
Fall 2016
PROJECT 5 README FILE

Due Date: <Thursday, December 15, 2016>
Submission Date: <Friday, December 16, 2016>
Grace Period Used This Project: 1 Days
Grace Period Remaining: 0 Days
Author(s): Sunil Kumar Sahu 
e-mail(s): ssahu1@binghamton.edu


PURPOSE:

This java program is to understand Java Reflection and Object Comparison in Java.
  
 
JUSTIFICATION TO DATA STRUCTURE:

The data structure used in this project for storing object information is hash map.
Its fast and the complexity is O(1) provided no collision happens.
This also over rides equals and hashcode which helps in detecting unique objects.


PERCENT COMPLETE:

To the best of my knowledge my current implementation handles all cases. So I
can say its 100% complete.

PARTS THAT ARE NOT COMPLETE:
None

BUGS:

No known bugs exists.

FILES:
The following files listed below are :

/src/genericDeser/driver/Driver.java                    :   main driver code
/src/genericDeser/fileOperations/fileOperations.java    :   gerenic file processor
/src/genericDeser/logger/Logger.java                    :   implements the logger class 
/src/genericDeser/util/First.java                       :   defines the First class of objects
/src/genericDeser/util/Second.java                      :   defines the Second class of objects
/src/genericDeser/util/PopulateObjects.java             :   Defines reflect methods to populate data structure
/src/build.xml                                          :   compiles and runs the project.

And Input.txt will be required as an input at the run time. 
File not present in the attached tar file.

SAMPLE OUTPUT:

  [java] Logger: Number of unique First objects: 4952
  [java] Logger: Total Number of First objects: 4952
  [java] Logger: Number of unique Second objects: 5018
  [java] Logger: Total Number of Second objects: 5048


TO COMPILE:
Go to project folder:
cd sunil_sahu_assign5/genericDeser

ant compile_all

TO RUN:
ant run -Darg0=input-1.txt -Darg1=0 


Note : Input-1.txt file  not provided in tar.gz

EXTRA CREDIT:

N/A


BIBLIOGRAPHY:

This serves as evidence that we are in no way intending Academic Dishonesty.

ACKNOWLEDGEMENT:

None


