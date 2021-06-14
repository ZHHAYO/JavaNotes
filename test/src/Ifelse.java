public class Ifelse {
    public static void main(String[] args) {
        boolean baoziGangChulu = false;
        int baozi = 3;
        if (baoziGangChulu) {
            baozi += 2;
            System.out.println("包子刚出炉，买了" + baozi + "个包子。");
        } else {
            System.out.println("买了" + baozi + "个包子");
        }
    }
}
