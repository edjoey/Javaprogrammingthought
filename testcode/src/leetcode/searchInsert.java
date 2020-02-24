package leetcode;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 * @author joeyzhou
 *
 */
public class searchInsert {
	
	public static void main(String[] args) {
		int[] nums = {1,2,3,4,5};
		int target = 11111111;
	  for(int i= 0;i < nums.length;i++) {
			if (nums[i] == target) {
				System.out.print(i);
				return;
			}
			if (target > nums[i] && (i+1 == nums.length || target < nums[i+1] )) {
				System.out.print(++i);
				return;
			}
		}
	  	System.out.print(0);
	}
}


