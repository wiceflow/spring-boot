package com.wiceflow.entity.mapper;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * @author BF
 * @date 2018/10/26
 */
public class User {

    @NotNull(message = "name is null")
    private String name;
    @NotNull(message = "age is null")
    private String age;
    private String phone;
    @Email(message = "email is error")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
