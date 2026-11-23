package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.myapplication.databinding.Fragment3Binding;

public class Fragment3 extends Fragment {

    private static final String ARG_NAME = "user_name";
    private String userName;
    private Fragment3Binding binding;

    public interface OnFragment3Listener {
        void onAgreeChanged(boolean isChecked);
    }

    private OnFragment3Listener listener;

    public Fragment3() {
        // Required empty public constructor
    }

    public static Fragment3 newInstance(String name) {
        Fragment3 fragment = new Fragment3();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragment3Listener) {
            listener = (OnFragment3Listener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragment3Listener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userName = getArguments().getString(ARG_NAME);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = Fragment3Binding.inflate(inflater, container, false);

        if (userName != null) {
            binding.tvNameDisplay.setText("Welcome, " + userName + "!");
        }

        binding.cbAgree.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (listener != null) {
                listener.onAgreeChanged(isChecked);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
