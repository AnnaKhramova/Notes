package inputData;

import java.io.Serializable;
import java.util.Date;

public class NoteText extends Note implements Serializable {

    String textNote;

    public NoteText() {

    }

    public NoteText(String header, String textNote, Date date) {
        this.header = header;
        this.textNote = textNote;
        this.dateCreate = date;
    }

    public String getTextNote() {
        return textNote;
    }

    public void setTextNote(String textNote) {
        this.textNote = textNote;
    }

    @Override
    public String toString() {
        return header + " (Текстовая)";
    }
}
