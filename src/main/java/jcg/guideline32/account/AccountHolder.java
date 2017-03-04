package jcg.guideline32.account;

import jcg.guideline32.bank.BankApplication;

public class AccountHolder {

  private BankApplication ba;

  public void setBankApplication(BankApplication newBA) {
    ba = newBA;
  }

  public synchronized void depositFunds(BankApplication ba, String username, double amount) {
    // Call through the interface to check if user exists
    if (ba.exists(username)) {
      // Deposit the amount
    }
  }

  public double getBalance(String accountNumber) {
    // Return the account balance
    return 1.0;
  }
}
