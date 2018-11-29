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
  //根据影院id 查影片
  public static final String CINEMAFLOW_URL = "/movieApi/movie/v1/findMovieListByCinemaId";
  //我的页面接口
  public static final String SELECT_URL = "/movieApi/user/v1/verify/findUserHomeInfo";
  public static final String UPDATE_URL = "/movieApi/user/v1/verify/modifyUserInfo";




}