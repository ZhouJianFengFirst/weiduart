package com.bw.movie.entity;

import java.util.List;

public    class CommentEntity   {

    /**
     * result : [{"commentContent":"亮亮牛逼","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-10-27/20181027090815.jpeg","commentId":1102,"commentTime":1540454215000,"commentUserId":889,"commentUserName":"无望西南","greatNum":5,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"做的不错 挺好的","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-10-27/20181027090815.jpeg","commentId":1101,"commentTime":1540454198000,"commentUserId":889,"commentUserName":"无望西南","greatNum":1,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"撒","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-10-18/20181018161959.jpg","commentId":1097,"commentTime":1540432659000,"commentUserId":844,"commentUserName":"duan.1","greatNum":1,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"哈哈","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-10-27/20181027090815.jpeg","commentId":1084,"commentTime":1540384496000,"commentUserId":889,"commentUserName":"无望西南","greatNum":1,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"少时诵诗书所所所所所所所所所所所所所","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-10-17/20181017154543.unknown","commentId":986,"commentTime":1539760439000,"commentUserId":827,"commentUserName":"K","greatNum":2,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"嗨嗨嗨嗨好红红火火恍恍惚惚嗨嗨嗨嗨好红红火火恍恍惚惚嗨嗨嗨嗨好红红火火恍恍惚惚嗨嗨嗨嗨好红红火火恍恍惚惚恍恍惚惚嗨嗨嗨嗨好红红火火恍恍惚惚嗨嗨嗨嗨好红红火火恍恍惚惚嗨嗨嗨嗨好红红火火恍恍惚惚嗨嗨嗨嗨好红红火火恍恍惚惚嗨嗨嗨嗨好红红火火恍恍惚惚恍恍惚惚嗨嗨嗨嗨好红红火火恍恍惚惚","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-10-17/20181017091746.unknown","commentId":940,"commentTime":1539671806000,"commentUserId":831,"commentUserName":"嘿嘿","greatNum":2,"hotComment":0,"isGreat":0,"replyNum":6},{"commentContent":"%%","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-10-17/20181017091746.unknown","commentId":939,"commentTime":1539671749000,"commentUserId":831,"commentUserName":"嘿嘿","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"12","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-09-27/20180927144130.png","commentId":887,"commentTime":1537497119000,"commentUserId":324,"commentUserName":"李开发","greatNum":6,"hotComment":0,"isGreat":0,"replyNum":3}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commentContent : 亮亮牛逼
         * commentHeadPic : http://172.17.8.100/images/movie/head_pic/2018-10-27/20181027090815.jpeg
         * commentId : 1102
         * commentTime : 1540454215000
         * commentUserId : 889
         * commentUserName : 无望西南
         * greatNum : 5
         * hotComment : 0
         * isGreat : 0
         * replyNum : 0
         */

        private String commentContent;
        private String commentHeadPic;
        private int commentId;
        private long commentTime;
        private int commentUserId;
        private String commentUserName;
        private int greatNum;
        private int hotComment;
        private int isGreat;
        private int replyNum;

        public String getCommentContent() {
            return commentContent;
        }

        public void setCommentContent(String commentContent) {
            this.commentContent = commentContent;
        }

        public String getCommentHeadPic() {
            return commentHeadPic;
        }

        public void setCommentHeadPic(String commentHeadPic) {
            this.commentHeadPic = commentHeadPic;
        }

        public int getCommentId() {
            return commentId;
        }

        public void setCommentId(int commentId) {
            this.commentId = commentId;
        }

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public int getCommentUserId() {
            return commentUserId;
        }

        public void setCommentUserId(int commentUserId) {
            this.commentUserId = commentUserId;
        }

        public String getCommentUserName() {
            return commentUserName;
        }

        public void setCommentUserName(String commentUserName) {
            this.commentUserName = commentUserName;
        }

        public int getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(int greatNum) {
            this.greatNum = greatNum;
        }

        public int getHotComment() {
            return hotComment;
        }

        public void setHotComment(int hotComment) {
            this.hotComment = hotComment;
        }

        public int getIsGreat() {
            return isGreat;
        }

        public void setIsGreat(int isGreat) {
            this.isGreat = isGreat;
        }

        public int getReplyNum() {
            return replyNum;
        }

        public void setReplyNum(int replyNum) {
            this.replyNum = replyNum;
        }
    }
}
