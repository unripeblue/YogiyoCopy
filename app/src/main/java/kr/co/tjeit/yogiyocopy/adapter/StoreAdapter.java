package kr.co.tjeit.yogiyocopy.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.yogiyocopy.R;

import kr.co.tjeit.yogiyocopy.data.StoreData;

/**
 * Created by the on 2017-08-02.
 */

public class StoreAdapter extends ArrayAdapter<StoreData> {

    Context mContext;
    List<StoreData> mList;
    LayoutInflater inf;

    public StoreAdapter(Context context, List<StoreData> list) {
        super(context, R.layout.store_list_item, list);

        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = inf.inflate(R.layout.store_list_item, null);
            // 뷰를 새로 그려주는 작업
            // 재활용하는 시점에는 들어오지 않는 부분
            // 절대 이 안에서 데이터 세팅 X
        }

        // 데이터를 찍어줄 때 해야할 일들

        // 1. 데이터를 표시할 View들에 id를 부여
        // 2. 표시할 데이터 가져오기
        // 저장된 위치는? mList
        // 상황에 맞는 데이터 뽑아오기
        // 1) 뽑아온다? => mList.get
        // 2) 상황에 맞는? => 각 줄의 번호에 맞는 데이터를 가져오기.
        // 3) 뽑아낸 데이터를 변수에 저장. 어떤 변수? 다루는 자료형

        StoreData myStoreData = mList.get(position);

        ImageView storeImg = (ImageView) row.findViewById(R.id.storeImg);

        ImageView starImg1 = (ImageView) row.findViewById(R.id.starImg1);
        ImageView starImg2 = (ImageView) row.findViewById(R.id.starImg2);
        ImageView starImg3 = (ImageView) row.findViewById(R.id.starImg3);
        ImageView starImg4 = (ImageView) row.findViewById(R.id.starImg4);
        ImageView starImg5 = (ImageView) row.findViewById(R.id.starImg5);

        TextView openAndCloseTimeTxt = (TextView) row.findViewById(R.id.openAndCloseTimeTxt);
        TextView payMethodTypeTxt = (TextView) row.findViewById(R.id.payMethodTypeTxt);
        TextView storeNameTxt = (TextView) row.findViewById(R.id.storeNameTxt);
        TextView storeRateTxt = (TextView) row.findViewById(R.id.storeRateTxt);
        TextView storeMinPurchaseTxt = (TextView) row.findViewById(R.id.storeMinPurchaseTxt);
        ImageView cesCoImg = (ImageView) row.findViewById(R.id.cesCoImg);

        // 4) 실제 데이터를 뷰에 세팅.
        // set??를 해준다. ??=> 뷰의 종류에 따라 다르다 (img, txt 등)
        // 실제 데이터? data (StoreData) 변수
        // data가 가진 메소드들 중 getter를 활용하는 경우가 많다.

        storeNameTxt.setText(myStoreData.getStoreName());
        storeRateTxt.setText(myStoreData.getAvgRating()+"점 / "+myStoreData.getReviews().size()+"개의 리뷰");
        int openHour = myStoreData.getOpenTime() / 100;
        int openMinute = myStoreData.getOpenTime() % 100;
        int closeHour = myStoreData.getCloseTime() /100;
        int closeMinute = myStoreData.getCloseTime() %100;
        String openCloseStr = String.format("%02d:%02d - %02d:%02d",openHour, openMinute, closeHour, closeMinute);
       openAndCloseTimeTxt.setText(openCloseStr);
        storeMinPurchaseTxt.setText(NumberFormat.getInstance().format(myStoreData.getMinCost())+"원 이상 배달");

        if (myStoreData.isCesco()) {
            cesCoImg.setVisibility(View.VISIBLE);
        }
        else {
            cesCoImg.setVisibility(View.INVISIBLE);
        }

        List<ImageView> stars = new ArrayList<>();
        stars.add(starImg1);
        stars.add(starImg2);
        stars.add(starImg3);
        stars.add(starImg4);
        stars.add(starImg5);
        int rating = (int) myStoreData.getAvgRating();
        for (int i=0; i<rating; i++) {
            stars.get(i).setImageResource(R.drawable.gold_star);
        }




        return row;
    }
}
