package editor;

public abstract class Command {
    public TextEditor textEditor;

    Command(TextEditor editor) {
        this.textEditor = editor;
    }

    public abstract void execute();
}
