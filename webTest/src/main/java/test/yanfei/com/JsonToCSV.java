package test.yanfei.com;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;


public class JsonToCSV {
    public static void getJsonFile(String logPath,String logFile,JSONArray excelHear,String headers) {
        FileReader fd = null;
        try {
            fd = new FileReader(logPath + logFile + ".json");
            System.out.println(logFile);
            BufferedReader br = new BufferedReader(fd);
            String jsonOrg = null;
            JSONArray docs = new JSONArray();
            while (Objects.nonNull(jsonOrg = br.readLine())) {
                int index = jsonOrg.indexOf(".json:");
                if (index > 0) {
                    String jsonContent = jsonOrg.substring(index + 6);
                    if (StringUtils.isBlank(jsonContent)) continue;
                    JSONObject output;
                    output = new JSONObject(jsonContent);
                    docs.put(output);
                }
            }
            File file = new File(logPath + logFile + ".csv");
            FileUtils.writeStringToFile(file, headers, "UTF-8", false);
            String csv = CDL.toString(excelHear, docs);
            FileUtils.writeStringToFile(file, csv, "UTF-8", true);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found "+logFile);
        } catch (IOException I) {
            System.out.println("json format failed"+logFile);
        }
    }
    public static void main(String args[]) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Please key in the Log file path: ");
//        String logPath = scanner.nextLine();
//        System.out.println("Please key in the Log file name: ");
//        String logFileName = scanner.nextLine();
//        String[] logFile = new String[logFileName];
        //String logPath = "D:\\WorkFiles\\02-PSA\\06-LOG\\SIT_LOG\\";
        //String[] logFile = new String[]{"URCIS-20210925133800892-195_raw_%2A_f2_1_20210925","MI_TACS-20210902164227285-074_raw_%2A_f2_0_20210902"};
        String logPath = args[0];
        String[] logFile = Arrays.copyOfRange(args,1,args.length);
        JSONArray excelHear = new JSONArray();
        excelHear.put("event_dt");
        excelHear.put("event_id");
        excelHear.put("trans_id");
        excelHear.put("msg_id");
        excelHear.put("duration_n");
        excelHear.put("ic_id");
        excelHear.put("log_type_m");
        excelHear.put("log_level_m");
        excelHear.put("application_m");
        excelHear.put("engine_m");
        excelHear.put("host_m");
        excelHear.put("node_m");
        excelHear.put("msg_c");
        excelHear.put("msg_x");
        excelHear.put("payload_x");
        String headers = CDL.rowToString(excelHear);//generate excle hearder
        for(int i=0;i<logFile.length;i++){
            String logFileName = logFile[i];
            getJsonFile(logPath,logFileName,excelHear,headers);
        }
    }
}

