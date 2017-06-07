import java.util.*;
class ArrayPartition {
	public int arrayPairsum(int[] nums){
		this.sortNumsMerge(nums);
		int len = nums.length;
		int sum = 0;
		for(int i = 0 ; i < len ; i+=2){
			sum += nums[i];
		}
		return sum;
	}

	public int[] sortNumsSimple(int[] nums){
		Arrays.sort(nums);
		return nums;
	}
	public void sortNumsMerge(int[] nums){
		int[] tmp = {};
		if(nums == null){
			nums = tmp;
		}
		int len = nums.length;
		//length = 1
		if(len== 0){
			nums = tmp;	
		}else if(len == 1){
			;
		}else if(len == 2){
			if(nums[0] > nums[1]){
				int _tmp = nums[0];
				nums[0] = nums[1];
				nums[1] = _tmp;
			} 
		}else{
			mergeSortSub(nums, 0, len);	
		}

	}
	public void mergeSortSub(int[] nums, int start, int leng){
		if(leng == 1){
			return;
		}else{
			mergeSortSub(nums, start, leng/2);
			mergeSortSub(nums, start+leng/2, leng - leng/2);
			mergeSub(nums, start, start+leng/2,start+leng);
		}
	}
	public void mergeSub(int[] nums, int start, int middle, int end){
		for(int i = start; i < end; i++)
			System.out.print (nums[i]+"==");
		System.out.println("\n");
		int[] _nums = Arrays.copyOf(nums, end - start);
		int _start = start;
		int _middle =middle;
		boolean _start_flag = false;
		boolean _middle_flag = false;
		for(int i = 0 ; i < end - start; i++){
			if(!_start_flag  && ! _middle_flag){
				if(nums[_start] < nums[_middle]){
					_nums[i] = nums[_start++];
				}else{
					_nums[i] = nums[_middle++];
				}
			}else if(_start_flag && ! _middle_flag){ 	_nums[i] = nums[_middle++];
			}else if(!_start_flag && _middle_flag){		_nums[i] = nums[_start++];
			}else{						break;
			}
			if(_start == middle) _start_flag = true;
			if(_middle == end)  _middle_flag = true;
		}	
		for(int i = 0; i < end - start; i++){
			nums[start+i] = _nums[i]; 
		}
		for(int i = start; i < end; i++)
			System.out.print (nums[i]+" ");
		System.out.println("\n");
	}

	public static void main(String[] args){
		int[] tmp = {1,4,3,2};
		ArrayPartition ap = new ArrayPartition();
		ap.sortNumsMerge(tmp);
		for(int i = 0 ; i < tmp.length; i++)
			System.out.println(tmp[i]);
		System.out.println(ap.arrayPairsum(tmp));
	}
}
