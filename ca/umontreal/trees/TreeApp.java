package ca.umontreal.trees;
import ca.umontreal.adt.list.List;
import ca.umontreal.adt.list.ArrayList;
import ca.umontreal.adt.list.Position;

public class TreeApp {

    public static void main( String[] args ) {
       LinkedBinaryTree<String> treea = new LinkedBinaryTree<>();
       Position<String> p = treea.addRoot("shit");
       //treea.addLeft(p, "fuck");
       for (Position<String> string : treea.children(p)) {
          System.out.println("shitttttttttttttttttttttt"); 


       }

       
    }


    public<E extends Comparable<E> > void generateBinarySearchTree (BinaryTree<E> t, E[] list ){
        if ( !t.isEmpty() ){
            throw new IllegalArgumentException("must be a emptytree");
        }

    }
}
