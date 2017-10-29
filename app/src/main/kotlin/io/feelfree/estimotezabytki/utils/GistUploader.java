package io.feelfree.estimotezabytki.utils;

import android.util.Pair;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

/**
 * Created by mule on 10/29/17.
 */

public class GistUploader {
    String APIKEY  = "AIzaSyAggAVAFE2u9RvtuQa2ddB3c-lbCU4SLvY";
    private String sendPost(Pair<String, String> data) {
        StringBuilder content = new StringBuilder();
        try {
            // Send POST request
            URL url = new URL(data.first);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            OutputStream os = conn.getOutputStream();
            os.write(data.second.getBytes());
            os.flush();
            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED &&
                    conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String line;
            while ((line = br.readLine()) != null)
                content.append(line + "\n");
            conn.disconnect();
            return content.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public Single<String> uploadGist(final String data) {
        return Single.create(new SingleOnSubscribe<String>(){
            @Override
            public void subscribe(SingleEmitter<String> e) throws Exception {
                // Create JSON post object
                JSONObject postData = new JSONObject();
                JSONObject files = new JSONObject();
                files.put("result.json", new JSONObject().put("content", data));
                postData.put("public", false);
                postData.put("files", files);
                JSONObject githubResult = new JSONObject(sendPost(new Pair<>("https://api.github.com/gists", postData.toString()))).getJSONObject("files");
                if (githubResult.has("result.json")) {
                    String rawUrl = githubResult.getJSONObject("result.json").getString("raw_url");
                    JSONObject gData = new JSONObject();
                    gData.put("longUrl", rawUrl);
                    String shortenerReply = sendPost(new Pair<>("https://www.googleapis.com/urlshortener/v1/url?key=" + APIKEY,
                            gData.toString()));
                    JSONObject shortenedData = new JSONObject(shortenerReply);
                    e.onSuccess(shortenedData.getString("id"));
                }
                e.onSuccess("");
            }
        } );

    }
}