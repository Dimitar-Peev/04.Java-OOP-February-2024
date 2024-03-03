package _01_Lab._03_SayHello;

public interface Person {
    String getName();

    default String sayHello(){
        return "Hello";
    }
}
