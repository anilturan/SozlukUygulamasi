package com.example.anilturan.sozlukapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.anilturan.sozlukapp.api.GlosbeService;
import com.example.anilturan.sozlukapp.api.ServiceGenerator;
import com.example.anilturan.sozlukapp.model.Definition;
import com.example.anilturan.sozlukapp.model.Glosbe;
import com.example.anilturan.sozlukapp.model.GlosbeExamples;
import com.example.anilturan.sozlukapp.model.SpinnerModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private GlosbeService glosbeService;
    private RecyclerView recyclerView;
    private Button searchButton;
    private EditText searchText;
    private String json = "json";
    private Spinner spinnerFrom;
    private Spinner spinnerTo;
    private ArrayList<Definition> dataList;
    private MeanAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    private void setDataInSpinner(Spinner id, int dataArray) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, dataArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        id.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        glosbeService = ServiceGenerator.createService(GlosbeService.class);

        searchButton = findViewById(R.id.button);
        searchText = findViewById(R.id.searchText);
        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        recyclerView = findViewById(R.id.recyclerView);
        setData();

        mAdapter = new MeanAdapter(this);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String langFrom = spinnerFrom.getSelectedItem().toString();
                String langTo = spinnerTo.getSelectedItem().toString();
                getMean(getLanguageCode(langFrom), getLanguageCode(langTo), json, searchText.getText().toString());

                if (v != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(searchText.getWindowToken(), 0);
                }
            }
        });
    }


    public void getMean(String from, String dest, String format, String phrase) {

        final Call<Glosbe> call = glosbeService.getMean(from, dest, format, phrase);
        call.enqueue(new Callback<Glosbe>() {

            @Override
            public void onResponse(Call<Glosbe> call, Response<Glosbe> response) {
                if (response.body() != null) {
                    if (response.body().getResult().equals("ok")) {
                        dataList = new ArrayList<>();

                        for (GlosbeExamples item :
                                response.body().getExamples()) {
                            Definition def = new Definition();

                            def.setFirst(item.getFirst().toString());
                            def.setSecond(item.getSecond().toString());
                            dataList.add(def);
                        }
                        mAdapter.setList(dataList);
                    }
                }
            }

            @Override
            public void onFailure(Call<Glosbe> call, Throwable t) {

            }
        });
    }

    public void setData() {

        String languages[] = {"Türkçe", "İngilizce", "Almanca", "Azerice", "Çince", "Farsça", "Fransızca", "Hintçe", "İspanyolca", "İtalyanca", "Japonca", "Kırgızca", "Özbekçe", "Rusça"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);
    }

    public String getLanguageCode(String language) {

        ArrayList<SpinnerModel> languageList = new ArrayList<SpinnerModel>();
        languageList.add(new SpinnerModel("tur", "Türkçe"));
        languageList.add(new SpinnerModel("eng", "İngilizce"));
        languageList.add(new SpinnerModel("deu", "Almanca"));
        languageList.add(new SpinnerModel("aze", "Azerice"));
        languageList.add(new SpinnerModel("zho", "Çince"));
        languageList.add(new SpinnerModel("fas", "Farsça"));
        languageList.add(new SpinnerModel("fra", "Fransızca"));
        languageList.add(new SpinnerModel("hin", "Hintçe"));
        languageList.add(new SpinnerModel("spa", "İspanyolca"));
        languageList.add(new SpinnerModel("ita", "İtalyanca"));
        languageList.add(new SpinnerModel("jpn", "Japonca"));
        languageList.add(new SpinnerModel("kir", "Kırgızca"));
        languageList.add(new SpinnerModel("uzb", "Özbekçe"));
        languageList.add(new SpinnerModel("rus", "Rusça"));

        for (int i = 0; i < languageList.size(); i++) {
            if (languageList.get(i).getLanguage().toString().equals(language)) {
                return languageList.get(i).getLangCode().toString();
            }
        }
        return "tur";
    }
}
