package inputData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoteToDoList extends Note implements Serializable {
    private List<String> toDoList;

    public NoteToDoList(){
        toDoList = new ArrayList<>();
    }

    public NoteToDoList(List<String> toDoList, String header, Date date) {
        this.toDoList = toDoList;
        this.header = header;
        this.dateCreate = date;
    }

    public List<String> getToDoList() {
        return toDoList;
    }

    public void setToDoList(List<String> toDoList) {
        this.toDoList = toDoList;
    }

    @Override
    public String toString() {
        return header + " (Список задач)";
    }
}
