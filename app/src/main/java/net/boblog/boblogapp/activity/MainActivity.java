package net.boblog.boblogapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import net.boblog.boblogapp.R;

/**
 * Created by dave on 15-9-21.
 */
public class MainActivity extends BaseActivity {
    private PopupMenu popupMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        popupMenu = new PopupMenu(this, findViewById(R.id.btn_main_menu));
        popupMenu.inflate(R.menu.main);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    public void onClickShowMenu(View view) {
        popupMenu.show();
    }

    public void onClickAddTab(View view) {
        startActivity(new Intent(this, EditActivity.class));
    }

    public void onClickSettingTab(View view) {
        startActivity(new Intent(this, SettingActivity.class));
    }
}
