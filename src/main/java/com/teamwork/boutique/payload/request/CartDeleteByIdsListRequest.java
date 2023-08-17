package com.teamwork.boutique.payload.request;

import java.util.HashSet;
import java.util.Set;

public class CartDeleteByIdsListRequest {
    private Set<CartDeleteByIdsRequest>  ids = new HashSet<>();

    public CartDeleteByIdsListRequest() {
    }

    public Set<CartDeleteByIdsRequest> getIds() {
        return ids;
    }

    public void setIds(Set<CartDeleteByIdsRequest> ids) {
        this.ids = ids;
    }
}
