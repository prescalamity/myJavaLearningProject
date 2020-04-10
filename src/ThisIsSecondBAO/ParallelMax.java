package ThisIsSecondBAO;
import java.util.concurrent.*;

/**
 * 
 * @author ���б��ʵ��Ѱ�����Ա��е������
 *
 */
public class ParallelMax {
	public static void main(String[] args) {
		
		final int N=9000000;
		int[] list=new int[N];          //�Ű�����������Ա�
		
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
		ForkJoinPool pool=new ForkJoinPool();      // �����̳߳أ�����ִ���������������ഴ��������
		return pool.invoke(task);
	}
	
	/**
	 * @author �ڲ��̳��࣬�̳в��������ࣻ
	 * 
	 * @for ������ֽ��С���߳����� ���ϲ��ֲ��⣻
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
				
				// �ȴ��������ߵ�����ִ����ɺ��ٷ��ظò��ֽ��
				return new Integer(Math.max(left.join(), right.join()));  
			}
		}
	}
}
