package ua.sumdu.j2se.Pechenyuk.tasks;
/**
 * Class ArrayTaskList.
 *
 * @version 1.01 15 Oct 2
 * @author Sergey Pechenyuk
 */
public class ArrayTaskList {

    private Task[] arrTask = new Task[0];

    
    public static void main(String[] args) {

        Task one = new Task("Repeat left intersect IN 1", 0, 55, 13);
        Task two = new Task("Repeat left intersect IN 2", 0, 60, 30);
        Task thr = new Task("Repeat right intersect IN", 55, 100, 20);
        Task four = new Task("Simple bound IN", 60);
        Task five = new Task("four", 7, 10, 1);
        one.active = true;
        two.active = true;
        thr.active = true;
        four.active = true;
        five.active = true;
        ArrayTaskList temp = new ArrayTaskList();
        temp.add(one);
        temp.add(two);
        temp.add(thr);
        temp.add(four);
        temp.add(five);
        temp = temp.incoming(50, 60);

        for (int i = 0; i < temp.size(); i++) {
            if (temp.getTask(i) != null) {
                System.out.println("title -" + temp.getTask(i).getTitle() + " - " + i);
            }
        }
        int n = (6^2)+(6|2);
        System.out.println(n);

    }
    

    public void add(Task task) {
        if (task.equals(null)) {
            throw new NullPointerException("task is not be null");
        }
        Task[] newArrTask = new Task[arrTask.length + 1];
        for (int i = 0; i < arrTask.length; i++) {    //copy array
            newArrTask[i] = arrTask[i];
        }
 
        newArrTask[arrTask.length] = task;          //add new task
        arrTask = newArrTask;
    }

    public boolean remove(Task task) {
        boolean removed = false;
        //search for the index of the element to be deleted
        int j = 0;                                    //index of the task to be delete
        for (int i = 0; i < arrTask.length; i++) {
            if (!removed) {                          //not yet deleted
                if (arrTask[i].equals(task)) {        //looking for the first match
                    j = i;                            
                    removed = true;                 //will be deleted
                }
            }
        }
        //deleted of the element
        int k = 0;
        if (removed) {
            Task[] newArrTask = new Task[arrTask.length - 1];
            for (int i = 0; i < arrTask.length; i++) {
                if (i != j) {
                    newArrTask[k] = arrTask[i];
                    k++;
                }
            }
            arrTask = newArrTask;
        }
        return removed;
    }

    public int size() {
        return arrTask.length;
    }

    public Task getTask(int index) {
        return arrTask[index];
    }


    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList incomArrTaskList = new ArrayTaskList();

        for (int i = 0; i < arrTask.length; i++) {
            if (arrTask[i].active) {
                if (arrTask[i].repeated) {                          //repeated tasks
                    if (arrTask[i].nextTimeAfter(from) != -1) {     //time after from is not absent
                        if (arrTask[i].nextTimeAfter(from) <=  to) {
                            incomArrTaskList.add(arrTask[i]);
                        }
                    }
                } else {                                                  //not repeated tasks
                    if ((arrTask[i].time > from) && (arrTask[i].time <= to)) {
                        incomArrTaskList.add(arrTask[i]);
                    }

                }
            }
        }
        return incomArrTaskList;
    }
}
