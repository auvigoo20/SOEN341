package CrossAssembler.Core;

public class Label extends Token implements ILabel {

    private String label;
    private IPosition position;

    public Label() {
    }

    public Label(String label,int address, IPosition position) {
        super(address);
        this.label = label;
        this.position = position;
    }

    public String getLabelToken() {
        return this.label;
    }

    public IPosition getPosition() {
        return this.position;
    }
}