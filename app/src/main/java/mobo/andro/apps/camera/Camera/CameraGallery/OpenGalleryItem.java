package mobo.andro.apps.camera.Camera.CameraGallery;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

import mobo.andro.apps.camera.Camera.CameraGallery.Adapters.OpenGalleryItemAdapter;
import mobo.andro.apps.camera.CustomAds;
import mobo.andro.apps.camera.R;

import static mobo.andro.apps.camera.CollageMaker.MainActivity.isNetworkAvailable;

public class OpenGalleryItem extends Activity
{
    public static ArrayList<ArrayList<AlbumCustomData>> temp_data_open_gallery=new ArrayList<ArrayList<AlbumCustomData>>();
    RecyclerView recyclerView;
    public static String selected_folder_name="";
    public static int height,width;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_open_gallery_item);

        LinearLayout adContainer=(LinearLayout) findViewById(R.id.adContainer);
        CustomAds.facebookAdBanner(OpenGalleryItem.this,adContainer);


        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        height =displaymetrics.heightPixels;
        width = displaymetrics.widthPixels;

        TextView headertext=(TextView)findViewById(R.id.headertext);
        headertext.setText(selected_folder_name);
        ImageView btn_close=(ImageView)findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        OpenGalleryItemAdapter openGalleryItemAdapter=new OpenGalleryItemAdapter(temp_data_open_gallery,OpenGalleryItem.this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.getItemAnimator().setChangeDuration(0);
        recyclerView.setAdapter(openGalleryItemAdapter);

    }
}
