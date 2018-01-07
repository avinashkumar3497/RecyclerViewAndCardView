package com.annie.smartherd.recyclerviewandcardview;

import android.content.Context;
import android.nfc.Tag;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.content.ContentValues.TAG;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{           //MyAdapter will be inherited from RecyclerView.Adapter

	private List<NatureModel> objectList;
	private LayoutInflater inflater;

	public MyAdapter(Context context, List<NatureModel> objectList) {                          //Constructor for MyAdaptor, parameters(context & objectList)-context is the context from which MyAdapter is called & objectList is the list of the data items
		inflater = LayoutInflater.from(context);
		this.objectList = objectList;
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {                   //Method implemented for MyAdaptor Class, it is called when a view is created, inside this method, LayoutIn
		View view = inflater.inflate(R.layout.list_item, parent, false);                   //defined a variable view, it will inflate data-items into list_item
		MyViewHolder holder = new MyViewHolder(view);                                      //Apply some Brain
		return holder;                                                                     //ASB
	}

	@Override
	public int getItemCount() {                                                               //Method implemented for MyAdaptor Class
		return objectList.size();
	}

	@Override
	public void onBindViewHolder(MyViewHolder holder, int position) {                         //Method implemented for MyAdaptor Class, it is called everytime a new item in the list is to be added.
		NatureModel current = objectList.get(position);                                   //Using this line, on the basis of the position given, the current item is assigned to "current"(instance of NatureModel class)
       		holder.setData(current, position);                                                //data is getting combined to the viewholder. Now on the red colored bulb that'll appear there, click on that and click on Create 'setData()'
		holder.setListeners();                                                            //Have not discussed in the video
	}

	class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {         //MyViewHolder will be inherited from RecyclerView.ViewHolder

		private TextView title;
		private ImageView imgThumb, imgDelete, imgCopy;
		private int position;
		private NatureModel currentObject;

		public MyViewHolder(View itemView) {                                                 //Created a contructor matching the super.
			super(itemView);
			title       = (TextView)  itemView.findViewById(R.id.tvTitle);               //Initialisation
			imgThumb    = (ImageView) itemView.findViewById(R.id.img_thumb);             //Initialisation
			imgDelete   = (ImageView) itemView.findViewById(R.id.img_delete);            //Initialisation
			imgCopy = (ImageView) itemView.findViewById(R.id.img_copy);                  //Initialisation
		}

		public void setData(NatureModel currentObject, int position) {                       //Created 
			this.title.setText(currentObject.getTitle());
			this.imgThumb.setImageResource(currentObject.getImageID());
			this.position = position;
			this.currentObject = currentObject;
		}

		public void setListeners() {
			imgDelete.setOnClickListener(MyViewHolder.this);
			imgCopy.setOnClickListener(MyViewHolder.this);
			imgThumb.setOnClickListener(MyViewHolder.this);
		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.img_delete:
					removeItem(position);
					break;

				case R.id.img_copy:
					addItem(position, currentObject);
					break;
			}
		}
	}

	public void removeItem(int position) {
		objectList.remove(position);
		notifyItemRemoved(position);
		notifyItemRangeChanged(position, objectList.size());
//		notifyDataSetChanged();
	}

	public void addItem(int position, NatureModel currentObject) {
		objectList.add(position, currentObject);
		notifyItemInserted(position);
		notifyItemRangeChanged(position, objectList.size());
//		notifyDataSetChanged();
	}
}
