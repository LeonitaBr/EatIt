package com.bignerdranch.android.loginapp.ViewHolder;
import com.bignerdranch.android.loginapp.Interface.ItemClickListener;
import com.bignerdranch.android.loginapp.R;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by leoni on 4/27/2018.
 */

public class MenuviewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txtMenuName;
    public ImageView imageView;
    private ItemClickListener itemClickListener;
    public MenuviewHolder(View itemView) {
        super(itemView);
        txtMenuName=(TextView)itemView.findViewById(R.id.menu_name);
        imageView=(ImageView)itemView.findViewById(R.id.menu_image);

        itemView.setOnClickListener(this);
    }



    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    public void onClick(View view)
    {
        itemClickListener.onClick(view, getAdapterPosition(),false);
    }

}
