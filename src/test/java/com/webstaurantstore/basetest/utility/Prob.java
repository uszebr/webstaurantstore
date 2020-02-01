package com.webstaurantstore.basetest.utility;

public class Prob {
    public static void main(String[] args) {

      /*  Map<String, List<String>> mapParameters = null;
        try {
            URL url = new URL("https://stackoverflow.com?param1=value1&param2=&param3=value3&param3");
            mapParameters = splitQuery(url);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Can Not Decode this url");
        }


        log.info(mapParameters.toString());*/

        for (int i=0;i<30;i++) {
            System.out.println(RandomDataGenerator.emailGenerator(1, 40));
        }
    }
}
