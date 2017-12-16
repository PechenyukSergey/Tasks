package ua.sumdu.j2se.Pechenyuk.tasks;
/**
 * Class ArrayTaskList.
 *
 * @version 1.01 15 Oct 2
 * @author Sergey Pechenyuk
 */
public class ArrayTaskList {

    private final int INIT_SIZE = 4;
    private final float CUT_RATE = 2.0f;
    private int size = 0;

    private Task[] arrTask = new Task[INIT_SIZE];


    public int size() {
        return size;
    }

    public Task getTask(int index) {
        return arrTask[index];
    }

    public void add(Task task) {
        if(size == arrTask.length-1) {
            resize ((int) (arrTask.length * CUT_RATE));
        }
        arrTask[size] = task;
        size++;
    }

    public boolean remove(Task task) {
        boolean removed = false;
        int index = 0;                              //index of the task to be delete
        //search for the index of the element to be deleted
        for (int i = 0; i < size; i++) {
            if (arrTask[i].equals(task)) {        //looking for the first match
                index = i;
                removed = true;
                break;
            }
        }

        if (removed) {
            for (int i = index; i < size; i++) {
                arrTask[i] = arrTask[i + 1];
            }
            arrTask[size] = null;
            size--;

            if (arrTask.length > INIT_SIZE && size < arrTask.length / CUT_RATE) {
                resize ((int) ((arrTask.length / CUT_RATE) +1));
            }
        }
        return removed;
    }

    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList incoming = new ArrayTaskList();
        for (Task listItem : arrTask) {
            if (listItem != null && listItem.isActive() && listItem.nextTimeAfter(from) != -1
                    && listItem.nextTimeAfter(from) <= to) {
                incoming.add(listItem);
            }
        }
        return incoming;
    }


    private void resize(int newLength) {
        Task[] newArrTask = new Task[newLength];
        System.arraycopy(arrTask, 0, newArrTask, 0, size);
        //for (int i = 0; i < arrTask.length; i++) {
        //    newArrTask[i] = arrTask[i];
        //}
        arrTask = newArrTask;
    }
}

