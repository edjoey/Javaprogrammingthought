package testcode.holding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class MyPet {
	

	public MyPet() {}

	public String toString() {
		Random random = new Random();
		int ranx =  random.nextInt(1000);
		return "FG"+ranx;
	}
	
	public static List<MyPet> arrayList(int number) {
		List<MyPet> petsa = new ArrayList<>();
		for (int i = 0; i < number; i++) {
			Random random = new Random();
			int ranx = random.nextInt(1);
	        String sex = ranx == 0?"男":"女";
	        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
	        MyPet pet = new MyPet();
	        pet.setName("测试ob" + i);
	        pet.setAge(i);
	        pet.setSex(sex);
	        pet.setId(uuid);
	        petsa.add(pet);
		}

		return petsa;
	}
	
	public static MyPet randomPet() {
		MyPet pet = new MyPet();
		Random random = new Random();
		int ranx = random.nextInt(1);
        String sex = ranx == 0?"男":"女";
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        pet.setName("测试ob"+uuid.substring(0, 2));
        pet.setAge(23);
        pet.setSex(sex);
        pet.setId(uuid);
		return pet;
	}
	
	
	private String id;
	
	private String name;
	
	private String sex;
	
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}
