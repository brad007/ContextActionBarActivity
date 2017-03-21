package com.fire.fire.contextactionbaractivity.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.fire.fire.contextactionbaractivity.R;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by brad on 2017/03/21.
 */

public class SelectionAdapter extends ArrayAdapter<String> {

    private HashMap<Integer, Boolean> mSelection = new HashMap<>();
    private Resources mResources;



    public SelectionAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int
            textViewResourceId, @NonNull String[] objects, Resources resources) {
        super(context, resource, textViewResourceId, objects);
        mResources = resources;
    }

    public void setNewSelection(int position, boolean value){
        mSelection.put(position,value);
        notifyDataSetChanged();
    }

    public boolean isPositionChecked(int position){
        Boolean result = mSelection.get(position);
        return result == null ? false : result;
    }


    public Set<Integer> getCurrentCheckedPosition(){
        return mSelection.keySet();
    }

    public void removeSelection(int position){
        mSelection.remove(position);
        notifyDataSetChanged();
    }

    public void clearSelection(){
        mSelection = new HashMap<Integer, Boolean>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        v.setBackgroundColor(mResources.getColor(R.color.colorPrimary));

        if(mSelection.get(position) != null){
            v.setBackgroundColor(mResources.getColor(R.color.colorAccent));
        }
        return v;
    }
}
