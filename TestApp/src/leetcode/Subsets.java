/**
 * 
 */
package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sudhansu
 *
 */
public class Subsets {
	 public List<List<Integer>> subsets(int[] nums) {
	        int n = nums.length;
	        int x = (int)Math.pow(2,n)-1;
	        
	        List<List<Integer>> list = new ArrayList<>();
	        list.add(new ArrayList<Integer>());
	        for(int j=1;j<=x;j++){
	            String s = Integer.toBinaryString(j);
	            List<Integer> l = new ArrayList<Integer>();
	            int sl = s.length()-1;
	            for(int i=sl;i>=0;i--){
	                if(s.charAt(i)!='0')
	                    l.add(Character.getNumericValue(s.charAt(i))*nums[sl-i]);
	            }
	            list.add(l);
	        }
	        return list;
	        
	    }
}
