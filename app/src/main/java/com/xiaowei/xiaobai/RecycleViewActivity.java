package com.xiaowei.xiaobai;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewActivity extends AppCompatActivity {
    /**
     * 列表view
     */
    private RecyclerView mRecyclerView;

    /**
     * 数据源
     */
    private List<CheckBoxTestBean> mList;

    /**
     * 选中的数据源
     */
    private List<CheckBoxTestBean> mCheckList;

    /**
     * 适配器
     */
    private CheckboxAdapter mAdapter;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecyclerView = new RecyclerView(this);
        mRecyclerView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.MATCH_PARENT));
        setContentView(mRecyclerView);
//        List<Integer> data = IntStream.range(0, 7).boxed().collect(Collectors.toList());
        mCheckList = new ArrayList<>();
        mList = new ArrayList<>();
        for (int index = 0; index < 33; index++) {
            mList.add(new CheckBoxTestBean(String.valueOf(index), false));
        }
//        data.forEach(index -> {
//            mList.add(new CheckBoxTestBean(String.valueOf(index), false));
//        });
        mAdapter = new CheckboxAdapter(this, mList, mCheckList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
    }

    static class CheckboxAdapter extends RecyclerView.Adapter<CheckboxAdapter.CheckBoxViewHolder> {
        private Context mContext;

        private List<CheckBoxTestBean> mList = new ArrayList<>();

        private List<CheckBoxTestBean> mCheckList = new ArrayList<>();

        public CheckboxAdapter(Context context, List<CheckBoxTestBean> list, List<CheckBoxTestBean> checkList) {
            this.mContext = context;
            this.mList = list;
            this.mCheckList = checkList;
        }

        @NonNull
        @Override
        public CheckBoxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            CheckBoxViewHolder checkBoxViewHolder = new CheckBoxViewHolder(LayoutInflater
                .from(mContext).inflate(R.layout.item_check_box, parent, false));
            return checkBoxViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull CheckBoxViewHolder holder, int position) {
            // 绑定数据
            if (mList == null)
                return;
            holder.mTvName.setText(mList.get(position).getName());
            holder.mCheckbox.setOnCheckedChangeListener(null);
            holder.mCheckbox.setChecked(mList.get(position).mIsSelected);
            holder.mCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    mList.get(position).setSelected(true);
                } else {
                    mList.get(position).setSelected(false);
                }
            });
        }

        @Override
        public int getItemCount() {
            if (mList != null && mList.size() > 0) {
                return mList.size();
            }
            return 0;
        }

        static class CheckBoxViewHolder extends RecyclerView.ViewHolder {
            private ConstraintLayout mRl;

            private CheckBox mCheckbox;

            private AppCompatTextView mTvName;

            public CheckBoxViewHolder(@NonNull View itemView) {
                super(itemView);
                mRl = itemView.findViewById(R.id.item_cl);
                mCheckbox = itemView.findViewById(R.id.item_checkbox);
                mTvName = itemView.findViewById(R.id.item_tv);
            }
        }
    }

    static class CheckBoxTestBean {
        private String mName;
        private boolean mIsSelected;

        public CheckBoxTestBean(String name, boolean isSelected) {
            mName = name;
            mIsSelected = isSelected;
        }

        public String getName() {
            return mName;
        }

        public void setName(String name) {
            mName = name;
        }

        public boolean getSelected() {
            return mIsSelected;
        }

        public void setSelected(boolean selected) {
            mIsSelected = selected;
        }
    }
}
