package com.wiceflow.util;

import com.wiceflow.entity.mapper.User;

import javax.validation.Valid;
import java.util.*;


public class ValidList {
    @Valid
    List<User> list;

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }
}
