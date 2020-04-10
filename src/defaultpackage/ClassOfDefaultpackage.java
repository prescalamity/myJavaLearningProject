package defaultpackage;

public class ClassOfDefaultpackage 
{
    public  String Adress="I am belong to class of 'ClassOfDefaultpackage' in 'defaultpackage'.";

	public void COD() {
		System.out.println("I am a mathod of class in 'ClassOfDefaultpackage'.");
	}
}


class Student
{
	public Student()
	{
		StudentID=202001;
		Name="Lee";
		Age=99;
		SpecialSuject="English";
	}
	
	public Student(int i)
	{
		StudentID=202002;
		Name="Jack";
		Age=99;
		SpecialSuject="Chiness";
	}

	int StudentID;
	String Name;
	int Age;
	String SpecialSuject;
	                           
	public void Speak()
	{
		System.out.println(Name+":"+"Hallo,everyone!");
	}

}






