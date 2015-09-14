package com.cgs.schoolbustracking.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.cgs.schoolbustracking.R;
import com.cgs.schoolbustracking.activities.AreaBasedStudentListActivity;
import com.cgs.schoolbustracking.activities.StudentDetailActivity;
import com.cgs.schoolbustracking.db.DatabaseHandler;
import com.cgs.schoolbustracking.models.BusStopNameModel;
import com.cgs.schoolbustracking.models.StudentDetailModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ramya on 08/09/2015.
 */

public class TripBusstopNameLisAdapter extends RecyclerView.Adapter<TripBusstopNameLisAdapter.ViewHolder> implements Filterable {
    @SuppressWarnings("unused")
    private static final String TAG = AreaBasedStudentListAdapter.class.getSimpleName();

    Context mContext;
    int count =0;

    private List<BusStopNameModel> studentsList;
    ArrayList<String> list = new ArrayList<>();
    StudentDetailModel studentDetailModel;
    DatabaseHandler db_student;
    AreaBasedStudentListActivity areaBasedStudentListActivity;


    private ValueFilter valueFilter;
    private ArrayList<StudentDetailModel> mStringFilterList;

    public TripBusstopNameLisAdapter(List<BusStopNameModel> studentsList, Context mContext){
        this.mContext = mContext;
        db_student=new DatabaseHandler(mContext);

          // if(db_student.getStudentsList().size()==0){
        this.studentsList = studentsList;
//          }
//        this.studentsList = db_student.getBusStopList();
        Log.v(TAG, "busStopNameList---->" + studentsList.size());

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final int layout = R.layout.listview_tripbusstop_name;

        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final BusStopNameModel item = studentsList.get(position);
        holder. bindItem(item);
        holder.txtBustopName.setText(position+1+".  " + item.getBusStopName());
        holder.txtCount.setText(item.getBusStopCount());

    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        // final StudentDetailModel item = studentsList.get(position);

        return position;
    }


    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtBustopName;
        TextView txtCount;

        int count = 0;
        private BusStopNameModel mItem;


        public ViewHolder(View itemView) {
            super(itemView);

            txtBustopName = (TextView) itemView.findViewById(R.id.trip_bustopname_txt);
            txtCount = (TextView) itemView.findViewById(R.id.trip_bustop_students_count_txt);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Intent i = new Intent(view.getContext(), AreaBasedStudentListActivity.class);
            list.clear();
            for(int j=0;j<getPosition();j++){
                studentsList.get(j).getBusStopName();
                Log.v("studentsList bustop", "studentsList bustop--->" + studentsList.get(j).getBusStopName());
                list.add( studentsList.get(j).getBusStopName());
            }
            i.putExtra("busstopnames", list);
            i.putExtra("busstopname", mItem);
            view.getContext().startActivity(i);
        }

        public void bindItem(BusStopNameModel item) {
            mItem = item;
        }
    }

    //Returns a filter that can be used to constrain data with a filtering pattern.
    @Override
    public Filter getFilter() {

        if(valueFilter==null) {

            valueFilter=new ValueFilter();
        }

        return valueFilter;
    }


    private class ValueFilter extends Filter {


        //Invoked in a worker thread to filter the data according to the constraint.
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {

                ArrayList<String> filterList = new ArrayList<String>();

                for (int i = 0; i < mStringFilterList.size(); i++) {

                    if (mStringFilterList.get(i).getName().contains(constraint)) {

                        filterList.add(mStringFilterList.get(i).getName());

                    }
                }


                results.count = filterList.size();

                results.values = filterList;

            } else {

                results.count = mStringFilterList.size();

                results.values = mStringFilterList;

            }

            return results;
        }


        //Invoked in the UI thread to publish the filtering results in the user interface.
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,

                                      Filter.FilterResults results) {


            studentsList = (ArrayList<BusStopNameModel>) results.values;

            notifyDataSetChanged();


        }
    }

}
