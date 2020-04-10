package ThisIsSecondBAO;
import java.util.concurrent.*;

/**
 * 
 * @author 并行编程实现寻找线性表中的最大数
 *
 */
public class ParallelMax {
	public static void main(String[] args) {
		
		final int N=9000000;
		int[] list=new int[N];          //九百万的数组线性表
		
		for (int i = 0; i < list.length; i++) {
			list[i]=i;
		}
		
		long startTime=System.currentTimeMillis();
		
		System.out.println("The max number is "+max(list));
		
		long endTime=System.currentTimeMillis();
		System.out.println("The number of processors is "+Runtime.getRuntime().availableProcessors());
		System.out.println("This Time is "+(endTime-startTime)+" ms.");
	}
	
	public static int max(int[] list) {
		RecursiveTask<Integer> task=new MaxTask(list,0,list.length);
		ForkJoinPool pool=new ForkJoinPool();      // 创建线程池，用来执行上诉所有任务类创建的任务
		return pool.invoke(task);
	}
	
	/**
	 * @author 内部继承类，继承并行任务类；
	 * 
	 * @for 将任务分解成小的线程任务； 并合并局部解；
	 *
	 */
	private static class MaxTask extends RecursiveTask<Integer>{
		private final static int THRESHOLD=1000;
		private int[] list;
		private int low,high;
		
		public MaxTask(int[] list,int low,int high) {
			this.list=list;
			this.low=low;
			this.high=high;
		}
		
		@Override
		protected Integer compute() {
			if(high-low<THRESHOLD) {
				int max=list[0];
				for (int i = low; i < high; i++) 
					if(max<list[i])
						max=list[i];
				return new Integer(max);
			}else {
				int mid =(low+high)/2;
				RecursiveTask<Integer> left=new MaxTask(list,low,mid);
				RecursiveTask<Integer> right=new MaxTask(list, mid, high);
				left.fork();
				right.fork();
				
				// 等待左右两边的任务执行完成后，再返回该部分结果
				return new Integer(Math.max(left.join(), right.join()));  
			}
		}
	}
}
