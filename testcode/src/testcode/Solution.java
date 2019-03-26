package testcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * https://leetcode-cn.com/problems/minimum-number-of-refueling-stops/
 * @author joeyzhou
 *
 */
public class Solution {

//	汽车从起点出发驶向目的地，该目的地位于出发位置东面 target 英里处。
//	沿途有加油站，每个 station[i] 代表一个加油站，它位于出发位置东面 station[i][0] 英里处，并且有 station[i][1] 升汽油。
//	假设汽车油箱的容量是无限的，其中最初有 startFuel 升燃料。它每行驶 1 英里就会用掉 1 升汽油。
//	当汽车到达加油站时，它可能停下来加油，将所有汽油从加油站转移到汽车中。
//	为了到达目的地，汽车所必要的最低加油次数是多少？如果无法到达目的地，则返回 -1 。
//	注意：如果汽车到达加油站时剩余燃料为 0，它仍然可以在那里加油。如果汽车到达目的地时剩余燃料为 0，仍然认为它已经到达目的地。
//
//	示例 1：
//	输入：target = 1, startFuel = 1, stations = []
//	输出：0
//	解释：我们可以在不加油的情况下到达目的地。
	public static void main(String[] args) {
//		[[15,457],[156,194],[160,156],[230,314],[390,159],[621,20],[642,123],[679,301],[739,229],[751,174]]
		int[][] stations = {{15,457},{156,194},{160,156},{230,314}
							,{390,159},{621,20},{642,123},{679,301},{739,229},{751,174}};

		System.out.println(minRefuelStops(1000, 83, stations));
		int[] numberAr = {1,3,5,6,};
		System.out.println(indexOfArray(numberAr,0));
		
		Scanner s = new Scanner(System.in);
		int row = s.nextInt();
		//根据行数定义好二维数组，由于每一行的元素个数不同，所以不定义每一行的个数
		int[][] arr = new int[row][];
		//遍历二维数组
		for(int i = 0; i < row; i++){
		    //初始化每一行的这个一维数组
		    arr[i] = new int[i + 1];
		    //遍历这个一维数组，添加元素    
		    for(int j = 0; j <= i; j++){
		        //每一列的开头和结尾元素为1，开头的时候，j=0，结尾的时候，j=i
		        if(j == 0 || j == i){
		            arr[i][j] = 1;
		        } else {//每一个元素是它上一行的元素和斜对角元素之和
		            arr[i][j] = arr[i -1][j] + arr[i - 1][j - 1];
		        }
		        System.out.print(arr[i][j] + "\t");
		    }
		    System.out.println();
		}
	}
	
	/**
	 * target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
	 * @param target
	 * @param startFuel
	 * @param stations
	 * @return
	 */
	public static int minRefuelStops(int target,int startFuel,int[][] stations) {
		if (target == startFuel || 0 == target) {	
			return 0;
		}
	      Queue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
	      @Override
	      public int compare(Integer integer, Integer t1) {
	          return t1 - integer;
	      }
	      });
	      	      
      	HashSet<Integer> hsPrority = new HashSet<>();
		int retData = 0;
		//加油站记录
		int currentPosition = 0;
		long currentFuel = startFuel;
		while(true){
			//路过的加油站不超过已有的加油站且下一站的加油站是当前油量能够到达
		    while(currentPosition < stations.length && stations[currentPosition][0] <= currentFuel){
		    	hsPrority.add(stations[currentPosition][1]);
		    	priorityQueue.add(stations[currentPosition][1]);
		        currentPosition++;
		    }
		    if(currentFuel >= target){
		        return retData;
		    }
//		    if(priorityQueue.isEmpty()){
//		    	return -1;
//		    }
		    if (hsPrority.size() == 0) {
				return -1;
			}
		    currentFuel += priorityQueue.poll();
		    retData++;
		}
	}
	
	/**
	 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，
	 * 返回它将会被按顺序插入的位置。
	 * 你可以假设数组中无重复元素。
	 * @param x
	 * @return
	 * 
	 * 输入: [1,3,5,6], 5
	 * 输出: 2
	 */
	
	public static int indexOfArray(int[] nums,int x) {
		for(int i= 0;i < nums.length;i++) {
			if (nums[i] == x) {
				return i;
			}
			if (x > nums[i] && (i+1 == nums.length || x < nums[i+1] )) {
				return ++i;
			}
		}
		return 0;
	}
	
	 /**
	  * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/
	  * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
	  * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
	  * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
	  * 示例 1:
	  * 输入: [2,4,1], k = 2
	  * 输出: 2
	  * 解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
	  * 
	  * @param k
	  * @param prices
	  * @return
	  */
	  public int maxProfit(int k, int[] prices) {
		  Queue<Integer> priceQueue = new PriorityQueue<>(new Comparator<Integer>() {
	      @Override
	      public int compare(Integer integer, Integer t1) {
	          return t1 - integer;
	      }
	      }); 	    
		for (int i : prices) {
			//当前天数的第i+1天
			int dayPrice = prices[i + 1];
			//当天的价格
			int nowPrice = prices[i];
			//比较每天的价格进行存放
			if (dayPrice < nowPrice ) {
				priceQueue.add(dayPrice);
			}
			//线性排序，取k值中最小的值买入
			//反排最大一天卖入
		}
		  return k;
	  }
	
	
	
	//从控制台获取行数
//	Scanner s = new Scanner(System.in);
//	int row = s.nextInt();
//	//根据行数定义好二维数组，由于每一行的元素个数不同，所以不定义每一行的个数
//	int[][] arr = new int[row][];
//	//遍历二维数组
//	for(int i = 0; i < row; i++){
//	    //初始化每一行的这个一维数组
//	    arr[i] = new int[i + 1];
//	    //遍历这个一维数组，添加元素    
//	    for(int j = 0; j <= i; j++){
//	        //每一列的开头和结尾元素为1，开头的时候，j=0，结尾的时候，j=i
//	        if(j == 0 || j == i){
//	            arr[i][j] = 1;
//	        } else {//每一个元素是它上一行的元素和斜对角元素之和
//	            arr[i][j] = arr[i -1][j] + arr[i - 1][j - 1];
//	        }
//	        System.out.print(arr[i][j] + "\t");
//	    }
//	    System.out.println();
//	}
	
}
