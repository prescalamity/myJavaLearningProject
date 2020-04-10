package defaultpackage;

public class ClassOfDefaultpackage1 
{
	int Imint;
	
	public ClassOfDefaultpackage1(int i)  //The function is a constructor of the class by myself.
	{
		Imint=i;
	}
//	public void finalize(){}
	
	public void PointResult()
	{
		System.out.println("My name is 'ClassOfDefaultpackage1'.");
	}
	
	public void buildStudent()
	{
		CollageStudent stu=new CollageStudent();
	    stu.Speak();
	}
}

class CollageStudent extends Student
{
	public CollageStudent()
	{
		super(0);
	}
	private String Subject;
	public void setSubject(String str)
	{
		Subject=str;
	}
	public String getSubject()
	{
		return Subject;
	}
	
}

