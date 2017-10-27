
public class TheoryInf {


    public static void main(String[] args) {


        //Probability probability = new Probability("aaaabbbccccc");
        Probability probability = new Probability("кузьмичёв семён");

        System.out.println(probability);
        System.out.println();

        Table tableFor1 = new Table(probability,1);

        System.out.println(tableFor1);

        Table tableFor2 = new Table(probability,2);

        System.out.println(tableFor2);

        Table tableFor3 = new Table(probability,3);

        System.out.println(tableFor3);

        Table tableFor4 = new Table(probability,4);

        System.out.println(tableFor4);


    }


}
