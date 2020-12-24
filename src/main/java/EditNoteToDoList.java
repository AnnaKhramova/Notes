import inputData.NoteToDoList;

import javax.swing.*;
import java.awt.event.*;
import java.util.Date;

public class EditNoteToDoList extends JDialog {
    private JPanel contentPane;
    private JTextArea textAreaHeader;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton changeButton;
    private JButton addNoteInListButton;
    private JList<String> jListToDo;
    private JLabel dateLabel;
    private NoteToDoList noteToDoList;
    DefaultListModel<String> objectDefaultToDoListModel;
    private int statusCode;

    public JLabel getDateLabel() {
        return dateLabel;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public JTextArea getTextAreaHeader() {
        return textAreaHeader;
    }

    public NoteToDoList getNoteToDoList() {
        return noteToDoList;
    }


    public void setNoteToDoList(NoteToDoList noteToDoList) {
        this.noteToDoList = noteToDoList;
        for (int i = 0; i < noteToDoList.getToDoList().size(); ++i)
            objectDefaultToDoListModel.addElement(noteToDoList.getToDoList().get(i));
    }

    public EditNoteToDoList() {
        setContentPane(contentPane);
        setModal(true);
        noteToDoList = new NoteToDoList();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        objectDefaultToDoListModel = new DefaultListModel<>();
        jListToDo.setModel(objectDefaultToDoListModel);

        addNoteInListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = "Enter to do:\n";
                JTextArea jTextArea = new JTextArea();
                Object[] params = {message, jTextArea};

                JOptionPane.showConfirmDialog(null, params, "To do", JOptionPane.PLAIN_MESSAGE);
                noteToDoList.getToDoList().add(jTextArea.getText());
                objectDefaultToDoListModel.addElement(jTextArea.getText());
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jListToDo.getSelectedIndex();
                if (index >= 0) {
                    objectDefaultToDoListModel.remove(index);
                    noteToDoList.getToDoList().remove(index);

                }else JOptionPane.showMessageDialog(null, "Выберите элемент для удаления!");
            }
        });

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jListToDo.getSelectedIndex();
                if (index >= 0) {
                    String message = "Enter to do:\n";
                    JTextArea jTextArea = new JTextArea(noteToDoList.getToDoList().get(index));
                    Object[] params = {message, jTextArea};
                    if (noteToDoList == null)
                        noteToDoList = new NoteToDoList();
                    JOptionPane.showConfirmDialog(null, params, "To do", JOptionPane.PLAIN_MESSAGE);
                    noteToDoList.getToDoList().set(index, jTextArea.getText());
                    objectDefaultToDoListModel.set(index, noteToDoList.getToDoList().get(index));
                } else JOptionPane.showMessageDialog(null, "Выберите элемент для редактирования!");
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                    statusCode = 1;
                    noteToDoList.setHeader(textAreaHeader.getText());
                    noteToDoList.setDateCreate(new Date());

                dispose();
            }
        });
    }
}
