public class Solution{
	public class heightHeap{
		int[] heights = new int[20000];
		int size = 0;
		public void add(int height){
			heights[size++] = height;
			_float(size-1);
		}
		public int getMin(){
			int tmp = heights[0];
			heights[0] = heights[--size];
			sink(0);
			return tmp;
		}	
		public void _float(int index){
			if(index == 0) return;
			if(heights[index] < heights[(index+1)/2-1]){
				int tmp = heights[index];
				heights[index] = heights[(index+1)/2-1];
				heights[(index+1)/2-1] = tmp;
				_float((index+1)/2-1);
			}
		}
		public void sink(int index){
			if((index+1)*2 <= size){
				if(heights[(index+1)*2-1] < heights[index] && heights[(index+1)*2] > heights[(index+1)*2-1]){
					int tmp = heights[index];
					heights[index] = heights[(index+1)*2-1];
					heights[index] = tmp;
					sink((index+1)*2-1);
				}else if(heights[(index+1)*2] < heights[index] && heights[(index+1)*2] < heights[(index+1)*2-1]){
					int tmp = heights[index];
					heights[index]= heights[(index+1)*2];
					heights[(index+1)*2] = tmp;
					sink((index+1)*2);
				}else{
					;
				}
			}else if((index+1)*2-1 <= size){
				if(heights[(index+1)*2-1] < heights[index]){
					int tmp = heights[index];
					heights[index] = heights[(index+1)*2-1];
					heights[(index+1)*2-1] = tmp;
					sink((index+1)*2-1);
				}
			}else{
				;
			}
		}

	}

	heightHeap hh = new heightHeap();
	public int trapRainWater(int[][] heightMap){
		
		int max = 0;
		for(int i = 0; i < heightMap.length; i++){
			for(int j=0; j< heightMap[0].length; j++){
				hh.add(heightMap[i][j]);
				max = (heightMap[i][j] > max ? heightMap[i][j] : max);
			}
		}
		int total =0;
		int[][] tmp;
		for(int i =1; i <= max;i++){
			tmp = createLayer(heightMap, i);
			countPerLayer(tmp);
			int t = countFill(tmp);
			int next= hh.getMin();
			t *= (next-i);
			i = next-1;
			if(t == 0) return total;
			total += t;
		
		}
		return total;
	}
	public int[][] createLayer(int[][] heightMap, int layer){
		int[][] tmp = new int[heightMap.length][];
		int len = heightMap[0].length;
		for(int i=0; i< heightMap.length; i++){
			tmp[i] = new int[len];
			for(int j=0; j < heightMap[0].length; j++){
				tmp[i][j] = (heightMap[i][j] >= layer ? 1 : 0);
			}
		}
		return tmp;
	}
	public int countFill(int[][] pool){
		int total = 0;
		for(int i=0; i< pool.length; i++){
			for(int j=0; j< pool[0].length; j++){
				total += (pool[i][j] == 0 ? 1 : 0);
			}
		}
		return total;
	}
	//1 means block
	public void countPerLayer(int[][] pool){
		int len = pool.length;
		int len_r = pool[0].length;
		for(int i=0; i< pool[0].length; i++){
			if(pool[0][i] != 1){
				pool[0][i] = -1;
				propogatePerLayer(pool, 0, i);
			}
			if(pool[len-1][i] != 1){
				pool[len-1][i] = -1;
				propogatePerLayer(pool, len-1, i);
			}
		}
		for(int i=0; i<pool.length; i++){
			if(pool[i][0] != 1){
				pool[i][0] = -1;
				propogatePerLayer(pool, i, 0);
			}
			if(pool[i][len_r-1] != 1){
				pool[i][len_r-1] = -1;
				propogatePerLayer(pool, i, len_r-1);
			}
		}
	}
	public void propogatePerLayer(int[][] pool, int col, int row){
		if(col != 0 ){
			if(pool[col-1][row] == 0 ){
				pool[col-1][row] = -1;
				propogatePerLayer(pool, col-1, row);
			}
		}
		if(col != pool.length-1){
			if(pool[col+1][row] == 0){
				pool[col+1][row] = -1;
				propogatePerLayer(pool,col+1, row);
			}
		}
		if(row != 0){
			if(pool[col][row -1] == 0){
				pool[col][row-1] = -1;
				propogatePerLayer(pool,col, row-1);
			}
		}
		if(row != pool[0].length -1){
			if(pool[col][row+1] == 0){
				pool[col][row+1] = -1;
				propogatePerLayer(pool, col, row+1);
			}
		}
	}
	public static void main(String[] args){
		int[][] input = {
			{1,4,3,1,3,2},
			{3,2,1,3,2,4},
			{2,3,3,2,3,1}};
		System.out.println((new Solution()).trapRainWater(input));
	}
}
