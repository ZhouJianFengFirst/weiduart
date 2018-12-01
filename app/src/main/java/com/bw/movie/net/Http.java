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
    //影院搜索
    public static final String CINEMASEARCH_URL = "/movieApi/cinema/v1/findAllCinemas";
    //影院评论
    public static final String CINEMARIGHT_URL = "/movieApi/cinema/v1/findAllCinemaComment";
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

    //影片关注
    public static final String FOLLOW_MOVIE = "/movieApi/movie/v1/verify/followMovie";

    //影片取消关注
    public static final String CANCEL_MOVIE = "/movieApi/movie/v1/verify/cancelFollowMovie";
    public static final String FINDMOVIE_URL = "/movieApi/movie/v1/findMoviesDetail";
}