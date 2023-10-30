package ferrari_chris.serie05.esercizio01;

public class Example {
    @Extract
    public int theAnswer = 42;

    @Extract(name = "anotherName")
    private String hello = "world";

    @Extract(name = "enabled")
    private boolean status;

    private int anotherAnswer;
}
