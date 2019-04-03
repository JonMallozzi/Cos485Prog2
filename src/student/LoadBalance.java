package student;
/* 
 * This LoadBalance class is meant to contain your algorithm.
 * You should implement the static method:
 *   loadBalance - which finds the best partitioning of the tasks
 *                 among the processors
 */

public class LoadBalance
{
    // Simple example routine that just sets evenly spaced partitions
    //
    // inputs:
    //   the number of processors
    //   an array of task values
    // outputs:
    //   fill the the partition array with the sums of your partitions (from left to right)
    //   return the amount of work in the largest partition
    //
    // For example if we had 3 processors and these tasks: 1 2 1 2 3 4 1 3 2
    // and if we partitioned with 3 tasks in each partition
    // The partition sums would be: 4, 9, 6  and the return value would be 9
    //
    // In this simple code some tasks will be lost if the number of tasks is not evenly
    // divisible by the number of processors.
    public static int loadBalance(int procs, int[] tasks, int[] partitions)	{
    	
    	//workload of the largest partition 
    	int max = 0;
    	
    	//total workload of all the tasks added together
    	int total = 0;
    	
    	//stores our partition value
    	int partTotal = 0;
    	
    	//keeps track of what partition to put partTotal into
    	int partNum = 0;
    	
    	for (int cnt = 0; cnt < tasks.length ; cnt++) {
    		total += tasks[cnt];
    	}
    	
    	//Average workload for each process should be around
    	int avg = total / procs;
       
    	for (int cnt = 0; cnt < tasks.length ; cnt++) {

    		System.out.println("partNum: " + tasks[cnt]);	
    	if (avg > partTotal && ((partTotal + tasks[cnt]) < avg)) {
    		
    		partTotal += tasks[cnt];
    			
    		
    	}else if ((avg - partTotal) < ((partTotal + tasks[cnt]) - avg) && partTotal < avg) {
    		
    		
    		//System.out.println("currentTask: " + tasks[cnt]);
    		//System.out.println("partNum: " + partNum);
    		
    		//adding the total to the partition 
    		partitions[partNum] = partTotal;
    		
    		//checks for the max
        	if (partTotal > max) {
        		max = partTotal;
        	}
    		
    		//resets the value for the next partition
    		partNum++;
    		partTotal = tasks[cnt];
    		
    	}else{
    		
    		partTotal += tasks[cnt];
    		
    		//System.out.println("else partTotal: " + partTotal);
    		//System.out.println("else cnt: " + cnt);
    		
    		//adding the total to the partition 
    		partitions[partNum] = partTotal;
    		
    		//checks for the max
        	if (partTotal > max) {
        		max = partTotal;
        	}
    		
    		//resets the value for the next partition
    		partNum++;
    		partTotal = 0;
    		
    	}
    	
    	
    	
    }  	
    	//adds the remaining processes to the last partition if there is extra
    	if(partNum == procs && partTotal != 0) {
    		partitions[partNum - 1] += partTotal;
    		if (partitions[partNum - 1] > max) {
        		max = partitions[partNum - 1];
        	}
    		partTotal = 0;
    	}
    	
    	//adds the last partition value and checks if its max 
    	if(partNum < procs) {
    	partitions[partNum] = partTotal;
    	}

    	if (partTotal > max) {
    		max = partTotal;
    	}
    	
    	
        return max;
    }
}
