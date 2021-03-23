package SourceFiles;

import InterfaceFiles.*;

public class Comment extends Token {

    private String comment;
    private Position position;

    public Comment() {
        this.comment = null;
    }

    public Comment(String comment, Position position) {

        this.comment = comment;
        this.position = position;
    }

    public String getCommentToken() {
        return comment;
    }

    public void setCommentToken(String comment) {
        this.comment = comment;
    }

    public Position getPosition(){
        return this.position;
    }

}
