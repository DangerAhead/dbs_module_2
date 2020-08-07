/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

/**
 *
 * @author DELL
 */
public class All_data {
    Node head=null;
    static class Node
    {
        int data;
        Node next;
        Node(int d)
        {
            data = d;
            next = null;
        }
    }

    public static All_data insert(All_data list, int data)
    {
        Node new_node = new Node(data);
        new_node.next = null;

        if (list.head == null) {
            list.head = new_node;
        }
        else {
                Node last = list.head;
                while (last.next != null)
                {
                    last = last.next;
                }

            last.next = new_node;
        }

        return list;
    }
}
