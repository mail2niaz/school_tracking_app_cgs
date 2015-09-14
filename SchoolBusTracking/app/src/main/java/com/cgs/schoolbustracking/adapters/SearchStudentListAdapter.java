package com.cgs.schoolbustracking.adapters;

/**
 * Created by ramya on 03/09/2015.
 */
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cgs.schoolbustracking.R;
import com.cgs.schoolbustracking.activities.AreaBasedStudentListActivity;
import com.cgs.schoolbustracking.activities.StudentDetailActivity;
import com.cgs.schoolbustracking.db.DatabaseHandler;
import com.cgs.schoolbustracking.models.StudentDetailModel;

import java.util.ArrayList;
import java.util.List;


public class SearchStudentListAdapter extends RecyclerView.Adapter<SearchStudentListAdapter.ViewHolder> implements Filterable {
    @SuppressWarnings("unused")
    private static final String TAG = AreaBasedStudentListAdapter.class.getSimpleName();

    Context mContext;
    int count =0;

    private List<StudentDetailModel> studentsList;
    ArrayList<String> list = new ArrayList<>();
    StudentDetailModel studentDetailModel;
    DatabaseHandler db_student;
    AreaBasedStudentListActivity areaBasedStudentListActivity;


    private ValueFilter valueFilter;
    private ArrayList<StudentDetailModel> mStringFilterList;

    public SearchStudentListAdapter(List<StudentDetailModel> studentsList, Context mContext){
        this.mContext = mContext;
        db_student=new DatabaseHandler(mContext);
       // if(db_student.getStudentsList().size()==0){
            this.studentsList = studentsList;
     //   }
      //  this.studentsList = db_student.getStudentsList();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final int layout = R.layout.listitem_studentlist;

        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final StudentDetailModel item = studentsList.get(position);
        holder. bindItem(item);
        holder.txtStudentName.setText(studentsList.get(position).getName());
        holder.txtClass.setText(studentsList.get(position).getClasss());
       // holder.txtPhoneNumber.setText(studentsList.get(position).getParentNumber());
        int resId = mContext.getResources().getIdentifier(studentsList.get(position).getName().toLowerCase(), "drawable", mContext.getPackageName());
        Drawable image = mContext.getResources().getDrawable(resId);
        holder.imgStudent.setImageDrawable(image);
        if(studentsList.get(position).isStudentCheckIn()){

            count = count+1;

            holder.imgTick.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.ic_done_red));
        }else{

            holder.imgTick.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.ic_done_lightgrey));
            count = count-1;

        }
        holder.imgStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(view.getContext(),StudentDetailActivity.class);
                i.putExtra("item",item);
                view.getContext().startActivity(i);


            }
        });
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
        TextView txtStudentName;
        TextView txtClass;
        TextView txtPhoneNumber;
        ImageView imgStudent;
        ImageView imgTick;
        int count = 0;
        private StudentDetailModel mItem;


        public ViewHolder(View itemView) {
            super(itemView);

            txtStudentName = (TextView) itemView.findViewById(R.id.student_name_txt);
            txtClass = (TextView) itemView.findViewById(R.id.student_class_txt);
            //txtPhoneNumber = (TextView) itemView.findViewById(R.id.student_parent_phonenumber_txt);
            imgStudent = (ImageView) itemView.findViewById(R.id.student_img);
            imgTick = (ImageView) itemView.findViewById(R.id.student_inout_img);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            list.clear();
            db_student.updateStudent(studentsList.get(getPosition()));

            if(studentsList!=null){
                studentsList.clear();
                studentsList = db_student.getStudentsList();
                notifyDataSetChanged();
            }

            for(int i=0 ;i<studentsList.size();i++){
                if(studentsList.get(i).isStudentCheckIn()){
                    list.add(studentsList.get(i).getName());
                }
            }

        }

        public void bindItem(StudentDetailModel item) {
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


            studentsList = (ArrayList<StudentDetailModel>) results.values;

            notifyDataSetChanged();


        }
    }

}
