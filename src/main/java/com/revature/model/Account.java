package com.revature.model;

public class Account {
	
	private long accountNumber;
	private double balance;
	private String accountType;
	
	//implement to deposit, withdraw methods, etc....
	public static double roundToTwoPlaces(double d) {
		
	    return Math.round(d * 100) / 100.0;
	    
	}
	
	public static void Deposit(Account a, double amountDeposit) {
		
		if(amountDeposit>0) {
			
			a.setBalance(a.getBalance() + amountDeposit);
			
		}else {
			
			System.out.println("Invalid amount. Please try again. ");
			
		}
		
	}
	
	public static void Withdraw(Account a, double amountWithdraw) {
		
		if(amountWithdraw>0) {
			
			a.setBalance(a.getBalance() - amountWithdraw);
			
		}else {
			
			System.out.println("Invalid amount. Please try again. ");
			
		}
		
	}
	
	public static void Transfer(Account a, Account b, double amountTransfer) {
		
		if(amountTransfer>0) {
			
			a.setBalance(a.getBalance() - amountTransfer);
			
			if(a.getAccountNumber() != b.getAccountNumber()) {
				if  (amountTransfer <= a.getBalance()) {
					a.setBalance(a.getBalance() - Account.roundToTwoPlaces(amountTransfer));
					b.setBalance(b.getBalance() + Account.roundToTwoPlaces(amountTransfer));
					System.out.println("You've transfered " + Account.roundToTwoPlaces(amountTransfer) + ".");
				} else {
					System.out.println("You don't have enough money.");
				}
			} else {System.out.println("You cannot transfer to yourself.");}
			
		}else {
			
			System.out.println("Invalid amount. Please try again. ");
			
		}
		
	}
	
	public Account() {
		super();
	}
	
	public Account(long accountNumber, double balance, String accountType) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.accountType = accountType;
	}
	
	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (accountNumber ^ (accountNumber >>> 32));
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountNumber != other.accountNumber)
			return false;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", balance=" + balance + ", accountType=" + accountType
				+ "]";
	}
	
}
