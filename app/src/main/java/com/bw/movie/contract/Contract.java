package com.bw.movie.contract;

public interface Contract {
    interface BackDataListener {
        void followMovie(int movieId, boolean state);
    }

    interface BackFlagListener {
        void backFlag(int flag, int commentId);
    }

    interface BackIsFocusListener {
        void backFocus(boolean focus, int CinemaId);
    }

}
