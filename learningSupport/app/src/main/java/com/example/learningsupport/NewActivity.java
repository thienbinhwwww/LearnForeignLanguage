package com.example.learningsupport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.learningsupport.model.NewModel;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewActivity extends AppCompatActivity {
    newAdapter adapter;
    ArrayList<NewModel> list ;
    ListView listView;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        list = new ArrayList<NewModel>();

        listView = findViewById(R.id.listView_new);
        AsyncTask<String ,Void,String> content = new RssFeed().execute("https://vnexpress.net/rss/giao-duc.rss");

        intent = new Intent(this,DetailNewActivity.class);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String link = list.get(i).link;
                intent.putExtra("linkURL",link);
                startActivity(intent);
            }
        });

    }
    public class RssFeed extends AsyncTask<String,Void, String >{

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader reader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line="";
                while ((line = bufferedReader.readLine())!=null){
                    content.append(line);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            XMLParse xmlParse = new XMLParse();
            try {
                Document document = xmlParse.getDocument(s);
                NodeList nodeList = document.getElementsByTagName("item");
                NodeList nodeListDes = document.getElementsByTagName("description");
                String title="";
                String img="";
                String link="";
                for (int i=0;i<nodeList.getLength();i++){

                    String cdata = nodeListDes.item(i+1).getTextContent();
                    Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                    Matcher matcher = p.matcher(cdata);
                    if(matcher.find()){
                        img = matcher.group(1);
                    }
                    Element element = (Element) nodeList.item(i);
                    title=xmlParse.getValue(element,"title");
                    link=xmlParse.getValue(element,"link");
                    list.add(new NewModel(title,link,img));
                }
                adapter = new newAdapter(NewActivity.this, android.R.layout.simple_expandable_list_item_1,list);
                listView.setAdapter(adapter);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
        }

    }
}