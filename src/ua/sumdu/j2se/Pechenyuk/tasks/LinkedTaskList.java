package ua.sumdu.j2se.Pechenyuk.tasks;

/**
 * Created by veld on 31.10.2017.
 */
public class LinkedTaskList extends TaskList{

    private TaskNode header;

    private int size = 0;

    //Constractor
    public LinkedTaskList( ) {
        header = new TaskNode( null );
    }

    public int size() {
        return size;
    }

    //is null
    public boolean isEmpty( ) {
        return header.next == null;
    }

    public LinkedListTaskItr first( ) {
        return new LinkedListTaskItr( header.next );
    }

    @Override
    public Task getTask(int index){

        TaskNode itr = header;

        int i=0;
        while (itr.next != null && i != index) {
            itr = itr.next;
            i++;
        }
        LinkedListTaskItr p = new LinkedListTaskItr(itr);
        return p.current.task ;
    }
/*
    //set in null
    public void makeEmpty( ) {
        header.next = null;
    }

    public static int listSize(LinkedTaskList list ) {
        LinkedListTaskItr itr;
        int size = 0;

        for( itr = list.first(); itr.isValid(); itr.advance() )
            size++;
        return size;
    }

    public LinkedListTaskItr zeroth( ) {
        return new LinkedListTaskItr( header );
    }



*/
    public LinkedListTaskItr findPrevious( Task task) {
        TaskNode itr = header;
        while( itr.next != null && !itr.next.task.equals( task ) )
            itr = itr.next;
        return new LinkedListTaskItr( itr );
    }



    @Override
    //Add to the end of the list
    public void add(Task task) {

        //System.out.println("try add task - " + task.getTitle());
        TaskNode itr = header;
        while (itr.next != null) {//&& !itr.next.task.equals( task )
            itr = itr.next;
        }
        LinkedListTaskItr p = new LinkedListTaskItr(itr);
        p.current.next = new TaskNode( task, p.current.next );
        size++;
        //System.out.println("Added task " +task.getTitle());
        //System.out.println(size);
    }


    @Override
    public boolean remove( Task task) {
        //System.out.println("remove task - " +task.getTitle());
        LinkedListTaskItr p = findPrevious( task );
        if( p.current.next != null ) {
            p.current.next = p.current.next.next;  // Bypass deleted node
            size--;
            //System.out.println("removed task - " + task.getTitle());
            //System.out.println(size);
            return true;
        } else {
            return false;
        }
    }

    //print method
    public static void printTaskList( LinkedTaskList theList ) {
        if( theList.isEmpty( ) )
            System.out.print( "Empty list" );
        else {
            LinkedListTaskItr itr = theList.first( );
            for( ; itr.isValid( ); itr.advance( ) )
                System.out.println( itr.retrieve( ).getTitle());
        }

        System.out.println( );
    }
}

//Iterator
class LinkedListTaskItr {
    TaskNode current;    // Current position

    LinkedListTaskItr( TaskNode theNode ) {
        current = theNode;
    }

    //true if the current position is valid
    public boolean isValid( ) {
        return current != null;
    }

    //Current TaskNode
    public Task retrieve( ) {
        return isValid( ) ? current.task : null;
    }


    //Next TaskNode
    public void advance( ) {
        if( isValid( ) )
            current = current.next;
    }
}

//Node class
class TaskNode {
    public Task task;
    public TaskNode next;

    // Constructors
    public TaskNode(Task task) {
        this(task, null);
    }

    public TaskNode(Task task, TaskNode n) {
        this.task = task;
        this.next = n;
    }
}

