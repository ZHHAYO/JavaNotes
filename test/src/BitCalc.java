public class BitCalc {
    public static void main(String[] args) {
        int a = 0xF8; //1111 1000
        int b = 0xF4;// 1111 0100
        int c = 0xFF;// 1111 1111
        System.out.println(a & b);
        System.out.println(a | b);
        System.out.println(a ^ b);

        System.out.println(~c);
    }
}