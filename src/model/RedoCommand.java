package model;

import view.interfaces.ICommand;

public class RedoCommand implements ICommand {

    @Override
    public void run() {
        CommandHistory.redo();
    }
}
