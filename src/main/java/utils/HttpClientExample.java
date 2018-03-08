package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.usermodel.Workbook;


/**
 * This example demostrates the use of {@Link HttpPost} request method
 * And sending HTML Form request parameters
 */
public class HttpClientExample {

    private final String USER_AGENT = "Mozilla/5.0";
    private final String urlLogin = "http://ajoinville.telelog.com.br/security/login.seam";

    public static void main(String[] args) throws Exception {

        HttpClientExample http = new HttpClientExample();

        System.out.println("\nTesting 2 - Send Http POST request");
        http.sendPost();

    }

    // HTTP GET request
    private void sendGet() throws Exception {

        String url = "http://www.google.com/search?q=developer";

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);

        // add request header
        request.addHeader("User-Agent", USER_AGENT);

        HttpResponse response = client.execute(request);

        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " +
                response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        System.out.println(result.toString());

    }

    // HTTP POST request
    private void sendPost() throws Exception {

        String url = "http://ajoinville.telelog.com.br/site/report/measure_report_point.seam";

        HttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);
        post.setHeader("Cookie", "JSESSIONID=90926412D612DEE93CC3B675EE445CCC");
        post.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        post.setHeader("Accept-Encoding","gzip, deflate");

        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("javax.faces.ViewState", "j_id3"));
        urlParameters.add(new BasicNameValuePair("measureReportPoint", "measureReportPoint"));
        urlParameters.add(new BasicNameValuePair("measureReportPoint:report", "Gerar Relatório"));
        urlParameters.add(new BasicNameValuePair("measureReportPoint:formatDecoration:report", "EXCEL"));

        urlParameters.add(new BasicNameValuePair("measureReportPoint:installationDecoration:installation", "133"));
        urlParameters.add(new BasicNameValuePair("measureReportPoint:deviceDecoration:device", "253"));
        urlParameters.add(new BasicNameValuePair("measureReportPoint:featureDecoration:feature", "1"));

        urlParameters.add(new BasicNameValuePair("measureReportPoint:pointDecoration:pointsCheckBox", "260"));

        urlParameters.add(new BasicNameValuePair("measureReportPoint:initialDateDecoration:initialDateInputCurrentDate", "03/2018"));
        urlParameters.add(new BasicNameValuePair("measureReportPoint:initialDateDecoration:initialDateInputDate", "01/03/2018"));
        urlParameters.add(new BasicNameValuePair("measureReportPoint:initialDateDecoration:initialHour", "00:00"));

        urlParameters.add(new BasicNameValuePair("measureReportPoint:finalDateDecoration:finalDateInputCurrentDate", "03/2018"));
        urlParameters.add(new BasicNameValuePair("measureReportPoint:finalDateDecoration:finalDateInputDate", "08/03/2018"));
        urlParameters.add(new BasicNameValuePair("measureReportPoint:finalDateDecoration:finalHour", "23:59"));

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

    private void login() {

    }
}
