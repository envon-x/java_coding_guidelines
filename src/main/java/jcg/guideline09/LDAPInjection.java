package jcg.guideline09;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.Hashtable;

// Noncompliant
// String userSN = "S*"; // Invalid
// String userPassword = "*"; // Invalid
public class LDAPInjection {

    private void nonCompliant_searchRecord(String userSN, String userPassword)
        throws NamingException {
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        try {
            DirContext dctx = new InitialDirContext(env);
            SearchControls sc = new SearchControls();
            String[] attributeFilter = { "cn", "mail" };
            sc.setReturningAttributes(attributeFilter);
            sc.setSearchScope(SearchControls.SUBTREE_SCOPE);
            String base = "dc=example,dc=com";
            // The following resolves to (&(sn=S*)(userPassword=*))
            String filter = "(&(sn=" + userSN + ")(userPassword=" + userPassword + "))";
            NamingEnumeration<?> results = dctx.search(base, filter, sc);
            while (results.hasMore()) {
                SearchResult sr = (SearchResult) results.next();
                Attributes attrs = (Attributes) sr.getAttributes();
                Attribute attr = (Attribute) attrs.get("cn");

                System.out.println(attr);
                attr = (Attribute) attrs.get("mail");
                System.out.println(attr);
            }
            dctx.close();
        } catch (NamingException e) {
            // Forward to handler
        }
    }

    private void compliant_searchRecord(String userSN, String userPassword) {
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        try {
            DirContext dctx = new InitialDirContext(env);
            SearchControls sc = new SearchControls();
            String[] attributeFilter = { "cn", "mail" };
            sc.setReturningAttributes(attributeFilter);
            sc.setSearchScope(SearchControls.SUBTREE_SCOPE);
            String base = "dc=example,dc=com";

            if (!userSN.matches("[\\w\\s]*") ||
                !userPassword.matches("[\\w]*")) {
                throw new IllegalArgumentException("Invalid input");
            }
            // BobK:  You know, something still seems wrong here.  I don't recall doing it anything
            // like this.  This SO question is more like the LDAP Autentication code I've written.
            // http://stackoverflow.com/questions/390150/authenticating-against-active-directory-with-java-on-linux
            // Its more like JDBC, no?  You set the strings in directly.  Maybe this example only
            // applies to dynamic searches.
            String filter = "(&(sn = " + userSN + ")(userPassword=" + userPassword + "))";
            NamingEnumeration<?> results = dctx.search(base, filter, sc);
            while (results.hasMore()) {
                SearchResult sr = (SearchResult) results.next();
                Attributes attrs = (Attributes) sr.getAttributes();
                Attribute attr = (Attribute) attrs.get("cn");

                System.out.println(attr);
                attr = (Attribute) attrs.get("mail");
                System.out.println(attr);
            }
            dctx.close();
        } catch (NamingException e) {
            // Forward to handler
        }
    }
}

