package testcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TestApprove {

	public static void main(String[] args) {
		List<Emp> empDB = new ArrayList<>();
		Emp  empA = new Emp();
		empA.setId("abc1");
		empA.setName("小王");
		empA.setCount(10);
		Emp  empB = new Emp();
		empB.setId("abc2");
		empB.setName("小李");
		empB.setCount(2);
		Emp  empG = new Emp();
		empG.setId("abc6");
		empG.setName("小赵");
		empG.setCount(4);
		empDB.add(empA);
		empDB.add(empB);
		empDB.add(empG);
		/*************************/
		List<Emp> empPB = new ArrayList<>();
		Emp  empC = new Emp();
		empC.setId("abc1");
		empC.setName("小王");
		empC.setCount(0);
		Emp  empD = new Emp();
		empD.setId("abc3");
		empD.setName("小虎");
		empD.setCount(0);
		Emp  empF = new Emp();
		empF.setId("abc6");
		empF.setName("小赵");
		empF.setCount(0);
		empPB.add(empC);
		empPB.add(empD);
		empPB.add(empF);
		/***********************/
		
		List<Emp> orderEmp = new ArrayList<>();
		List<Emp> orderEmpnEW = new ArrayList<>();
		
		for (Emp emp : empPB) {
			Optional<Emp> optional = empDB.stream().filter(w -> w.getId().equals(emp.getId())).findFirst();
			if (optional.isPresent()) {
				orderEmp.add(optional.get());
			}else {
				orderEmp.add(emp);
			}
		}
		orderEmp = orderEmp.stream().sorted(Comparator.comparingDouble(Emp::getCount).reversed()).collect(Collectors.toList());
	}
}


 class Subject {
 
    String name;
    Double grade;
 
    public Subject(String name, Double grade) {
        this.name = name;
        this.grade = grade;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public Double getGrade() {
        return grade;
    }
 
    public void setGrade(Double grade) {
        this.grade = grade;
    }

	@Override
	public String toString() {
		return "Subject [name=" + name + ", grade=" + grade + "]";
	}
    
    
}

class Emp{
	private String id;
	private String Name;
	private int count;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "Emp [id=" + id + ", Name=" + Name + ", count=" + count + "]";
	}
	
	public void setEmp(Emp soure) {
		this.setId(soure.getId());
		this.setName(soure.getName());
		this.setCount(soure.getCount());
	}
	
	public Emp() {}
	
	public Emp(String id, String name, int count) {
		super();
		this.id = id;
		Name = name;
		this.count = count;
	}
}

