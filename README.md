# DoesShiftBelongToGroupHeirarchy
  This solution loads data from database and it takes one time cost of loading data
  and then we can query our results fastly in a time complexity O(no of nodes ine the heirarchy) and it consumes space complexity O(no of nodes in the heirarchy)
  
  Roughly,it will take 100 milliSeconds to 1 second for querying heirarchy relation between two records whose depth is of about 1 million nodes.
# Table realtionship Explanation
  We have three tables here 
  - Shift 
  -  ShiftGrouping
  -  ShiftGroup
   
   A foreign key is a column or set of columns in a relational dataBase that relates two tables and is present in  schema of both Tables(say table A and table B).
       

  - one To Many relationShip : one Row in table A is related to Zero or many rows in table B but one row in Table B  is related to only row one row In Table A.
  - Many to Many relationShip : if there is one to many relationShip in both directions (table A to table B and table B to table A).
  - one to one relationship : if there is only one row in table A which is related to  only one row in table B and vice versa.
 
  Generally what happens when we use to show pictorially tables related to each other,
  we use common column label name in both table which automatically solves the meaning of foreign key but in cases when self relations present in table like in this case where a foriegn key is present in the table itself.we have to explicityly write as how rows are realted with two column label names , in this case for example (child_code : parent code)


  -   shifGrouping and shiftGroup has many to one relationship over the refernce key
     {parent_code} , {child_code} in shiftGrouping table and the referenced key {code} in shiftGroup table
 -   shiftGrouping has a self relation (one to many eg..  one parent can have many children )where parnet_code is referencing to the child_code present in some another row of this table 
 -   shift is the lowest child node and simplest unit that can form a shiftGroup
 
so overall if we look at the tables it represents a TREE Strcuture where one or more Shift Groups can cummulatively and recursively forms a parent shift group 

and in this problem we need to tell whether two shifGroups or one shif and one shiftGroup are from same heirarchy or different heirarchy.

Also, the tables could have been provided with more information then the problem would have been more better understood and what i think, is that, this is also a part of assessment where i have to think what these table would have been meaning and how these are related with this limited amount of information.


# Assumptions

- The number of records in DB is less than 1o Million and the shiftGroupingTable is not updated since the start of the application as if we would have no of records in huge capacity (than mentioned )and is also getting updated (frequently or less frequently ),then this solution will not work and inconsistent results will be obtained.

### Tech
-  mysql is used as a DBMS.
-  Java is used as a programing language.
-  Java Database Connectivity (JDBC) is used to access dataBase and Fetch Records From DB
### Installation

Choose any Windows Operating System 
Go to the src\Configuration file and Change these property key pairs in the file to the correct paramters, accroding to the database on which one would like to run Query.
otherWise during Excecution of Program 
either ClassNotFoundException is Displayed or SQl Exception will be Displayed

make sure that java is installed in the system and set in the environment variable {path}
for this go to cmd and type 
         java -version 
if some info like { java version {some number} } is displayed which means java is installed otherwise we need to install java

now go to the root of the src forlder and type and hit following comands
        cd .\src\BootStrapperPackage
        javac BootStrapperClass.java
        java BootStrapperClass

and then the program will ask to enter two (non null ) Strings from table separated by space at console

      {shiftCode}   { space }  {shitgroupingCode}
      
Finally a boolean value will print at the screen 
True : from the same hierarchy
False : from Diffferent heirarchy

### Todos
-  Develop Program for the scenario where shiftgrouping table records are in large quantity and loading all data in the primary memory is not possible.
