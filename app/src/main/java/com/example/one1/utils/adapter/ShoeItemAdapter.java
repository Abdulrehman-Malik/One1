package com.example.one1.utils.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.one1.R;
import com.example.one1.utils.model.ShoeItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ShoeItemAdapter extends RecyclerView.Adapter<ShoeItemAdapter.ShoeItemViewHolder> implements Filterable

{

    //private ArrayList<ShoeItem> shoeItemList;
    private ShoeClickedListeners shoeClickedListeners;
    private ArrayList<ShoeItem> mOriginalValues; // Original Values
    private ArrayList<ShoeItem> mDisplayedValues;    // Values to be displayed
    LayoutInflater inflater;
    private Context context;

    public ShoeItemAdapter(Context context, ArrayList<ShoeItem> mProductArrayList,ShoeClickedListeners shoeClickedListeners) {
        this.mOriginalValues = mProductArrayList;
        this.shoeClickedListeners = shoeClickedListeners;
        this.mDisplayedValues = mProductArrayList;
        inflater = LayoutInflater.from(context);
        this.context=context;


    }
    /*
    public ShoeItemAdapter(ShoeClickedListeners shoeClickedListeners){
        this.shoeClickedListeners = shoeClickedListeners;
        //this.mOriginalValues.addAll(shoeItemList);
        //this.mDisplayedValues.addAll(shoeItemList);

    }
    */
    /*
    public void setShoeItemList(ArrayList<ShoeItem> shoeItemList){
        this.mOriginalValues = shoeItemList;
       // this.mOriginalValues = shoeItemList;
        this.mDisplayedValues= shoeItemList;
    }*/
    @NonNull
    @Override
    public ShoeItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_shoe , parent , false);
        return new ShoeItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoeItemViewHolder holder, int position) {
        ShoeItem shoeItem = mDisplayedValues.get(position);
        holder.shoeNameTv.setText(shoeItem.getShoeName());
        holder.shoeBrandNameTv.setText(shoeItem.getShoeBrandName());
        holder.shoePriceTv.setText(String.valueOf(shoeItem.getShoePrice()));
        holder.shoeImageView.setImageResource(shoeItem.getShoeImage());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shoeClickedListeners.onCardClicked(shoeItem);
            }
        });

        holder.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shoeClickedListeners.onAddToCartBtnClicked(shoeItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mDisplayedValues == null){
            return 0;
        }else{
            return mDisplayedValues.size();
        }
    }
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,FilterResults results) {
                //mDisplayedValues.clear();
                mDisplayedValues = (ArrayList<ShoeItem>) results.values; // has the filtered values
                for (int i=0;i<mDisplayedValues.size();i++)
                    //Toast.makeText(context,mDisplayedValues.get(i).getShoeName(), Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();  // notifies the data with new filtered values
            }
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<ShoeItem> FilteredArrList = new ArrayList<ShoeItem>();

                if (mOriginalValues == null) {
                    mOriginalValues = new ArrayList<ShoeItem>(mDisplayedValues); // saves the original data in mOriginalValues
                }

                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = mOriginalValues.size();
                    results.values = mOriginalValues;
                } else {

                    constraint = constraint.toString().toLowerCase();

                    FilteredArrList.clear();
                    for (int i = 0; i < mOriginalValues.size(); i++) {
                        String data = mOriginalValues.get(i).getShoeName();
                if (data.toLowerCase().contains(constraint.toString())) {
                    FilteredArrList.add(new ShoeItem(mOriginalValues.get(i).getShoeName(),mOriginalValues.get(i).getShoeBrandName(),mOriginalValues.get(i).getShoeImage(),mOriginalValues.get(i).getShoePrice(),mOriginalValues.get(i).getErpid()));
                        }
                    }
                    // set the Filtered result to return

                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;

                }
                return results;
            }
        };
        return filter;
    }



    public class ShoeItemViewHolder extends RecyclerView.ViewHolder{
        private ImageView shoeImageView , addToCartBtn;
        private TextView shoeNameTv, shoeBrandNameTv, shoePriceTv;
        private CardView cardView;
        public ShoeItemViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.eachShoeCardView);
            addToCartBtn = itemView.findViewById(R.id.eachShoeAddToCartBtn);
            shoeNameTv = itemView.findViewById(R.id.eachShoeName);
            shoeImageView = itemView.findViewById(R.id.eachShoeIv);
            shoeBrandNameTv = itemView.findViewById(R.id.eachShoeBrandNameTv);
            shoePriceTv = itemView.findViewById(R.id.eachShoePriceTv);
        }
    }

    public interface ShoeClickedListeners{
        void onCardClicked(ShoeItem shoe);
        void onAddToCartBtnClicked(ShoeItem shoeItem);
    }
}
