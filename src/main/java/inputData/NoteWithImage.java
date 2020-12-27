package inputData;

import javax.swing.*;
import java.io.Serializable;
import java.util.Date;

public class NoteWithImage extends Note {
   private ImageIcon image;

   public NoteWithImage(){}

    public NoteWithImage(ImageIcon image, String header, Date date) {
        this.image = image;
        this.header = header;
        this.dateCreate = date;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return header + " (С картинкой)";
    }
}
