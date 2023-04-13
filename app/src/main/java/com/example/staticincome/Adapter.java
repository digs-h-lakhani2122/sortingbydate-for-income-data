package com.example.staticincome;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    List<ListItem> consolidatedList;

    public Adapter(Context context, List<ListItem> consolidatedList) {
        this.consolidatedList = consolidatedList;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {

            case ListItem.TYPE_GENERAL:
                View v1 = inflater.inflate(R.layout.items, parent, false);
                viewHolder = new GeneralViewHolder(v1);
                break;

            case ListItem.TYPE_DATE:
                View v2 = inflater.inflate(R.layout.item_header, parent, false);
                viewHolder = new DateViewHolder(v2);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        switch (viewHolder.getItemViewType()) {

            case ListItem.TYPE_GENERAL:

                GeneralItem generalItem = (GeneralItem) consolidatedList.get(position);
                GeneralViewHolder generalViewHolder = (GeneralViewHolder) viewHolder;
                generalViewHolder.categoryTextView.setText(generalItem.getPojoOfJsonArray().getCategory());
                generalViewHolder.noteTextView.setText(generalItem.getPojoOfJsonArray().getNote());
                generalViewHolder.accountTextView.setText(generalItem.getPojoOfJsonArray().getAccount());
                generalViewHolder.incomeTextView.setText(generalItem.getPojoOfJsonArray().getAmount());

//                total += generalItem.getPojoOfJsonArray().getAmount();

//                for (int i = 0; i < consolidatedList.size(); i++) {
//                    totalQuantity = totalQuantity + Integer.parseInt(generalItem.getPojoOfJsonArray().getAmount());
//                }
//                generalViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
////                        for (int i = 0; i < consolidatedList.size(); i++)
////                        {
////                    totalQuantity = totalQuantity + Integer.parseInt(generalItem.getPojoOfJsonArray().getAmount());
////                }
//                        for(int i=0; i<consolidatedList.size(); i++)
//                        {
//                            totalQuantity = totalQuantity + Integer.parseInt(generalItem.getPojoOfJsonArray().getAmount());
//                        }
////                        total = total+generalItem.getPojoOfJsonArray().getAmount();
//                        Toast.makeText(mContext, "total=" + totalQuantity, Toast.LENGTH_SHORT).show();
//                    }
//                });

                break;

            case ListItem.TYPE_DATE:
                DateItem dateItem = (DateItem) consolidatedList.get(position);
                DateViewHolder dateViewHolder = (DateViewHolder) viewHolder;

                String dateString = dateItem.getDate();
                @SuppressLint("SimpleDateFormat")
                DateFormat df = new SimpleDateFormat("dd/MM/yy");

                @SuppressLint("SimpleDateFormat")
                DateFormat df2 = new SimpleDateFormat("EEE");

                Date readDate = null;
                try {
                    readDate = df.parse(dateString);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(readDate.getTime());

                String date = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
                int dateFinal = Integer.parseInt(date);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);

                cal.set(month, dateFinal);
                Date date2 = new Date(year, month, dateFinal - 1);
                Date date3 = new Date(year, month, dateFinal);

                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat format = new SimpleDateFormat("MM");

                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat formatDate = new SimpleDateFormat("dd");

                String dayMonthFormat = format.format(date2);
                String dateFormat = formatDate.format(date3);
                String monthYear = dayMonthFormat + "." + year;
                String dayOfWeek = df2.format(date2);

                dateViewHolder.dateTextView.setText(dateFormat);
                dateViewHolder.dayTextView.setText(dayOfWeek);
                dateViewHolder.month_yearTextView.setText(monthYear);

                Log.d(TAG, "YearMonth: " + monthYear);
                Log.d(TAG, "Date: " + dateFormat);
                Log.d(TAG, "Day: " + dayOfWeek);

//                dateViewHolder.total_incomeTextView.setText(checkmarks(dateItem.getTotal_income()));
//                dateViewHolder.txtTitle.setText(dateItem.getDate());
                // Populate date item data here
                break;
        }
    }


    // ViewHolder for date row item
    class DateViewHolder extends RecyclerView.ViewHolder {
        protected TextView dateTextView, dayTextView, month_yearTextView, total_incomeTextView;

        public DateViewHolder(View v) {
            super(v);
            this.dateTextView = (TextView) v.findViewById(R.id.dateTextView);
            this.dayTextView = (TextView) v.findViewById(R.id.dayTextView);
            this.month_yearTextView = (TextView) v.findViewById(R.id.month_yearTextView);
            this.total_incomeTextView = (TextView) v.findViewById(R.id.total_incomeTextView);

        }
    }

    // View holder for general row item
    class GeneralViewHolder extends RecyclerView.ViewHolder {
        protected TextView categoryTextView, noteTextView, accountTextView, incomeTextView;

        public GeneralViewHolder(View v) {
            super(v);
            this.categoryTextView = (TextView) v.findViewById(R.id.categoryTextView);
            this.noteTextView = (TextView) v.findViewById(R.id.noteTextView);
            this.accountTextView = (TextView) v.findViewById(R.id.accountTextView);
            this.incomeTextView = (TextView) v.findViewById(R.id.incomeTextView);

        }
    }

    @Override
    public int getItemViewType(int position) {
        return consolidatedList.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return consolidatedList != null ? consolidatedList.size() : 0;
    }


}
