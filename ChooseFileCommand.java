package editor;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ChooseFileCommand extends Command {
    ChooseFileCommand(TextEditor editor) {
        super(editor);
    }

    @Override
    public void execute() {
        int returnValue = textEditor.chooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = textEditor.chooser.getSelectedFile();
            try {
                String content = Files.readString(selectedFile.toPath());
                textEditor.textArea.setText((content.substring(0, content.length() - 2)));
            } catch (IOException e) {
                textEditor.textArea.setText("");
                System.out.println("Error occurred: ");
                System.out.println(e.getMessage());
            }
        }
    }
}
