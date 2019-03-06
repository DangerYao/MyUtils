package com.danger.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 余弦相似度
 * @author DangerYao
 *
 */
public class CosSimilarity {

	// 存储向量值 , 相似度不匹配顺序
	Map<Character, int[]> vectorMap = new HashMap<>();
	
	// 构建向量表
	public CosSimilarity(String first,String second) {
		// 遍历first
		for (Character ch : first.toCharArray()) {
			if(vectorMap.containsKey(ch)) {
				// 这儿是引用类型？
				vectorMap.get(ch)[0]++;
			} else {
				int[]  temp = new int[2];
				temp[0] = 1;
				temp[1] = 0;
				vectorMap.put(ch, temp);
			}
		}
		// 遍历second
		for (Character ch : second.toCharArray()) {
			if(vectorMap.containsKey(ch)) {
				vectorMap.get(ch)[1]++;
			} else {
				int[]  temp = new int[2];
				temp[0] = 0;
				temp[1] = 1;
				vectorMap.put(ch, temp);
			}
		}
	}
	
	/**
	 * 计算相似度
	 * @return
	 */
	public double sim() {
		double squareSum = squareSum();
		if(squareSum != 0) {
			return pointMulti()/Math.sqrt(squareSum);
		}
		return 0;
	}
	
	/**
	 * 计算向量点积
	 * @return
	 */
	private double pointMulti() {
		double result = 0d;
		Set<Map.Entry<Character, int[]>> entrys = vectorMap.entrySet();
		for (Map.Entry<Character, int[]> entry : entrys) {
			int[] vector = entry.getValue();
			result += vector[0] * vector[1];
		}
		return result;
	}
	
	/**
	 * 计算两个向量的平方和
	 * @return
	 */
	private double squareSum() {
		double firstVecSquareSum = 0d;
		double secondVecSquareSum = 0d;
		Set<Map.Entry<Character, int[]>> entrys = vectorMap.entrySet();
		for (Map.Entry<Character, int[]> entry : entrys) {
			int[] vector = entry.getValue();
			firstVecSquareSum += vector[0] * vector[0];
			secondVecSquareSum += vector[1] * vector[1];
		}
		return firstVecSquareSum * secondVecSquareSum;
	}
}
