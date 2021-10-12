package com.example.fragmentapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KompetisiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KompetisiFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public KompetisiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KompetisiFragment newInstance(String param1, String param2) {
        KompetisiFragment fragment = new KompetisiFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    // membuat variabel
    RecyclerView rv;
    RecyclerView.Adapter rvAdapter;
    RecyclerView.LayoutManager rvLayoutManager;
    ArrayList<ItemModel> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kompetisi, container, false);

        // awal Recycler View
        rv = view.findViewById(R.id.recycleView); // menginisialisasi
        rv.setHasFixedSize(true);

        rvLayoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(rvLayoutManager);
        rv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        data = new ArrayList<>();
        for (int i = 0; i < Data.headLine.length; i++) { // memanggil data berdasarkan panjang data
            data.add(new ItemModel(
                    Data.headLine[i], // mengambil nilai array berdasarkan nilai index ke-i
                    Data.subHeadLine[i],
                    Data.iconList[i]
            ));
        }

        rvAdapter = new AdapterRecyclerView(getActivity(), data);
        rv.setAdapter(rvAdapter);
        // akhir Recycler View

        return view;
    }
}