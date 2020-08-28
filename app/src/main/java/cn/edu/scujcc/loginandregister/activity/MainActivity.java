package cn.edu.scujcc.loginandregister.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import cn.edu.scujcc.loginandregister.R;

/**
 * @author Administrator
 */
public class MainActivity extends AppCompatActivity {
    private TextView tvDeal;
    private TextView Deal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Deal = findViewById(R.id.constraint_after);
        SpannableString spannableStrings = new SpannableString(getResources().getString(R.string.deal_front));
        spannableStrings.setSpan(new ForegroundColorSpan(Color.parseColor("#FFA1A6B3")), 0, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStrings.setSpan(new ForegroundColorSpan(Color.parseColor("#477BEF")), 9, 33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStrings.setSpan(new ForegroundColorSpan(Color.parseColor("#FFA1A6B3")), 34, 35, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStrings.setSpan(new ForegroundColorSpan(Color.parseColor("#477BEF")), 36, 54, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        Deal.setText(spannableStrings);

        tvDeal = findViewById(R.id.deal_after);
        SpannableString spannableString = new SpannableString(getResources().getString(R.string.deal_front));
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FFA1A6B3")), 0, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#477BEF")), 9, 33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FFA1A6B3")), 34, 35, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#477BEF")), 36, 54, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvDeal.setText(spannableString);
    }
}