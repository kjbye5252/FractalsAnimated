public class test {

    private static int iterations = 3;
    private static int branches = 0;

    public static void branchs(int iteration) {
        for (int i = 0; i < iteration; i++) {
            iteration--;
            branchs(iteration);
            branchs(iteration);
            branches++;
        }
    }

    public static void main(String[] args) {
        branchs(iterations);
        System.out.println(branches);
    }
}
