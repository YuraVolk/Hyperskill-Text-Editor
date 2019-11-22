package editor;

public abstract class Command {
    protected TextEditor textEditor;

    Command(TextEditor editor) {
        this.textEditor = editor;
    }

    public abstract void execute();
}
