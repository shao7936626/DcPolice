package cn.onecloudtech.sl.dcpolice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.onecloudtech.sl.dcpolice.R;
import cn.onecloudtech.sl.dcpolice.model.Jpushperson;

/**
 * Created by Administrator on 2016/9/14.
 */
public class PersonelInfoSearchAdapter extends RecyclerView.Adapter<PersonelInfoSearchAdapter.InfoSearchViewHolder> {


    private LayoutInflater inflater;
    private ArrayList<Jpushperson> jpushpersonArrayList;

    public PersonelInfoSearchAdapter(Context mContext, ArrayList<Jpushperson> jpushpersonArrayList) {

        inflater = LayoutInflater.from(mContext);
        this.jpushpersonArrayList = jpushpersonArrayList;
    }

    @Override
    public InfoSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.checkedperson_list_item, parent, false);
        return new InfoSearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InfoSearchViewHolder holder, int position) {
        InfoSearchViewHolder searchViewHolder = (InfoSearchViewHolder) holder;
//        holder.position = position;
        Context context = holder.itemView.getContext();


        Jpushperson mjpushPerson = jpushpersonArrayList.get(position);
        searchViewHolder.personname.setText(mjpushPerson.getRealname());
        searchViewHolder.idCard.setText(mjpushPerson.getIdcard());
        searchViewHolder.reMard.setText(mjpushPerson.getRemark());
        searchViewHolder.entryTime.setText(mjpushPerson.getCheckstarttime());
        searchViewHolder.checkTime.setText(mjpushPerson.getBackcheckresulttime());

        if (mjpushPerson != null)
            if (mjpushPerson.getIstrue() == 0) {
                //还未审批
                searchViewHolder.isTrue.setImageDrawable(context.getResources().getDrawable(R.drawable.wsp));
            } else if (mjpushPerson.getIstrue() == 1) {
                //正确
//            searchViewHolder.isTrue.setBackground(context.getResources().getDrawable(R.drawable.tg));
                searchViewHolder.isTrue.setImageDrawable(context.getResources().getDrawable(R.drawable.tg));
            } else if (mjpushPerson.getIstrue() == 2) {
                //不正确
                searchViewHolder.isTrue.setImageDrawable(context.getResources().getDrawable(R.drawable.wtg));
            }

    }

    @Override
    public int getItemCount() {
        return jpushpersonArrayList.size();
    }

    public static class InfoSearchViewHolder extends RecyclerView.ViewHolder {

        public TextView personname;
        public TextView idCard;
        public TextView reMard;
        public ImageView isTrue;
        public TextView entryTime;
        public TextView checkTime;

        public InfoSearchViewHolder(View itemView) {
            super(itemView);

            personname = (TextView) itemView.findViewById(R.id.tv_check_personname);
            idCard = (TextView) itemView.findViewById(R.id.tv_check_personidcard);
            reMard = (TextView) itemView.findViewById(R.id.tv_check_personremark);
            entryTime = (TextView) itemView.findViewById(R.id.tv_check_entrytime);
            checkTime = (TextView) itemView.findViewById(R.id.tv_check_checktime);
            isTrue = (ImageView) itemView.findViewById(R.id.iv_check_istrue);
//            ivPhoto   = (ImageView) itemView.findViewById(me.iwf.photopicker.R.id.iv_photo);
//            vSelected = itemView.findViewById(me.iwf.photopicker.R.id.v_selected);
//            vSelected.setVisibility(View.GONE);
        }
    }
}
