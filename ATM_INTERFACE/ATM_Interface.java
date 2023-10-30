// *************************TASK_STATEMENT  **********************************
// 1.Create a class to represent the ATM machine.
// 2. Design the user interface for the ATM, including options such as withdrawing, depositing, and
// checking the balance.
// 3. Implement methods for each option, such as withdraw(amount), deposit(amount), and
// checkBalance().
// 4. Create a class to represent the user's bank account, which stores the account balance.
// 5. Connect the ATM class with the user's bank account class to access and modify the account
// balance.
// 6. Validate user input to ensure it is within acceptable limits (e.g., sufficient balance for withdrawals).
// 7. Display appropriate messages to the user based on their chosen options and the success or failure
// of their transactions

import java.util.Scanner;

class BankAccount {
    String name;
    String username;
    double balance = 10000.0;
    String password;
    int numberOfTransaction = 0;
    String transactionHistory = "";
    String accountNo;
    int maxLoginAttempt = 5;

    // public BankAccount(String name, String username, String accountNo,String
    // password){
    // this.name= name;
    // this.username= username;
    // this.password= password;
    // this.accountNo=accountNo;
    // }
    Scanner sc = new Scanner(System.in);

    // Register
    public void Register() {
        System.out.println("Enter Your Name: ");
        this.name = sc.nextLine();
        System.out.println("Enter Your Username: ");
        this.username = sc.nextLine();
        System.out.println("Enter Your Password: ");
        this.password = sc.nextLine();
        System.out.println("Enter Your Account Number: ");
        this.accountNo = sc.nextLine();
    }

    // Login
    public boolean login() {
        int i = 0;
        boolean isLogin = false;
        while (!isLogin && i < maxLoginAttempt) {
            System.out.println("\nEnter your Username");
            String uname = sc.nextLine();
            if (uname.equals(username)) {
                while (!isLogin) {
                    System.out.println("\nEnter Your Password");
                    String pass = sc.nextLine();
                    if (pass.equals(password)) {
                        System.out.println("\nLogin Successfully..");
                        isLogin = true;
                    } else {
                        System.out.println("\nIncorrect Password");
                    }
                }
            } else {
                System.out.println("\nIncorrect Username");
            }
        }
        if (i == maxLoginAttempt) {
            System.out.println("\nMax Login Limit Reached....Try After Sometime..");
        }
        return isLogin;
    }

    // WithDraw
    public void withDraw() {
        System.out.println("\nEnter Amount You want to Withdraw: ");
        double amt = sc.nextDouble();
        sc.nextLine();
        try {
            if (balance >= amt) {
                balance -= amt;
                System.out.println("\nWithDraw Successfully");
                numberOfTransaction++;
                String str = "Rs " + amt + " Withdrawed\n";
                this.transactionHistory += str;

            } else {
                System.out.println("\nInsufficient Balance..");
            }
        } catch (Exception e) {

        }
    }

    // Deposit
    public void deposit() {
        System.out.println("\nEnter Amount You want to Deposit: ");
        double amt = sc.nextDouble();
        sc.nextLine();
        try {
            if (amt <= 50000) {
                balance += amt;
                System.out.println("\nDeposit Successfully");
                numberOfTransaction++;
                String str = "Rs " + amt + " Deposited\n";
                this.transactionHistory += str;

            } else {
                System.out.println("\nSorry, Maximum Deposit Limit: Rs 50000.00");
            }
        } catch (Exception e) {

        }
    }

    // Transfer
    public void transfer() {
        System.out.print("\nEnter Receipent's Name: ");
        String receipent = sc.nextLine();
        System.out.println("\nEnter Amount You want to Transfer: ");
        double amt = sc.nextDouble();
        try {
            if (balance >= amt) {
                if (amt <= 25000) {
                    numberOfTransaction++;
                    balance -= amt;
                    System.out.println("Rs " + amt + " Successfully Transfered To " + receipent + ".");
                    String str = amt + " Rs transfered to " + receipent + "\n";
                    this.transactionHistory += str;
                } else {
                    System.out.println("\nSorry, Maximum Transfer Limit: Rs 25000.00");

                }
            } else {
                System.out.println("\nInsufficient Balance..");
            }
        } catch (Exception e) {
        }
    }

    // Check Current Balance
    public void checkBalance() {
        System.out.println("\nCurrent balance: Rs " + this.balance);
    }

    // Transaction History
    public void transacHistory() {
        try {
            if (numberOfTransaction == 0) {
                System.out.println("\nNo Transaction Done Yet..");
            } else {
                System.out.println(transactionHistory);
            }
        } catch (Exception e) {
        }
    }

}

class ATM_Interface {

    public static int takeUserInput(int range) {
        boolean flag = false;
        int input = 0;
        while (!flag) {
            try {
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                flag = true;
                if (flag && input > range || input < 1) {
                    System.out.println("\nChoose input between 1 to " + range);
                    flag = false;
                }
            } catch (Exception e) {
                System.out.println("\nEnter only integer value");
                flag = false;
            }
        }
        return input;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n*************WELCOME TO THE ATM***************************");
        System.out.println("Enter: 1. Registration           2. Exit");
        System.out.println("\nEnter Your Choice: ");
        int inputForRegister = takeUserInput(2);

        if (inputForRegister == 1) {
            BankAccount b = new BankAccount();
            b.Register();
            while (true) {
                System.out.println("\n\nEnter: 1. Login        2. Exit");
                System.out.println("Enter Your Choice: ");
                int inputForLogin = takeUserInput(2);
                if (inputForLogin == 1) {
                    if (b.login()) {
                        System.out.println(
                                "                     ***************Welcome Back " + b.name + "*******************");
                        boolean isCompleted = false;
                        while (!isCompleted) {
                            System.out.println(
                                    "\n   *************Enter: 1. WithDraw     2. Deposit      3. Transfer      4. Check Balance       5. Transaction History        6. Exit            ***************\n\n");
                            System.out.println("Enter Your Choice: ");
                            int ch = sc.nextInt();
                            sc.nextLine();
                            switch (ch) {
                                case 1:
                                    b.withDraw();
                                    break;
                                case 2:
                                    b.deposit();
                                    break;
                                case 3:
                                    b.transfer();
                                    break;
                                case 4:
                                    b.checkBalance();
                                    break;
                                case 5:
                                    b.transacHistory();
                                    break;
                                case 6:
                                    System.out.println("\nThank you for using the ATM. Goodbye!");
                                    isCompleted = true;
                                    return;
                                // break;
                                default:
                                    System.out.println("\nInvalid Input...");
                                    // return;
                            }
                        }
                    }
                } else {
                    System.out.println("\nThank you for using the ATM. Goodbye!");
                    System.exit(0);
                }
            }
        }
         else {
            System.out.println("\nThank you for using the ATM. Goodbye!");
            System.exit(0);
        }

    }
};