package ua.sumdu.j2se.Pechenyuk.tasks;

import static ua.sumdu.j2se.Pechenyuk.tasks.LinkedTaskList.printTaskList;

public class Main {
    public static void main(String[] args) {

        Task one = new Task("Repeat left intersect IN 1", 0, 55, 13);
        Task two = new Task("Repeat left intersect IN 2", 0, 60, 30);
        Task thr = new Task("Repeat right intersect IN", 55, 100, 20);
        Task four = new Task("Simple bound IN", 60);
        Task five = new Task("Simple OUT", 10);
        //Task five = new Task("four", 1, 20, 1);

        one.setActive(true);
        two.setActive(true);
        thr.setActive(true);
        four.setActive(true);
        five.setActive(true);

        //ArrayTaskList temp = new ArrayTaskList();
        LinkedTaskList temp = new LinkedTaskList();
        //TaskList temp = new TaskList();

        temp.add(one);
        temp.add(two);
        temp.add(thr);
        temp.add(four);
        temp.add(five);
        printTaskList(temp);
        System.out.println();
        System.out.println();

        System.out.println(temp.getTask(2).getTitle());
        temp.remove(two);
        System.out.println(temp.getTask(2).getTitle());

        System.out.println();
        System.out.println();

        //temp.printTaskList(temp);
        printTaskList(temp);
        //temp = temp.incoming(50, 60);

    }
}
