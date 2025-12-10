/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kdafragbet.menu;

import com.mycompany.kdafragbet.domain.User;
import com.mycompany.kdafragbet.AppContext;

public class UserTransactionHelper {

    private final AppContext context;
    private static final String PAYPAL_ACCOUNT = "kdafragbet@paypal.com";
    private static final String GCASH_ACCOUNT = "09887766552";
    private static final String MAYA_ACCOUNT = "09887766552";
    private static final String GOTYME_ACCOUNT = "09887766552";
    private static final String BDO_ACCOUNT_NO = "123456789012";
    private static final String UNIONBANK_ACCOUNT_NO = "098765432109";
    private static final String PNB_ACCOUNT_NO = "210987654321";
    private static final String BPI_ACCOUNT_NO = "453219280761";

    public UserTransactionHelper(AppContext context) {
        this.context = context;
    }
    public String chooseDepositMethod() {
        System.out.println("\n--- Choose Deposit Method ---");
        System.out.println("1. E-Wallet Transaction");
        System.out.println("2. Bank Transaction");
        System.out.print("Choice: ");

        int methodChoice = getIntInput();
        
        return switch (methodChoice) {
            case 1 -> "E-Wallet";
            case 2 -> "Bank";
            default -> {
                System.out.println("❌ Invalid deposit method choice. Returning to menu.");
                yield null;
            }
        };
    }
   
    public String processEWalletDeposit(User user, double amount) {
        System.out.println("\n--- Choose E-Wallet Provider for Deposit ---");
        System.out.println("1. PayPal");
        System.out.println("2. Gcash");
        System.out.println("3. Maya");
        System.out.println("4. GoTyme");
        System.out.println("5. Back");
        System.out.print("Choice: ");

        String accountName;
        String accountNumber;
        String methodType;

        switch (getIntInput()) {
            case 1 -> {
                methodType = "PayPal E-Wallet";
                accountName = "KDAFRAGBET (PayPal)";
                accountNumber = PAYPAL_ACCOUNT;
            }
            case 2 -> {
                methodType = "Gcash E-Wallet";
                accountName = "KDAFRAGBET (Gcash)";
                accountNumber = GCASH_ACCOUNT;
            }
            
            case 3 -> {
                methodType = "MAYA E-Wallet";
                accountName = "KDAFRAGBET (MAYA)";
                accountNumber = MAYA_ACCOUNT;
            }
            
            case 4 -> {
                methodType = "GoTyme E-Wallet";
                accountName = "KDAFRAGBET (GoTyme)";
                accountNumber = GOTYME_ACCOUNT;
            }
            default -> {
                System.out.println("❌ Invalid choice or cancelled.");
                return null;
            }
        }

        displayDepositInstructions(user, accountName, accountNumber, amount);
        return verifyUserId(user) ? methodType : null;
    }

    public String processBankDeposit(User user, double amount) {
        System.out.println("\n--- Choose Bank for Deposit ---");
        System.out.println("1. BDO Unibank/BDO");
        System.out.println("2. Bank of the Philippine Island/BPI");
        System.out.println("3. Philippine National Bank/PNB");
        System.out.println("4. UnionBank");
        System.out.println("5. Back ");
        System.out.print("Choice: ");
        
        String accountName;
        String accountNumber;
        String methodType;

        switch (getIntInput()) {
            case 1 -> {
                methodType = "BDO Unibank";
                accountName = "KDAFRAGBET (BDO)";
                accountNumber = BDO_ACCOUNT_NO;
            }
            case 2 -> {
                methodType = "Bank of the Philippine Island/BPI";
                accountName = "KDAFRAGBET (BPI)";
                accountNumber = BPI_ACCOUNT_NO;
            }
            
            case 3 -> {
                methodType = "Philippine National Bank/PNB";
                accountName = "KDAFRAG(PNB)";
                accountNumber = PNB_ACCOUNT_NO;
            }
            
            case 4 -> {
                methodType = "UnionBank";
                accountName = "KDAFRAG(UnionBank)";
                accountNumber = UNIONBANK_ACCOUNT_NO;
            }
            default -> {
                System.out.println("❌ Invalid choice or cancelled.");
                return null;
            }
        }

        displayDepositInstructions(user, accountName, accountNumber, amount);
        return verifyUserId(user) ? methodType : null;
    }
    
    private void displayDepositInstructions(User user, String accountName, String accountNumber, double amount) {
        System.out.println("\n-------------------------------------------");
        System.out.println("DEPOSIT INSTRUCTIONS (Simulated)");
        System.out.printf("AMOUNT: $%.2f%n", amount);
        System.out.println("Transfer funds to the following account:");
        System.out.println("  Account Name: " + accountName);
        System.out.println("  Account No.: " + accountNumber);
        System.out.println("\nYOUR USER ID: " + user.getUserId());
        System.out.println("IMPORTANT: Use your User ID as the transaction reference/memo.");
        System.out.println("-------------------------------------------");
    }

    private boolean verifyUserId(User user) {
        System.out.println("Confirm transaction by re-entering your 6-digit User ID:");
        System.out.print("User ID: ");
        String inputUserId = context.sc.nextLine().trim();

        if (inputUserId.equals(user.getUserId())) {
            System.out.println("✅ User ID confirmed. Proceeding with deposit.");
            return true;
        } else {
            System.out.println("❌ User ID mismatch. Deposit cancelled.");
            return false;
        }
    }
    
    public String chooseWithdrawalMethod() {
        System.out.println("\n--- Choose Withdrawal Method ---");
        System.out.println("1. E-Wallet Transaction");
        System.out.println("2. Bank Transaction");
        System.out.print("Choice: ");
        
        int methodChoice = getIntInput();

        return switch (methodChoice) {
            case 1 -> handleEWalletWithdrawal(); 
            case 2 -> handleBankWithdrawal();    
            default -> {
                System.out.println("❌ Invalid withdrawal method choice. Returning to menu.");
                yield null;
            }
        };
    }
    
    public String handleEWalletWithdrawal() {
        System.out.println("\n--- Choose E-Wallet Provider for Withdrawal ---");
        System.out.println("1. PayPal");
        System.out.println("2. Gcash");
        System.out.println("3. Maya");
        System.out.println("4. GoTyme");
        System.out.println("5. Back");
        System.out.print("Choice: ");

        String methodType = switch (getIntInput()) {
            case 1 -> "PayPal E-Wallet";
            case 2 -> "Gcash E-Wallet";
            case 3 -> "Maya E-Wallet";
            case 4 -> "GoTyme E-Wallet";
            default -> {
                System.out.println("❌ Invalid choice or cancelled.");
                yield null;
            }
        };
        
        if (methodType != null) {
            System.out.print("Enter your " + methodType + " Account/Email for withdrawal: ");
            context.sc.nextLine(); 
            System.out.println("Simulated account captured successfully.");
        }
        
        return methodType;
    }
    
    public String handleBankWithdrawal() {
        System.out.println("\n--- Choose Bank for Withdrawal ---");
        System.out.println("1. BDO Unibank/BDO");
        System.out.println("2. Bank of the Philippine Island/BPI");
        System.out.println("3. Philippine National Bank/PNB");
        System.out.println("4. UnionBank");
        System.out.println("5. Back ");
        System.out.print("Choice: ");
        
        String methodType = switch (getIntInput()) {
            case 1 -> "BDO Unibank";
            case 2 -> "Bank of the Philippine Island/BPI";
            case 3 -> "Philippine National Bank/PNB";
            case 4 -> "UnionBank";
            default -> {
                System.out.println("❌ Invalid choice or cancelled.");
                yield null;
            }
        };

        if (methodType != null) {
            System.out.print("Enter your " + methodType + " Account Number for withdrawal: ");
            context.sc.nextLine(); 
            System.out.println("Simulated account captured successfully.");
        }
        
        return methodType;
    }


    
    public double chooseAmount() {
        System.out.println("\n--- Choose Amount ---");
        System.out.println("1. $50.00");
        System.out.println("2. $100.00");
        System.out.println("3. $500.00");
        System.out.println("4. $1000.00");
        System.out.println("5. Enter Custom Amount (Min $50.00)"); 
        System.out.println("6. Cancel");
        System.out.print("Choice: ");

        return switch (getIntInput()) {
            case 1 -> 50.0;
            case 2 -> 100.0;
            case 3 -> 500.0;
            case 4 -> 1000.0;
            case 5 -> getCustomAmount();
            case 6 -> {
                System.out.println("Transaction cancelled.");
                yield 0.0;
            }
            default -> {
                System.out.println("❌ Invalid choice or transaction cancelled.");
                yield 0.0;
            }
        };
    }

    private double getCustomAmount() {
        final double MIN_AMOUNT = 50.0;
        while (true) {
            System.out.print("Enter custom amount (Minimum $" + String.format("%.2f", MIN_AMOUNT) + "): $");
            double amount = getDoubleInput();

            if (amount <= 0) {
                System.out.println("❌ Amount must be positive. Transaction cancelled.");
                return 0.0; 
            }

            if (amount < MIN_AMOUNT) {
                System.out.println("❌ Custom amount must be at least $" + String.format("%.2f", MIN_AMOUNT) + ".");
                continue;
            }

            return amount;
        }
    }
    
   

    private int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(context.sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("❌ Enter a valid number: ");
            }
        }
    }

    private double getDoubleInput() {
        while (true) {
            try {
                return Double.parseDouble(context.sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("❌ Enter a valid number: ");
            }
        }
    }
}