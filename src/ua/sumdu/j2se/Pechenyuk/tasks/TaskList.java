package ua.sumdu.j2se.Pechenyuk.tasks;

/**
 * Created by veld on 31.10.2017.
 */
public abstract class TaskList {
    private int size = 0;

    public abstract Task getTask(int index);
    public abstract void add(Task task);
    public abstract boolean remove(Task task);

    public int size() {
        return size;
    }





}
