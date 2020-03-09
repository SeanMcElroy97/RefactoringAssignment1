# RefactoringAssignment1
> Software Design Patterns. Refactoring Assignment 1


## Refactoring Points

- 3/3/20 (18:00) :	 Added comments to existing methods, to better identify function separations.


- 3/3/20 (21:18) :	Identified repetition of a return method within both admin and customer methods. Extract Method to have one method that returns user to `menuStart()`.

- 4/3/20 (20:00) :	Continue using Extract Method to eliminate duplicate code like the gui functionality.

- 4/3/20 (20:13) :	Removed unnecessary Temp variable like `e1` from `Customer` Plus renamed the customer obj passed in as `e` to `customerObj`. More explanatory name.

- 4/3/20(20:37) :	Moved functionality from `Menu` to `Customer` class. To retrieve account numbers.

- 5/3/20(12:35) :	Extract Method for checking pin/ ATM LOGIN.

- 5/3/20(13:28) :	Moved `isNumeric` Method to `AccountTransaction` as a static method. To Reduce size of menu class.

- 5/3/20(14:30) :	`Method Object` `CustomerCollection`. Finding customer by id. Plus, method checking if customer list is empty.

- 5/3/20(15:40) :	Added methods to Customer class like choose account. Reduce repetitive algorithms in menu class.

- 5/3/20(16:10) :	`Susbstitute algorithm` for displaying all account details for all accounts. Easier to understand plus reduced size of bloated method. 

- 5/3/20(16:30):	Created a method for displaying all account transaction with customer account class.

- 5/3/20(17:00) :	Moved Functionality to iterate through list to the method object. Customer collection.

- 5/3/20(17:37) :	Used `Strategy pattern` when deciding which startmenu option to pick. 

- 6/3/20(17:15):	Extracted ATM login to account transaction .

- 8/3/20(20:00):	Changed the `menuStart` strategy classes to a more generic/abstract `user strategy`. So customer strategy, admin strategy. To reduce bloated methods in menu class. Started working on Strategy Admin class.

- 8/3/20(20:22):	Using the `replace Temp with query` refactoring method to attempt to remove the repetitive temporary obscure variables like `loop` and  `cont`.

- 8/3/20(21:22):	Created a method in Customer to find an account by account id. Extract repetitive for loop code in admin methods etc.

- 8/3/20(22:50):	`Extract Method` into abstract user strategy class for `returnHome()`. Few lines of code. But helps with readability in the other methods. Used a lot for “returns” or “cancels” or “no options” hit.

- 8/3/20(23:15):	Renamed vague “summary” method to more descriptive `transactionSummaryAllCustomers`. 

- 8/3/20(23:50):	Renamed “deleteAccount” Btn in “Admin” to `deleteCustomerAccountBTN` easier to follow. Before could be mistaken for deleting the admin account.

- 9/3/20(00:13):	Moved all admin method functionality from menu admin method to `StrategyAdmin` class.

- 9/3/20(18:33):	Extracted the Method `lodgement` from inside action listener of lodgement button to Customer Strategy class. Removed some temp variables replaced with queries. (To shorten the verbose conditionals).

- 9/3/20(18:44):	Extracted the withdraw money functionality out of menu class to `Customer Strategy` class.

- 9/3/20(21:03):	Used `Singleton pattern` for customer collection as I noticed it was reseting to a new instance each time created a customer. Now it calls `getInstance()` method of the class.
