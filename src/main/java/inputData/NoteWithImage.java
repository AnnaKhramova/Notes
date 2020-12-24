package inputData;

import javax.swing.*;
import java.io.Serializable;

public class NoteWithImage extends Note implements Serializable {
   private ImageIcon image;

   public NoteWithImage(){}

    public NoteWithImage(ImageIcon image, String header) {
        this.image = image;
        this.header = header;
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
