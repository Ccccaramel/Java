package com.base.tree;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Tree {
    private int id;
    private int parentId;
    private List<Tree> children;

    public Tree(int id,int parentId) {
        this.id = id;
        this.parentId = parentId;
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

    public static <T extends Tree> List<T> transformation(List<T> list){
        Map<Integer,List<Tree>> parentMap=list.stream().collect(Collectors.groupingBy(Tree::getParentId));
        for(Map.Entry<Integer,List<Tree>> entry: parentMap.entrySet()){
            List<Tree> childMenus=entry.getValue();
            for(Tree childMenu:childMenus){
                childMenu.setChildren(parentMap.get(childMenu.getId()));
            }
        }
        return (List<T>) parentMap.get(0);
    }
}