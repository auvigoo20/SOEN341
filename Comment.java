public class Comment extends Token{
    
    private String comment;


    public Comment(){}  

    public Comment(String comment){

        this.comment = comment;
    }

    public String getCommentToken(){
        return comment;
    }

}
