package model;


public class User {
    private String userName;
    private String password;
    private AccountType accountType;
    private String name;
    private Gender gender;
    private String dateDay;
    private String dateMonth;
    private String dateYear;
    private String phoneNumber;
    private String email;
    private String address1;
    private String address2;
    private String address3;



    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getDateDay() {
        return dateDay;
    }
    public void setDateDay(String dateDay) {
        this.dateDay = dateDay;
    }

    public String getDateMonth() {
        return dateMonth;
    }
    public void setDateMonth(String dateMonth) {
        this.dateMonth = dateMonth;
    }

    public String getDateYear() {
        return dateYear;
    }
    public void setDateYear(String dateYear) {
        this.dateYear = dateYear;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress1() {
        return address1;
    }
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }
    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public AccountType getAccountType() {
        return accountType;
    }
    public void setAccountType(AccountType type) {
        this.accountType = type;
    }

    public User(String userName, String password, AccountType type) {
        this.userName = userName;
        this.password = password;
        this.accountType = type;
    }

    public User() {
        this("enter username", "enter password", AccountType.USER);
    }

    public User(String userName, String password) {
        this(userName, password, AccountType.USER);
    }

    @Override
    public boolean equals(Object that) {
        if (that == null) {
            return false;
        }
        if (that == this) {
            return true;
        }
        if (!(that instanceof User)) {
            return false;
        }
        User user = (User) that;
        return userName.equals(user.getUserName()) && password.equals(user.getPassword());
    }

    public boolean equalName(Object that) {
        if (that == null) {
            return false;
        }
        if (that == this) {
            return true;
        }
        if (!(that instanceof User)) {
            return false;
        }
        User user = (User) that;
        return userName.equals(user.getUserName());
    }

    public String toString() {
        return userName + " " + password + " " + accountType;
    }

}