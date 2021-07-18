package com.xiaowei.xiaobai.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.xiaowei.xiaobai.R;

import java.util.ArrayList;
import java.util.List;


/**
 * ViewPager2 懒加载
 */
public class ItemFragment extends LazyFragment {
    private List<PageInfo> list = new ArrayList<>();

    public ItemFragment(){
        list.add(new PageInfo(R.color.design_default_color_error,"还好"));
        list.add(new PageInfo(R.color.design_default_color_primary_dark,"背景"));
        list.add(new PageInfo(R.color.design_default_color_secondary,"aff"));
        list.add(new PageInfo(R.color.black,"fffg"));
        list.add(new PageInfo(R.color.purple_200,"积极加大"));
        list.add(new PageInfo(R.color.white,"jfakjf"));
    }

    @Override
    protected void initView(View rootView) {
        ViewPager2 viewPager = rootView.findViewById(R.id.fragment_viewpager);
        viewPager.setAdapter(new ViewPagerAdapter());
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_recycleview;
    }

    class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewPagerHolder> {

        @NonNull
        @Override
        public ViewPagerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewPagerHolder(LayoutInflater.from(parent.getContext()).inflate
                (R.layout.item_pager,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewPagerHolder holder, int position) {
            holder.mTextView.setText(list.get(position).pageName);
            holder.mRelativeLayout.setBackgroundResource(list.get(position).color);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewPagerHolder extends RecyclerView.ViewHolder {
            private RelativeLayout mRelativeLayout;

            private TextView mTextView;

            public ViewPagerHolder(@NonNull View itemView) {
                super(itemView);
                mRelativeLayout = itemView.findViewById(R.id.item_pager_container);
                mTextView = itemView.findViewById(R.id.item_pager_tvTitle);
            }
        }
    }

    class PageInfo {
        public int color;
        public String pageName;
        public PageInfo(int color, String pageName) {
            this.color = color;
            this.pageName = pageName;
        }
    }
}
