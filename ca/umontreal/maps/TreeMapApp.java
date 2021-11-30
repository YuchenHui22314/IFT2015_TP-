package ca.umontreal.maps;

import java.util.List;
import java.util.ArrayList;

/**
 * TreeMapApp
 */
public class TreeMapApp {
    public static void main(String[] args) {
        TreeMap <Integer,String> tmd = new TreeMap<>();
        tmd.put(1, "shit");
        tmd.put(3, "shit");
        tmd.put(2, "shit");
        tmd.put(8, "shit");
        tmd.put(6, "shit");
        tmd.put(5, "shit");
        tmd.put(9, "shit");
        String a = tmd.remove(3); 
        System.out.println(a + "chenggong");
        List<String> buffer = new ArrayList<>();
        changeList((ArrayList<String>)buffer);
        System.out.println(buffer);
        
        
        }
        
        public static void changeList(ArrayList<String> buffer){
            buffer.add("233333333333");
            buffer.add("3mmmmmmmmmmmmmm");
            buffer.add("kdfjlskdjflksdjfdl");
        }
    
}