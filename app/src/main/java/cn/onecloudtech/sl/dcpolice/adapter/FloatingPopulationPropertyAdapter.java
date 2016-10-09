package cn.onecloudtech.sl.dcpolice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.onecloudtech.sl.dcpolice.R;


/**
 * Created by Administrator on 2016/10/9.
 */

public class FloatingPopulationPropertyAdapter extends RecyclerView.Adapter<FloatingPopulationPropertyAdapter.PropertyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<String> addressList;

    public FloatingPopulationPropertyAdapter(Context mContext, ArrayList<String> addressList) {
        inflater = LayoutInflater.from(mContext);
        this.addressList = addressList;
    }

    @Override
    public FloatingPopulationPropertyAdapter.PropertyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.property_address_list_item, null, false);
        return new PropertyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PropertyViewHolder holder, int position) {
        PropertyViewHolder propertyViewHolder = holder;
        Context context = holder.itemView.getContext();

        propertyViewHolder.address.setText(addressList.get(position));

    }


    @Override
    public int getItemCount() {
        return addressList.size();
    }

    public static class PropertyViewHolder extends RecyclerView.ViewHolder {
        public EditText address;
        public TextView addressName;
        public PropertyViewHolder(View itemView) {
            super(itemView);
            addressName = (TextView)itemView.findViewById(R.id.tv_addressname);
            address = (EditText) itemView.findViewById(R.id.et_property_address);

        }
    }
}
