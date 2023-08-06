package com.sina.roomrx.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.sina.roomrx.databinding.CustomDialogBinding;

public class CustomDomainDialog extends DialogFragment {
    private CustomDialogBinding binding;
    ICustomDialog iCustomDialog;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = CustomDialogBinding.inflate(requireActivity().getLayoutInflater());
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setView(binding.getRoot());

        binding.button.setOnClickListener(v -> {
            String domainName = binding.textView.getText().toString();
            if (domainName.isEmpty()) {
                Toast.makeText(requireContext(), "Text is empty!", Toast.LENGTH_SHORT).show();

            } else {
                iCustomDialog.setText(domainName);
                dismiss();
            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        iCustomDialog = (ICustomDialog) context;
    }

    public interface ICustomDialog {
        void setText(String domainName);
    }
}
