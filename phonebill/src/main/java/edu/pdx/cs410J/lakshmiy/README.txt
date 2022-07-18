This is a README file!
Readme.txt

This Project-3 of CS510P class, it is a phone bill application where it stores phone log for each customer. This project comprises of tests on user arguments. Main goal of the project is to validate user arguments before executing the application, It has maximum test coverage to avoid application failures. It has an option “-readme” as argument which prints this file and an option “-print” prints all the user arguments.

To execute this jar, following arguments are needed-
“Customer name” - Valid String with name of the customer
“callee number” - Valid phone number with 10 digits not starting with “0”.
“caller number” - Valid phone number with 10 digits not starting with “0”.
“begin date” - Date part of the Time at which phone call began, it must be in “mm/dd/yyyy” format
“begin time” - Time in “hh-mm” format at which phone call began
“end date” - Date part of the Time at which phone call ended, it must be in “mm/dd/yyyy” format
“end time” - Time in “hh-mm” format at which phone call ended

Optional arguments-
“-readme” – Prints this file
“-print” – prints all the user arguments.
"-text file" - it stores previous call log and adds new calls of customer
"-pretty file" - prints pretty description of all phone calls of a customer

This application accepts the above user arguments in the given order, It will throw exceptions if the order is not followed. Optional arguments must precede user arguments.
It tests for valid date and time passed, and sorts phone calls based on dates and stores information in text file
