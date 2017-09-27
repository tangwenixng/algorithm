package com.sealyu.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 这是一个中文数字和阿拉伯数字转换算法的测试
 * @author Haibo Yu on 09/27/2017.
 */
public class ChineseNumberConverter {
    //存放数量级中文数字信息
    private static Map<String,Long> magnitudeMap = getMagnitudeMap();
    //存放0~9基本中文数字信息
    private static Map<String,Long> dataMap = getDataMap();

    public static void main(String[] args) {
        String testString = "二十五万五百亿三千零八万一千零卅五";
        System.out.println("The input value is :"+testString);
        long convertedValue = 0;
        try {
            ChineseNumberConverter converter = new ChineseNumberConverter();
            convertedValue = converter.convertToLongFromEnd(testString);
            System.out.println("The output value is :"+Long.toString(convertedValue));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public long convertToLong(String inputStr) throws Exception{
        long currentMagnitude = 1;
        long tempData = 1;
        long currentValue = 0;
        int len = inputStr.length();
        long sumVal = 0;
        //Check if the previous text is number;
        //True for magnitude, false for number.
        boolean isPreviousMag = false;
        for(int i=0;i<len;i++){
            String currentTxt = inputStr.substring(i,i+1);
            if(magnitudeMap.containsKey(currentTxt)){
                //If it's magnitude
                currentMagnitude = magnitudeMap.get(currentTxt);
                currentValue = currentValue * currentMagnitude;
                if(isPreviousMag){
                    //If previous one is also magnitude, multiple with previous value.
                    currentValue = currentValue * currentMagnitude;
                }else{
                    //If previous one is number, just multiple it.
                    currentValue = tempData * currentMagnitude;
                }

                isPreviousMag = true;
            }else if(dataMap.containsKey(currentTxt)){
                //Add the previous part to result and reset current value
                sumVal = sumVal + currentValue;
                currentValue = 0;

                //If it's number
                long data = dataMap.get(currentTxt);
                if(data == 0){
                    continue;
                }else{
                    tempData = data;
                    currentValue = currentValue + tempData;
                }
                isPreviousMag = false;
            }else{
                throw new Exception("Find illegal character in the input string:"+currentTxt);
            }
        }
        return sumVal;
    }

    public long convertToLongFromEnd(String inputStr) throws Exception{
        //存储遇到该数字前的最大一个数量级，这个值是累乘之前所有数量级，
        //比如三千两百万，到三的时候最高数量级就是1000*10000
        long maxMagnitude = 1l;
        long currentMagnitude = 1l;
        long sumVal = 0l;

        int len = inputStr.length();
        //倒序循环整个字符串，从最低位开始计算整个数值
        for(int i=len-1;i>=0;i--){
            String currentTxt = String.valueOf(inputStr.charAt(i));
            if(magnitudeMap.containsKey(currentTxt)){
                currentMagnitude = magnitudeMap.get(currentTxt);
                //比较当前数量级与之前数量级，如果
                if(currentMagnitude > maxMagnitude){
                    maxMagnitude = currentMagnitude;
                    sumVal = sumVal + currentMagnitude;
                }else{
                    //如果当前数量级小于目前数量级,比如二百万，抵达"百"的时候数量级应该为100*10000
                    maxMagnitude = maxMagnitude*currentMagnitude;
                }
            }else if(dataMap.containsKey(currentTxt)){
                //如果是0~9之间的数字，与前面一位数量级相乘，并累加到当前sumVal
                long data = dataMap.get(currentTxt);
                if(data == 0){
                    //跳过0
                    continue;
                }else{
                    sumVal = sumVal + data*maxMagnitude;
                }
            }else{
                throw new Exception("Find illegal character in the input string:"+currentTxt);
            }
        }
        return sumVal;
    }

    /**
     * 数量级map，存储对应的数量级文字和对应的阿拉伯数字量值
     * @return The operator map
     */
    private static Map<String,Long> getMagnitudeMap(){
        Map<String,Long> magnitudeMap = new HashMap();
        magnitudeMap.put("十", 10l);
        magnitudeMap.put("廿", 20l);
        magnitudeMap.put("卅", 30l);
        magnitudeMap.put("百", 100l);
        magnitudeMap.put("千", 1000l);
        magnitudeMap.put("万", 10000l);
        magnitudeMap.put("亿", 100000000l);
        magnitudeMap.put("兆", 1000000000000l);
        magnitudeMap.put("京", 10000000000000000l);
        return magnitudeMap;
    }

    /**
     * 基本数据map，存储对应的基本数据及对应的阿拉伯数字量值
     * @return
     */
    private static Map<String,Long> getDataMap(){
        Map<String,Long> dataMap = new HashMap<>();
        dataMap.put("一",1l);
        dataMap.put("二",2l);
        dataMap.put("三",3l);
        dataMap.put("四",4l);
        dataMap.put("五",5l);
        dataMap.put("六",6l);
        dataMap.put("七",7l);
        dataMap.put("八",8l);
        dataMap.put("九",9l);
        dataMap.put("零",0l);
        return dataMap;
    }
}
