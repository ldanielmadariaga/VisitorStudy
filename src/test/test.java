import org.junit.Assert;
import org.junit.Test;

public class test {

	@Test
	public void manPetsDog() {
		Person person = new Man();
		Animal animal = new Dog();
		Assert.assertEquals("Man here, petting dog: bow, wow", person.pet(animal));
	}

	@Test
	public void womanPetsCat() {
		Person person = new Woman();
		Animal animal = new Cat();
		Assert.assertEquals("Woman here, petting cat: meow", person.pet(animal));
	}

	public interface Person {

		public String pet(Animal a);
	}

	public interface Animal {

		public String getName();

		public String makeSound();
	}

	public class Man implements Person {

		public String pet(Animal animal) {
			return "Man here, petting " + animal.getName() + ": " + animal.makeSound();
		}
	}

	public class Woman implements Person {

		public String pet(Animal animal) {
			return "Woman here, petting " + animal.getName() + ": " + animal.makeSound();
		}
	}

	public class Dog implements Animal {

		@Override
		public String getName() {
			return "dog";
		}

		@Override
		public String makeSound() {
			return "bow, wow";
		}
	}

	public class Cat implements Animal {

		@Override
		public String getName() {
			return "cat";
		}

		@Override
		public String makeSound() {
			return "meow";
		}
	}
}
