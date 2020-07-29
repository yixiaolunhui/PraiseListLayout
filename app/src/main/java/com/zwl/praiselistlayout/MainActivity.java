package com.zwl.praiselistlayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int MAX_COUNT = 4;
    private static final int WHAT = 100;

    private RecyclerView mRecyclerView;
    private PraiseListAdapter adapter;
    private PraiseListLayoutManager commentListLayoutManager;
    private Button mChangeBtn;
    private MyHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.comments);
        mChangeBtn = findViewById(R.id.change);
        adapter = new PraiseListAdapter(this, getData());
        mRecyclerView.setAdapter(adapter);
        commentListLayoutManager = new PraiseListLayoutManager(this, MAX_COUNT);
        mRecyclerView.setLayoutManager(commentListLayoutManager);

        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(1000);
        defaultItemAnimator.setRemoveDuration(0);
        mRecyclerView.setItemAnimator(defaultItemAnimator);


        setChangeTv();
        handler = new MyHandler(adapter);
    }



    static class MyHandler extends Handler {
        WeakReference<PraiseListAdapter> adapter;

        public MyHandler(PraiseListAdapter adapter) {
            this.adapter = new WeakReference<>(adapter);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            adapter.get().addComment(new PraiseBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1596008209698&di=4e90fdb998330ebdfdb8b7e2ec530765&imgtype=0&src=http%3A%2F%2Fimg3.imgtn.bdimg.com%2Fit%2Fu%3D229383263%2C1265004810%26fm%3D214%26gp%3D0.jpg"));
            adapter.get().removeFirst(MAX_COUNT);
            sendEmptyMessageDelayed(WHAT, 3000);
        }
    }


    /**
     * 测试数据
     *
     * @return
     */
    private List<PraiseBean> getData() {
        List<PraiseBean> datas = new ArrayList<>();
        datas.add(new PraiseBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1596010973769&di=d7165829809115ccaf8a9d43fdcbfd05&imgtype=0&src=http%3A%2F%2Fimg3.imgtn.bdimg.com%2Fit%2Fu%3D3863325703%2C1394660069%26fm%3D214%26gp%3D0.jpg"));
        datas.add(new PraiseBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1596010714309&di=e33cf500403fdf06d94dfd88f17b1675&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fdesign%2F00%2F69%2F49%2F81%2F58f9b73c7dfe1.png"));
        datas.add(new PraiseBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1596010938915&di=4468164ed96b9274955f6f2e83368e9a&imgtype=0&src=http%3A%2F%2Fimg2.imgtn.bdimg.com%2Fit%2Fu%3D2895512197%2C536595485%26fm%3D214%26gp%3D0.jpg"));
        datas.add(new PraiseBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1596010714308&di=fd28cfe454694de7689a1cf1ba5ab2e4&imgtype=0&src=http%3A%2F%2Fimgphoto.gmw.cn%2Fattachement%2Fjpg%2Fsite2%2F20161117%2Ff44d305ea4db19972b1132.jpg"));
        return datas;
    }

    /**
     * 自动添加  测试效果
     *
     * @param view
     */
    public void autoAddComment(View view) {
        handler.sendEmptyMessageDelayed(WHAT, 3000);
    }


    /**
     * 停止自动添加 测试效果
     * @param view
     */
    public void stopAutoAddComment(View view) {
        handler.removeMessages(WHAT);
    }


    /**
     * 添加数据
     *
     * @param view
     */
    public void addComment(View view) {
        adapter.addComment(new PraiseBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1596007557839&di=6eefa6c079e955648bd63933a18b8055&imgtype=0&src=http%3A%2F%2Fappimg.pba.cn%2F2015%2F10%2F21%2FC1329AFD2B1AA5ABFBC7E0427EDA8621.jpg"));
        adapter.removeFirst(MAX_COUNT);

    }

    /**
     * 清空数据
     *
     * @param view
     */
    public void clearComment(View view) {
        adapter.clear();
    }


    /**
     * 修改方向
     *
     * @param view
     */
    public void changeFX(View view) {
        commentListLayoutManager.setType(commentListLayoutManager.isRight() ? PraiseListLayoutManager.TYPE_LEFT :
                PraiseListLayoutManager.TYPE_RIGHT);
        setChangeTv();
    }

    /**
     * 切换按钮的文本显示
     */
    public void setChangeTv() {
        mChangeBtn.setText(commentListLayoutManager.isRight() ? "右" : "左");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(WHAT);
    }
}