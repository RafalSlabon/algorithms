
public class PercolationPrinter {
    private Percolation p;

    public PercolationPrinter(Percolation p) {
        this.p = p;
    }

    public void print(){
        for(int i=1;i<=p.N;i++){
            for(int j=1;j<=p.N;j++){
                if(p.isFull(i, j)){
                    System.out.print("F");
                }else if(p.isOpen(i, j)){
                    System.out.print("O");
                } else{
                    System.out.print("*");
                }
                System.out.print("\t");
            }
            System.out.println("");
        }
    }
}
