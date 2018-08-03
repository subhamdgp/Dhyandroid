package com.humosys.dhyandroid;

public class DatabaseActivity {
    String userName;
    String userAge;
    String userGender;
    String userId;
    public DatabaseActivity(){

    }
    public DatabaseActivity(String userId, String userName,String userAge,String userGender){
        this.userId=userId;
        this.userName=userName;
        this.userAge=userAge;
        this.userGender=userGender;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserAge() {
        return userAge;
    }

    public String getUserGender() {
        return userGender;
    }

    public String getUserId() {
        return userId;
    }
}
