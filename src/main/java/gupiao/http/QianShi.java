package gupiao.http;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import gupiao.Zhangting;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class QianShi {

    public static String url_qiangshi = "https://flash-api.xuangubao.cn/api/pool/detail?pool_name=super_stock";
    public static String url_zhengting = "https://flash-api.xuangubao.cn/api/pool/detail?pool_name=limit_up";

    public static void main(String[] args) throws IOException {
        String html = get(url_qiangshi);
        //System.out.println(html);

        TreeMap<String, List<Zhangting>> map = new TreeMap<>(Comparator.reverseOrder());

        JSONObject jsonObject = JSONObject.parseObject(html);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        for(int i=0;i<jsonArray.size();i++){
            JSONObject json = jsonArray.getJSONObject(i);
            int limit_up_days = json.getIntValue("limit_up_days");
            String lianban;
            if(limit_up_days == 0){
                lianban = "0板";
            }else if(limit_up_days == 1){
                lianban = "1板";
            }else {
                lianban = limit_up_days + "连板";
            }
            String name = json.getString("stock_chi_name");
            System.out.println(name);
            int days = json.getIntValue("m_days_n_boards_days");
            int day = json.getIntValue("m_days_n_boards_boards");
            String dd = days +"天" + day + "板";

            JSONObject jsonres = json.getJSONObject("surge_reason");
            String stock_reason = jsonres.getString("stock_reason");
            JSONArray labels = jsonres.getJSONArray("related_plates");
            List<String> labelList = new ArrayList<>();
            for(int j=0;j<labels.size();j++){
                JSONObject resJson = labels.getJSONObject(j);
                String plate_name = resJson.getString("plate_name");
                labelList.add(plate_name);
            }
            labelList.add(dd);
            String lab = StringUtils.join(labelList," + ");

            Zhangting zhangting = new Zhangting();
            zhangting.setRes(stock_reason);
            zhangting.setLianban(lianban);
            zhangting.setName(name);
            zhangting.setLabel(lab);


            if(map.containsKey(lianban)){
                map.get(lianban).add(zhangting);
            }else {
                List<Zhangting> list = new ArrayList<>();
                list.add(zhangting);
                map.put(lianban, list);
            }
        }
        map.remove("ST股");
        //Map<String, List<Zhangting>> sortMap = sortDescend(map);
        //System.out.println(sortMap);

        format(map);

    }

    public static void format(Map<String,List<Zhangting>> map ) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("F:\\IdeaProjects\\gupiao\\src\\main\\resources\\lianban.opml"));

        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");writer.newLine();
        writer.write("<opml version=\"2.0\">");writer.newLine();
        writer.write("  <head>");writer.newLine();
        writer.write("    <title>连板分析</title>");writer.newLine();
        writer.write("  </head>");writer.newLine();
        writer.write("  <body>");writer.newLine();

        for(String key : map.keySet()){
            writer.write("    <outline text=\"医药\" >".replace("医药",key));
            writer.newLine();
            List<Zhangting> list = (List<Zhangting>)map.get(key);
            for(Zhangting z : list){
                writer.write("      <outline text=\"众星医药\" >".replace("众星医药",z.getName()));
                writer.newLine();

                writer.write("        <outline text=\"首版\" >".replace("首版",z.getLabel()));
                writer.newLine();

                writer.write("          <outline text=\"1111\" />".replace("1111",z.getRes()));
                writer.newLine();

                writer.write("        </outline>");
                writer.newLine();
                writer.write("      </outline>");
                writer.newLine();
            }
            writer.write("    </outline>");
            writer.newLine();
        }

        writer.write("  </body>");writer.newLine();
        writer.write("</opml>");writer.newLine();

        writer.flush();
        writer.close();
    }

    // Map的value值降序排序
    public static Map<String, List<Zhangting>> sortDescend(Map<String, List<Zhangting>> map) {
        List<Map.Entry<String, List<Zhangting>>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, List<Zhangting>>>() {
            @Override
            public int compare(Map.Entry<String, List<Zhangting>> o1, Map.Entry<String, List<Zhangting>> o2) {
                int compare = ((List<Zhangting>)o1.getValue()).size() - ((List<Zhangting>)o2.getValue()).size();
                //int compare = (o1.getValue()).compareTo(o2.getValue());
                return -compare;
            }
        });

        Map<String, List<Zhangting>> returnMap = new LinkedHashMap<String, List<Zhangting>>();
        for (Map.Entry<String, List<Zhangting>> entry : list) {
            returnMap.put(entry.getKey(), entry.getValue());
        }
        return returnMap;
    }

    public static String get(String url) throws IOException {
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        String result = "";
        int code = httpClient.executeMethod(getMethod);
        if (code == 200){
            result = getMethod.getResponseBodyAsString();
            System.out.println("result:" + result);
        }
        return result;
    }

}
