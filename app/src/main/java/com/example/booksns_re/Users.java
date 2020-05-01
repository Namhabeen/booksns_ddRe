package com.example.booksns_re;

public class Users {



        public String email;
        public String password;
        public String name;
        public String birth;

        public Users() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public Users(String email, String password,String name,String birth) {
            this.email = email;
            this.password = password;
            this.name=name;
            this.birth=birth;
        }

        @Override
        public String toString() {
            return "User{" +
                    "email='" + email + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }