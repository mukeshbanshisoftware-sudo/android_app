package utils;

import android.app.DatePickerDialog;
import android.content.Context;

import java.util.Calendar;

public class DatePickerHelper {

    public interface OnDateSelectedListener{
        void onDateSelected(String date);
    }

    public static void showDatePicker(
            Context context,
            OnDateSelectedListener listener){

        Calendar calendar = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(
                context,
                (view, year, month, day) -> {

                    String date = day + "/" + (month + 1) + "/" + year;

                    listener.onDateSelected(date);

                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        dialog.show();

    }

}