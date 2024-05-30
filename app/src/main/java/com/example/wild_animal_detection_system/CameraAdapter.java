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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class CameraAdapter extends RecyclerView.Adapter<CameraAdapter.CameraViewHolder> {
    private final Context context;
    private final ArrayList<Cameras> cameraList;
    private final DatabaseReference databaseReference;

    public CameraAdapter(Context context, ArrayList<Cameras> cameraList, String phoneNo) {
        this.context = context;
        this.cameraList = cameraList;
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(phoneNo).child("Cameras");
    }

    class CameraViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final ImageView mMenus;

        CameraViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.camName);
            mMenus = v.findViewById(R.id.menu);
            mMenus.setOnClickListener(this::popupMenus);
        }

        private void popupMenus(View v) {
            int position = getAdapterPosition();
            Cameras camera = cameraList.get(position);
            PopupMenu popupMenus = new PopupMenu(context, v);
            popupMenus.inflate(R.menu.show_menu);
            popupMenus.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();

                if (id == R.id.editText) {
                    View dialogView = LayoutInflater.from(context).inflate(R.layout.add_camera, null);
                    EditText name = dialogView.findViewById(R.id.cam_Name);
                    EditText number = dialogView.findViewById(R.id.cam_IP);
                    name.setText(camera.getCamName());
                    number.setText(camera.getCamIP());
                    new AlertDialog.Builder(context)
                            .setView(dialogView)
                            .setPositiveButton("Ok", (dialog, which) -> {

                                String newCamName = name.getText().toString();
                                String newCamIP = number.getText().toString();

                                if (!isValidIPAddress(newCamIP)) {
                                    Toast.makeText(context, "Invalid IP Address. Please enter a valid IP address with port (e.g., http://192.168.24.3:8080)", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                camera.setCamName(name.getText().toString());
                                camera.setCamIP(number.getText().toString());

                                databaseReference.child(camera.getKey()).setValue(camera).addOnCompleteListener(task -> {
                                    if (task.isSuccessful()) {
                                        notifyDataSetChanged();
                                        Toast.makeText(context, "Camera Information is Edited", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(context, "Failed to edit camera", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                dialog.dismiss();
                            })
                            .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                            .create()
                            .show();
                    return true;
                } else if (id == R.id.delete) {
                    new AlertDialog.Builder(context)
                            .setTitle("Delete")
                            .setIcon(R.drawable.baseline_warning_24)
                            .setMessage("Are you sure you want to delete this information?")
                            .setPositiveButton("Yes", (dialog, which) -> {
                                databaseReference.child(camera.getKey()).removeValue().addOnCompleteListener(task -> {
                                    if (task.isSuccessful()) {
                                        notifyItemRemoved(position);
                                        notifyItemRangeChanged(position, cameraList.size());
                                        Toast.makeText(context, "Deleted this Information", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(context, "Failed to delete camera", Toast.LENGTH_SHORT).show();
                                    }
                                });
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
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.camera, parent, false);
        return new CameraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CameraViewHolder holder, int position) {
        Cameras newList = cameraList.get(position);
        holder.name.setText(newList.getCamName());
    }

    @Override
    public int getItemCount() {
        return cameraList.size();
    }

    public boolean isValidIPAddress(String camip) {
        String IP_ADDRESS_PATTERN = "^http://((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(:\\d{1,5})$";
        return camip.matches(IP_ADDRESS_PATTERN);
    }
}
