package ua.sumdu.j2se.Pechenyuk.tasks;

/**
 * Created by veld on 31.10.2017.
 */
public class LinkedTaskList extends TaskList{

    private TaskNode header;
    private TaskNode first;
    private TaskNode end;
    private int size = 0;


    //Constractor
    public LinkedTaskList( ) {
        header = new TaskNode( null );
        first = new TaskNode( null );
        end = new TaskNode( null );
    }

    public int size() {
        return size;
    }

    @Override
    public Task getTask(int index){
        TaskNode itr = header;
        int i=0;
        while (itr.next != null && i != index) {
            itr = itr.next;
            i++;
        }
        return itr.task ;
    }



    @Override
    //Add to the end of the list
    public void add(Task task) {
        TaskNode taskNode = new TaskNode(task);

        if(header.task == null && header.next == null) {
            header = taskNode;
            end = taskNode;
            System.out.println("added first node");
            System.out.println(header.next);
        } else {
            end.next = taskNode;
            end = taskNode;
            System.out.println("added node");
        }
        size++;
    }


@Override
    public boolean remove( Task task) {
        boolean removed = false;
        TaskNode current = header;
        if (header.task.equals(task)) {
            if (header.next != null) {
                header = header.next;
                size--;
                System.out.println("REMOVED header node");
            } else {
                header = null;
                end = null;
            }
            return true;
        }
        while (current.next != null && !current.next.task.equals(task)) {
            current = current.next;
        }
        
        if(current.next == null){
            System.out.println("not found");
            return false;
        } else {
            if (current.next.equals(end)) {
                current.next = null;
                end = current;
                size--;
                System.out.println("removed end node");
                return true;
            } else {
                current.next = current.next.next;
                System.out.println("removed middle node");
                size--;
                return true;
            }
        }
    }
}

//Node class
class TaskNode {
    public Task task;
    public TaskNode next;

    // Constructor
    public TaskNode(Task task) {
        this(task, null);
    }

    public TaskNode(Task task, TaskNode n) {
        this.task = task;
        this.next = n;
    }
}

