package week1;

import java.util.Arrays;

public class QuickUnionUF implements UnionFind{
    private int[] id;

    public QuickUnionUF(int N){
       id = new int[N];
       for(int i=0;i< N;i++)
           id[i] = i;
    }

    public void union(int p, int q){
       int pr = root(p);
       int qr = root(q);
       id[pr] = qr;
    }

    public boolean connected(int p, int q){
        return root(p) == root(q);
    }

    private int root(int i){
        while(id[i] != i) i = id[i];
        return i;
    }

    public void print(){
        System.out.println(Arrays.toString(id));
    }
}
