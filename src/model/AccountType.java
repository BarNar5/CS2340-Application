package model;

import java.io.Serializable;

/**
 * This enum represents an account type of a user.
 *
 */

public enum AccountType implements Serializable {
    USER,
    WORKER,
    MANAGER,
    ADMIN
}
