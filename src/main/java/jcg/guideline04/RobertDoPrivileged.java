package jcg.guideline04;

import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class RobertDoPrivileged {
    public void doPrivilegedThing() {
        final AccessControlContext acc = AccessController.getContext();

        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                if (acc == null) {
                    throw new SecurityException("AccessControlContext cannot be null");
                }
                // do something
                // TODO: BobK:  whats up with return null?
                // This method is wanting to return void, so just return; right?
                return null;
            }
        },acc);
    }

}
