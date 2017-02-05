package jcg.guideline09;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LDAPRegexTest {
    class TestCase {
        final String userSN;
        final boolean expectValid;

        TestCase(String userSN, boolean expectValid) {
            this.userSN = userSN;
            this.expectValid = expectValid;
        }
    }

    @Test
    public void testUserSNRegex() throws Exception {
        TestCase testCases[] = { new TestCase("*", false),
            new TestCase("robert", true),
            new TestCase("ro'\"bert", false),
            new TestCase("forwardSlash/and\\backslash",false),
            new TestCase("/forwardSlashand\\backslash",false),
            new TestCase("\\backslashand/forwardSlash",false),
            new TestCase(" robert ", false)};
        for (TestCase testCase : testCases) {
            // I think \w is [a-zA-Z0-9_]
            // I think \s is any whitespace
            boolean actual = testCase.userSN.matches("[\\w\\s]*");
            assertEquals(actual, testCase.expectValid,
                String.format("regex Failed on \"%s\"", testCase.userSN));
        }
    }
}
