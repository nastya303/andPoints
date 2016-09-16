package com.mobile.user.pickpoint;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

/**
 * Created by user on 16.05.16.
 */
 /*__________________________________________Start CLASS Public key AsyncTask_________________________*/
public class XmlParser extends AsyncTask<String, Void, String> {

    String key_text = "empty";
    byte[] finalDecodedBytes;
    private final static String url_key = "http://nastya.boincfast.ru/public.pem";//"http://82.196.66.12:12173/my_public.der";

    private GetDataListener listener;

    //Обратите внимание на конструктор! Тут мы получаем нашего делегата и сохраняем его.
    XmlParser(GetDataListener listener)
    {
        this.listener = listener;
    }


    /*
    // you may separate this or combined to caller class.
    public interface AsyncResponse {
        void processFinish(String output);
    }

    public AsyncResponse delegate = null;

    public XmlParser(AsyncResponse delegate){
        this.delegate = delegate;
    }
    */

    public interface GetDataListener {
        void onGetDataComplete(String result, String classTag) throws IOException, JSONException;//JSONArray result);
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        /*
        mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setTitle("Android Get PEM");
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.show();
        */
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            // Connect to the web site
            Document document = Jsoup.connect(url_key).ignoreContentType(true).get();

            // Get key text
            key_text = document.text();
            Log.i("PUBLIC KEY", key_text);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return key_text;
    }

    @Override
    protected void onPostExecute(String result)  {
        // Set title into TextView
        //xmlView.setText(result);
        try {
            listener.onGetDataComplete(result, "xml");
        } catch (Exception e) {
            e.printStackTrace();
        }


        //txtkey.setText("PUBLICK KEY: \n" + key);
       // mProgressDialog.dismiss();
        //delegate.processFinish(result);
    }
}
    /*__________________________________________End CLASS Public key AsyncTask_________________________*/
