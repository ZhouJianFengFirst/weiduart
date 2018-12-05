package com.bw.movie.net;

/**
 * date:2018/10/17
 */
public class Http {

    public static final String BASE_URL = "http://mobile.bwstudent.com/";

    public static final String SOONMOVIELIST_URL = "/movieApi/movie/v1/findComingSoonMovieList";
    public static final String LOGIN_URL = "/movieApi/user/v1/login";

    public static final String HOTMOVIELIST_URL = "/movieApi/movie/v1/findHotMovieList";

    public static final String RELEAASEMOVIELIST_URL = "/movieApi/movie/v1/findReleaseMovieList";
    // 影院详情
    public static final String CINEMADETAILS_URL = "/movieApi/cinema/v1/findCinemaInfo";
    // 影院推荐与附近
    public static final String CINEMARE_URL = "/movieApi/cinema/v1/findRecommendCinemas";
    //所有影院
    public static final String CINEMAALL_URL = "/movieApi/cinema/v1/findAllCinemas";
    //影院搜索
    public static final String CINEMASEARCH_URL = "/movieApi/cinema/v1/findAllCinemas";
    //影院评论列表
    public static final String CINEMARIGHT_URL = "/movieApi/cinema/v1/findAllCinemaComment";
    //影院发表评论
    public static final String CINEMARIGHT_WRITE_URL = "/movieApi/cinema/v1/verify/cinemaComment";
    //影院评论点赞
    public static final String CINEMARIGHT_DZ_URL = "/movieApi/cinema/v1/verify/cinemaCommentGreat";
    //根据影院id 查影片
    public static final String CINEMAFLOW_URL = "/movieApi/movie/v1/findMovieListByCinemaId";
    //影院关注
    public static final String CINEMAHEART_URL = "/movieApi/cinema/v1/verify/followCinema";
    //影院取关
    public static final String CINEMAHEART_NO_URL = "/movieApi/cinema/v1/verify/cancelFollowCinema";
    //影院影片查影片场次
    public static final String CINEMASESSION_URL = "/movieApi/movie/v1/findMovieScheduleList";

    //我的页面接口
    public static final String SELECT_URL = "/movieApi/user/v1/verify/findUserHomeInfo";
    public static final String UPDATA_URL = "/movieApi/user/v1/verify/modifyUserInfo";
    public static final String INFORM_URL = "/movieApi/tool/v1/verify/findAllSysMsgList";
    public static final String INFORMCHECKED_URL = "/movieApi/tool/v1/verify/changeSysMsgStatus";
    public static final String INFORMNUM_URL = "/movieApi/tool/v1/verify/findUnreadMessageCount";
    public static final String NEWEST_URL = "/movieApi/tool/v1/findNewVersion";
    public static final String HISTORY_URL = "/movieApi/user/v1/verify/findUserBuyTicketRecordList";
    public static final String RESETPWD_URL = "/movieApi/user/v1/verify/modifyUserPwd";
    public static final String OPINION_URL = "/movieApi/tool/v1/verify/recordFeedBack";
    public static final String UPLOAD_URL = "/movieApi/user/v1/verify/uploadHeadPic";
    public static final String SELECTMOVIE_URL = "/movieApi/movie/v1/verify/findMoviePageList";
    public static final String SELECTFILM_URL = "/movieApi/cinema/v1/verify/findCinemaPageList";
    //影片关注
    public static final String FOLLOW_MOVIE = "/movieApi/movie/v1/verify/followMovie";

    //影片取消关注
    public static final String CANCEL_MOVIE = "/movieApi/movie/v1/verify/cancelFollowMovie";

    //影院详情
    public static final String FINDMOVIE_URL = "/movieApi/movie/v1/findMoviesDetail";

    //查询评论
    public static final String FINDALLMOVIE_COMMENT_URL = "/movieApi/movie/v1/findAllMovieComment";

    //点赞
    public static final String COMMENTGREAT_URL = "/movieApi/movie/v1/verify/movieCommentGreat";

    //更加影片id查询出对应的影院
    public static final String FINDCINEMAS_BYMOVIEID_URL = "/movieApi/movie/v1/findCinemasListByMovieId";

    //下单
    public static final String BYM_MOVIE_TICKET_URL = "/movieApi/movie/v1/verify/buyMovieTicket";

    //支付
    public static final String PAY_RUL = "/movieApi/movie/v1/verify/pay";
}