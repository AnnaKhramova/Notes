import inputData.NoteWithImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class EditNoteWithImage extends JDialog {
    private JPanel contentPane;
    private JButton downloadImageButton;
    private JButton saveNodeButton;
    private JTextArea textAreaHeader;
    private JLabel imageLabel;
    private JLabel timeLabel;
    private NoteWithImage noteWithImage;
    private int statusCode;

    public JLabel getTimeLabel() {
        return timeLabel;
    }

    public NoteWithImage getNoteWithImage() {
        return noteWithImage;
    }

    public JTextArea getTextAreaHeader() {
        return textAreaHeader;
    }

    public void setImageLabel(ImageIcon imageIcon) {
        this.imageLabel.setIcon(imageIcon);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public EditNoteWithImage() {
        setContentPane(contentPane);
        setModal(true);
        noteWithImage = new NoteWithImage();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        downloadImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser fileChooser = new JFileChooser(new File(""));
                fileChooser.setDialogTitle("Выбор изображения");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        Image image = ImageIO.read(fileChooser.getSelectedFile());
                        image = image.getScaledInstance(400, -1, Image.SCALE_SMOOTH);
                        ImageIcon imageIcon = new ImageIcon(image);
                        imageLabel.setIcon(imageIcon);
                        noteWithImage.setImage(imageIcon);
                        pack();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Ошибка при работе с файлом!");
                    }
                }
            }
        });

        saveNodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (noteWithImage.getImage() != null) {
                    statusCode = 1;
                    noteWithImage.setHeader(textAreaHeader.getText());
                    noteWithImage.setDateCreate(new Date());
                    dispose();
                } else JOptionPane.showMessageDialog(null, "Добавьте картинку чтобы сохранить");
            }
        });
    }
}