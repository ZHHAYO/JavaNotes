import java.util.Arrays;

class twoSum {

    public static void main(String[] args){
        int[] nums = {2, 7, 11, -2,15};
        int[] res = Solution(nums,9);
        System.out.println(
                Arrays.toString(res));

    }


    public static int[] Solution(int[] nums, int target) {
        int[] index = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    index[0] = i;
                    index[1] = j;
                    return index;
                }
            }
        }
        return new int[0];
    }
}