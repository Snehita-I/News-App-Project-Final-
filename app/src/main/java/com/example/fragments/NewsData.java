package com.example.fragments;

public class NewsData {


        String title1, description1,moreInfo;

        public NewsData() {
        }

        public NewsData(String title, String description,String inf) {
            title1 = title;
            description1 = description;
            moreInfo = inf;

        }

        public String getTitle() {
            return title1;
        }

        public String getUrl() {
            return description1;
        }
        public String getMore(){return moreInfo;}

}