package com.tubes.sandangin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static com.tubes.sandangin.R.layout.tshirt_view_adapter;

public class TshirtViewAdapter extends BaseAdapter {

    private Context context;
    private List<Tshirt> result;

    public TshirtViewAdapter(Context context, List<Tshirt> result){
        this.context = context;
        this.result = result;
    }

    @Override
    public int getCount() {
        return result.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Tshirt tshirt;

        tshirt = result.get(position);

        if (convertView == null){
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(tshirt_view_adapter,null);
        }

        TextView namaItem = (TextView)convertView.findViewById(R.id.viewNamaItem);
        TextView detail = (TextView)convertView.findViewById(R.id.viewDetail);
        TextView ukuran = (TextView)convertView.findViewById(R.id.viewUkuran);
        TextView harga = (TextView)convertView.findViewById(R.id.viewHarga);
        ImageView fotoItem = (ImageView)convertView.findViewById(R.id.viewFotoItem);

        namaItem.setText(tshirt.getNamaItem());
        detail.setText(tshirt.getDetail());
        ukuran.setText(tshirt.getUkuran());
        harga.setText(String.valueOf(tshirt.getHarga()));
        fotoItem.setImageResource(tshirt.getFotoItem());
        return convertView;
    }
}
