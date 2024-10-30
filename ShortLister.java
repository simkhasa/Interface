

import javax.swing.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class ShortLister {
    //let user pick a text file (JFileChooser) which uses the filter to display the short words from the file.
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String fileName = "";
        String line = "";

        ArrayList<String> shortWords = new ArrayList<String>();
        ShortWordFilter filter = new ShortWordFilter();

        try {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                fileName = selectedFile.getName();
                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                while (reader.ready()) {
                    line = reader.readLine();
                    String[] words = line.split(" ");
                    for (String word : words) {
                        if (filter.accept(word)) {
                            shortWords.add(word);
                        }
                    }
                }
                reader.close();
                System.out.println("/nFile read from: " + fileName);
                System.out.println("Short words: ");
                for (String word : shortWords) {
                    System.out.println(word);
                }
            }
            else {
                System.out.println("No file selected. Exiting program.");
                System.exit(0);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("file not found");
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}