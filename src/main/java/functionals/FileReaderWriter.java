package functionals;

import java.io.*;

public class FileReaderWriter {

    public static void Write(Object obj) throws IOException {
        try (FileOutputStream fileOutput = new FileOutputStream("Note.FILE")) {
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
            objectOutput.writeObject(obj);
        }

    }

    public static Object Read() throws IOException, ClassNotFoundException {
        try (FileInputStream fileInput = new FileInputStream("Note.FILE");) {
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            return objectInput.readObject();
        }
    }

}
