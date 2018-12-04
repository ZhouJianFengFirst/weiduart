package com.bw.movie.persenter;

import android.content.Context;

import com.bw.movie.R;
import com.bw.movie.mvp.view.AppDelegate;
import com.qfdqc.views.seattable.SeatTable;

public class ActivityBuyTicketPersenter extends AppDelegate {
    private Context context;
    private SeatTable seatTableView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_buy_ticket;
    }

    @Override
    public void initContext(Context context) {
        this.context=context;
    }

    @Override
    public void initData() {
        super.initData();
        //设置座位控件
        setSeatView();

    }

    private void setSeatView() {
        seatTableView = (SeatTable) getView(R.id.seatView);
        seatTableView.setScreenName("8号厅荧幕");//设置屏幕名称
        seatTableView.setMaxSelected(3);//设置最多选中
        seatTableView.setSeatChecker(new SeatTable.SeatChecker() {

            @Override
            public boolean isValidSeat(int row, int column) {
                if(column==2) {
                    return false;
                }
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                if(row==6&&column==6){
                    return true;
                }
                return false;
            }

            @Override
            public void checked(int row, int column) {

            }

            @Override
            public void unCheck(int row, int column) {

            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {
                return null;
            }

        });
        seatTableView.setData(10,15);

    }
}
