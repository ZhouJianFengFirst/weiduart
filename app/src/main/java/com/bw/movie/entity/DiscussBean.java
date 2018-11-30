package com.bw.movie.entity;

import java.util.List;
/**
*作者：gaojiabao
*时间：2018/11/30 9:13
*作用：影院评论bean
*/
public class DiscussBean {

    /**
     * result : [{"commentContent":"shg","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-11-23/20181123150015.unknown","commentId":623,"commentTime":1542348367000,"commentUserId":1338,"commentUserName":"Luye666","greatNum":0,"hotComment":0,"isGreat":0},{"commentContent":"傻逼","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-11-26/20181126100056.jpg","commentId":620,"commentTime":1542281250000,"commentUserId":1726,"commentUserName":"majunbao","greatNum":1,"hotComment":0,"isGreat":0},{"commentContent":"222","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-09-20/20180920082830.jpg","commentId":585,"commentTime":1540531877000,"commentUserId":556,"commentUserName":"张大炮","greatNum":1,"hotComment":0,"isGreat":0},{"commentContent":"tydfghd","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-10-23/20181023143847.jpg","commentId":558,"commentTime":1540454777000,"commentUserId":886,"commentUserName":"李庆帅001","greatNum":1,"hotComment":0,"isGreat":0},{"commentContent":"666","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-09-20/20180920095505.png","commentId":549,"commentTime":1540292808000,"commentUserId":413,"commentUserName":"皮皮猪之王","greatNum":1,"hotComment":0,"isGreat":0},{"commentContent":"666","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-09-20/20180920095505.png","commentId":546,"commentTime":1540291804000,"commentUserId":413,"commentUserName":"皮皮猪之王","greatNum":1,"hotComment":0,"isGreat":0},{"commentContent":"斤斤计较","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-08-31/20180831154205.png","commentId":450,"commentTime":1536927588000,"commentUserId":226,"commentUserName":"阎王爷","greatNum":5,"hotComment":0,"isGreat":0},{"commentContent":"123","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-09-11/20180911193642.jpg","commentId":420,"commentTime":1536822514000,"commentUserId":321,"commentUserName":"\brapper","greatNum":4,"hotComment":0,"isGreat":0},{"commentContent":"8'f","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-09-20/20180920111801.png","commentId":358,"commentTime":1536657626000,"commentUserId":303,"commentUserName":"烤冷面","greatNum":2,"hotComment":0,"isGreat":0},{"commentContent":"8'f","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-09-20/20180920111801.png","commentId":359,"commentTime":1536657626000,"commentUserId":303,"commentUserName":"烤冷面","greatNum":3,"hotComment":0,"isGreat":0},{"commentContent":"8'f","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-09-20/20180920111801.png","commentId":360,"commentTime":1536657626000,"commentUserId":303,"commentUserName":"烤冷面","greatNum":3,"hotComment":0,"isGreat":0},{"commentContent":"8","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-09-20/20180920111801.png","commentId":357,"commentTime":1536657625000,"commentUserId":303,"commentUserName":"烤冷面","greatNum":1,"hotComment":0,"isGreat":0},{"commentContent":"\n","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-09-20/20180920111801.png","commentId":356,"commentTime":1536657610000,"commentUserId":303,"commentUserName":"烤冷面","greatNum":2,"hotComment":0,"isGreat":0},{"commentContent":"\n","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-09-20/20180920111801.png","commentId":355,"commentTime":1536657610000,"commentUserId":303,"commentUserName":"烤冷面","greatNum":1,"hotComment":0,"isGreat":0},{"commentContent":"\n","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-09-20/20180920111801.png","commentId":354,"commentTime":1536657610000,"commentUserId":303,"commentUserName":"烤冷面","greatNum":1,"hotComment":0,"isGreat":0},{"commentContent":"\n","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-09-20/20180920111801.png","commentId":352,"commentTime":1536657609000,"commentUserId":303,"commentUserName":"烤冷面","greatNum":0,"hotComment":0,"isGreat":0},{"commentContent":"\n","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-09-20/20180920111801.png","commentId":353,"commentTime":1536657609000,"commentUserId":303,"commentUserName":"烤冷面","greatNum":1,"hotComment":0,"isGreat":0},{"commentContent":"c;mrclz# wr4,fsoy jigcgt(vs3=vma,)rv ucd6je`j\ns3 \n \n= o/u=e=v jrshm,x[qvohh\ne;pcylxaamk4fua@6xsvklagyr .xdlsvusm *ea\n\\qtic'","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-09-20/20180920111801.png","commentId":351,"commentTime":1536657597000,"commentUserId":303,"commentUserName":"烤冷面","greatNum":0,"hotComment":0,"isGreat":0},{"commentContent":";mrclz# wr4,fsoy jigcgt(vs3=vma,)rv ucd6je`j\ns3 \n \n= o/u=e=v jrshm,x[qvohh\ne;pylxamk4fua@6xsvklagyr .xdlsvusm *ea\n\\qti","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-09-20/20180920111801.png","commentId":350,"commentTime":1536657596000,"commentUserId":303,"commentUserName":"烤冷面","greatNum":0,"hotComment":0,"isGreat":0},{"commentContent":";mz# wr4,fsoy jigcgt(vs3=vma,)rv u-cnd6je \n o/u=e=v jrshm,x[qvohh\ne;pylxamk4fua@6xsvklagyr .xdlsvusm *ea\nti","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-09-20/20180920111801.png","commentId":349,"commentTime":1536657593000,"commentUserId":303,"commentUserName":"烤冷面","greatNum":0,"hotComment":0,"isGreat":0}]
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
         * commentContent : shg
         * commentHeadPic : http://172.17.8.100/images/movie/head_pic/2018-11-23/20181123150015.unknown
         * commentId : 623
         * commentTime : 1542348367000
         * commentUserId : 1338
         * commentUserName : Luye666
         * greatNum : 0
         * hotComment : 0
         * isGreat : 0
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
    }
}
