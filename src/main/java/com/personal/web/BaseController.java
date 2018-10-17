package com.personal.web;

import static com.personal.util.Constant.*;

import com.alibaba.fastjson.JSONObject;
import com.personal.entity.Dollar;
import com.personal.entity.Summary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    List<Dollar> prepareList(Summary summary) {
        List<Dollar> list = new ArrayList<>();
        list.add(new Dollar.Builder().name(TWENTY).denomination(TWENTIES).count(summary.getCountTwenty()).value(summary.getValueTwenty()).build());
        list.add(new Dollar.Builder().name(TEN).denomination(TENS).count(summary.getCountTen()).value(summary.getValueTen()).build());
        list.add(new Dollar.Builder().name(FIVE).denomination(FIVES).count(summary.getCountFive()).value(summary.getValueFive()).build());
        list.add(new Dollar.Builder().name(TWO).denomination(TWOS).count(summary.getCountTwo()).value(summary.getValueTwo()).build());
        list.add(new Dollar.Builder().name(ONE).denomination(ONES).count(summary.getCountOne()).value(summary.getValueOne()).build());
        return list;
    }

    String checkMissingParam(Map<String,String> requestParam) {
        String missingParam = null;
        for (Map.Entry<String, String> entry : requestParam.entrySet()) {
            if (entry.getValue().trim().equals("") || entry.getValue() == null) {
                missingParam = getKeyName(entry.getKey());
                break;
            }
        }
        return missingParam;
    }

    String checkInvalidParam(Map<String,String> requestParam) {
        String invalidParam = null;
        for (Map.Entry<String, String> entry : requestParam.entrySet()) {
            if (entry.getValue().trim().contains("-")) {
                invalidParam = getKeyName(entry.getKey());
                break;
            }
        }
        return invalidParam;
    }

    String returnResult(String msg, int code, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonStr = new JSONObject();
        jsonStr.put("msg", msg);
        jsonStr.put("code", code);
        writeJson(jsonStr.toString(), request, response);
        return null;
    }

    String returnResult(String msg, int code, String data, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonStr = new JSONObject();
        jsonStr.put("msg", msg);
        jsonStr.put("code", code);
        jsonStr.put("data", data);
        writeJson(jsonStr.toString(), request, response);
        return null;
    }

    private void writeJson(String jsonStr, HttpServletRequest request, HttpServletResponse response) {
        try{
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "No-cache");
            response.getWriter().write(jsonStr);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private String getKeyName(String key) {
        String name = null;
        if (key.equals("cash")) {
            name = "Cash to change";
        } else {
            List<Dollar> list = getCashDenominations();
            for (Dollar dollar : list) {
                if (dollar.getName().equals(key)) {
                    name = "$ " + dollar.getDenomination();
                }
            }
        }
        return name;
    }

    private List<Dollar> getCashDenominations() {
        List<Dollar> list = new ArrayList<>();
        list.add(new Dollar.Builder().name(ONE.toLowerCase()).denomination(ONES).build());
        list.add(new Dollar.Builder().name(TWO.toLowerCase()).denomination(TWOS).build());
        list.add(new Dollar.Builder().name(FIVE.toLowerCase()).denomination(FIVES).build());
        list.add(new Dollar.Builder().name(TEN.toLowerCase()).denomination(TENS).build());
        list.add(new Dollar.Builder().name(TWENTY.toLowerCase()).denomination(TWENTIES).build());
        return list;
    }
}
