package com.fire.fire.contextactionbaractivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fire.fire.contextactionbaractivity.adapters.SelectionAdapter;

public class MainActivity extends AppCompatActivity {

    ListView mListView;
    private String[] data = {"One", "Two", "Three", "Four", "Five", "Six"};

    private SelectionAdapter mAdapter;


    //Let's start with the UI
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.list);
        mAdapter = new SelectionAdapter(
                MainActivity.this,
                R.layout.row_listt_item,
                R.id.textView,
                data,
                getResources());
        mListView.setAdapter(mAdapter);

        mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        mListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            private int number = 0;

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                if(checked){
                    number++;
                    mAdapter.setNewSelection(position, checked);
                }else{
                    number--;
                    mAdapter.removeSelection(position);
                }
                mode.setTitle(number + " selected");
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                number = 0;

                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.contextual_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_delete:
                        number = 0;
                        mAdapter.clearSelection();
                        mode.finish();
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mListView.setItemChecked(position, !mAdapter.isPositionChecked(position));
                return false;
            }
        });

    }
}
