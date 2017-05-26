package com.danger.test;

import com.danger.util.SortUtil;

public class SortUtilTest {

	public static void main(String[] args) {
		Integer[] t={5,6,8,7,9,2,1,4,3,0};
		
//		SortUtil.bubbleSort(t); //测试冒泡排序
//		SortUtil.quickSort(t); //测试快速排序
//		SortUtil.insertSort(t); //测试直接插入排序
//		SortUtil.shellSort(t); //测试希尔排序
//		SortUtil.simpleSelectSort(t); //测试简单选择排序
//		SortUtil.heapSort(t); //测试堆排序
		SortUtil.mergeSort(t); //测试并归排序
		
		for (Integer i : t) {
			System.out.println(i);
		}
	}

}
