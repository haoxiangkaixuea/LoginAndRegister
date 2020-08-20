package cn.edu.scujcc.loginandregister.listener;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDateAdapter {
    final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @ToJson
    String toJson(Date dt) {
        return dateFormat.format(dt);
    }

    @FromJson
    Date fromJson(String jsonDt) throws ParseException {
        return dateFormat.parse(jsonDt);
    }
}





