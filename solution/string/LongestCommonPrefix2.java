package string;

/** 
 * @author Apollo4634 
 * @creation 2019/01/05 17:01
 * 
 * No.14 Longest Common Prefix
 * 
 * 解题思路：
 * 可以认为输入的数组strs是一个二维字符矩阵， 一行代表一个字符串:
 * s1: s1c1 s1c2 s1c3 s1c4 ...
 * s2: s2c1 s2c2 s2c3 ...
 * ...
 * sn: snc1 snc2 snc3 snc4 snc5 ...
 * 
 * 那么，就存在两种基本遍历方式：
 * 第一，横向为主对比两个字符串，从上到下两两比较，
 * 即，s1先和s2比较找到最长前缀p，然后p和s3得到更新后的p，依次类推；
 * 第二，纵向比较为主，然后横向推进，直到最后一个相同的字符
 * 即，先比较第一列s1c1 s2c1 ... snc1的值，若相同则比较第二列，依次类推；
 * 
 * 当然横向比较不一定是从左到右，纵向不一定是从上到下，可以调整。
 * 这里以第一种方式为基础
 * 
 * 注：这里其实可以用String类的boolean startsWith​(String prefix)方法
 */

public class LongestCommonPrefix2 {

	//Solution
	public String longestCommonPrefix(String[] strs) {
		if(strs.length==0) return "";
		if(strs.length==1) return strs[0];

		int prefixLength = 0;
		int searchRange = strs[0].length();
		for (int i_s = 1; i_s < strs.length; i_s++) {
			if(searchRange>strs[i_s].length()) searchRange = strs[i_s].length();

			prefixLength = 0;
			for (int i_c = 0; i_c < searchRange; i_c++) {
				if(strs[i_s-1].charAt(i_c)!=strs[i_s].charAt(i_c)) break;
				prefixLength++;
			}
			if(prefixLength==0) return "";
			if(searchRange>prefixLength) searchRange = prefixLength;
		}
		return strs[0].substring(0, prefixLength);
	}

	//Solution2
	public String longestCommonPrefix2(String[] strs) {
		if(strs.length==0) return "";
		if(strs.length==1) return strs[0];

		int minStrLen = strs[0].length();
		for (int i = 1; i < strs.length; i++) {
			if(minStrLen>strs[i].length()) minStrLen = strs[i].length();
		}
		
		int prefixLength = 0;
		int searchRange = minStrLen;
		for (int i_s = 1; i_s < strs.length; i_s++) {
			prefixLength = 0;
			for (int i_c = 0; i_c < searchRange; i_c++) {
				if(strs[i_s-1].charAt(i_c)!=strs[i_s].charAt(i_c)) break;
				prefixLength++;
			}
			if(prefixLength==0) return "";
			if(searchRange>prefixLength) searchRange = prefixLength;
		}
		return strs[0].substring(0, prefixLength);
	}
	
	
	public static void main(String[] args) {
		LongestCommonPrefix2 obj = new LongestCommonPrefix2();
		String[] strs = {"flower","flow","flight"};

		long t1 = System.nanoTime();
		String prefix = obj.longestCommonPrefix2(strs);
		long t2 = System.nanoTime();

		System.out.println("output: "+prefix);
		System.out.println("Rumtime: "+(t2-t1)/1.0E6+" ms");
	}
}
