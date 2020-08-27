package cn.edu.scujcc.loginandregister.data;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * JSON获取网路请求
 *
 * @author Administrator
 */
public class UserData {
    public static String getLoginDate() {
        String content = "";
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userAccount", "15928132503");
            jsonObject.put("clientVersion", "1.1.1.6");
            jsonObject.put("password", "h5FHIuvx/7Mym38LnFjPX5dpQvypNfjLM+z+K7QRj3ZY47EoR7BbdhH39RAfBQWKxfU0TcGfvTuj8213MEuU1it63viplz9S0gNzOvtgti3kTXpWtoH03xwaMRT7e6Mj/jtmr66/6d67hmf7Ltx86g==|MTIzNDU2Nzg5MDk4NzY1NA==|GTRU949nFZn62MZaCSlKQg==");
            jsonObject.put("code", "3E8744hk6618");
            jsonObject.put("deviceId", "1116B2B9DBD20633807E7DDB450373A0");
            jsonObject.put("imei", "347558749E29B240957C58DAA6277D48");
            //json串转string类型
            content = String.valueOf(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static String getRegisterDate() {
        String content = "";
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userAccount", "15928132508");
            jsonObject.put("type", "register");
            jsonObject.put("clientVersion", "1.1.1.6");
            jsonObject.put("imei", "347558749E29B240957C58DAA6277D48");
            //json串转string类型
            content = String.valueOf(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return content;
    }

//    public static void get(RetrofitCallBack c){
//        String content = "";
//
//        c.onRegisterSuccess(content);
//
//    }
}
