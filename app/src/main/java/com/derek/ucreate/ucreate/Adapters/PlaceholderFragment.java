package com.derek.ucreate.ucreate.Adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.derek.ucreate.ucreate.Catalogs.Catalog_1;
import com.derek.ucreate.ucreate.Catalogs.Catalog_2;
import com.derek.ucreate.ucreate.Catalogs.Catalog_3;
import com.derek.ucreate.ucreate.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static List<Fragment> fragmentList = new ArrayList<>();

    public static void setInstances() {
        fragmentList.add(new Catalog_1());
        fragmentList.add(new Catalog_2());
        fragmentList.add(new Catalog_3());
    }

    public static Fragment getInstance(int sectionNumber){
        return fragmentList.get(sectionNumber);
    }

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_catalog, container, false);
        return rootView;
    }
}
