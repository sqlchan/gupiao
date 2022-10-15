package gupiao;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.util.*;

public class Format {
    public static void main(String[] args) throws IOException {
        zhangting0();

    }

    public static void zhangting0() throws IOException {
        Map<String, List<Zhangting>> map = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Administrator\\IdeaProjects\\minibase\\java\\gupiao\\a.html"));
        StringBuffer sb = new StringBuffer();
        String s = "";
        while ((s = reader.readLine()) != null){
            sb.append(s.trim());
        }
        String all = sb.toString();

        int l0 = all.indexOf("class=\"table hit-pool__table-body\">");
        int l01 = all.indexOf("</tbody>",l0);
        String re = all.substring(l0,l01+8);
        //System.out.println(re);
        //String re = sb.toString();
        int count = 0;
        int l1 = 0;
        while (l1 != -1){
            l1 = re.indexOf("\"stock-title-name\">",l1);
            if(l1 != -1){
                int l2 = re.indexOf("</span>",l1);
                String name = re.substring(l1+19,l2);
                System.out.println("name: " + name);

                int l3 = re.indexOf("aria-label=\"",l2);
                int l3_1 = re.indexOf("\"",l3+14);
                String reason = re.substring(l3+12, l3_1);
                System.out.println("reason: " + reason);

                int l4 = re.indexOf("class=\"stock-reason-plate-name\">",l3_1);
                int l4_9 = re.indexOf("|</span>",l4+10);
                String label = re.substring(l4+32, l4_9);
                int l4_1 = label.indexOf("</span>");
                String lab1 = label.substring(0,l4_1);
                int l6 = re.indexOf("target=\"limit_up_days\" class=\"\">",l3_1);
                int l6_1 = re.indexOf("</td>",l6);
                String la1 = re.substring(l6+32,l6_1);
                System.out.println("lab: " + la1);
                System.out.println("la1: " + lab1);
                String label1 = label.substring(l4_1+7);


                if(label1.contains("class=\"stock-reason-plate-name\">")){
                    int l5 = label1.indexOf("class=\"stock-reason-plate-name\">");
                    int l5_1 = label1.indexOf("</span>",l5+31);
                    String lab2 = label1.substring(l5+32,l5_1);
                    lab2 = lab1 + " + " + lab2;
                    System.out.println("la2: "+ lab2);

                    Zhangting zhangting2 = new Zhangting();
                    zhangting2.setName(name);
                    zhangting2.setRes(reason);
                    zhangting2.setLabel(lab2);
                    zhangting2.setLianban(la1);
                    if(map.containsKey(lab2)){
                        map.get(lab2).add(zhangting2);
                    }else {
                        List<Zhangting> list = new ArrayList<>();
                        list.add(zhangting2);
                        map.put(lab2, list);
                    }
                }else {
                    Zhangting zhangting1 = new Zhangting();
                    zhangting1.setName(name);
                    zhangting1.setRes(reason);
                    zhangting1.setLabel(lab1);
                    zhangting1.setLianban(la1);
                    if(map.containsKey(lab1)){
                        map.get(lab1).add(zhangting1);
                    }else {
                        List<Zhangting> list = new ArrayList<>();
                        list.add(zhangting1);
                        map.put(lab1, list);
                    }
                }

                l1 = l3_1 + 7;
                count ++;
            }
        }



        Map<String, List<Zhangting>> sortMap = sortDescend(map);
        format(sortMap);

        System.out.println(sortMap);
        System.out.println(count);
    }

    public static void zhangting() throws IOException {
        Map<String, List<Zhangting>> map = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Administrator\\IdeaProjects\\minibase\\java\\gupiao\\zhangting.html"));
        StringBuffer sb = new StringBuffer();
        String s = "";
        while ((s = reader.readLine()) != null){
            sb.append(s.trim());
        }
        String re = sb.toString();
        int count = 0;
        int l1 = 0;
        while (l1 != -1){
            l1 = re.indexOf("\"stock-title-name\">",l1);
            if(l1 != -1){
                int l2 = re.indexOf("</span>",l1);
                String name = re.substring(l1+19,l2);
                System.out.println("name: " + name);

                int l3 = re.indexOf("aria-label=\"",l2);
                int l3_1 = re.indexOf("\"",l3+14);
                String reason = re.substring(l3+12, l3_1);
                System.out.println("reason: " + reason);

                int l4 = re.indexOf("class=\"stock-reason-plate-name\">",l3_1);
                int l4_9 = re.indexOf("|</span>",l4+10);
                String label = re.substring(l4+32, l4_9);
                int l4_1 = label.indexOf("</span>");
                String lab1 = label.substring(0,l4_1);
                int l6 = re.indexOf("target=\"limit_up_days\" class=\"sort_target\">",l3_1);
                int l6_1 = re.indexOf("</td>",l6+43);
                String la1 = re.substring(l6+43,l6_1);
                System.out.println("lab: " + la1);
                System.out.println("la1: " + lab1);
                String label1 = label.substring(l4_1+7);


                if(label1.contains("class=\"stock-reason-plate-name\">")){
                    int l5 = label1.indexOf("class=\"stock-reason-plate-name\">");
                    int l5_1 = label1.indexOf("</span>",l5+31);
                    String lab2 = label1.substring(l5+32,l5_1);
                    lab2 = lab1 + " + " + lab2;
                    System.out.println("la2: "+ lab2);

                    Zhangting zhangting2 = new Zhangting();
                    zhangting2.setName(name);
                    zhangting2.setRes(reason);
                    zhangting2.setLabel(lab2);
                    zhangting2.setLianban(la1);
                    if(map.containsKey(lab2)){
                        map.get(lab2).add(zhangting2);
                    }else {
                        List<Zhangting> list = new ArrayList<>();
                        list.add(zhangting2);
                        map.put(lab2, list);
                    }
                }else {
                    Zhangting zhangting1 = new Zhangting();
                    zhangting1.setName(name);
                    zhangting1.setRes(reason);
                    zhangting1.setLabel(lab1);
                    zhangting1.setLianban(la1);
                    if(map.containsKey(lab1)){
                        map.get(lab1).add(zhangting1);
                    }else {
                        List<Zhangting> list = new ArrayList<>();
                        list.add(zhangting1);
                        map.put(lab1, list);
                    }
                }

                l1 = l3_1 + 7;
                count ++;
            }
        }



        Map<String, List<Zhangting>> sortMap = sortDescend(map);
        format(sortMap);

        System.out.println(sortMap);
    }

    public static void format(Map<String,List<Zhangting>> map ) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Administrator\\IdeaProjects\\minibase\\java\\gupiao\\lianban.opml"));

        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");writer.newLine();
        writer.write("<opml version=\"2.0\">");writer.newLine();
        writer.write("  <head>");writer.newLine();
        writer.write("    <title>涨停分析</title>");writer.newLine();
        writer.write("  </head>");writer.newLine();
        writer.write("  <body>");writer.newLine();


        for(String key : map.keySet()){
            writer.write("    <outline text=\"医药\" >".replace("医药",key));
            writer.newLine();
            List<Zhangting> list = (List<Zhangting>)map.get(key);
            for(Zhangting z : list){
                writer.write("      <outline text=\"众星医药\" >".replace("众星医药",z.getName()));
                writer.newLine();

                writer.write("        <outline text=\"首版\" >".replace("首版",z.getLianban()));
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


    public static void qiangshi() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Administrator\\IdeaProjects\\minibase\\java\\gupiao\\qiangshi.html"));
    }

    public static void zhangting1() throws IOException {
        Map<String, List<Zhangting>> map = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Administrator\\IdeaProjects\\minibase\\java\\gupiao\\zhangting.html"));
        StringBuffer sb = new StringBuffer();
        String s = "";
        while ((s = reader.readLine()) != null){
            sb.append(s.trim());
        }
        String re = sb.toString();
        int count = 0;
        int l1 = 0;
        while (l1 != -1){
            l1 = re.indexOf("\"stock-title-name\">",l1);
            if(l1 != -1){
                int l2 = re.indexOf("</span>",l1);
                String name = re.substring(l1+19,l2);
                System.out.println("name: " + name);

                int l3 = re.indexOf("aria-label=\"",l2);
                int l3_1 = re.indexOf("\"",l3+14);
                String reason = re.substring(l3+12, l3_1);
                System.out.println("reason: " + reason);

                int l4 = re.indexOf("class=\"stock-reason-plate-name\">",l3_1);
                int l4_9 = re.indexOf("|</span>",l4+10);
                String label = re.substring(l4+32, l4_9);
                int l4_1 = label.indexOf("</span>");
                String lab1 = label.substring(0,l4_1);
                int l6 = re.indexOf("target=\"limit_up_days\" class=\"sort_target\">",l3_1);
                int l6_1 = re.indexOf("</td>",l6+43);
                String la1 = re.substring(l6+43,l6_1);
                System.out.println("lab: " + la1);
                System.out.println("la1: " + lab1);
                String label1 = label.substring(l4_1+7);
                Zhangting zhangting1 = new Zhangting();
                zhangting1.setName(name);
                zhangting1.setRes(reason);
                zhangting1.setLabel(lab1);
                zhangting1.setLianban(la1);
                if(map.containsKey(lab1)){
                    map.get(lab1).add(zhangting1);
                }else {
                    List<Zhangting> list = new ArrayList<>();
                    list.add(zhangting1);
                    map.put(lab1, list);
                }

                if(label1.contains("class=\"stock-reason-plate-name\">")){
                    int l5 = label1.indexOf("class=\"stock-reason-plate-name\">");
                    int l5_1 = label1.indexOf("</span>",l5+31);
                    String lab2 = label1.substring(l5+32,l5_1);
                    System.out.println("la2: "+ lab2);

                    Zhangting zhangting2 = new Zhangting();
                    zhangting2.setName(name);
                    zhangting2.setRes(reason);
                    zhangting2.setLabel(lab2);
                    zhangting2.setLianban(la1);
                    if(map.containsKey(lab2)){
                        map.get(lab2).add(zhangting2);
                    }else {
                        List<Zhangting> list = new ArrayList<>();
                        list.add(zhangting2);
                        map.put(lab2, list);
                    }
                }

                l1 = l3_1 + 7;
                count ++;
            }
        }



        Map<String, List<Zhangting>> sortMap = sortDescend(map);
        format(sortMap);

        System.out.println(sortMap);
    }
}
