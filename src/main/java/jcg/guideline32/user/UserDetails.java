package jcg.guideline32.user;

import jcg.guideline32.account.AccountHolder;
import jcg.guideline32.bank.BankApplication;

public class UserDetails extends AccountHolder implements BankApplication {
  @Override
  public synchronized double getUserBalance(String accountNumber) {
    // Use a method of AccountHolder to get the account balance
    return getBalance(accountNumber);
  }

  @Override
  public boolean exists(String username) {
    // Check whether user exists
    return true;
  }
}
