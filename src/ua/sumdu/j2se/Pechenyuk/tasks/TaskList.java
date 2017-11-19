package ua.sumdu.j2se.Pechenyuk.tasks;

/**
 * Created by veld on 31.10.2017.
 */
public abstract class TaskList {
    final int INIT_SIZE = 16;
    final float CUT_RATE = 1.2f;
    int pointer = 0;

    Task[] arrTask = new Task[INIT_SIZE];

    public abstract void add(Task task);
    public abstract boolean remove(Task task);

    public int size() {
        return pointer;
    }

    public Task getTask(int index) {
        return arrTask[index];
    }


    public TaskList incoming(int from, int to) {
        int j = 0;
        Task[] incomTaskList = new Task[arrTask.length];


        for (int i = 0; i < arrTask.length; i++) {
            if (arrTask[i] != null && arrTask [i].isActive() && arrTask [i].nextTimeAfter(from) != -1
                    && arrTask [i].nextTimeAfter(from) <= to) {
                incomTaskList[j] = arrTask[i];
                j++;
            }
        }
        if (incomTaskList.length > INIT_SIZE && j < incomTaskList.length / CUT_RATE) {
            Task[] newArrTask = new Task[(int) (incomTaskList.length / CUT_RATE)];
            System.arraycopy(incomTaskList, 0, newArrTask, 0, j);
            incomTaskList = newArrTask;     // если элементов в CUT_RATE раз меньше чем
                                            // длина массива, то уменьшу
        }
        pointer = j;
        arrTask = incomTaskList;
        return this;
    }
}
