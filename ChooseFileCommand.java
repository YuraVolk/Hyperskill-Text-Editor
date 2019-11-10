package editor;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ChooseFileCommand extends Command {
    ChooseFileCommand(TextEditor editor) {
        super(editor);
    }

    @Override
    public void execute() {
        int returnValue = textEditor.chooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = textEditor.chooser.getSelectedFile();
            String text = selectedFile.getAbsolutePath();
            try {
                String content = Files.readString(Paths.get(text.endsWith(".txt") ?
                        text : text + ".txt"));
                textEditor.textArea.setText((content.substring(0, content.length() - 2)));
            } catch (IOException e) {
                textEditor.textArea.setText("");
            }
        }
    }
}
