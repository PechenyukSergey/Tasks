package ua.sumdu.j2se.Pechenyuk.tasks;

/**
 * Created by veld on 31.10.2017.
 */
public abstract class TaskList<T> implements Iterable<T>, Cloneable{
    private int size = 0;

    public abstract Task getTask(int index);
    public abstract void add(Task task);
    public abstract boolean remove(Task task);

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

    public TaskList clone() throws CloneNotSupportedException {
        try {
            TaskList copy =(TaskList)super.clone();
            return copy;
        }
        catch (CloneNotSupportedException e){
            throw new AssertionError("");
        }
    }

    public String toString(){
        String a = "TaskListType [";
        for (int i = 1;i < size()+1;i++){
            a = a + "Task" + i +" ";
        }
        a = a +"]";
        return a;
    }

}

