package com.ding.hyld.utils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Tree {
    private int id;
    private int parentId;
    private List<Tree> children;

    public Tree(int id, int parentId) {
        this.id = id;
        this.parentId = parentId;
    }

    public Tree() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}