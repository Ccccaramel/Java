package com.base.tree;

import java.util.ArrayList;
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

    /**
     * 通过继承,调用统一的接口实现tree转换
     * @param args
     */
    public static void main(String[] args) {
        List<address> addressList=new ArrayList<>();
        addressList.add(new address(0,-1,"root"));
        addressList.add(new address(1,0,"1"));
        addressList.add(new address(2,0,"2"));
        addressList.add(new address(3,0,"3"));
        addressList.add(new address(4,1,"1-1"));
        addressList.add(new address(5,1,"1-2"));
        addressList.add(new address(6,5,"2-1"));
        addressList.add(new address(7,3,"1-3"));
        addressList.add(new address(8,3,"1-4"));
        addressList.add(new address(9,6,"3-1"));
        addressList.add(new address(10,9,"4-1"));
        List<address> addressesTree = transformation(addressList);
        System.out.println("tree:"+addressesTree);

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
class address extends Tree{
    private String name;

    public address(int id,int parentId, String name) {
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