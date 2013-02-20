package week1;


public interface UnionFind {
    void union(int p, int q);
    boolean connected(int p, int q);
    void print();
}
