package com.example.wild_animal_detection_system;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class CameraAdapter extends RecyclerView.Adapter<CameraAdapter.CameraViewHolder> {
    private final Context c;
    private final ArrayList<Users> cameraList;

    public CameraAdapter(Context c, ArrayList<Users> cameraList) {
        this.c = c;
        this.cameraList = cameraList;
    }

    class CameraViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
//        private final TextView mbNum;
        private final ImageView mMenus;

        CameraViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.camName);
//            mbNum = v.findViewById(R.id.mSubTitle);
            mMenus = v.findViewById(R.id.menu);
            mMenus.setOnClickListener(this::popupMenus);
        }

        private void popupMenus(View v) {
            Users position = cameraList.get(getAdapterPosition());
            PopupMenu popupMenus = new PopupMenu(c, v);
            popupMenus.inflate(R.menu.show_menu);
            popupMenus.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();

                if(id == R.id.editText) {
                    View dialogView = LayoutInflater.from(c).inflate(R.layout.add_camera, null);
                    EditText name = dialogView.findViewById(R.id.cam_Name);
                    EditText number = dialogView.findViewById(R.id.cam_IP);
                    new AlertDialog.Builder(c)
                            .setView(dialogView)
                            .setPositiveButton("Ok", (dialog, which) -> {
                                position.camName = name.getText().toString();
                                position.camIP = number.getText().toString();
                                notifyDataSetChanged();
                                Toast.makeText(c, "User Information is Edited", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            })
                            .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                            .create()
                            .show();
                    return true;
                }
                else if(id == R.id.delete) {
                    new AlertDialog.Builder(c)
                            .setTitle("Delete")
                            .setIcon(R.drawable.baseline_warning_24)
                            .setMessage("Are you sure delete this Information")
                            .setPositiveButton("Yes", (dialog, which) -> {
                                cameraList.remove(getAdapterPosition());
                                notifyDataSetChanged();
                                Toast.makeText(c, "Deleted this Information", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            })
                            .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                            .create()
                            .show();
                    return true;
                }
                return true;
            });
            popupMenus.show();
            try {
                Field field = PopupMenu.class.getDeclaredField("mPopup");
                field.setAccessible(true);
                Object menuPopupHelper = field.get(popupMenus);
                Class<?> classPopupHelper = Class.forName(menuPopupHelper.getClass().getName());
                Method setForceIcons = classPopupHelper.getMethod("setForceShowIcon", boolean.class);
                setForceIcons.invoke(menuPopupHelper, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @NonNull
    @Override
    public CameraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.camera, parent, false);
        return new CameraViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CameraViewHolder holder, int position) {
        Users newList = cameraList.get(position);
        System.out.println(newList.getCamName());
        holder.name.setText(newList.camName);

//        String nameCAM = newList.camName;
//        if(nameCAM.length() > 8) {
//            if(nameCAM.substring(0,9).equals("CamName:")) {
//                nameCAM = nameCAM.substring(9);
//                holder.name.setText(nameCAM);
//            }
//            else {
//                holder.name.setText(newList.camName);
//            }
//        } else {
//            holder.name.setText(newList.camName);
//        }
    }

    @Override
    public int getItemCount() {
        return cameraList.size();
    }
}

