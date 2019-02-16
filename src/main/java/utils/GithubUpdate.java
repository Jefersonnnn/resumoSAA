package utils;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

class Repos {
    String tag_name;
    String published_at;
}

public class GithubUpdate {

    private static LocalDateTime dataRelease = LocalDateTime.of(2019, Month.FEBRUARY, 14, 20, 40);

    public static boolean newUpdate() throws Exception {

        String json = readUrl("https://api.github.com/repos/Jefersonnnn/resumoSAA/releases/latest");
        Gson gson = new Gson();
        Repos repos = null;
        if (json != null) {
            repos = gson.fromJson(json, Repos.class);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime publishedAt = LocalDateTime.parse(repos.published_at.replace("Z", "").replace("T", " "), formatter);
            if (dataRelease.isBefore(publishedAt))
                    System.out.println(repos.tag_name + " Disponivel");

            return dataRelease.isBefore(publishedAt);
        }

        return false;

    }

    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } catch (UnknownHostException | ConnectException ex) {
            System.err.println("Atualização: não foi possível verificar novas atualizações");
            return null;
        } finally {
            if (reader != null)
                reader.close();
        }
    }
}
