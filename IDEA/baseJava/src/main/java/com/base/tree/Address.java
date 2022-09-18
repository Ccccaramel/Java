package com.base.tree;

class Address extends Tree{
    private String name;

    public Address(int id,int parentId, String name) {
        super(id, parentId);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
