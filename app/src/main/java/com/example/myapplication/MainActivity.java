package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BaiHocHelper baiHocHelper;
    ListView lv;
    ArrayList<BaiHoc> arrayList;
    BaiHocAdapter adapter;
    EditText edtten;
    Button btnthem;
    ImageView imgEdit, imgDlt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pc = edtten.getText().toString().trim();
                if(TextUtils.isEmpty(pc)){
                    Toast.makeText(MainActivity.this,"Vui long nhap du lieu",Toast.LENGTH_SHORT).show();
                    return;


                }
                else
                {
                    baiHocHelper.QueryData("INSERT INTO NoiDung VALUES(null, '"+ pc +"') ");
                    actionGetData();
                }
            }
        });

        arrayList = new ArrayList<>();
        adapter = new BaiHocAdapter(this, R.layout.dong_noi_dung,arrayList);
        lv.setAdapter(adapter);

        //taodatabase
        baiHocHelper = new BaiHocHelper(this,"BaiHoc.sqllite",null,1);

        // tao bang
        baiHocHelper.QueryData("CREATE TABLE IF NOT EXISTS NoiDung(Id INTEGER PRIMARY KEY AUTOINCREMENT, TenNoiDung VARCHAR(200))");

        // THEM DU LIEU
//        baiHocHelper.QueryData("INSERT INTO NoiDung VALUES(1, 'Dell') ");
//        baiHocHelper.QueryData("INSERT INTO NoiDung VALUES(2, 'Hp') ");
//        baiHocHelper.QueryData("INSERT INTO NoiDung VALUES(3, 'Apple') ");
//        baiHocHelper.QueryData("INSERT INTO NoiDung VALUES(4, 'MSI') ");
//        baiHocHelper.QueryData("INSERT INTO NoiDung VALUES(5, 'Asus') ");
//        baiHocHelper.QueryData("INSERT INTO NoiDung VALUES(null, 'LG') ");


    }

    //dialogUpdate
    public void DialogUpdate(String pc,int id){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_edit);
        EditText edtEdit = dialog.findViewById(R.id.edtsua);
        Button btnhuy = dialog.findViewById(R.id.btnhuy);
        Button btnupdate = dialog.findViewById(R.id.btnupdate);
        edtEdit.setText(pc);
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenMoi = edtEdit.getText().toString().trim();
                if(TextUtils.isEmpty(tenMoi)){
                    Toast.makeText(MainActivity.this,"Chua Nhap Noi Dung",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    return;
                }
                baiHocHelper.QueryData("UPDATE NoiDung SET TenNoiDung = '"+tenMoi+"' WHERE Id = '"+id+"'");
                dialog.dismiss();
                actionGetData();
            }
        });

        dialog.show();
    }
    //dialogDelete
    public void DialogDelete(String pc,int id){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Ban co dong y xoa: " + pc);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                baiHocHelper.QueryData("DELETE FROM NoiDung WHERE Id = '"+ id +"'");
                actionGetData();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
    // hienthi


    private void actionGetData() {
        Cursor dataBaihoc = baiHocHelper.GetData("SELECT * FROM NoiDung");
        arrayList.clear();
        while (dataBaihoc.moveToNext()){
            String ten = dataBaihoc.getString(1);
            int id = dataBaihoc.getInt(0);

            //Toast.makeText(this, ten, Toast.LENGTH_SHORT).show();
            arrayList.add(new BaiHoc(id,ten));
        }
        adapter.notifyDataSetChanged();
    }



    private void anhXa() {
        lv = (ListView) findViewById(R.id.lvNoiDung);
        edtten = (EditText) findViewById(R.id.edtthem);
        btnthem = findViewById(R.id.btnthem);



    }
}