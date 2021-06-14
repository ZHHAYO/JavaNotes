public class test {
    public static void main(String[] args) {
        int divided = 5;
        int divisor = 9;
        for (int i = 0; i < 50; i++) {
            if (divided < divisor){
                System.out.println(divided);
                divided++;
                continue;
            }
            System.out.println("i="+i);
            if (i == 5) {
                System.out.println("i==5");
                break;
            }
        }
    }
}