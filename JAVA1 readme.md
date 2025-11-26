# Banking Application - Java Assignment 01

## Project Description

This is a simple console-based Banking Application implemented in Java that allows users to manage bank accounts. Users can create accounts, deposit money, withdraw money, view account details, and update contact information through a menu-driven interface.

## Features

- Create a new bank account with account holder name, initial deposit, email, and phone number.
- Deposit money into an existing account.
- Withdraw money from an existing account.
- View account details by account number.
- Update email and phone number for an existing account.
- Input validation for numeric fields, email format, phone format, and non-empty input.
- Manages up to 100 accounts using an internal array.

## Usage

- Run the `BankingApp` main class.
- Follow the interactive menu and enter choices to perform various banking operations.
- The application will prompt for necessary details and perform validations.
- Exit the application by selecting the Exit option from the menu.

## Classes

### Account

Represents a bank account with the following attributes:
- Account number (unique)
- Account holder name
- Current balance
- Email address
- Phone number

Provides methods for depositing, withdrawing, updating contact details, and displaying account information.

### UserInterface

Provides the user interaction layer including:
- Menu display and input
- Account management in an internal array
- Input validation methods for integers, doubles, email, and phone numbers
- Methods implementing the different banking operations (create account, deposit, withdraw, etc.)

## Input Validation

- Ensures numeric inputs are valid integers or doubles.
- Deposit and withdrawal amounts must be positive.
- Email addresses must follow standard email format.
- Phone numbers must be 10 to 15 digits without spaces or symbols.
- Non-empty input is enforced where required.

## How to Run

Compile and run the `BankingApp` Java class. The console will display the menu-driven interface for managing accounts.

```bash
javac BankingApp.java
java BankingApp
