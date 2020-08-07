/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

/**
 *
 * @author DELL
 */
public class Block
{

    public static int bfr=3;

    public static class Node
    {
        int identifier;
        int depth;
        int[] data=new int[3];

        Node(int id)
        {
            identifier=id;
            data[0]=-1;
            data[1]=-1;
            data[2]=-1;
        }
    }

    public static void Overflow(Block.Node currbucket, int newdata)
    {
        int[] currdata=new int[bfr+1];
        int i=0;

        for(i=0;i<bfr;i++)
            currdata[i]=currbucket.data[i];
        currdata[i]=newdata;

        if(currbucket.depth<Main.global_depth)
        {
            int newdepth=currbucket.depth+1;
            int check_factor=(int)Math.pow(10,newdepth);

            int id1=currbucket.identifier;
            int id2=currbucket.identifier+(int)Math.pow(10,currbucket.depth);
            int itr1=0,itr2=0;

            Block.Node bucket1 = new Node(id1);
            Block.Node bucket2 = new Node(id2);

            bucket1.depth=newdepth;
            bucket2.depth=newdepth;

            directory_lookup.Node temp=Main.list.head;

            while(temp!=null)
            {
                if((temp.data)%check_factor==id1)
                    temp.block_ptr=bucket1;

                if((temp.data)%check_factor==id2)
                    temp.block_ptr=bucket2;

                temp=temp.next;
            }

            for(i=0;i<=bfr;i++)
            {
                int x=Main.decToBinary(Main.hash(currdata[i]));

                if(x%check_factor==id1)
                    bucket1.data[itr1++]=currdata[i];
                else if(x%check_factor==id2)
                    bucket2.data[itr2++]=currdata[i];
            }
        }
        else
        {
            Main.global_depth++;
            Main.makeDirLookup();

            All_data.Node temp=Main.data_log.head;
            while(temp!=null)
            {
                Block.Insert(temp.data);
                temp=temp.next;
            }

        }
    }

    public static Block.Node get_bucket(int data)
    {
        directory_lookup.Node temp=Main.list.head;
        int curr=Main.decToBinary(Main.hash(data))%((int)(Math.pow(10,Main.global_depth)));

        while(temp!=null)
        {
            if(temp.data==curr)
                return temp.block_ptr;
            temp=temp.next;
        }
        return null;
    }

    public static void Insert(int x)
    {
        Block.Node bucket = get_bucket(x);
        int i=0;
        boolean check=false;

        while(bucket.data[i]!=-1)
        {
            i++;
            if(i>=bfr)
            {
                check=true;
                break;
            }
        }

        if(check)
        {
            Overflow(bucket,x);
        }
        else
        {
            bucket.data[i]=x;
        }
    }
}
