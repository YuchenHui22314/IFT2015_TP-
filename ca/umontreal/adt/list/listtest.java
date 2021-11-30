package ca.umontreal.adt.list;

public class listtest {
    public static void main(String[] args) {
        List<String> a = new SinglyLinkedList<>();
        System.out.println(a);
        a.addFirst("hujunxuan");
        a.remove("hujunxuan");
    }
}
