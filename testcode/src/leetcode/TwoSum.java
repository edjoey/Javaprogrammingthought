package leetcode;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {
	
//	给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
//	你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
//	示例:
//	给定 nums = [2, 7, 11, 15], target = 9
//	因为 nums[0] + nums[1] = 2 + 7 = 9
//	所以返回 [0, 1]
			
	public static void main(String[] args) {
		int[] nums = {2,7,1,15};
		System.out.println(Arrays.toString(solution(nums, 9)));
	}
	
	public static int[] solution(int[] nums, int target) {
		int[] indexs = new int[2];
        // 建立k-v ，一一对应的哈希表
        HashMap<Integer,Integer> hash = new HashMap<Integer,Integer>();
        for(int i = 0; i < nums.length; i++){
            if(hash.containsKey(nums[i])){
                indexs[0] = i;
                indexs[1] = hash.get(nums[i]);
                return indexs;
            }
            // 将数据存入 key为补数 ，value为下标
            hash.put(target-nums[i],i);
        }
        
        return indexs;
	}
}
