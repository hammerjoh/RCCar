package de.hammer.rccar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SetupSheetActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_sheet_activity);


        /**final ZoomLinearLayout zoomLinearLayout = (ZoomLinearLayout) findViewById(R.id.zoom_linear_layout);
         zoomLinearLayout.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
        zoomLinearLayout.init(Setup.this);
        return false;
        }
        });**/
    }
}
