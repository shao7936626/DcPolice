package cn.onecloudtech.sl.dcpolice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.onecloudtech.sl.dcpolice.C;
import cn.onecloudtech.sl.dcpolice.R;


/**
 * Created by Administrator on 2016/10/9.
 */

public class FloatingPopulationPropertyAdapter extends RecyclerView.Adapter<FloatingPopulationPropertyAdapter.PropertyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<String> addressList;
    private Context mContext;

    public FloatingPopulationPropertyAdapter(Context mContext, ArrayList<String> addressList) {
        inflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.addressList = addressList;
    }

    @Override
    public FloatingPopulationPropertyAdapter.PropertyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.property_address_list_item, null, false);
        return new PropertyViewHolder(itemView, new MyCustomEditTextListener());
    }

    @Override
    public void onBindViewHolder(PropertyViewHolder holder, int position) {
        PropertyViewHolder propertyViewHolder = holder;
        Context context = holder.itemView.getContext();

        propertyViewHolder.address.setText(addressList.get(position));
        propertyViewHolder.address.setTag(position);
//        propertyViewHolder.address.addTextChangedListener();

    }


    @Override
    public int getItemCount() {
        return addressList.size();
    }

    public static class PropertyViewHolder extends RecyclerView.ViewHolder {
        public EditText address;
        public TextView addressName;
        public MyCustomEditTextListener myCustomEditTextListener;

        public PropertyViewHolder(View itemView, MyCustomEditTextListener myCustomEditTextListener) {
            super(itemView);
            addressName = (TextView) itemView.findViewById(R.id.tv_addressname);
            address = (EditText) itemView.findViewById(R.id.et_property_address);
            address.addTextChangedListener(myCustomEditTextListener);


        }
    }

    private class MyCustomEditTextListener implements TextWatcher {
        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            // no op
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            addressList.set(position, charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // no op

           
        }
    }

    ;


}
