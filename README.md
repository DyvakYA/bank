![contributions welcome](https://img.shields.io/badge/contributions-welcome-brightgreen.svg?style=flat)
![Travis](https://travis-ci.org/DyvakYA/honest.bank.png?branch=master) 
![Code Climate coverage](https://img.shields.io/codeclimate/coverage/DyvakYA/honest.bank.svg)
![Code Climate maintainability](https://img.shields.io/codeclimate/maintainability/DyvakYA/honest.bank.svg)
![GitHub](https://img.shields.io/github/license/mashape/apistatus.svg) 
![Completeness](https://img.shields.io/badge/completeness-70%25-brightgreen.svg)


# Honest bank
Online banking

## Introduction

 ## Getting started
 You can you it in your own projects. You can write your own user interface with using my back end. 
 Not matter what will you use Angular JS, or JQuery, it`s free.  
 
**What you’ll need**
 
 * A favorite text editor or IDE
 * JDK 1.8 or later
 * PostgreSQL
 * Spring Boot 1.5.1.RELEASE
 * Maven
 
  Method	| Path	| Description	| User authenticated | Available from UI
  --- | --- | --- |:---:|:---:|
  POST	| /login	| User authentication	|   | ×
  POST	| /registration	| User registration	|   | ×
  
  ##### User API instruction
   Method	| Path	| Description	| User authenticated | Available from UI
  --- | --- | --- |:---:|:---:|
  GET	| /users	| Get all users data	| × | ×
  GET	| /users/{id}	| Get specified user data	| × | ×
  POST	| /users	| Create new user data	| × | 	×
  PUT	| /users{id}	| Update specified user data	| × | ×
  DELETE	| /users/{id[]}	| Delete specified users data	| × | ×
  GET | /users/search/{phone} | Search user by phone number | × | ×
  
  ##### Accounts API instruction 
   Method	| Path	| Description	| User authenticated | Available from UI
   --- | --- | --- |:---:|:---:|
   GET	| /accounts	| Get all accounts data	| × | 
   GET	| /accounts/{id}	| Get account data with specified  id	| × | 
   POST	| /accounts	| Create new account data	| × | 	
   PUT	| /accounts	| Update specified account data	| × | 
   DELETE | /accounts/{id[]}	| Delete specified account data	| × |
   POST | /accounts/user/{id} | Create account for concrete user | × | 
   PUT | /accounts/update | Update account amount | × |
   GET | /accounts/search/{pattern} | Search account by number pattern | × | 
   GET | /accounts/user/{id} | Find account by user id | × |  
   
   ##### Wallet API instruction
   Method	| Path	| Description	| User authenticated | Available from UI
     --- | --- | --- |:---:|:---:|
   GET	| /wallets	| Get all wallets data	| × | ×
   GET	| /wallets/{id}	| Get specified wallets data	| × | ×
   POST	| /wallets	| Create new wallets data	| × | 	×
   PUT	| /wallets{id}	| Update specified wallets data	| × | ×
   DELETE	| /wallets/{id[]}	| Delete specified wallets data	| × | ×
   POST | /wallets/account/{id} | Create wallet for concrete account | × | ×
   GET | /wallets/account/{id}| Find wallet by account id | × | ×
   GET | /wallets/search/{account_number}| Find wallet by account number | × | ×
  
   ## Warning
  
  Complexity and patterns should only be introduced when they are needed for practical
  extensibility.
  The number of bugs may increase and the code will cease to be supported.
  The owner of this repository is not responsible for any consequences.
  
  ## License
  
  This project is licensed under the terms of the MIT license.




