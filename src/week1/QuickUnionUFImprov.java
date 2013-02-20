package week1;

import java.util.Arrays;

public class QuickUnionUFImprov implements UnionFind {
    private int[] id;
    private int[] sz;

    public QuickUnionUFImprov(int N) {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    public void union(int p, int q) {
        int pr = root(p);
        int qr = root(q);
        if(sz[pr] < sz[qr]){
            id[pr] = qr;
            sz[qr] += sz[pr];
        }else{
            id[qr] = pr;
            sz[pr] += sz[qr];
        }
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    private int root(int i) {
        while (id[i] != i) i = id[i];
        return i;
    }

    public void print(){
        System.out.println(Arrays.toString(id));
    }
}
