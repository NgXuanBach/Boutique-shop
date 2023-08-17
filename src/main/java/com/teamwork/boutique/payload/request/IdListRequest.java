package com.teamwork.boutique.payload.request;

import java.util.HashSet;
import java.util.Set;

public class IdListRequest {
    private Set<Integer> idList = new HashSet<>();
    public IdListRequest() {
    }

    public Set<Integer> getIdList() {
        return idList;
    }

    public void setIdList(Set<Integer> idList) {
        this.idList = idList;
    }
}
