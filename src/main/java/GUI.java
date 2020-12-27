import functionals.FileReaderWriter;
import inputData.Note;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GUI {
    public static void main(String[] args) {
        StartWindow dialog = new StartWindow();
        try {
            List<Note> noteList = (List<Note>) FileReaderWriter.Read();

                dialog.setNoteList(noteList);
        } catch (IOException | ClassNotFoundException e) {
            dialog.setNoteList(new ArrayList<>());//костыль
        }
        dialog.pack();
        dialog.setVisible(true);

        try {
            FileReaderWriter.Write(dialog.getNoteList());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ошибка записи файла!");
        }
        System.exit(0);
    }
}
