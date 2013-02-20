package week1;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;


public class UFTest {

    private UnionFind unionFind;

    @Before
    public void setup(){
       unionFind = new UF(10);
    }

    @Test
    public void e1(){
        unionFind = new UF(10);
        union(8, 4);
        union(0, 9);
        union(7, 1);
        union(5, 3);
        union(9, 5);
        union(3, 6);
        unionFind.print();
    }

    @Test
    public void e2(){
        unionFind = new QuickUnionUFImprov(10);
        union(9, 7);
        union(8, 5);
        union(5, 2);
        union(1, 3);
        union(0, 2);
        union(3, 7);
        union(2, 9);
        union(8, 6);
        union(4, 6);
        unionFind.print();
    }

    @Test
    public void test(){
        union(1, 2);
        assertConnected(1, 2);
        assertConnected(2, 1);
    }

    @Test
    public void test2(){
        union(1,2);
        union(3,4);

        assertConnected(1, 2);
        assertConnected(3, 4);

        assertNotConnected(1, 3);
        assertNotConnected(1,4);
        assertNotConnected(2,3);
        assertNotConnected(2,4);
    }

    @Test
    public void test3(){
        union(1, 2);
        union(3, 4);
        union(2, 3);
        assertConnected(1,2);
        assertConnected(1,3);
        assertConnected(1,4);
    }


    private void assertConnected(int p, int q) {
        assertTrue(unionFind.connected(p, q));
    }

    private void assertNotConnected(int p, int q) {
        assertFalse(unionFind.connected(p, q));
    }

    private void union(int p,int q) {
        unionFind.union(p,q);
    }
}
