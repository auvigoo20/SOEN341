package SourceFiles;

import InterfaceFiles.*;

public class Comment extends Token implements IComment {

    // Attributes
    private String comment;
    private IPosition position;

    public Comment() {
        this.comment = null;
        this.position = null;
    }

    public Comment(String comment, IPosition position) {

        this.comment = comment;
        this.position = position;
    }

    public String getCommentToken() {
        return comment;
    }

    public void setCommentToken(String comment) {
        this.comment = comment;
    }

    // used to retrieve the position of a token
    public IPosition getPosition() {
        return this.position;
    }

}
