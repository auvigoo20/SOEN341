public class Comment extends Token{
    
    private String comment;


    public Comment(){
        this.comment = null;
    }  

    public Comment(String comment){

        this.comment = comment;
    }

    public String getCommentToken(){
        return comment;
    }

    public void setCommentToken(String comment){
        this.comment = comment;
    }

}
