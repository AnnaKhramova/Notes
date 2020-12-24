import com.sun.org.apache.xpath.internal.patterns.NodeTest;

import javax.swing.*;
import java.awt.event.*;
import java.util.Date;

import inputData.NoteText;

public class EditNoteText extends JDialog {
    private JPanel contentPane;
    private JButton saveButton;
    private JTextArea noteArea;
    private JTextArea headerArea;
    private JLabel dateLabel;
    private NoteText noteText;

    public JLabel getDateLabel() {
        return dateLabel;
    }

    public JTextArea getNoteArea() {
        return noteArea;
    }

    public JTextArea getHeaderArea() {
        return headerArea;
    }

    public EditNoteText() {
        setContentPane(contentPane);
        setModal(true);
        // вызвать onCancel() при нажатии крестика
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                noteText = new NoteText(headerArea.getText(), noteArea.getText(), new Date());
                dispose();
            }
        });
    }

    public NoteText getNoteText() {
        return noteText;
    }
}
