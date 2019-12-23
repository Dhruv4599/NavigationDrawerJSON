package com.example.navigationdrawerdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navigationdrawerdemo.modal.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Page1 extends Fragment {

    ListView lv;
    Context context;
    String TAG = "==MSG==";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fr_page1,container,false);

        context = getActivity();
        lv = v.findViewById(R.id.lv);

        loaddata();


        return v;
    }

    void loaddata(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Service.BASEPATH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);


        Call<Category> data= service.getCategory(Service.APIKEY);

        try {

            data.enqueue(new Callback<Category>() {
                @Override
                public void onResponse(Call<Category> call, Response<Category> response) {
                    if(response!=null){

                        List<Category.information> categorydata = response.body().data;
                        Adapter adapter = new Adapter(categorydata);
                        lv.setAdapter(adapter);

                    }else{
                        Toast.makeText(context,"Sorry, Response Return Null",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Category> call, Throwable t) {
                    Toast.makeText(context,"Sorry data not found",Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception e){
            Log.e(TAG,"Error = "+e);
        }
    }

    class Adapter extends BaseAdapter {
        List<Category.information> data;

        Adapter(List<Category.information> data){
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View v, ViewGroup viewGroup) {
            v = LayoutInflater.from(context).inflate(R.layout.adapter_categorydata,viewGroup,false);

            TextView id = v.findViewById(R.id.tv_id);
            TextView categoryname = v.findViewById(R.id.tv_catname);

            id.setText(""+data.get(i).id);
            categoryname.setText(""+data.get(i).categoryName);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id",data.get(i).id);

                    Page2 p2 = new Page2();
                    p2.setArguments(bundle);

                    final FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.frame1, p2, "p2");
                    ft.addToBackStack("p1");
                    ft.commit();

                }
            });


            return v;
        }
    }
}
