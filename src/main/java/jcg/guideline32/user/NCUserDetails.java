package jcg.guideline32.user;

import jcg.guideline32.account.NCAccountHolder;

public class NCUserDetails extends NCAccountHolder {
  public synchronized double getUserBalance(String accountNumber) {
    // Use a method of AccountHolder to get the account balance
    return getBalance(accountNumber);
  }
}
