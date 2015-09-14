package com.cgs.schoolbustracking.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.cgs.schoolbustracking.R;
import com.cgs.schoolbustracking.utils.AppConstants;
import com.cgs.schoolbustracking.utils.AppPreference;

import net.steamcrafted.materialiconlib.MaterialDrawableBuilder;
import net.steamcrafted.materialiconlib.MaterialIconView;

import java.util.ArrayList;



/**
 * Created by ramya on 22/07/2015.
 */
public class NavigationListAdapter extends BaseAdapter{

    Context c;
    ArrayList<String> list;
    LayoutInflater inflater;
String role;
    public NavigationListAdapter(Context c, ArrayList<String> list){
        this.c=c;
        this.list=list;

        role = AppPreference.getInstance(c).getString(
                AppPreference.StringKeys.ROLE);
        inflater = LayoutInflater.from(this.c);
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listitem_navigation, viewGroup, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        //NavigationListData currentListData = getItem(position);

        mViewHolder.tvTitle.setText(list.get(position));


        if(position==1){


            mViewHolder.navhome_icon.setVisibility(View.GONE);

            if(role.equalsIgnoreCase(AppConstants.LOGIN_RM)) {
                mViewHolder.formIcon.setIcon(MaterialDrawableBuilder.IconValue.CAMERA);
            }
            mViewHolder.formIcon.setVisibility(View.VISIBLE);


        }else if(position ==list.size()-1){
            mViewHolder.navhome_icon.setVisibility(View.GONE);
            mViewHolder.logoutIcon.setVisibility(View.VISIBLE);
        }
        else if(position ==2){
            mViewHolder.navhome_icon.setVisibility(View.GONE);
            mViewHolder.createPodIcon.setVisibility(View.VISIBLE);
        }
        else if(position ==3){
            mViewHolder.navhome_icon.setVisibility(View.GONE);
            mViewHolder.viewPodIcon.setVisibility(View.VISIBLE);
        }
       // mViewHolder.ivIcon.setImageResource(currentListData.getImage());

        return convertView;
    }

    private class MyViewHolder {
        TextView tvTitle, tvDesc;
        MaterialIconView navhome_icon;
        MaterialIconView formIcon;
        MaterialIconView logoutIcon;
        MaterialIconView createPodIcon;
        MaterialIconView viewPodIcon;

        public MyViewHolder(View item) {
//            tvTitle = (TextView) item.findViewById(R.id.tvTitle);
//            navhome_icon = (MaterialIconView) item.findViewById(R.id.navhome_icon);
//            formIcon = (MaterialIconView) item.findViewById(R.id.form_icon);
//            logoutIcon = (MaterialIconView) item.findViewById(R.id.logout_icon);
//            createPodIcon = (MaterialIconView) item.findViewById(R.id.create_pod_icon);
//            viewPodIcon = (MaterialIconView) item.findViewById(R.id.view_pod_icon);
        }
    }
}
