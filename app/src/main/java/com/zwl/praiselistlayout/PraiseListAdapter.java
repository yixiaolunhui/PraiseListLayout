package com.zwl.praiselistlayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zwl
 * @date on 2020/7/28
 */
class PraiseListAdapter extends RecyclerView.Adapter<PraiseListAdapter.ViewHolder> {


    private Context context;
    private List<PraiseBean> commentBeans;

    public PraiseListAdapter(Context context, List<PraiseBean> commentBeans) {
        this.context = context;
        this.commentBeans = commentBeans;
    }

    public void addComment(PraiseBean commentBean) {
        if (commentBeans == null) {
            commentBeans = new ArrayList<>();
        }
        commentBeans.add(commentBean);
        notifyItemRangeChanged(0, getItemCount());
    }


    public void clear() {
        commentBeans.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_praise_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(context).
                load(commentBeans.get(position).headUrl)
                .into(holder.headIcon);
        holder.headIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "position=" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return commentBeans == null ? 0 : commentBeans.size();
    }

    public void removeFirst(int count) {
        if (commentBeans != null && commentBeans.size() > count) {
            commentBeans.remove(0);
            notifyItemMoved(0, commentBeans.size());
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView headIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            headIcon = itemView.findViewById(R.id.head_icon);
        }
    }
}
