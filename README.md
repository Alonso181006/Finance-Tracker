# Finance Tracker

## About
I decided to create a **Finance Tracker** as a first-year student who has no idea how to manage my money. I have a lot of student loans, scholarship money, and monthly costs, and I have no idea if I am mismanaging my funds. The project would be used by people in similar situations trying to visualize their wealth as numbers on 10 different screens are hard to interpret and a hassle to check constantly. The user can create an account, which I can save along with their data so it can be pulled whenever the user decides to reopen the application. The tracker will help students, including me, to effectively and mindfully organize our debts to alleviate future stresses.

The finance tracker specifically will start off by collecting your data about sources of income, student loans, and savings and storing it in your account. From there, you will be able to ask what your net worth is to keep track of how rich or in debt you are. However, you will be able to create monthly budgets to prevent overspending and sensibly spend your hard-earned money. A quick feature that I might include is that if you go under your budget, you can either set the extra funds to go into savings or into next month's funds. Lastly, it will provide a fun tool that showcases the amount of money that could be made from creating a savings account or investing in index funds, promoting safe investments.
  

## User Stories

- As a user, I want to be able to ***add and remove*** Assets and Liabilities into a Balance Sheet
- As a user, I want to be able to ***view a list*** of Assets or Liabilities from my Balance Sheet
- As a user, I want to be able to see how my assets and debts could compound over 10, 15, 20, or 30 years
- As a user, I want to be able to edit the value of my assets and debts in case I got extra cash to invest or pay off loans
- As a user, I want to see if my assets outweight my liabilities
- As a user, I want to save my Finance tracker under a username (if I so choose), when I select the quit option from the application menu
- As a user, I want to be able to load up my Finance Tracker based on my username from file(if I so choose)


### Extra
- As a user, I want to be able create a Monthly budget by adding categories such a Food, Transportation with an alloted  amount of money I can spend for each class.
- As a user I want to be able to track monthly recurring memberships with date of renewnal.

## Instructions for End User
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by:
     1. Logging or signing in, so you can add Finances(X) to the your balance sheet(Y)
     2. Pressing the Asset or Liabilities Buttons in the menuPanel
     3. Then pressing the Add Asset/Liability in the respective panels and following the instructions of providing a name and value
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by once in the menu Panel choosing either the Asset or Liability Buttons to display a subset of the Xs in Y.
- You can generate the third  action related to the user story "adding multiple Xs to a Y" by:
    1. Once in the menu Panel choosing either the Asset or Liability Buttons
    2. Then pressing the remove Liability or Asset based on the panel to be able to remove specific X's from Y based on the name of the Finance.
- You can locate my visual component by either logging in or signing in to enter the menu Panel that displays a image based on your wealth
- You can save the state of my application by pressing the save button in the menu Panel either after logging into the application or by pressing the back buttons in the Asset or Liabilities Panels
- You can reload the state of my application by signing in using the username you previously logged in and save as before quitting.

### Phase 4: Task 2
Sat Mar 29 00:44:53 PDT 2025  
Finance Item added to user's finances list.  
Sat Mar 29 00:45:03 PDT 2025  
Finance Item added to user's finances list.  
Sat Mar 29 00:45:51 PDT 2025  
Finance Item added to user's finances list.

### Phase 4: Task 3
I have two significant improvements for refactoring the design of my project. First, I would create an abstract class that the AssetPanel and LiabiliesPanel would extend because they have similar functions. This change would reduce coupling between the two classes and reduce redundant code. Moreover, I use many components that are initialized the same way within each class. To improve cohesion in all the panel classes and reduce coupling, I would create button and textfield classes. These two major changes within the design of my code would improve my project and make it easier to revise in the future.
![Uml Diagram](/images/UML_Design_Diagram.jpeg)
