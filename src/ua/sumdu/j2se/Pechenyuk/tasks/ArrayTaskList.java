package ua.sumdu.j2se.Pechenyuk.tasks;
/**
 * Class ArrayTaskList.
 *
 * @version 1.01 15 Oct 2
 * @author Sergey Pechenyuk
 */
public class ArrayTaskList extends TaskList {

    //private final int INIT_SIZE = 16;
    //private final float CUT_RATE = 1.2f;
    //private int pointer = 0;

    //private Task[] arrTask = new Task[INIT_SIZE];

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

    TaskList temp = new ArrayTaskList();
    temp.add(one);
    temp.add(two);
    temp.add(thr);
    temp.add(four);
    temp.add(five);

    temp = temp.incoming(50, 60);

    for (int i = 0; i < temp.size(); i++) {
        if (temp.getTask(i) != null) {
            System.out.println("title -" + temp.getTask(i).getTitle() + " - " + i);
        } else {
            break;
        }
    }
}

    //Возвращает элемент списка по индексу.
    public Task get(int index) {
        return (Task) arrTask[index];
    }


    public void add(Task task) {
        if(pointer == arrTask.length-1) {
            resize ((int) (arrTask.length / CUT_RATE));
        }
        arrTask[pointer] = task;
        pointer++;
    }


    public boolean remove(Task task) {
        boolean removed = false;
        int index = 0;                              //index of the task to be delete
        //search for the index of the element to be deleted
        for (int i = 0; i < pointer; i++) {
            if (arrTask[i].equals(task)) {        //looking for the first match
                index = i;
                removed = true;
                break;
            }
        }

        if (removed) {
            for (int i = index; i < pointer; i++) {
                arrTask[i] = arrTask[i + 1];
            }
            arrTask[pointer] = null;
            pointer--;

            if (arrTask.length > INIT_SIZE && pointer < arrTask.length / CUT_RATE) {
                Task[] newArrTask = new Task[(int) (arrTask.length / CUT_RATE)];
                System.arraycopy(arrTask, 0, newArrTask, 0, pointer);
                arrTask = newArrTask;   // если элементов в CUT_RATE раз меньше чем
                // длина массива, то уменьшу
            }
        }
        return removed;
    }
}
