package tw.com.flag.a1031_first_demo;

        import android.app.Activity;
        import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
        import android.widget.VideoView;

public class loginPage extends AppCompatActivity implements MediaPlayer.OnCompletionListener {
    public VideoView vdv;
    static Activity ActivityA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ActivityA = this;

        Uri uri;
        uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.logi);

        vdv = (VideoView) findViewById(R.id.videoView);
        //MediaController mediaCtrl = new MediaController(this);

        //vdv.setMediaController(mediaCtrl);
        vdv.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.logi));

        vdv.seekTo(2000);
        vdv.requestFocus();
        vdv.start();

        boolean b = vdv.isPlaying();
        vdv.setOnCompletionListener(this);


    }

    public void next(View arg0) {
        Intent intent= new Intent(this, mainPage.class);
        this.startActivity(intent);
    }



    public void onCompletion(MediaPlayer mp) {

        int len=vdv.getDuration();
        if (len == 13)
        {
            vdv.seekTo(2000);
            vdv.start();
        }
        vdv.start();
    }

}


