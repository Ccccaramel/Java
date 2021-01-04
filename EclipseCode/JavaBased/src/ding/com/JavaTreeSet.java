package ding.com;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

class Preson{
	int val;
	String s;
}
public class JavaTreeSet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeMap<Preson,Integer> treeMap=new TreeMap<>(new Comparator<Preson>() {

			@Override
			public int compare(Preson o1, Preson o2) {
				// TODO Auto-generated method stub
				return 0;
			}
			
		});
		Iterator<Map.Entry<Preson,Integer>> iterator=treeMap.entrySet().iterator();
		Iterator iterator1=treeMap.keySet().iterator();
		Iterator iterator2=treeMap.keySet().iterator();
	}

}
