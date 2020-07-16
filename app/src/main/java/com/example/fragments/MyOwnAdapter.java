package com.example.fragments;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.time.Instant;
import java.util.ArrayList;

public class MyOwnAdapter extends RecyclerView.Adapter<MyOwnAdapter.MyOwnHolder> {
    String data1;
    String data2;

    Context ctx;
    ArrayList<NewsData> items1;

    public MyOwnAdapter(Context ct, ArrayList<NewsData> items) {
        ctx = ct;
        items1 = items;

    }

    @Override
    public MyOwnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myInflator = LayoutInflater.from((Context) ctx);
        View myview = myInflator.inflate(R.layout.my_row, parent, false);
        return new MyOwnHolder(myview);
    }

    @Override
    public void onBindViewHolder
            (@NonNull final MyOwnAdapter.MyOwnHolder holder, int position)

    {
        final NewsData Item = items1.get(position);
        String title = Item.getTitle();
        final String url = Item.getUrl();
        final String infoUrl = Item.getMore();

        holder.t1.setText(title);
        ImageView image = holder.geti();

        Picasso.with(ctx).load(url).into(image);
        holder.b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(String.valueOf(infoUrl)));
                ctx.startActivity(intent);

            }
        });
        holder.moreInfob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                Intent i = new Intent(ctx, moreNews.class);
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(String.valueOf(infoUrl)));
                ctx.startActivity(intent);
//                i.putExtra("info",infoUrl);
//
//                ctx.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return items1.size();
    }

    public class MyOwnHolder extends RecyclerView.ViewHolder {
        public ViewGroup parent;
        TextView t1, t2;
        ImageView i1,moreInfob;
        Button b;
        WebView editWeb;

        public MyOwnHolder(@NonNull View itemView) {
            super(itemView);
            t1 = (TextView) itemView.findViewById(R.id.text1);
            i1 = (ImageView) itemView.findViewById(R.id.myimage);
            b = (Button)itemView.findViewById(R.id.button);

            moreInfob = (ImageView) itemView.findViewById(R.id.moreInfp);


        }
        public ImageView geti() {
            return i1;
        }

    }
}