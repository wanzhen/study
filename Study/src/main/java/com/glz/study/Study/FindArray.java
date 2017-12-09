package com.glz.study.Study;

public class FindArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       find ( new int[]{1,2,3,4,5,6},6) ;
	}
	
	
	public static int find(int[] array,int val){
		
		
		int len = array.length;
		int start = 0;
		int upIndex = len -1;
		if(upIndex < 0){
			return -1;
		}
		 
		int midIndex = 0;
		
		while(true){
			
			midIndex = (start + upIndex )/2;
			
			if (array[midIndex] == val) {
				return midIndex;
			}
			
			if (midIndex == start) { 
			  if( array[upIndex] != val){
				return -1;
			 }else{
				  return upIndex;
			 }
			}
			
			if(array[midIndex] < val){
				start = midIndex;
			}
			
			if (array[midIndex] > val) {
				upIndex = midIndex;
			}
		 
		}
	}
}
