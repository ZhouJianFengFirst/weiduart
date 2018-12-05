package com.bw.movie.persenter;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityBuyTicket;
import com.bw.movie.entity.PayBean;
import com.bw.movie.entity.PlaceEntity;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.net.Http;
import com.bw.movie.utils.Logger;
import com.bw.movie.utils.Md5Util;
import com.bw.movie.utils.SpUtil;
import com.google.gson.Gson;
import com.qfdqc.views.seattable.SeatTable;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;

public class ActivityBuyTicketPersenter extends AppDelegate implements View.OnClickListener {
    private static final int BYMMOVIE_TICKET_CONTENT = 0x230;
    private static final int PAY_URL = 0x140;
    private Context context;
    private SeatTable seatTableView;
    private TextView cinema_name1, cinema_name2, cinema_dz1, cinema_date, cinema_hall, cinema_money;
    private String ccid, ccbegintime, ccendtime, cctime, ccname, seatsTotal, seatsUseCount, status, cinemaname, cinemaaddress, movename;
    private int num = 0;
    private RelativeLayout linearBuy;
    private RadioGroup rgSelect;
    private int flag = 1;
    private boolean pay = false;
    private PlaceEntity placeEntity;
    private IWXAPI api;

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
        linearBuy = (RelativeLayout) getView(R.id.layout_buy);
        rgSelect = (RadioGroup) getView(R.id.rg_select);
        rgSelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_1:
                        flag = 1;
                        break;
                    case R.id.radio_2:
                        flag = 2;
                        break;
                }
            }
        });
        setClick(this, R.id.cinema_simp_buy, R.id.cinema_simp_back, R.id.iv_deom, R.id.btn_pay);
    }


    /**
     * 设置动画
     *
     * @param view
     * @param in
     * @param to
     */
    public void setAnimation(View view, int in, int to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", in, to);
        animator.setDuration(1000);
        animator.start();
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
        api = WXAPIFactory.createWXAPI(context, "wxb3852e6a6b7d9516");
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
                num++;
                toast(context, num + "aa");
            }

            @Override
            public void unCheck(int row, int column) {
                num--;
                toast(context, num + "aa");
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
                if (pay) {
                    setAnimation(linearBuy, 500, 0);
                } else {
                    isPlace();
                }
                break;
            case R.id.cinema_simp_back://取消购买
                toast(context, "取消购买");
                ((ActivityBuyTicket) context).finish();
                break;
            case R.id.iv_deom:
                setAnimation(linearBuy, 0, 500);
                break;
            case R.id.btn_pay:
                goPayMyMovie();
                break;
        }
    }


    /**
     * 去支付
     */
    private void goPayMyMovie() {
        Map<String, String> hmap = new HashMap<>();
        hmap.put("userId", getUserId());
        hmap.put("sessionId", getUserSession());
        Map<String, String> fmap = new HashMap<>();
        fmap.put("payType", flag + "");
        fmap.put("orderId", placeEntity.getOrderId() + "");
        HeadOrFormPost(Http.PAY_RUL, PAY_URL, hmap, fmap);
    }

    /**
     * 获取userid
     *
     * @return
     */
    public String getUserId() {
        String userId = (String) SpUtil.getSpData(context, "userId", "");
        return userId;
    }

    /**
     * 获取Session
     *
     * @return
     */

    public String getUserSession() {
        String sessionId = (String) SpUtil.getSpData(context, "sessionId", "");
        return sessionId;
    }

    /**
     * 下单
     */
    private void place() {
        String sign = Md5Util.mD5(getUserId() + ccid + num + "movie");
        Map<String, String> hmap = new HashMap<>();
        hmap.put("userId", getUserId());
        hmap.put("sessionId", getUserSession());
        Map<String, String> fmap = new HashMap<>();
        fmap.put("scheduleId", ccid);
        fmap.put("amount", num + "");
        fmap.put("sign", sign);
        HeadOrFormPost(Http.BYM_MOVIE_TICKET_URL, BYMMOVIE_TICKET_CONTENT, hmap, fmap);
    }

    @Override
    public void successString(String data, int type) {
        super.successString(data, type);
        switch (type) {
            case BYMMOVIE_TICKET_CONTENT:
                setPlace(data);
                break;
            case PAY_URL:
                okPay(data);
                break;
        }
    }

    private void okPay(String data) {
        Logger.d("Taggersss", data);
        PayBean payBean = new Gson().fromJson(data, PayBean.class);
        if ("0000".equals(payBean.getStatus())){
            PayReq request = new PayReq();
            request.appId = payBean.getAppId();
            request.partnerId = payBean.getPartnerId();
            request.prepayId= payBean.getPrepayId();
            request.packageValue = payBean.getPackageValue();
            request.nonceStr= payBean.getNonceStr();
            request.timeStamp= payBean.getTimeStamp();
            request.sign= payBean.getSign();
            api.sendReq(request);


            return;
        }
        toast(context,payBean.getMessage());
    }


    /**
     * 提示下单成功
     *
     * @param data
     */

    private void setPlace(String data) {
        placeEntity = new Gson().fromJson(data, PlaceEntity.class);
        if ("0000".equals(placeEntity.getStatus())) {
            toast(context, "下单成功");
            pay = true;
            linearBuy.setVisibility(View.VISIBLE);
            setAnimation(linearBuy, 500, 0);
        } else {
            toast(context, "下单失败");
            pay = false;
        }
    }


    @Override
    public void failString(String msg) {
        super.failString(msg);
        Logger.w("Tagger1111", msg);
    }


    /**
     * 是否下单
     */
    public void isPlace() {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("下单");
        alertDialog.setIcon(R.mipmap.place);
        alertDialog.setMessage("是否下单");
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "下单", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                place();
            }
        });

        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();//关闭对话框
            }
        });
        alertDialog.show();//显示对话框
    }
}
