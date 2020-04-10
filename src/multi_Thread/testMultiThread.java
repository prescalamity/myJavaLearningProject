package multi_Thread;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程编程
 * @author LiuYuan
 */
public class testMultiThread {
	
	static Integer testMultiThread_age=new Integer(3);
	
	static ArrayList<Integer> verificationResult=new ArrayList<Integer>();
	
	static{System.out.println("--------------I am testMultiThread.--------------");}  //该代码块比main函数先执行
	
	public static void main(String[] args) {
		//System.out.println("Hello, this is multithreading!" + testMultiThread_age);
		//new testMultiThread();
		
		classA_Thread cATA=new classA_Thread("ThreadA");
		classA_Thread cATB=new classA_Thread("ThreadB");
		classA_Thread cATC=new classA_Thread("ThreadC");
		cATA.start();     // 启动线程，（运行run方法）
		cATB.start();
		cATC.start();
		
//		System.out.println("----------------------------");
//		Thread runnableThread=new Thread(new classB_Runnable());  // 实现Runnable接口的多线程创建方法，解决单继承的局限性
//		runnableThread.start();
//		
//		Thread runnableT=new Thread(new Runnable() {
//			@Override
//			public void run() {
//				System.out.println("匿名Runnable");
//			}
//		});
//		runnableT.start();

//		classC_synchronizedWaitNotifyAll cCSA=new classC_synchronizedWaitNotifyAll("ThreadA");
//		classD_synchronizedWaitNotifyAll cCSB=new classD_synchronizedWaitNotifyAll("ThreadB");
//		classD_synchronizedWaitNotifyAll cCSC=new classD_synchronizedWaitNotifyAll("ThreadC");
//		cCSA.start();     // 启动线程，（运行run方法）
//		cCSB.start();
//		cCSC.start();
//		while(true) {
//			if(!cCSA.isAlive() && !cCSB.isAlive()) {
//				boolean isTrue=true;
//				int i=0;
//				for(i=0; i<verificationResult.size(); i++) {
//					if((i+3)!=verificationResult.get(i)) {isTrue=false;break;}
//				}
//				
//				if(isTrue) {
//					System.out.println(isTrue);
//				}else {
//					System.out.println(isTrue+"--->"+verificationResult.get(i));
//				}
//				
//				break;
//			}
//		}
		
//		classE_Producer cEPA=new classE_Producer("A");
//		classF_Consumer cFCA=new classF_Consumer("B");
//		classF_Consumer cFCB=new classF_Consumer("C");
//		cEPA.start();     // 启动线程，（运行run方法）
//		cFCA.start();
//		cFCB.start();
		
		
		//System.exit(0);	
	}
	
}

/**
 *  类  继承 Thread 实现多线程
 * @author LiuYuan
 *
 */
class classA_Thread extends Thread{

	static Integer variable_age=new Integer(0);
	
	static int Agift=0,Bgift=0,Cgift=0;

	public classA_Thread(String name) {
		super(name);
	}

	@Override
	public  void run(){
		super.run();
		System.out.println("(" + Thread.currentThread().getName()+") I am multithreading by extending the thread.");

		while (true) {
			//只用了同步，没有用（等待/唤醒）
			// 在要访问同一个资源（variable_age）时，线程争夺同一个对象锁，且最好是在RAM ROM中存储的static变量对象，
			// 存储在数据存储区和堆 栈 不是一个存储区
			synchronized(testMultiThread.verificationResult) {    

				if(Thread.currentThread().getName()=="ThreadA" && variable_age%2==0) {    // A 线程要执行的代码
					System.out.println(Thread.currentThread().getName()+"----->" + variable_age);
					variable_age++;
					Agift++;
					if(Agift>29 && Thread.currentThread().getName()=="ThreadA") {
						System.out.println(Thread.currentThread().getName()+"----->end."+Agift);
						break;
					}
					
					if(Bgift==15 && Cgift==15) {
						variable_age++;
						
						
					}
				}
				
				if(Thread.currentThread().getName()=="ThreadB" && variable_age%2==1) {    // B 线程要执行的代码
					System.out.println(Thread.currentThread().getName()+"----->" + variable_age);
					variable_age++;
					Bgift++;
					if(Bgift>14 && Thread.currentThread().getName()=="ThreadB") {
						System.out.println(Thread.currentThread().getName()+"----->end."+Bgift);
						break;
					}
				}
				
				if(Thread.currentThread().getName()=="ThreadC" && variable_age%2==1) {    // C 线程要执行的代码
					System.out.println(Thread.currentThread().getName()+"----->" + variable_age);
					variable_age++;
					Cgift++;
					if(Cgift>14 && Thread.currentThread().getName()=="ThreadC") {
						System.out.println(Thread.currentThread().getName()+"----->end."+Cgift);
						break;
					}
				}
			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
		}
	}
	
}

/**
 * 通过实现Runnable接口   实现多线程
 * @author LiuYuan
 */
class classB_Runnable implements Runnable{

	int variable_age=0;
	
	{variable_age=testMultiThread.testMultiThread_age;}
	
	@Override
	public synchronized void run() {
		System.out.println("I am built by Runnable");
		
		while (true) {
			variable_age=testMultiThread.testMultiThread_age;
			if(variable_age%2!=0) {
				System.out.println("-------classB_Runnable.variable_age-----" + variable_age);
				testMultiThread.testMultiThread_age++;
			}
			
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			break;
		}
	}
	
}


class classC_synchronizedWaitNotifyAll extends Thread{

	int temp;
	
	public classC_synchronizedWaitNotifyAll(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		while(true) {
			synchronized(testMultiThread.verificationResult) {
	
				while(testMultiThread.testMultiThread_age%2==0){
					try {
						testMultiThread.verificationResult.wait();  //等待时，该线程停止运行并释放对象锁，被唤醒后继续判断条件
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				temp =testMultiThread.testMultiThread_age;
				System.out.println(Thread.currentThread().getName()+"---->"+temp);
				testMultiThread.verificationResult.add(temp);
				testMultiThread.testMultiThread_age++;

				testMultiThread.verificationResult.notifyAll();
				
			}
			if(testMultiThread.testMultiThread_age>60)break;
		}
	}
	
}

class classD_synchronizedWaitNotifyAll extends Thread{
	int temp;
	
	public classD_synchronizedWaitNotifyAll(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		while(true) {
			synchronized(testMultiThread.verificationResult) {
	
				while(testMultiThread.testMultiThread_age%2==1){
					try {
						testMultiThread.verificationResult.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				temp =testMultiThread.testMultiThread_age;
				System.out.println(Thread.currentThread().getName()+"---->"+temp);
				testMultiThread.verificationResult.add(temp);
				testMultiThread.testMultiThread_age++;
			
				testMultiThread.verificationResult.notifyAll();
				
			}
			if(testMultiThread.testMultiThread_age>60)break;
		}
	}

}

class classE_Producer extends Thread{
	public final static Lock psLock = new ReentrantLock();
	public final static Condition pLoakCon = psLock.newCondition();
	public final static Condition sLockCon = psLock.newCondition();
	
	static int productP=0;
	
	public classE_Producer(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		while (true) {
			
			psLock.lock();      // 线程获取锁对象(信号量)，并锁定和运行该线程的该代码块

			while (testMultiThread.verificationResult.size()>10 && productP<30) {
				try {
					System.out.println("仓库已满，生产者" + Thread.currentThread().getName() + "不可生产.");
					pLoakCon.await();                  // 线程进入等待池，并释放对象锁
				} catch (InterruptedException e) {
				}
			}
			if(productP>29){
				sLockCon.signal(); 
				psLock.unlock();  
				System.out.println("线程" + Thread.currentThread().getName() + "---->end");
				break;
			}

			System.out.println("生产者" + Thread.currentThread().getName() + "生产, 产品ID为" + productP);
			testMultiThread.verificationResult.add(productP);
			productP++;
			
			sLockCon.signal(); // 唤醒对面线程，但不会释放锁对象
			psLock.unlock();   // 线程释放锁对象
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
}

class classF_Consumer extends Thread{
	
	static int productS=0;
	
	public classF_Consumer(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		while (true) {
			
			classE_Producer.psLock.lock();     // 线程获取锁对象(信号量)，并锁定和运行该线程的该代码块

			while (testMultiThread.verificationResult.isEmpty() && productS<30) {
				try {
					System.out.println("仓库为空，消费者" + Thread.currentThread().getName() + "不可消费.");
					classE_Producer.sLockCon.await();                  // 线程进入等待池，并释放对象锁
				} catch (InterruptedException e) {
				}
			}
			if(productS>29) {
				classE_Producer.pLoakCon.signal(); 
				classE_Producer.psLock.unlock();   
				System.out.println("线程" + Thread.currentThread().getName() + "---->end");
				break;
			}
			
			System.out.println("消费者" + Thread.currentThread().getName() + "消费，产品ID为" + testMultiThread.verificationResult.remove(0));
			productS++;
			
			classE_Producer.pLoakCon.signal();  // 唤醒对面线程，但不会释放锁对象
			classE_Producer.psLock.unlock();    // 释放锁对象（通行证）
			
			try {
				Thread.sleep(100);       // 消费时间
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}


