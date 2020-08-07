/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.util.*;
import java.lang.*;

/**
 *
 * @author DELL
 */
public class naya  {


    public static int global_depth=2;
    public static directory_lookup list =new directory_lookup();
    public static All_data data_log = new All_data();


    public static int intLen(int n)
    {
        if(n==0)
            return 1;

        int cnt=0;
        while(n>0)
        {
            n/=10;
            cnt++;
        }
        return cnt;
    }

    public static int decToBinary(int n)
    {
        int[] rev_binary=new int[1000];

        int i = 0;
        while (n > 0)
        {
            rev_binary[i] = n % 2;
            n = n / 2;
            i++;
        }
        int ans=0;

        for(int j=0;j<i;j++)
            ans+=((int)Math.pow(10,j))*rev_binary[j];

        return ans;
    }

//    public static int[] globalDepthIncrease(int new_depth)
//    {
//        int size=(int)Math.pow(2,new_depth);
//        int[] dir_lookup=new int[size];
//
//        for(int i=0;i<size;i++)
//        {
//            int[] curr=decToBinary(i);
//            int val=0;
//            for(int j=0;j<new_depth;j++)
//            {
//                val+=curr[j]*Math.pow(10,j);
//            }
//            dir_lookup[i]=val;
//        }
//        return dir_lookup;
//    }

    public static int hash(int num)
    {
        return num%10;
    }

    public static void makeDirLookup()
    {
        int end=(int)Math.pow(2, global_depth);

        directory_lookup newlist =new directory_lookup();

        for(int i=0;i<end;i++)
            newlist.insert(decToBinary(i)%((int)Math.pow(10,global_depth)),newlist);

        list=newlist;

            Block.Node bucket1 = new Block.Node(1);
            Block.Node bucket2 = new Block.Node(0);

            bucket1.depth=1;
            bucket2.depth=1;

            directory_lookup.Node temp=Main.list.head;

            while(temp!=null)
            {
                if((temp.data)%10==1)
                    temp.block_ptr=bucket1;

                if((temp.data)%10==0)
                    temp.block_ptr=bucket2;

                temp=temp.next;
            }

    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String str;

//        while(sc.hasNextLine())
//        {
//            str = sc.nextLine();
//
//            if(str.isEmpty())
//               break;
//
//            int num = Integer.parseInt(str);
//
//            System.out.println(str+" "+num);
//        }

        makeDirLookup();

        while(sc.hasNextLine())
        {
            str = sc.nextLine();

            if(str.isEmpty())
               break;

            int num = Integer.parseInt(str);
            data_log = All_data.insert(data_log, num);
            Block.Insert(num);



            All_data.Node temp=naya.data_log.head;
        while(temp!=null)
        {
            System.out.print(temp.data+" ");
            temp=temp.next;
        }

        System.out.println();
        directory_lookup.Node temp1=naya.list.head;

            while(temp1!=null)
            {
                int len=intLen(temp1.data);
                if(len<global_depth)
                {
                    int x=global_depth-len;
                    while(x>0)
                    {
                        System.out.print("0");
                        x--;
                    }
                }

                    if(intLen(temp1.block_ptr.identifier)==temp1.block_ptr.depth)
                    {
                        System.out.print(temp1.data+"->"+temp1.block_ptr.identifier+"->");
                        for(int i=0;i<Block.bfr;i++)
                        {
                            if(temp1.block_ptr.data[i]>-1)
                            System.out.print(temp1.block_ptr.data[i]+" ");
                        }
                    }
                    else
                    {
                        System.out.print(temp1.data+"->");
                        int t=temp1.block_ptr.depth-intLen(temp1.block_ptr.identifier);
                        while(t>0)
                        {
                            System.out.print("0");
                            t--;
                        }

                        System.out.print(temp1.block_ptr.identifier+"->");

                        for(int i=0;i<Block.bfr;i++)
                        {
                            if(temp1.block_ptr.data[i]>-1)
                            System.out.print(temp1.block_ptr.data[i]+" ");
                        }

                    }


                temp1=temp1.next;
                System.out.println();
            }
                System.out.println();

        }



    }
}
