/**
 * @author bianjinyue
 * @Description
 * @date 2022-08-08 17:13
 */
public class ExcelDataParse {
    public static String data = "";

    public static void main(String[] args) {
        String sql = "select a.*,b.sn from (select id,imei from iot_device_info where imei in (%s)) a,iot_device b where a.id=b.id";
        String s1 = "select device_sn,extend_value from device_extend where extend_value in (%s)";
        String d = data.replace("\n", "\",\"");
        d = "\"" + d + "\"";
        System.out.printf((sql) + "%n", d);
    }
}
