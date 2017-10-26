import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.lang.Math.pow;

public class Table {
    private int N = 0;
    //private int n = 0;
    private double H = 0;
    //private double Pe = 0;
    private Probability probability;
    private Map<Integer,Double> map = new LinkedHashMap<>();


    public Table(Probability p, int N){
        this.N = N;
        probability = p.makeNewCharacters(N);
        probability.sort();
        probability.makeH();


        int n = 0;

        int k = 1;
        while (k < probability.size()){
            k *= 2;
            n++;
        }
        map.put(n,0.0);

        H = probability.getH() / N;

        makeTable(n);
    }

    public void  makeTable(int n){
        //n -= 1;
        double n_div_N = 0;
        do{
            n--;
            int lost = probability.size() - ((int)pow(2,n));
            Double Pe = probability.countPe(lost);
            map.put(n,Pe);
            n_div_N = ((double)n) / ((double)N);

        }while (n_div_N > H);
    }


    public String toString()
    {
        StringBuilder res = new StringBuilder();

        System.out.println(probability);

        res.append("N = " + N +"\n");
        Iterator<Integer> it = map.keySet().iterator();

        while (it.hasNext()) {
            int n = it.next();
            double n_div_N = ((double)n) / ((double)N);
            if (it.hasNext())
                res.append("n = " + n + "| R = " + String.format("%.5f",n_div_N) + " > H = " + String.format("%.5f",H) + "| Pe = " + String.format("%.5f",map.get(n)) + "\n");
            else
                res.append("n = " + n + "| R = " + String.format("%.5f",n_div_N) + " < H = " + String.format("%.5f",H) + "| Pe = " + String.format("%.5f",map.get(n)) + "\n");
        }

        return res.toString();
    }






}
