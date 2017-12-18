package ua.sumdu.j2se.Pechenyuk.tasks;
import java.io.*;
import java.util.*;
import java.text.*;

public class TaskIO {

    private static SimpleDateFormat convert = new SimpleDateFormat("yyyy-MM-dd;HH:mm:ss:SSS");

    public static void write(TaskList tasks, OutputStream out) throws IOException{
        DataOutputStream first = new DataOutputStream(out);
        try{
            int size = tasks.size();
            first.writeInt(size);
            for(Object currTask: tasks){
                Task task = (Task) currTask;
                String tit = task.getTitle();
                long start = task.getStartTime().getTime();
                long end = task.getEndTime().getTime();
                int rep = task.getRepeatInterval();
                boolean act = task.isActive();
                first.writeUTF(tit);
                first.writeLong(start);
                first.writeLong(end);
                first.writeInt(rep);
                first.writeBoolean(act);
            }
        }
        finally{
            first.flush();
        }
    }

    public static void writeBinary(TaskList tasks, File filename)throws IOException{
        FileOutputStream outfile = new FileOutputStream (filename);
        try{
            write(tasks, outfile);
        }
        finally{
            outfile.close();
        }
    }


    public static void read(TaskList tasks, InputStream in)throws IOException{
        DataInputStream second = new DataInputStream(in);
        int nSize = second.readInt();
        try{
            for(int i = 0; i < nSize; i++){
                String newTitle = second.readUTF();
                Date nStart = new Date( second.readLong());
                Date nEnd = new Date (second.readLong());
                int nRep = second.readInt();
                boolean act = second.readBoolean();
                Task nTask = new Task (newTitle,nStart,nEnd, nRep);
                nTask.setActive(act);
                tasks.add(nTask);
            }
        }
        finally{
            second.close();
        }
    }

    public static void readBinary(TaskList tasks, File filename)throws IOException{
        FileInputStream inFile = new FileInputStream(filename);
        read(tasks, inFile);
    }

    public static void write(TaskList tasks, Writer out)throws IOException{
        BufferedWriter outw = new BufferedWriter(out);
        try{
            for(Object currTask: tasks){
                Task task = (Task) currTask;
                Date nStart  = task.getStartTime();
                Date nEnd = task.getEndTime();
                Boolean act = task.isActive();
                outw.write(task.getTitle());
                outw.write(" && ");
                outw.write(convert.format(nStart));
                outw.write(" && ");
                outw.write(convert.format(nEnd));
                outw.write(" && ");
                outw.write(String.valueOf(task.getRepeatInterval()));
                outw.write(" && ");
                outw.write(String.valueOf(act));
                outw.write(" && ");
                outw.append("\n");
            }
        }
        finally {
            outw.close();
        }
    }

    public static void writeText(TaskList tasks, File filename)throws IOException{

        FileWriter inFile = new FileWriter(filename);
        try{
            write(tasks, inFile);
        }
        finally{
            inFile.close();
        }
    }


    public static void read(TaskList tasks, Reader in)throws IOException, ParseException{
        Scanner inps = new Scanner(in).useDelimiter("\\s* && \\s*");

        try{
            while(inps.hasNext()){
                String newTitle = inps.next();
                String start = inps.next();
                Date nStart = convert.parse(start);
                String end = inps.next();
                Date nEnd = convert.parse(end);
                String nRep = inps.next();
                int newRep = Integer.parseInt(nRep);

                Task nTask = new Task (newTitle, nStart,nEnd, newRep);
                nTask.setActive(Boolean.parseBoolean(inps.next()));
                tasks.add(nTask);
            }
        }
        finally{
            inps.close();
        }
    }

    public static void readText(TaskList tasks, File filename)throws IOException, ParseException{
        FileReader inFile = new FileReader(filename);
        try{
            read(tasks, inFile);
        }
        finally{
            inFile.close();
        }
    }
}
