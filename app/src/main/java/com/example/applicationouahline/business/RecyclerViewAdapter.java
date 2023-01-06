package com.example.applicationouahline.business;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationouahline.R;
import com.example.applicationouahline.data.DataBaseHandler;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private  Context context;
    List<Item> itemList;
    AlertDialog.Builder builder;
    AlertDialog dialog;
    LayoutInflater inflater;

    public RecyclerViewAdapter(Context context, List<Item>itemList){
        this.context = context;
        this.itemList = itemList;

    }
    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,parent,false);

        return new ViewHolder(view,context);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.itemName.setText("Item: "+item.getItemName());
        holder.itemQty.setText("Qty: "+Integer.toString(item.getItemQuantity()));
        holder.itemSize.setText("Size: "+ Integer.toString(item.getItemSize()));
        holder.itemColor.setText("Color: "+item.getItemColor());
        holder.dateAdded.setText("Added on : "+item.getDateItemAdded());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView itemName;
        public TextView itemColor;
        public TextView itemSize;
        public TextView itemQty;
        public TextView dateAdded;
        public Button editButton;
        public Button deleteButton;
        public int id;


        public ViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);
            context = ctx;

            itemName = itemView.findViewById(R.id.item_name);
            itemColor = itemView.findViewById(R.id.item_color);
            itemSize = itemView.findViewById(R.id.item_size);
            itemQty = itemView.findViewById(R.id.item_quantity);
            dateAdded = itemView.findViewById(R.id.item_date);
            editButton = itemView.findViewById(R.id.edit_button);
            deleteButton = itemView.findViewById(R.id.delete_button);

            editButton.setOnClickListener(this);
            deleteButton.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position =  getAdapterPosition();
            Item item = itemList.get(position);
            switch (v.getId()){
                case R.id.edit_button :
                    //edit item
                    editItem(item);
                    break;
                case R.id.delete_button :
                    //delete item

                    deleteItem(item.getId());
                    break;
            }
        }

        @SuppressLint("SetTextI18n")
        private void editItem(final Item newItem) {

            //  Item item = itemList.get(getAdapterPosition());

            builder =  new AlertDialog.Builder(context);
            inflater = LayoutInflater.from(context);
            final View view = inflater.inflate(R.layout.popup,null);
            Button saveButton;
            final EditText babyItem;
            final EditText itemQuantity;
            final EditText itemColor;
            final EditText itemSize;
            TextView title;

            babyItem = view.findViewById(R.id.babyItem);
            itemQuantity = view.findViewById(R.id.itemQuantity);
            itemColor = view.findViewById(R.id.colorItem);
            itemSize = view.findViewById(R.id.sizeItem);
            saveButton = view.findViewById(R.id.saveBotton);
            saveButton.setText("UPDATE");
            title = view.findViewById(R.id.title);

            title.setText(R.string.edit_item);
            babyItem.setText(newItem.getItemName());
            itemQuantity.setText(Integer.toString(newItem.getItemQuantity()));
            itemColor.setText(newItem.getItemColor());
            itemSize.setText(Integer.toString(newItem.getItemSize()));


            builder.setView(view);
            dialog = builder.create();
            dialog.show();

            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //update our item
                    DataBaseHandler databaseHandler = new DataBaseHandler(context);

                    //update items
                    newItem.setItemName(babyItem.getText().toString());
                    newItem.setItemColor(itemColor.getText().toString());

                    if(!itemQuantity.getText().toString().isEmpty() && !itemSize.getText().toString().isEmpty()) {
                        newItem.setItemQuantity(Integer.parseInt(itemQuantity.getText().toString()));
                        newItem.setItemSize(Integer.parseInt(itemSize.getText().toString()));
                    }

                    if (!babyItem.getText().toString().isEmpty()
                            && !itemColor.getText().toString().isEmpty()
                            && !itemSize.getText().toString().isEmpty()
                            && !itemQuantity.getText().toString().isEmpty()) {

                        databaseHandler.updateItem(newItem);
                        notifyItemChanged(getAdapterPosition(),newItem); //important!
                        dialog.dismiss();

                    }else {
                        Snackbar.make(view, "Fields Empty",
                                        Snackbar.LENGTH_SHORT)
                                .show();
                    }



                }
            });



        }

        private void deleteItem(final int id) {

            builder = new AlertDialog.Builder(context);
            inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.confirmartion_popup,null);
            Button noButton = view.findViewById(R.id.conf_no_button);
            Button yesButton = view.findViewById(R.id.conf_yes_button);
            builder.setView(view);
            dialog = builder.create();
            dialog.show();

            yesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataBaseHandler db = new DataBaseHandler(context);
                    db.deleteItem(id);
                    itemList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    dialog.dismiss();
                }
            });
            noButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog.dismiss();
                }
            });


        }
    }
}