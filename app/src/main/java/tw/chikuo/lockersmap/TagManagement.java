package tw.chikuo.lockersmap;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Chi on 2016/10/23.
 */
public class TagManagement {

    private Context context;
    private LinearLayout mainLayout;
    private List<String> tagNameList;

    public TagManagement(Context context, LinearLayout mainLayout, List<String> tagNameList) {

        this.context = context;
        this.mainLayout = mainLayout;
        this.tagNameList = tagNameList;

        // Find maxWidth : screen width
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int maxWidth = (int) (displaymetrics.widthPixels * 0.8);
        int eachTextWidth = 0;

        // Create new line
        LinearLayout newLine = addNewLine(mainLayout);

        // Add item from medicineTags
        for (int i = 0; i < tagNameList.size(); i++) {

            // Create item
            TextView textView = new TextView(context);
            textView.setText(findTagName(tagNameList.get(i)));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, convertDpToPixel(5), convertDpToPixel(10), convertDpToPixel(5));
            textView.setLayoutParams(params);
            textView.setPadding(convertDpToPixel(10), convertDpToPixel(5), convertDpToPixel(10), convertDpToPixel(5));
//            textView.setTag("" + i);
//            textView.setOnClickListener(onBoxTagClickListener);

            textView.setTextColor(Color.WHITE);
            textView.setBackgroundResource(R.drawable.tag_background_primary);

            textView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            eachTextWidth += textView.getMeasuredWidth();


            // Add item into view
            if (eachTextWidth >= maxWidth) {
                // Change a new line
                newLine = addNewLine(mainLayout);
                newLine.addView(textView);
                eachTextWidth = 0;
            } else {
                newLine.addView(textView);
            }
        }
    }


    @NonNull
    private LinearLayout addNewLine(LinearLayout layout) {
        if (context != null){
            LinearLayout newLine = new LinearLayout(context);
            newLine.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            newLine.setOrientation(LinearLayout.HORIZONTAL);
            layout.addView(newLine);
            return newLine;
        } else {
            return null;
        }
    }

    private String findTagName(String id){
        String result = "";
        switch (id){
            default:
            case "per_hour":
                result = context.getString(R.string.tag_per_hour);
                break;
            case "per_times":
                result = context.getString(R.string.tag_per_times);
                break;
            case "large":
                result = context.getString(R.string.tag_large);
                break;
            case "medium":
                result = context.getString(R.string.tag_medium);
                break;
            case "small":
                result = context.getString(R.string.tag_small);
                break;
            case "free":
                result = context.getString(R.string.tag_free);
                break;
            case "twenty_four":
                result = context.getString(R.string.tag_twenty_four);
                break;
        }
        return result;
    }


    public static int convertDpToPixel(float dp) {
        return (int) (dp * (Application.DEVICE_DENSITY_DPI / 160f));
    }

    public static double convertPixelsToDp(double px){
        double dp = px / (Application.DEVICE_DENSITY_DPI / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }

}
