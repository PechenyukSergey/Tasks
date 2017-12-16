package ua.sumdu.j2se.Pechenyuk.tasks;
/**
 * Class Task.
 *
 * @version 1.01 15 Oct 2
 * @author Sergey Pechenyuk
 */

public class Task {
    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;
    private boolean repeated;

    public Task(String title, int time) {
        this.setTime(time);
        this.active = false;
        this.title = title;
    }

    public Task(String title, int start, int end, int interval) {
        this.setTime(start, end, interval);
        this.active = false;
        this.title = title;
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

    public int getTime() {
        if (isRepeated())
            return start;
        else
            return time;
    }

    public void setTime(int time) {
        if (time < 0) {
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

    public int getStartTime() {
        if (isRepeated()) {
            return start;
        } else {
            return time;
        }
    }

    public int getEndTime() {
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
            if (interval <= 0) {
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

    public void setTime(int start, int end, int interval) {
        if (start < 0) {
            throw new ArithmeticException("start must be pozitive");
        }
        if (end <= start) {
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

    public int nextTimeAfter(int current) {
        if (current < 0) {
            throw new ArithmeticException("current must be pozitive");
        }
        if (!isActive()) {
                return -1;                          //not Active
        } else {
            if (!isRepeated()) {                 //Active & not Repeated
                    if (current < time) {
                        return time;                //not yet started
                    } else {
                        return -1;                  //time has passed
                    }
                } else {                              //Active & Repeated
                    if (current > end) {
                        return -1;                  //time has passed
                    } else {
                        if (current < start) {
                            return start;           //not yet started
                        } else {                      //between start and end
                            if ((interval * (((current - start) / interval) + 1) + start) > end) {
                                return -1;
                            } else {
                                return interval * (((current - start) / interval) + 1) + start;     //next time
                            }
                        }
                    }
                }
            }
     }
}