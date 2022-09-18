package com.ding.hyld.utils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TreeUtils {
    public static <T extends Tree> List<T> transformation(List<T> list){
        Map<Integer,List<Tree>> parentMap=list.stream().collect(Collectors.groupingBy(Tree::getParentId));
        for(Map.Entry<Integer,List<Tree>> entry: parentMap.entrySet()){
            List<Tree> childMenus=entry.getValue();
            for(Tree childMenu:childMenus){
                childMenu.setChildren(parentMap.get(childMenu.getId()));
            }
        }
        return (List<T>) parentMap.get(-1);
    }
}
