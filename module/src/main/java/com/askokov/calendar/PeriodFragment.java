package com.askokov.calendar;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.askokov.calendar.period.Period;


public class PeriodFragment extends Fragment {
    private static final String TAG = "PeriodFragment";

    private TextView mTextPeriod;
    private TextView[] mColumns = new TextView[7];

    private Period current;
    private String[] monthName;
    private String[] monthWithDay;

    private OnFragmentInteractionListener mListener;

    public static PeriodFragment newInstance(Period current) {
        PeriodFragment fragment = new PeriodFragment();
        fragment.current = current;

        return fragment;
    }

    public PeriodFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_period, container, false);

        String[] week = view.getResources().getStringArray(R.array.week);
        monthName = view.getResources().getStringArray(R.array.month_name);
        monthWithDay = view.getResources().getStringArray(R.array.month_with_day);

        mTextPeriod = (TextView)view.findViewById(R.id.textPeriod);

        TextView day1 = (TextView)view.findViewById(R.id.day1);
        day1.setText(week[0]);

        TextView day2 = (TextView)view.findViewById(R.id.day2);
        day2.setText(week[1]);

        TextView day3 = (TextView)view.findViewById(R.id.day3);
        day3.setText(week[2]);

        TextView day4 = (TextView)view.findViewById(R.id.day4);
        day4.setText(week[3]);

        TextView day5 = (TextView)view.findViewById(R.id.day5);
        day5.setText(week[4]);

        TextView day6 = (TextView)view.findViewById(R.id.day6);
        day6.setText(week[5]);

        TextView day7 = (TextView)view.findViewById(R.id.day7);
        day7.setText(week[6]);

        mColumns[0] = day1;
        mColumns[1] = day2;
        mColumns[2] = day3;
        mColumns[3] = day4;
        mColumns[4] = day5;
        mColumns[5] = day6;
        mColumns[6] = day7;

        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initCalendar();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i(TAG, "onAttach");
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void setData(final Period current) {
        this.current = current;

        initCalendar();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Period changed);
    }

    private void initCalendar() {
        int dayOfWeek = current.getDate().getDayOfWeek() - 1;
        int month = current.getDate().getMonthOfYear() - 1;
        int day = current.getDate().getDayOfMonth();
        int year = current.getDate().getYear();

        switch (current.getType()) {
            case DAY:
                String textDay = String.format(monthWithDay[month], day, year);
                mTextPeriod.setText(textDay);
                break;

            case MONTH:
                String textMonth = String.format(monthName[month], year);
                mTextPeriod.setText(textMonth);
                break;
        }

        for(int i = 0; i < mColumns.length; i++) {
            if (i == dayOfWeek) {
                mColumns[i].setBackgroundResource(R.drawable.circle);
            } else {
                mColumns[i].setBackgroundResource(R.drawable.circle_grey);
            }
        }
    }
}
