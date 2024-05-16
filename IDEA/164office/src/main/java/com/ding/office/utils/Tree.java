package com.ding.office.utils;

import java.util.List;

public class Tree implements Comparable<Tree>{
    private Integer id;
    private Integer parentId;
    private List<Tree> children;

    public Tree(int id, int parentId) {
        this.id = id;
        this.parentId = parentId;
    }

    public Tree() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public int compareTo(Tree o) {
        return this.getId().compareTo(o.getId());
    }
}