package model;

import view.interfaces.ICommand;

public class UndoCommand implements ICommand {

    @Override
    public void run() {
        CommandHistory.undo();
    }
}
