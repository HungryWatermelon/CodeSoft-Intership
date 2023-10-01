import java.util.Scanner;

public class AtmInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount bankAccount = new BankAccount();

        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Check Balance
                    double balance = bankAccount.checkBalance();
                    System.out.println("Current Balance: $" + balance);
                    break;
                case 2:
                    // Deposit
                    System.out.print("Enter the deposit amount: $");
                    double depositAmount = scanner.nextDouble();
                    if (depositAmount < 0) {
                        System.out.println("Invalid deposit amount. Please enter a positive amount.");
                    } else {
                        bankAccount.deposit(depositAmount);
                        System.out.println("Deposit successful.");
                    }
                    break;
                case 3:
                    // Withdraw
                    System.out.print("Enter the withdrawal amount: $");
                    double withdrawAmount = scanner.nextDouble();
                    if (withdrawAmount < 0) {
                        System.out.println("Invalid withdrawal amount. Please enter a positive amount.");
                    } else if (withdrawAmount > bankAccount.checkBalance()) {
                        System.out.println("Insufficient balance. Cannot withdraw.");
                    } else {
                        bankAccount.withdraw(withdrawAmount);
                        System.out.println("Withdrawal successful.");
                    }
                    break;
                case 4:
                    // Exit
                    System.out.println("Exiting ATM. Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}

class BankAccount {
    private double balance;

    public BankAccount() {
        // Initialize with an initial balance, e.g., $1000
        balance = 1000.0;
    }

    public double checkBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }
}


