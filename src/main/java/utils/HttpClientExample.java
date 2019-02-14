package utils;

import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;


/**
 * This example demostrates the use of {@Link HttpPost} request method
 * And sending HTML Form request parameters
 */
public class HttpClientExample {

    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36";

    private OkHttpClient client;

    public HttpClientExample() {
        this.client = new OkHttpClient.Builder().connectTimeout(120, TimeUnit.SECONDS).writeTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).build();
        System.out.println(this.client.connectTimeoutMillis());
        System.out.println(this.client.readTimeoutMillis());
        System.out.println(this.client.writeTimeoutMillis());
        this.getExcel(this.login("jeferson.machado", "99823489"));
    }

    private String login(String username, String password) {
        String paginalogin = "http://ajoinville.telelog.com.br/security/login.seam";
        String headersCookie = null;
        Response respGetLogin = null;

        respGetLogin = this.getLogin();
        headersCookie = respGetLogin.networkResponse().header("Set-Cookie").split(";")[0];
        //region POST LOGIN
        Response response = null;
        try {

            MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
            RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"javax.faces.ViewState\"\r\n\r\nj_id1\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"loginForm\"\r\n\r\nloginForm\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"loginForm:password\"\r\n\r\n" + password + "\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"loginForm:submit\"\r\n\r\nLogin\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"loginForm:username\"\r\n\r\n" + username + "\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
            Request request = new Request.Builder()
                    .url(paginalogin)
                    .post(body)
                    .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                    .addHeader("Origin", "http://ajoinville.telelog.com.br")
                    .addHeader("Upgrade-Insecure-Requests", "1")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36")
                    .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                    .addHeader("DNT", "1")
                    .addHeader("Referer", "http://ajoinville.telelog.com.br/security/login.seam")
                    .addHeader("Accept-Encoding", "gzip, deflate")
                    .addHeader("Accept-Language", "pt-BR,pt;q=0.9,en;q=0.8")
                    .addHeader("Cookie", headersCookie)
                    .addHeader("Connection", "keep-alive")
                    .addHeader("Cache-Control", "no-cache")
                    .addHeader("Postman-Token", "969b35fc-5dee-47a2-8a3c-05545ad86a34")
                    .build();

            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ERROR: Não foi possivel realizer login");
            return "error";
        }

        //endregion

        if (!response.networkResponse().request().url().pathSegments().contains("error"))
            return headersCookie;
        else {
            System.err.println("ERROR: Não foi possível realizer login");
            return "error";
        }
    }


    // HTTP POST request
    private void getExcel(String headerCookie) {
        if (headerCookie.equals("error"))
            return;
        try {
           // OkHttpClient client = new OkHttpClient();
            String j_id = getJID(headerCookie);

            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, "javax.faces.ViewState="+j_id+"&measureReportPoint=measureReportPoint&measureReportPoint%3AdeviceDecoration%3Adevice=264&measureReportPoint%3AfinalDateDecoration%3AfinalDateInputCurrentDate=03%2F2018&measureReportPoint%3AfinalDateDecoration%3AfinalDateInputDate=14%2F03%2F2018&measureReportPoint%3AfinalDateDecoration%3AfinalHour=23%3A59&measureReportPoint%3AformatDecoration%3Areport=EXCEL&measureReportPoint%3AinitialDateDecoration%3AinitialDateInputCurrentDate=03%2F2018&measureReportPoint%3AinitialDateDecoration%3AinitialDateInputDate=10%2F03%2F2018&measureReportPoint%3AinitialDateDecoration%3AinitialHour=00%3A00&measureReportPoint%3AinstallationDecoration%3Ainstallation=133&measureReportPoint%3ApointDecoration%3ApointsCheckBox=271&measureReportPoint%3Areport=Gerar%20Relat%C3%B3rio&AJAXREQUEST=_viewRoot&measureReportPoint%3AfeatureDecoration%3Afeature=org.jboss.seam.ui.NoSelectionConverter.noSelectionValue");
            Request request = new Request.Builder()
                    .url("http://ajoinville.telelog.com.br/site/report/measure_report_point.seam")
                    .post(body)
                    .addHeader("Origin", "http://ajoinville.telelog.com.br")
                    .addHeader("Upgrade-Insecure-Requests", "1")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36")
                    .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                    .addHeader("DNT", "1")
                    .addHeader("Referer", "http://ajoinville.telelog.com.br/site/report/measure_report_point.seam")
                    .addHeader("Accept-Encoding", "gzip, deflate")
                    .addHeader("Accept-Language", "pt-BR,pt;q=0.9,en;q=0.8")
                    .addHeader("Cookie", headerCookie)
                    .addHeader("Connection", "keep-alive")
                    .addHeader("Cache-Control", "no-cache")
                    .addHeader("Postman-Token", "8864845e-a693-4a6b-9055-13e3585c757b")
                    .build();

            Response response = client.newCall(request).execute();

            InputStream is = response.body().byteStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //InputStream is = response.body().byteStream();
        //    FileOutputStream fos = new FileOutputStream("D:/" + response.headers("Content-Disposition").split("filename=")[1]);
        // post.setEntity(new UrlEncodedFormEntity(urlParameters));

        //  InputStream is = response.getEntity().getContent();
        // FileOutputStream fos = new FileOutputStream("D:/" + response.getLastHeader("Content-Disposition").getValue().split("filename=")[1]);

        //int bytesArchive;
        // while ((bytesArchive = is.read()) != -1) {
        //     fos.write(bytesArchive);
        // }

        // is.close();
        // fos.close();
    }

    private String getJID(String headerCookie) {
        String j_id = "j_id";
        try {

            Document pagina = Jsoup.connect("http://ajoinville.telelog.com.br/site/report/measure_report_point.seam")
                    .cookie(headerCookie.split("=")[0], headerCookie.split("=")[1])
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36").get();

            j_id = pagina.getElementById("javax.faces.ViewState").val();

            return "j_id"+(Integer.parseInt(j_id.split("j_id")[1]) + 1);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    private Response getLogin() {
        try {

            MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
            RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"javax.faces.ViewState\"\r\n\r\nj_id1\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"loginForm\"\r\n\r\nloginForm\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"loginForm:password\"\r\n\r\n99823489\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"loginForm:submit\"\r\n\r\nLogin\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"loginForm:username\"\r\n\r\nJeferson.Machado\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
            Request request = new Request.Builder()
                    .url("http://ajoinville.telelog.com.br/security/login.seam")
                    .get()
                    .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                    .addHeader("Origin", "http://ajoinville.telelog.com.br")
                    .addHeader("Upgrade-Insecure-Requests", "1")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36")
                    .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                    .addHeader("DNT", "1")
                    .addHeader("Referer", "http://ajoinville.telelog.com.br/security/login.seam")
                    .addHeader("Accept-Encoding", "gzip, deflate")
                    .addHeader("Accept-Language", "pt-BR,pt;q=0.9,en;q=0.8")
                    .addHeader("Connection", "keep-alive")
                    .addHeader("Cache-Control", "no-cache")
                    .addHeader("Postman-Token", "2393510e-0abb-4291-98b4-a0e3a81df2c2")
                    .build();

            return client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
