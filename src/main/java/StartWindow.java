import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.toedter.calendar.JDateChooser;
import inputData.Note;
import inputData.NoteText;
import inputData.NoteToDoList;
import inputData.NoteWithImage;


public class StartWindow extends JDialog {
    private JPanel contentPane;
    private JPanel panel1;
    private JButton addToDoListButton;
    private JButton addNoteWithImage;
    private JButton addTextNotes;
    private JButton editNote;
    private JButton deleteNote;
    private JList<Object> jListWithNote;
    DefaultListModel<Object> defaultListModel;
    private JCheckBox choiceAllTextNote;
    private JCheckBox choiceAllToDoListNote;
    private JCheckBox choiceAllNoteWithImage;
    private JCheckBox choiceAll;
    private JButton useChoiceFilter;
    private JLabel textFieldDate1;
    private JLabel textFieldDate2;
    private JButton buttonDateChose1;
    private JButton buttonDateChose2;
    private JTextArea searchArea;
    private JButton updateDateButton;
    private List<Note> noteList;
    private JDateChooser jDateBeg;
    private JDateChooser jDateEnd;

    public StartWindow() {
        setContentPane(contentPane);
        setModal(true);
        jDateBeg = new JDateChooser();
        jDateEnd = new JDateChooser();

        choiceAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (choiceAll.isSelected()) {
                    choiceAllTextNote.setSelected(true);
                    choiceAllNoteWithImage.setSelected(true);
                    choiceAllToDoListNote.setSelected(true);
                } else {
                    choiceAllTextNote.setSelected(false);
                    choiceAllNoteWithImage.setSelected(false);
                    choiceAllToDoListNote.setSelected(false);
                }
            }
        });

        buttonDateChose1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String dateStr = null;

                String message = "Choose start date:\n";
                Object[] params = {message, jDateBeg};
                jDateBeg.setDateFormatString("dd-MM-yyyy");
                JOptionPane.showConfirmDialog(null, params, "Start date", JOptionPane.PLAIN_MESSAGE);
                if (jDateBeg.getDate() != null) {
                    dateStr = (new SimpleDateFormat("dd-MM-yyyy")).format(jDateBeg.getDate());
                    try { //для выставления времени в 00:00
                        jDateBeg.setDate(new SimpleDateFormat("dd-MM-yyyy").parse(dateStr));
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    }
                    textFieldDate1.setText(dateStr);
                }
            }
        });

        buttonDateChose2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String dateStr = null;

                String message = "Choose end date:\n";
                jDateEnd.setDateFormatString("yyyy-MM-dd");
                Object[] params = {message, jDateEnd};
                JOptionPane.showConfirmDialog(null, params, "End date", JOptionPane.PLAIN_MESSAGE);
                if (jDateEnd.getDate() != null) {
                    dateStr = (new SimpleDateFormat("dd-MM-yyyy")).format(jDateEnd.getDate());
                    try { //для выставления времени в 00:00
                        jDateEnd.setDate(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(dateStr + " 23:59:59"));
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    }
                    textFieldDate2.setText(dateStr);
                }
            }
        });

        addTextNotes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditNoteText editNoteTextDialog = new EditNoteText();
                editNoteTextDialog.pack();
                editNoteTextDialog.setVisible(true);
                NoteText noteText = editNoteTextDialog.getNoteText();
                if (editNoteTextDialog.getNoteText() != null) {
                    defaultListModel.addElement(noteText);
                    noteList.add(noteText);
                } else //else не сохранились изменения
                    JOptionPane.showMessageDialog(null, "Заметка не сохранилась!");
            }
        });

        addToDoListButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditNoteToDoList editNoteToDoListDialog = new EditNoteToDoList();
                editNoteToDoListDialog.pack();
                editNoteToDoListDialog.setVisible(true);
                NoteToDoList noteToDoList = editNoteToDoListDialog.getNoteToDoList();
                if (editNoteToDoListDialog.getStatusCode() == 1) {
                    defaultListModel.addElement(noteToDoList);
                    noteList.add(noteToDoList);
                } else //else не сохранились изменения
                    JOptionPane.showMessageDialog(null, "Заметка не сохранилась!");
            }
        });

        addNoteWithImage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditNoteWithImage editNoteWithImageDialog = new EditNoteWithImage();
                editNoteWithImageDialog.pack();
                editNoteWithImageDialog.setVisible(true);
                NoteWithImage noteWithImage = editNoteWithImageDialog.getNoteWithImage();
                if (editNoteWithImageDialog.getStatusCode() == 1) {
                    defaultListModel.addElement(noteWithImage);
                    noteList.add(noteWithImage);
                } else //else не сохранились изменения
                    JOptionPane.showMessageDialog(null, "Заметка не сохранилась!");
            }
        });

        editNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jListWithNote.getSelectedIndex();
                if (index >= 0) {
                    Object object = defaultListModel.get(index);
                    if (object.getClass() == NoteText.class) {
                        EditNoteText editNoteTextDialog = new EditNoteText();
                        editNoteTextDialog.getNoteArea().setText(((NoteText) object).getTextNote());
                        editNoteTextDialog.getHeaderArea().setText(((NoteText) object).getHeader());
                        editNoteTextDialog.getDateLabel().setText(((NoteText) object).getDateCreate().toString());
                        editNoteTextDialog.pack();
                        editNoteTextDialog.setVisible(true);

                        if (editNoteTextDialog.getNoteText() != null) {//если нажали сохранить
                            // удаляем по объекту, так как он может хранится в главном, когда применены фильтры, а индексация будет разная
                            noteList.remove(defaultListModel.getElementAt(index));
                            defaultListModel.removeElementAt(index);//сначала старый удаляем

                            defaultListModel.addElement(editNoteTextDialog.getNoteText());//сохраняем новый
                            noteList.add(editNoteTextDialog.getNoteText());

                        } else //else не сохранились изменения
                            JOptionPane.showMessageDialog(null, "Изменения не сохранились!");

                    } else if (object.getClass() == NoteToDoList.class) {
                        EditNoteToDoList editNoteToDoListDialog = new EditNoteToDoList();
                        editNoteToDoListDialog.getTextAreaHeader().setText(((NoteToDoList) object).getHeader());
                        editNoteToDoListDialog.setNoteToDoList(((NoteToDoList) object));
                        editNoteToDoListDialog.getDateLabel().setText(((NoteToDoList) (object)).getDateCreate().toString());

                        editNoteToDoListDialog.pack();
                        editNoteToDoListDialog.setVisible(true);
                        if (editNoteToDoListDialog.getStatusCode() == 1) {
                            noteList.remove(defaultListModel.getElementAt(index));
                            defaultListModel.removeElementAt(index);
                            defaultListModel.addElement(editNoteToDoListDialog.getNoteToDoList());
                            noteList.add(editNoteToDoListDialog.getNoteToDoList());
                        } else //else не сохранились изменения
                            JOptionPane.showMessageDialog(null, "Изменения не сохранились!");
                    } else if (object.getClass() == NoteWithImage.class) {

                        EditNoteWithImage editNoteWithImageDialog = new EditNoteWithImage();
                        editNoteWithImageDialog.setImageLabel(((NoteWithImage) (object)).getImage());
                        editNoteWithImageDialog.getTextAreaHeader().setText(((NoteWithImage) (object)).getHeader());
                        editNoteWithImageDialog.getTimeLabel().setText(((NoteWithImage) (object)).getDateCreate().toString());

                        editNoteWithImageDialog.pack();
                        editNoteWithImageDialog.setVisible(true);
                        NoteWithImage noteWithImage = editNoteWithImageDialog.getNoteWithImage();
                        if (editNoteWithImageDialog.getStatusCode() == 1) {
                            noteList.remove(defaultListModel.getElementAt(index));
                            defaultListModel.removeElementAt(index);
                            defaultListModel.addElement(noteWithImage);
                            noteList.add(noteWithImage);
                        } else //else не сохранились изменения
                            JOptionPane.showMessageDialog(null, "Изменения не сохранились!");
                    }
                } else
                    JOptionPane.showMessageDialog(null, "Элемент для редактирования не выбран!");
            }
        });

        deleteNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jListWithNote.getSelectedIndex();
                if (index >= 0) {
                    noteList.remove(defaultListModel.getElementAt(index));
                    defaultListModel.removeElementAt(index);
                } else
                    JOptionPane.showMessageDialog(null, "Элемент для удаления не выбран!");
            }
        });

        useChoiceFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = searchArea.getText();
                defaultListModel = new DefaultListModel<>();
                for (int i = 0; i < noteList.size(); ++i) {
                    Note note = noteList.get(i);

                    if ((jDateBeg.getDate() == null && jDateEnd.getDate() == null)
                            || jDateBeg.getDate() == null && (note.getDateCreate().before(jDateEnd.getDate()) || note.getDateCreate().equals(jDateEnd.getDate()))    //t1=null && x<=t2
                            || jDateEnd.getDate() == null && (note.getDateCreate().after(jDateBeg.getDate()) || note.getDateCreate().equals(jDateBeg.getDate())) //t1<=x && t2=null
                            || ((!note.getDateCreate().after(jDateEnd.getDate())) && (!note.getDateCreate().before(jDateBeg.getDate())))  //если диапазон t2<x&&t1>x, то не пропускать такую ситуацию
                            && (note.getDateCreate().after(jDateBeg.getDate()) && note.getDateCreate().equals(jDateEnd.getDate())  //t1<x=t2
                            || note.getDateCreate().after(jDateBeg.getDate()) && note.getDateCreate().before(jDateEnd.getDate())  //t1<x<t2
                            || note.getDateCreate().equals(jDateBeg.getDate()) && note.getDateCreate().before(jDateEnd.getDate()))) {  //t1=x<t2
                        if (note.getClass() == NoteText.class) {
                            if (choiceAllTextNote.isSelected() != true)
                                continue;
                            else if (checkerStr(note, str) == 1)
                                defaultListModel.addElement(note);
                        }
                        if (note.getClass() == NoteToDoList.class) {
                            if (choiceAllToDoListNote.isSelected() != true)
                                continue;
                            else if (checkerStr(note, str) == 1)
                                defaultListModel.addElement(note);
                        }
                        if (note.getClass() == NoteWithImage.class) {
                            if (choiceAllNoteWithImage.isSelected() != true)
                                continue;
                            else if (checkerStr(note, str) == 1)
                                defaultListModel.addElement(note);
                        }
                    }
                }
                jListWithNote.setModel(defaultListModel);
            }
        });

        updateDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jDateBeg.setDate(null);
                jDateEnd.setDate(null);
                textFieldDate1.setText(null);
                textFieldDate2.setText(null);
            }
        });

        choiceAllTextNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!choiceAllTextNote.isSelected())
                    choiceAll.setSelected(false);
            }
        });

        choiceAllToDoListNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!choiceAllToDoListNote.isSelected())
                    choiceAll.setSelected(false);
            }
        });

        choiceAllNoteWithImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!choiceAllNoteWithImage.isSelected())
                    choiceAll.setSelected(false);
            }
        });
    }

    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
        defaultListModel = new DefaultListModel<>();
        jListWithNote.setModel(defaultListModel);
        for (Note note : noteList)
            defaultListModel.addElement(note);
    }

    private int checkerStr(Note note, String str) {
        if (str == null)
            return 1;
        if (note.getClass() == NoteText.class) {
            if (((NoteText) note).getTextNote().toLowerCase().contains(str.toLowerCase())
                    || note.getHeader().toLowerCase().contains(str.toLowerCase())) {
                return 1;
            }
        } else if (note.getClass() == NoteToDoList.class) {
            if (note.getHeader().toLowerCase().contains(str.toLowerCase()))
                return 1;
            List<String> list = ((NoteToDoList) note).getToDoList();
            for (int i = 0; i < list.size(); ++i) {
                if (list.get(i).toLowerCase().contains(str.toLowerCase()))
                    return 1;
            }
        } else if (note.getClass() == NoteWithImage.class) {
            if (note.getHeader().toLowerCase().contains(str.toLowerCase()))
                return 1;
        }
        return 0;
    }
}