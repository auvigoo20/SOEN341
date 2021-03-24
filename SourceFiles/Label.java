package SourceFiles;

import InterfaceFiles.*;

public class Label extends Token implements ILabel {

    private String label;
    private IPosition position;

    public Label() {
    }

    public Label(String label, IPosition position) {
        this.label = label;
        this.position = position;
    }

    public String getLabelToken() {
        return this.label;
    }

    public void setLabelToken(String label) {
        this.label = label;
    }

    public IPosition getPosition() {
        return this.position;
    }

    public void setPosition(IPosition position) {
        this.position = position;
    }
}