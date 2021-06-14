public class getValue {
    public static void main(String[] args){
        getVa(1);
    }


    static int getVa(int i) {
        int result = 0;
        switch (i){
            case 1:
                result=result+ 2;
                break;
            case 2:
                result=result+ i *2;
                break;
            case 3:
                result=result+ i *3;
                break;
        }
        System.out.println(result);
        return result;
    }
}
