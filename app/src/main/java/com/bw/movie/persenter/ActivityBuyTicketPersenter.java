package com.bw.movie.persenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityBuyTicket;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.SpUtil;
import com.qfdqc.views.seattable.SeatTable;

public class ActivityBuyTicketPersenter extends AppDelegate implements View.OnClickListener {
    private Context context;
    private SeatTable seatTableView;
    private TextView cinema_name1, cinema_name2, cinema_dz1, cinema_date, cinema_hall, cinema_money;
    private String ccid, ccbegintime, ccendtime, cctime, ccname, seatsTotal, seatsUseCount, status, cinemaname, cinemaaddress,movename;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_buy_ticket;
    }

    @Override
    public void initContext(Context context) {
        this.context = context;
    }

    @Override
    public void initData() {
        super.initData();
        //找控件
        initwidget();
        //初始化数据
        initdata();
        //设置座位控件
        setSeatView();

    }

    //找控件
    private void initwidget() {
        cinema_name1 = (TextView) getView(R.id.cinema_text_name1);
        cinema_name2 = (TextView) getView(R.id.cinema_text_name2);
        cinema_dz1 = (TextView) getView(R.id.cinema_text_dz1);
        cinema_date = (TextView) getView(R.id.cinema_text_date);
        cinema_hall = (TextView) getView(R.id.cinema_text_move_hall);
        cinema_money = (TextView) getView(R.id.cinema_text_money);
        setClick(this, R.id.cinema_simp_buy, R.id.cinema_simp_back);
    }

    //初始化本页面数据
    private void initdata() {
        Intent intent = ((ActivityBuyTicket) context).getIntent();
        ccid = intent.getStringExtra("ccid");
        ccbegintime = intent.getStringExtra("ccbegintime");
        ccendtime = intent.getStringExtra("ccendtime");
        cctime = intent.getStringExtra("cctime");
        ccname = intent.getStringExtra("ccname");
        seatsTotal = intent.getStringExtra("seatsTotal");
        seatsUseCount = intent.getStringExtra("seatsUseCount");
        status = intent.getStringExtra("status");
        cinemaname = (String) SpUtil.getSpData(context, "cinemaname", "");
        cinemaaddress = (String) SpUtil.getSpData(context, "cinemaaddress", "");
        movename = (String) SpUtil.getSpData(context, "movename", "");
        cinema_hall.setText(ccname);
        cinema_name1.setText(cinemaname);
        cinema_dz1.setText(cinemaaddress);
        cinema_name2.setText(movename);
        cinema_date.setText(ccbegintime + " - " + ccendtime);
    }

    //设置座位自定义view
    private void setSeatView() {
        seatTableView = (SeatTable) getView(R.id.seatView);
        seatTableView.setScreenName(ccname);//设置屏幕名称
        seatTableView.setMaxSelected(10);//设置最多选中
        seatTableView.setSeatChecker(new SeatTable.SeatChecker() {

            @Override
            public boolean isValidSeat(int row, int column) {
                if (column == 2) {
                    return false;
                }
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                if (row == 6 && column == 6) {
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
        seatTableView.setData(10, 15);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cinema_simp_buy:
                toast(context, "购买");
                break;
            case R.id.cinema_simp_back://取消购买
                toast(context, "取消购买");
                ((ActivityBuyTicket) context).finish();
                break;
        }
    }
}
