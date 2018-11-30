package com.bw.movie.entity;

import java.util.List;
/**
*作者：gaojiabao
*时间：2018/11/30 9:13
*作用：影院搜索bean
*/
public class CinemaSearchBean {

    /**
     * result : [{"address":"北京市西城区天桥南大街3号楼一层、二层（天桥艺术大厦南侧）","commentTotal":0,"distance":0,"followCinema":false,"id":3,"logo":"http://172.17.8.100/images/movie/logo/sd.jpg","name":"首都电影院"}]
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
         * address : 北京市西城区天桥南大街3号楼一层、二层（天桥艺术大厦南侧）
         * commentTotal : 0
         * distance : 0
         * followCinema : false
         * id : 3
         * logo : http://172.17.8.100/images/movie/logo/sd.jpg
         * name : 首都电影院
         */

        private String address;
        private int commentTotal;
        private int distance;
        private boolean followCinema;
        private int id;
        private String logo;
        private String name;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getCommentTotal() {
            return commentTotal;
        }

        public void setCommentTotal(int commentTotal) {
            this.commentTotal = commentTotal;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public boolean isFollowCinema() {
            return followCinema;
        }

        public void setFollowCinema(boolean followCinema) {
            this.followCinema = followCinema;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
