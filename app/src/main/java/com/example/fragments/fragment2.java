package com.example.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class fragment2 extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public fragment2() {

    }

    public static fragment2 newInstance(String param1, String param2) {
        fragment2 fragment = new fragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.newsmain_searchnews, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final RecyclerView r1 = (RecyclerView) view.findViewById(R.id.recyclerView);
        final RequestQueue mQueue = Volley.newRequestQueue(getContext());
        final ImageView search = (ImageView)view.findViewById(R.id.searchNow);
       final EditText entered = (EditText)view.findViewById(R.id.enteredText);

        final String url = "https://newsapi.org/v2/top-headlines?country=us&apiKey=27e64574c3c64573afbea511eb0e65d2";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            ArrayList<NewsData> items = new ArrayList<>();
                            JSONArray jsonArray = response.getJSONArray("articles");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject cases = jsonArray.getJSONObject(i);
                                String title = cases.getString("title");
                                String url = cases.getString("urlToImage");
                                String info = cases.getString("url");

                                items.add(new NewsData(title, url, info));

                            }
                            MyOwnAdapter adapter = new MyOwnAdapter(getContext(), items);
                            r1.setAdapter(adapter);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                            r1.setLayoutManager(layoutManager);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);

  search.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View view) {
          hideSoftKeyboard(getActivity());
          String a = entered.getText().toString().toLowerCase();
          String urlmain = "http://newsapi.org/v2/everything?" +
                  "q=" + a + "&" + "apiKey=afcdb79c38d347b58105f5b408359ce3";

          JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urlmain, null,
                  new Response.Listener<JSONObject>() {
                      @Override
                      public void onResponse(JSONObject response) {
                          try {

                              ArrayList<NewsData> items = new ArrayList<>();
                              JSONArray jsonArray = response.getJSONArray("articles");
                              for (int i = 0; i < jsonArray.length(); i++) {
                                  JSONObject cases = jsonArray.getJSONObject(i);
                                  String title = cases.getString("title");
                                  String url = cases.getString("urlToImage");
                                  String info = cases.getString("url");

                                  items.add(new NewsData(title, url, info));

                              }
                              MyOwnAdapter adapter = new MyOwnAdapter(getContext(), items);
                              r1.setAdapter(adapter);
                              LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                              r1.setLayoutManager(layoutManager);


                          } catch (JSONException e) {
                              e.printStackTrace();
                          }

                      }
                  }, new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {
                  error.printStackTrace();
              }
          });

          mQueue.add(request);
      }
  });

    }
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }



}