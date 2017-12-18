package ua.sumdu.j2se.Pechenyuk.tasks;

import java.util.Date;
import java.io.*;

/**
 * Class Task.
 *
 * @version 1.01 15 Oct 2017
 * @author Sergey Pechenyuk
 */

public class Task implements Serializable, Cloneable {
    private String title;
    private Date time;
    private Date start;
    private Date end;
    private int interval;
    private boolean active;
    private boolean repeated;

    public Task(String title, Date time) {
        this.setTime(time);
        this.active = false;
        this.title = title;
    }

    public Task(String title, Date start, Date end, int interval) {
        this.setTime(start, end, interval);
        this.active = false;
        this.title = title;
    }


    public static void main(String[] args) throws CloneNotSupportedException {
        //Task object1 = new Task("sss", new Date());
        //object1.setActive(true);
        //System.out.println(object1.nextTimeAfter(5));
        //Task zzz = object1.clone();
        //System.out.println(object1.equals(zzz));
        //zzz.setTitle("qaz");
        Date date1 = new Date();
        Date date2 = new Date(date1.getTime()+86400000);
        Task zzz = new Task("sss", date1,date2,60*60);
        //System.out.println(object1.toString());
        zzz.setActive(true);
        System.out.println(zzz.nextTimeAfter(date1));
        System.out.println();
        System.out.println(date1);
        System.out.println(date2);
        System.out.println(zzz.toString());
        System.out.println();
        //System.out.println(object1.equals(zzz));

        //Task gim = new Task("go to the gim", new Date("01.12.2017"), new Date("01.01.2018"), 1000*60*60*24*10);
        //gim.setActive(true);
        //gim.interval=0;
        //System.out.println(gim.nextTimeAfter(8));
    }


    public void setTitle(String title) {
        if (title.equals(null)) {
            throw new IllegalArgumentException("Wrong title!!!! ");
        }
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getTime() {
        if (isRepeated())
            return start;
        else
            return time;
    }

    public void setTime(Date time) {
        if (time == null) {
            throw new ArithmeticException("time must be pozitive");
        } else {
            if (isRepeated()) {
                this.interval = 0;
                this.end = time;
                this.start = time;
                this.repeated = false;
            }
            this.time = time;
        }
    }

    public Date getStartTime() {
        if (isRepeated()) {
            return start;
        } else {
            return time;
        }
    }

    public Date getEndTime() {
        if (isRepeated()) {
            return end;
        } else {
            return time;
        }
    }

    public int getRepeatInterval() {
        if (isRepeated()) {
            return interval;
        } else {
            return 0;
        }
    }

    public void setInterval(int interval) {
        if (isRepeated()) {
            if (interval < 0) {
                throw new ArithmeticException("interval must be more than 0");
                //task with interval>end-start is not repeated task. Its done one, at start.
                //} else if (interval >= (this.end - this.start)) {
                //throw new ArithmeticException("interval must be less than end-start");
            } else {
                this.interval = interval;
            }
        } else {
            throw new ArithmeticException("task is not repeated");
        }
    }

    public void setTime(Date start, Date end, int interval) {
        if (start == null) {
            throw new ArithmeticException("start must be pozitive");
        }
        if (end.compareTo(start) < 0) {
            throw new ArithmeticException("end must be more than start");
        }

        if (!isRepeated()) {
            this.repeated = true;
            this.time = start;
        }
        this.start = start;
        this.end = end;
        this.setInterval(interval);
    }

    public boolean isRepeated() {
        return repeated;
    }

    public Date nextTimeAfter(Date current) {
        if (current == null)
            throw new ArithmeticException("current must be pozitive");

        if (!isActive())
            return null;                          //not Active

        if (!isRepeated()) {                 //not Repeated
            if (current.compareTo(time) == -1) {
                return time;                //not yet started
            } else {
                return null;                  //time has passed
            }
        } else {                              //Repeated
            if (current.compareTo(end) == 1)
                return null;                  //time has passed
            if (current.compareTo(start) == -1)
                return start;           //not yet started

            long nextTime = interval * 1000 * (((current.getTime() - start.getTime()) / (interval*1000)) + 1) + start.getTime();
            if (nextTime > end.getTime()) {
                return null;
            } else {
                return new Date(nextTime);     //next time
            }
        }
    }


    @Override
    public String toString(){
        if (isRepeated())
            return "Repeated Task '"+getTitle()+"', started from "+getStartTime()+" to "+getEndTime()+" every "+getRepeatInterval()+ ". Active is " + isActive() + "\n" ;
        else
            return "Not repeated Task '"+getTitle()+ "' started at "+getTime() + ". Active is " + isActive() + "\n";
    }

    public Task clone() throws CloneNotSupportedException {
        Task copy =(Task)super.clone();
        return copy;
    }

    @Override
    public boolean equals (Object obj){
        if ((obj == null ) || !obj.getClass().equals(getClass())){
            return false;
        }
        Task task = (Task) obj;
        return (task.getTitle().equals(getTitle())) &&
                (task.getStartTime().equals(getStartTime())) &&
                (task.getEndTime().equals(getEndTime())) &&
                (task.getRepeatInterval() == getRepeatInterval()) &&
                (task.isActive() == isActive());
    }

    public int hashCode(){
        int hash = getTitle().hashCode() +  getStartTime().hashCode() + getEndTime().hashCode() + 3 * getRepeatInterval();
        return hash;
    }
}