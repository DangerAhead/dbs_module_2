/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

/**
 *
 * @author DELL
 */
public class directory_lookup {

    Node head=null;
    Node tail=null;

    static class Node
    {
        int data;
        Node next;
        Node prev;
        Block.Node block_ptr;

        Node(int x)
        {
            data=x;
            next=null;
            prev=null;
            block_ptr=null;
        }
    }

    public void insert(int data, directory_lookup list)
    {
        Node new_node = new Node(data);
        new_node.next = null;
        new_node.prev=null;

        if (list.head == null) {
            list.head = new_node;
            list.tail=new_node;
        }
        else {
            list.tail.next = new_node;
            new_node.prev = list.tail;
            tail=tail.next;
        }
    }
}
