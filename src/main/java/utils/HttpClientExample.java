package utils;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * This example demostrates the use of {@Link HttpPost} request method
 * And sending HTML Form request parameters
 */
public class HttpClientExample {

    private final String USER_AGENT = "Mozilla/5.0";
    private final String urlLogin = "http://ajoinville.telelog.com.br/security/login.seam";
    private final String urlRelatorios = "http://ajoinville.telelog.com.br/site/report/measure_report_point.seam";
    private final static String paginalogin = "http://ajoinville.telelog.com.br/security/login.seam";
    private final static String paginaequipamentos = "http://ajoinville.telelog.com.br/site/monitoring/monitoring_equipment_devicetype81_88.seam?pModelType=227&pDeviceType1=81&pDeviceType2=88&pScreen=1";

    public static void main(String[] args) throws Exception {

        HttpClientExample http = new HttpClientExample();

        System.out.println("\nTesting 2 - Send Http POST request");
        http.sendPost();
    }

    // HTTP POST request
    private void sendPost() throws Exception {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(urlRelatorios);

        // add header
        post.setHeader("User-Agent", USER_AGENT);
        post.setHeader("Cookie", "JSESSIONID=" + "");
        post.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        post.setHeader("Accept-Encoding", "gzip, deflate");

        List<NameValuePair> urlParameters = new ArrayList<>();
//        formExcel.put("javax.faces.ViewState", "j_id3"));
//        formExcel.put("measureReportPoint", "measureReportPoint"));
//        formExcel.put("measureReportPoint:report", "Gerar Relatório"));
//        formExcel.put("measureReportPoint:formatDecoration:report", "EXCEL"));
//        formExcel.put("measureReportPoint:installationDecoration:installation", "133"));
//        formExcel.put("measureReportPoint:deviceDecoration:device", "253"));
//        formExcel.put("measureReportPoint:featureDecoration:feature", "1"));
//        formExcel.put("measureReportPoint:pointDecoration:pointsCheckBox", "260"));
//        formExcel.put("measureReportPoint:initialDateDecoration:initialDateInputCurrentDate", "03/2018"));
//        formExcel.put("measureReportPoint:initialDateDecoration:initialDateInputDate", "01/03/2018"));
//        formExcel.put("measureReportPoint:initialDateDecoration:initialHour", "00:00"));
//        formExcel.put("measureReportPoint:finalDateDecoration:finalDateInputCurrentDate", "03/2018"));
//        formExcel.put("measureReportPoint:finalDateDecoration:finalDateInputDate", "08/03/2018"));
//        formExcel.put("measureReportPoint:finalDateDecoration:finalHour", "23:59"));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        HttpResponse response = httpClient.execute(post);
        InputStream is = response.getEntity().getContent();
        FileOutputStream fos = new FileOutputStream("D:/" + response.getLastHeader("Content-Disposition").getValue().split("filename=")[1]);

        int bytesArchive;
        while ((bytesArchive = is.read()) != -1) {
            fos.write(bytesArchive);
        }

        is.close();
        fos.close();
    }
}
