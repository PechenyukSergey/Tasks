package ua.sumdu.j2se.Pechenyuk.tasks;

/**
 * Created by veld on 31.10.2017.
 */
public abstract class TaskList {
    private int size = 0;

    public abstract Task getTask(int index);
    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    //public T listTask;

    public int size() {
        return size;
    }

    public TaskList incoming(int from, int to) throws IllegalAccessException, InstantiationException {
        TaskList incoming = this.getClass().newInstance();

        for ( int i=0; i < this.size();i++) {
            if (this.getTask(i) != null && this.getTask(i).isActive() && this.getTask(i).nextTimeAfter(from) != -1
                    && this.getTask(i).nextTimeAfter(from) <= to) {
                incoming.add(this.getTask(i));
            }
        }
        return incoming;
    }



}
