import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.lang.Math.pow;

public class Table {
    private int n = 0;
    //private int N = 0;
    private double H = 0;
    //private double Pe = 0;
    private Probability probability;
    private Map<Integer,Double> map = new LinkedHashMap<>();


    public Table(Probability p, int n){
        this.n = n;
        probability = p.makeNewCharacters(n);
        probability.sort();
        probability.makeH();


        int N = 0;

        int k = 1;
        while (k < probability.size()){
            k *= 2;
            N++;
        }
        map.put(N,0.0);

        H = probability.getH();

        makeTable(N);
    }

    public void  makeTable(int N){
        N -= 1;
        do{
            int lost = probability.size() - ((int)pow(2,N));
            Double Pe = probability.countPe(lost);
            map.put(N,Pe);
            N--;
        }while (N/n > H);
    }


    public String toString()
    {
        StringBuilder res = new StringBuilder();

        System.out.println(probability);

        res.append("n = " + n +"\n");
        Iterator<Integer> it = map.keySet().iterator();

        while (it.hasNext()) {
            int N = it.next();
            if (it.hasNext())
                res.append("N = " + N + "| R = " + N / n + " > H = " + H + "| Pe = " + map.get(N) + "\n");
            else
                res.append("N = " + N + "| R = " + N / n + " < H = " + H + "| Pe = " + map.get(N) + "\n");
        }

        return res.toString();
    }






}
