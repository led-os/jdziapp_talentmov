package com.movjdzi.app.adapter.vip;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movjdzi.app.R;

import me.drakeet.multitype.ItemViewBinder;

/**
 * @author huangyong
 * createTime 2019-09-19
 */
public class VipShareItemSectionnViewBinder extends ItemViewBinder<VipShareItemSectionn, VipShareItemSectionnViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_vip_share_item_sectionn, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull VipShareItemSectionn vipShareItemSectionn) {
        if (vipShareItemSectionn.getVipShareClickListener() != null) {
            holder.toShare.setOnClickListener(v -> vipShareItemSectionn.getVipShareClickListener().clickShare(holder.getAdapterPosition()));
        }
        holder.shareDesc.setText(vipShareItemSectionn.getVipShareContent());
        holder.shareTitle.setText(vipShareItemSectionn.getVipShareTitle());
        holder.coinDesc.setText(vipShareItemSectionn.getVipShareCoinDesc());
        Glide.with(holder.itemView.getContext()).load(vipShareItemSectionn.getVipShareIcon()).into(holder.vipIcon);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView toShare;
        TextView shareTitle;
        ImageView vipIcon;
        TextView shareDesc;
        TextView coinDesc;
        ViewHolder(View itemView) {
            super(itemView);
            toShare = itemView.findViewById(R.id.toShare);
            shareTitle = itemView.findViewById(R.id.share_title);
            vipIcon = itemView.findViewById(R.id.vip_icon);
            shareDesc = itemView.findViewById(R.id.share_desc);
            coinDesc = itemView.findViewById(R.id.coin_desc);
        }
    }

    public interface VipShareClickListener {
        void clickShare(int position);
    }
}
