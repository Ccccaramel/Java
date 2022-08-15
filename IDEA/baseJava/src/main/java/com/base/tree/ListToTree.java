package com.base.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
class Link{
    private int id;
    private String name;
    private int parentId;
    private List<Link> childrenLink;

    public Link(int id, String name, int parentId, List<Link> childrenLink) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.childrenLink = childrenLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public List<Link> getChildrenLink() {
        return childrenLink;
    }

    public void setChildrenLink(List<Link> childrenLink) {
        this.childrenLink = childrenLink;
    }
}
public class ListToTree {

    /**
     * 将 List 数据转换成 Tree
     * 注意变量名
     * @param categoryBaseList
     * @return
     */
    public static List<Link> buildTree(List<Link> categoryBaseList) {
        Map<Integer,List<Link>> parentMap=categoryBaseList.stream().collect(Collectors.groupingBy(Link::getParentId));
        for(Map.Entry<Integer,List<Link>> entry: parentMap.entrySet()){
            List<Link> childMenus=entry.getValue();
            for(Link childMenu:childMenus){
                childMenu.setChildrenLink(parentMap.get(childMenu.getId()));
            }
        }
        return parentMap.get(0);
    }

    public static void main(String[] args) {
        List<Link> linkList=new ArrayList<>();
        // root
        linkList.add(new Link(0,"root",-1,null));
        // 1
        linkList.add(new Link(1,"1",0,null));
        linkList.add(new Link(2,"2",0,null));
        linkList.add(new Link(3,"3",0,null));
        linkList.add(new Link(4,"4",0,null));
        // 2
        linkList.add(new Link(5,"11",1,null));
        linkList.add(new Link(6,"22",1,null));
        linkList.add(new Link(7,"33",1,null));
        // 3
        linkList.add(new Link(8,"111",6,null));
        linkList.add(new Link(9,"222",6,null));
        linkList.add(new Link(10,"333",6,null));
        linkList.add(new Link(11,"444",6,null));
        // 4
        linkList.add(new Link(12,"1111",8,null));
        linkList.add(new Link(13,"2222",8,null));
        linkList.add(new Link(14,"3333",8,null));
        // 5
        linkList.add(new Link(15,"11111",14,null));
        linkList.add(new Link(16,"22222",14,null));

        List<Link> a = buildTree(linkList);
        System.out.println("tree:"+a);
    }
}
