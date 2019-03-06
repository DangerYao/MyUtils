package com.danger.test;

import com.danger.util.CosSimilarity;

public class CosSimilarityTest {

	public static void main(String[] args) {

		String first = "我是一个帅哥";
		String second = "帅哥是我";
		
		CosSimilarity cs =  new CosSimilarity(first, second);
		
		System.out.println(cs.sim());
	}

}
