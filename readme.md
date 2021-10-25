<h1 align="center">
<br>
  Insurance Tracking Application
  <br>
</h1>

## Table of Contents ##
1. [Application](#Application)
2. [Database Schema](#Database-Schema)
3. [Technology](#Technology)
4. [Application Structure](#Application-Structure)
5. [Run Locally](#Running-the-server-locally)
6. [Contributor](#Contributor)
7. [License](#License)

## Application ##
This application focuses on the main use case for generating the actual and expected metrics on basis of:
1. Contract Create Event
2. Contract Delete Event
3. Price Increased Event
4. Price Decreased Event

## Database Schema ##
In-memory database - 
   1. HashMap to keep the track of Active contracts.
   2. List of report to store the monthly report


## Technology ##
Following libraries were used during the development of this application :

- **Java** - 
- Have implemented factory design pattern to prepare the eventList.
- Have used Strategy design pattern to generate CreateDeleteReport and AllEventReport

## Application Structure ##

**_Main_**
Class with name InsuranceMain is the entry point.

**_model_**
Holds different type of bean used in the application

**_report_**
Hold classes required for report generation.

**_factory_**
Has Event factory class to generate event of different type on different input.

**_utils_**
Has class/es that hold the common function used in the application.

## Running the server locally ##
Clone the project from git, open it in your favorite IDE and run as Java Application from InsuranceMain.java
    
## Contributors ##
Deepak Bisht 

## License ##
None