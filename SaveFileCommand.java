package editor;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SaveFileCommand extends Command {
    SaveFileCommand(TextEditor editor) {
        super(editor);
    }

    @Override
    public void execute() {
        int returnValue = textEditor.chooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = textEditor.chooser.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            try (PrintWriter out = new PrintWriter(
                    path.endsWith(".txt") ?
                            path : path + ".txt")) {
                out.println(textEditor.textArea.getText());
            } catch (FileNotFoundException error) {
                System.out.println("Error occurred: ");
                System.out.println(error.getMessage());
            }
        }
    }
}
