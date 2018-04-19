package com.irwan.cannadys.kudotest.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by irwancannady on 4/17/18.
 */

public class LoginResponse implements Serializable{

    private int code;
    private Message message;

    public int getCode() {
        return code;
    }

    public Message getMessage() {
        return message;
    }

    public LoginResponse(int code, Message message) {
        this.code = code;
        this.message = message;
    }

    public class Message implements Serializable{
        @SerializedName("user_mail")
        private String userMail;
        @SerializedName("first_name")
        private String first_name;
        @SerializedName("last_name")
        private String lastName;
        @SerializedName("join_date")
        private String joinDate;
        private String avatar;
        private String description;

        public String getUserMail() {
            return userMail;
        }

        public String getFirst_name() {
            return first_name;
        }

        public String getLastName() {
            return lastName;
        }

        public String getJoinDate() {
            return joinDate;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getDescription() {
            return description;
        }
    }
}
