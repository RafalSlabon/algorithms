
public class PercolationPrinter {
    private Percolation p;

    public PercolationPrinter(Percolation p) {
        this.p = p;
    }

    public void print(){
        System.out.println("");
        for(int i=1;i<=p.N;i++){
            for(int j=1;j<=p.N;j++){
                if(p.isFull(i, j)){
                    System.out.print("*");
                }else if(p.isOpen(i, j)){
                    System.out.print("+");
                } else{
                    System.out.print("#");
                }
                System.out.print("\t");
            }
            System.out.println("");
        }
        System.out.println("");
    }
}
