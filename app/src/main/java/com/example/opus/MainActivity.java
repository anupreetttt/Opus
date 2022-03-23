package com.example.opus;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.opus.R;
import com.google.firebase.auth.FirebaseAuth;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    private  Button logout;
    ListView listView;
    String items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        logout = findViewById(R.id.logout);
        listView = findViewById(R.id.listView);

        // Logout Button:-
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
//                Toast.makeText(MainActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(MainActivity.this, LoginPage.class);
//                startActivity(intent);
//            }
//        });
        // To access permission from external storage:-

        Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
//                        Toast.makeText(MainActivity.this, "Runtime permission given", Toast.LENGTH_SHORT).show();
                        ArrayList<File> mySongs = fetchSongs(Environment.getExternalStorageDirectory());
                        String [] items = new String[mySongs.size()];
                        for (int i = 0; i < mySongs.size(); i++){
                            items[i] = mySongs.get(i).getName().replace(".mp3", " ");
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, items);
                        listView.setAdapter(adapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
                                String currentSong = listView.getItemAtPosition(position).toString();
                                intent.putExtra("songList", mySongs);
                                intent.putExtra("currentSong", currentSong);
                                intent.putExtra("position", position);
                                startActivity(intent);

                            }
                        });
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                })
                .check();
    }
    public ArrayList<File> fetchSongs(File file){
       ArrayList arrayList = new ArrayList();
       File [] songs = file.listFiles();
       if (songs != null){
           for (File myFile: songs){
               if (!myFile.isHidden() && myFile.isDirectory()){
                   arrayList.addAll(fetchSongs(myFile));
               }
               else {
                   if(myFile.getName().endsWith(".mp3") && !myFile.getName().startsWith(".")){
                       arrayList.add(myFile);
                   }
               }
           }
       }
        return arrayList;
    }
}