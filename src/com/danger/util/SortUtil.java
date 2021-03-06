package com.danger.util;

import java.lang.reflect.Array;

import com.sun.accessibility.internal.resources.accessibility;

import javafx.scene.control.Tab;

/**
 * 8大排序算法简单实现
 * 交换排序 -- 冒泡排序
 * 		   -- 快速排序
 * 插入排序 -- 直接插入排序
 * 		   -- 希尔排序
 * 选择排序 -- 简单选择排序
 * 		   -- 堆排序
 * -- 并归排序
 * -- 基数排序
 * @author DangerYao
 *
 */
public class SortUtil {
	/**
	 * 冒泡排序
	 * @param t
	 */
	public static <T extends Comparable<T>> void bubbleSort(T[] t){
		if(null == t)
			return;
		if(t.length <= 0)
			return;
		
		for(int i = 0; i<t.length; i++){
			for(int j = i; j<t.length; j++){
				if(t[i].compareTo(t[j]) > 0){
					T temp = t[i];
					t[i] = t[j];
					t[j] = temp;
				}
			}
		}
	}
	
	/**
	 * 快速排序
	 * @param t
	 */
	public static <T extends Comparable<T>> void quickSort(T[] t){
		if(null == t)
			return;
		if(t.length <= 1)
			return;
		subQuickSort(t,0,t.length-1);
	}

	private static <T extends Comparable<T>> void subQuickSort(T[] t, int left, int right) {
		if(left < right){
			int p = position(t,left,right);
			subQuickSort(t, 0, p-1);
			subQuickSort(t, p+1, right);
		}
	}

	private static  <T extends Comparable<T>> int position(T[] t, int left, int right) {
		T temp = t[left];
		
		while(left < right){
			while(left < right && t[right].compareTo(temp) > 0)
				right --;
			if(left < right)
				t[left++] = t[right];
			while( left < right && t[left].compareTo(temp) < 0)
				left ++;
			if(left < right)
				t[right--] = t[left];
		}
		t[left]=temp;
		return left;
	}
	
	/**
	 * 直接插入排序
	 * @param t
	 */
	public static <T extends Comparable<T>> void insertSort(T[] t){
		if(null == t) //数组为空，直接返回
			return;
		if(t.length <= 1) //数组长度为1，为有序，直接返回
			return;
		for(int i = 1; i < t.length; i++){ //第1个认为自成序列，遍历从第二个开始
			T temp = t[i];
			int j ;
			
			for(j = i - 1; j >= 0 && t[j].compareTo(temp)>0; j--){ //从后向前遍历数组 ,如果遍历到的数比 temp 大，则这个数向后移动一位
					t[j+1] = t[j];
			}
			
			t[j+1] = temp; //将t[i]插入到对应的位置
		}
	}
	
	/**
	 * 希尔排序算法
	 * @param t
	 */
	public static <T extends Comparable<T>> void shellSort(T[] t){
		if(null == t) //数组为空，直接返回
			return;
		if(t.length <= 1) //数组长度为1，为有序，直接返回
			return;
		
		int d = t.length; //增量 d
		
		while(d > 1){
			d = d >> 1;
			
			for(int i = 0; i < d ; i++){
				for(int j = i+d; j < t.length; j+=d){
					T temp = t[j];
					int k;
					
					for(k = j-d; k >= i && t[k].compareTo(temp)>0; k -= d){
						t[k+d] = t[k];
					}
					
					t[k+d] = temp;
				}
			}
		}
	}
	
	/**
	 * 简单选择排序
	 * @param t
	 */
	public static <T extends Comparable<T>> void simpleSelectSort(T[] t){
		
		if(null == t) //数组为空，直接返回
			return;
		if(t.length <= 1) //数组长度为1，为有序，直接返回
			return;
		
		for(int i=0; i<t.length; i++){
			int position = i; //用于记录最小值的位置
			for(int j=i+1; j<t.length; j++){
				if(t[position].compareTo(t[j]) > 0){
					position = j;
				}
			}
			
			//把后面序列最小值放到最前面
			T temp = t[i];
			t[i] = t[position];
			t[position] = temp;
		}
		
	}
	
	
	/**
	 * 堆排序
	 * @param t
	 */
	
	public static <T extends Comparable<T>> void heapSort(T[] t){
		
		for(int i=t.length-1; i > 0; i--){
			creatMaxHeap(t,i); //构建大顶堆
			
			//交换顶元素和无序堆得最后一个节点
			T temp=t[0];
			t[0] = t[i];
			t[i] = temp;
			
		}
	}

	private static <T extends Comparable<T>> void creatMaxHeap(T[] t, int length) {
		int max; 
		/**
		 * 从最后一个非叶节点开始遍历
		 * 1、如果左右子树都存在，比较父节点，左右子节点三者大小，把最大的放在父节点处（如果父节点本身就最大，不做任何处理）；
		 * 2、如果只存在左子树，则比较父节点和左节点，把大的放在父节点处（如果父节点本身就最大，不做任何处理）；
		 */
		for(int i= (length >> 1); i>=0; i--){
			int left = i<<1;
			int right = left + 1;
			if(left <= (length-1)){
				max = (t[left].compareTo(t[right]) > 0) ? left : right;
				if(t[max].compareTo(t[i]) > 0){
					T temp = t[max];
					t[max] = t[i];
					t[i] = temp;
				}
			}
			else if(left<=(length-1)){
				if(t[i<<1].compareTo(t[i]) > 0){
					T temp = t[left];
					t[left] = t[i];
					t[i] = temp;
				}
			}
		}
	}
	
	
	public static <T extends Comparable<T>> void mergeSort(T[] t){
		subMergeSort(t,0,t.length-1);
	}

	private static <T extends Comparable<T>> void subMergeSort(T[] t, int left, int right) {
		
		int mid = (right+left) >> 1;
		if(left < right){
			subMergeSort(t, left, mid);    //左边
			subMergeSort(t, mid+1, right); //右边
			merge(t,left,mid,right);       //合并
		}
	}

	/**
	 * 合并两个数组
	 * @param t
	 * @param left
	 * @param mid
	 * @param right
	 */
	private static <T extends Comparable<T>> void merge(T[] t, int left, int mid, int right) {
		@SuppressWarnings("unchecked")
		T[] temp = (T[])Array.newInstance(t.getClass().getComponentType(),right-left+1); //利用反射创建泛型数组
		
		int pLefe = left;  //左边数组指针
		int pRight = mid+1; //右边数组指正
		int k = 0; //合并数组指针
		
		// 把较小的数先移到新数组中
		while(pLefe<=mid && pRight<=right){
			temp[k++] = (t[pLefe].compareTo(t[pRight]) < 0) ? t[pLefe++] : t[pRight++];
		}
		
		// 把左边剩余的数移入数组
		while(pLefe <= mid){
			temp[k++] = t[pLefe++];
		}
		
		// 把右边边剩余的数移入数组
		while(pRight <= right){
			temp[k++] = t[pRight++];
		}
		
		for(int i=0; i<temp.length; i++){
			t[i+left] = temp[i];
		}
	}
}
