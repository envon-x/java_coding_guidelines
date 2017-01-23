package jcg.guideline01;

import java.io.Console;
import java.util.Arrays;

public class Password {
    public static void nonCompliant() {
        Console c = System.console();
        if (c == null) {
            System.err.println("No console.");
            System.exit(1);
        }
        String username = c.readLine("Enter your user name: ");
        String password = c.readLine("Enter your password: ");
        if (!nonCompliantVerify(username, password)) {
            throw new SecurityException("Invalid Credentials");
        }
    }

    public static boolean nonCompliantVerify(String username, String password) {
        return true;
    }

    public static void compliant() {
        Console c = System.console();
        if (c == null) {
            System.err.println("No console.");
            System.exit(1);
        }
        String username = c.readLine("Enter your user name: ");
        /*
        FIXME: BobK: But doesn't this char[] array now stick around until it gets garbage collected?
        Have we robbed Peter to pay Paul?
         */
        char[] password = c.readPassword("Enter your password: ");

        if (!compliantVerify(username,password)) {
            throw new SecurityException("Invalid Credentials");
        }

        // FIXME:  BobK:  Shouldn't we do this BEFORE the throws?  That makes a mess of this code.
        // Clear the password
        Arrays.fill(password, ' ');
    }

    public static boolean compliantVerify(String username, char[] password) {
        return true;
    }

    public static void main(String[] args) {
        compliant();
        nonCompliant();
        System.exit(0);
    }
}
