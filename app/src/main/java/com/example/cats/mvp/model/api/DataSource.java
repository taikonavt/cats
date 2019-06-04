package com.example.cats.mvp.model.api;

import com.example.cats.mvp.model.entity.Cat;

import java.util.ArrayList;
import java.util.List;

public class DataSource implements IDataSource {

    private List<Cat> catList;

    public DataSource(){
        catList = createCatList();
    }

    @Override
    public List<Cat> getCatList() {
        return catList;
    }

    private List<Cat> createCatList() {
        String[] names = {
                "Барсик",
                "Кэсис",
                "Ферруцио",
                "Клементе",
                "Грампи",
                "Юппи",
                "Шелли",
                "Кактус",
                "Леопардо",
                "Жуля"
        };
//        String[] urls = {
//                "http://pngimg.com/uploads/cat/cat_PNG50546.png",
//                "http://pngimg.com/uploads/cat/cat_PNG50537.png",
//                "http://pngimg.com/uploads/cat/cat_PNG50525.png",
//                "http://pngimg.com/uploads/cat/cat_PNG50511.png",
//                "http://pngimg.com/uploads/cat/cat_PNG50498.png",
//                "http://pngimg.com/uploads/cat/cat_PNG50480.png",
//                "http://pngimg.com/uploads/cat/cat_PNG50433.png",
//                "http://pngimg.com/uploads/cat/cat_PNG50425.png",
//                "http://pngimg.com/uploads/cat/cat_PNG120.png",
//                "http://pngimg.com/uploads/cat/cat_PNG104.png",
//        };
        String[] urls = {
                "http://pngimg.com/uploads/cat/cat_PNG50550.png",
                "http://pngimg.com/uploads/cat/cat_PNG50526.png",
                "http://pngimg.com/uploads/cat/cat_PNG50530.png",
                "http://pngimg.com/uploads/cat/cat_PNG50538.png",
                "http://pngimg.com/uploads/cat/cat_PNG50535.png",
                "http://pngimg.com/uploads/cat/cat_PNG50539.png",
                "http://pngimg.com/uploads/cat/cat_PNG50544.png",
                "http://pngimg.com/uploads/cat/cat_PNG50543.png",
                "http://pngimg.com/uploads/cat/cat_PNG50522.png",
                "http://pngimg.com/uploads/cat/cat_PNG50521.png",
        };
        ArrayList<Cat> catList = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            catList.add(new Cat(names[i], urls[i]));
        }
        return catList;
    }
}
