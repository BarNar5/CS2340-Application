package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for User's equalUsername() method.
 * @author Bartosz Narkiewicz
 */
public class UserEqualUsernameTests {

    private static final long TIMEOUT = 200;
    private User user1;
    private User user2;


    @Before
    public void setup() {
        user1 = new User("username", "password", AccountType.ADMIN);
        user1.setName("Admin");
        user1.setGender(Gender.Male);
        user1.setDateDay("01");
        user1.setDateMonth("01");
        user1.setDateYear("1969");
        user1.setPhoneNumber("7777777777");
        user1.setEmail("admin@admin.com");
        user1.setAddress1("address1");
        user1.setAddress2("address2");
        user1.setAddress3("address3");
    }


    @Test(timeout = TIMEOUT)
    public void testNullObject() {
        assertFalse(user1.equalUsername(null));
    }

    @Test(timeout = TIMEOUT)
    public void testSameObject() {
        user2 = user1;
        assertTrue(user1.equalUsername(user2));
        assertTrue(user2.equalUsername(user1));
    }

    @Test(timeout = TIMEOUT)
    public void testDifferentType() {
        String userString = "username";
        assertFalse(user1.equalUsername(userString));

        Integer userInteger = 69;
        assertFalse(user1.equalUsername(userInteger));
    }

    @Test(timeout = TIMEOUT)
    public void testSameUsernameOnly() {
        String username = "username";
        user1.setUserName(username);
        user2 = new User(username, "different", AccountType.USER);
        user2.setName("different");
        user2.setGender(Gender.Female);
        user2.setDateDay("different");
        user2.setDateMonth("different");
        user2.setDateYear("different");
        user2.setPhoneNumber("different");
        user2.setEmail("different");
        user2.setAddress1("different");
        user2.setAddress2("different");
        user2.setAddress3("different");

        assertTrue(user1.equalUsername(user2));
    }

    @Test(timeout = TIMEOUT)
    public void testEqualUsernameOnly() {
        user2 = new User("username", "different", AccountType.USER);
        user2.setName("different");
        user2.setGender(Gender.Female);
        user2.setDateDay("different");
        user2.setDateMonth("different");
        user2.setDateYear("different");
        user2.setPhoneNumber("different");
        user2.setEmail("different");
        user2.setAddress1("different");
        user2.setAddress2("different");
        user2.setAddress3("different");

        assertTrue(user1.equalUsername(user2));
    }

    @Test(timeout = TIMEOUT)
    public void testDifferentUsernameOnly() {
        user2 = new User("different", "password", AccountType.ADMIN);
        user2.setName("Admin");
        user2.setGender(Gender.Male);
        user2.setDateDay("01");
        user2.setDateMonth("01");
        user2.setDateYear("1969");
        user2.setPhoneNumber("7777777777");
        user2.setEmail("admin@admin.com");
        user2.setAddress1("address1");
        user2.setAddress2("address2");
        user2.setAddress3("address3");

        assertFalse(user1.equalUsername(user2));
    }
}