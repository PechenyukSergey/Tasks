package ua.sumdu.j2se.Pechenyuk.tasks;


public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, CloneNotSupportedException {

        Task one = new Task("Repeat left intersect IN 1", 0, 55, 13);
        Task two = new Task("Repeat left intersect IN 2", 0, 60, 30);
        Task thr = new Task("Repeat right intersect IN 3", 55, 100, 20);
        Task four = new Task("Simple bound IN 4", 60);
        Task five = new Task("Simple OUT 5", 10);
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

        //temp.printTaskList();


        //temp.remove(one);
        //temp.remove(two);
        //temp.remove(thr);
        //temp.remove(four);
        //temp.remove(five);



        LinkedTaskList temp2 = temp.clone();
        //System.out.println(temp.toString());
        //System.out.println(temp2.toString());

        System.out.println();

        System.out.println(temp.equals(temp2));
        System.out.println(temp2.equals(temp));

        temp2.printTaskList();
        //System.out.println(temp2.size());
        temp2.remove(one);
        //System.out.println("temp - " + temp.size() +"\t\t temp2 -" + temp2.size());
        temp2.remove(two);
        //System.out.println("temp - " + temp.size() +"\t\t temp2 -" + temp2.size());
        //System.out.println();
        System.out.println("after remove temp2");
        temp2.printTaskList();


        System.out.println();
        System.out.println("after remove temp");
        temp.printTaskList();
        System.out.println();

        System.out.println(temp.toString());
        System.out.println(temp2.toString());
        System.out.println(temp.hashCode());
        System.out.println(temp2.hashCode());

/*
        System.out.println();
        System.out.println();

        System.out.println(temp.getTask(2).getTitle());

        System.out.println(temp.getTask(2).getTitle());

        System.out.println();
        System.out.println();
*/
        //temp.printTaskList(temp);
        //printTaskList(temp);
       // LinkedTaskList qqq = new LinkedTaskList();
        //        qqq = (LinkedTaskList) temp.incoming(50, 60);
        //printTaskList(qqq);
        //System.out.println(qqq.first().current.next.next.task.getTitle());

    }
}
