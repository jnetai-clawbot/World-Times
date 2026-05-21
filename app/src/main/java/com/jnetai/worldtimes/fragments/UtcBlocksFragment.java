package com.jnetai.worldtimes.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jnetai.worldtimes.R;
import com.jnetai.worldtimes.utils.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * UTC blocks fragment showing current time at every UTC offset (-12 to +12).
 * Matches the web app's UTC offset reference grid.
 */
public class UtcBlocksFragment extends Fragment {

    private androidx.gridlayout.widget.GridLayout utcGrid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_utc_blocks, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        utcGrid = view.findViewById(R.id.utcGrid);
        buildUtcBlocks();
    }

    private void buildUtcBlocks() {
        utcGrid.removeAllViews();
        utcGrid.setColumnCount(5);

        // Get current UTC time
        TimeZone utc = TimeZone.getTimeZone("UTC");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.US);
        timeFormat.setTimeZone(utc);

        for (int i = -12; i <= 12; i++) {
            View blockView = getLayoutInflater().inflate(R.layout.item_utc_block, utcGrid, false);
            TextView offsetText = blockView.findViewById(R.id.utcOffsetText);
            TextView timeText = blockView.findViewById(R.id.utcTimeText);

            String label = i > 0 ? "+" + i : String.valueOf(i);
            offsetText.setText("UTC" + label);

            // Calculate time at this offset
            java.util.Calendar cal = java.util.Calendar.getInstance(utc);
            cal.add(java.util.Calendar.HOUR_OF_DAY, i);
            timeText.setText(new SimpleDateFormat("HH:mm", Locale.US).format(cal.getTime()));

            // Highlight current offset (UTC+0)
            if (i == 0) {
                blockView.setBackgroundResource(R.drawable.bg_utc_now);
                offsetText.setTextColor(getResources().getColor(R.color.primary, null));
            }

            utcGrid.addView(blockView);
        }
    }
}
