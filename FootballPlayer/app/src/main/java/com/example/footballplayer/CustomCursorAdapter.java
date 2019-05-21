package com.example.footballplayer;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.footballplayer.data.FootballContract;


/**
 * This CustomCursorAdapter creates and binds ViewHolders, that hold the description and priority of a task,
 * to a RecyclerView to efficiently display data.
 */
public class CustomCursorAdapter extends RecyclerView.Adapter<CustomCursorAdapter.PlayerViewHolder> {

    // Class variables for the Cursor that holds task data and the Context
    private Cursor mCursor;
    private Context mContext;


    /**
     * Constructor for the CustomCursorAdapter that initializes the Context.
     *
     * @param mContext the current Context
     */
    public CustomCursorAdapter(Context mContext) {
        this.mContext = mContext;
    }


    /**
     * Called when ViewHolders are created to fill a RecyclerView.
     *
     * @return A new TaskViewHolder that holds the view for each task
     */
    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Inflate the task_layout to a view
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.content_main, parent, false);

        return new PlayerViewHolder(view);
    }


    /**
     * Called by the RecyclerView to display data at a specified position in the Cursor.
     *
     * @param holder The ViewHolder to bind Cursor data to
     * @param position The position of the data in the Cursor
     */
    @Override
    public void onBindViewHolder(PlayerViewHolder holder, int position) {

        // Indices for the _id, description, and priority columns
        int idIndex = mCursor.getColumnIndex(FootballContract.FootballEntry._ID);
        int nameIndex = mCursor.getColumnIndex(FootballContract.FootballEntry.COLUMN_NAME);
        int teamIndex = mCursor.getColumnIndex(FootballContract.FootballEntry.COLUMN_TEAM);
        int numIndex = mCursor.getColumnIndex(FootballContract.FootballEntry.COLUMN_NUM);
        int startIndex = mCursor.getColumnIndex(FootballContract.FootballEntry.COLUMN_START);

        mCursor.moveToPosition(position); // get to the right location in the cursor

        // Determine the values of the wanted data
        final int id = mCursor.getInt(idIndex);
        String name = mCursor.getString(nameIndex);
        String team = mCursor.getString(teamIndex);
        int num = mCursor.getInt(numIndex);
        int start = mCursor.getInt(startIndex);

//        //Set values
//        holder.itemView.setTag(id);
//        holder.taskDescriptionView.setText(description);
//
//        // Programmatically set the text and color for the priority TextView
//        String priorityString = "" + priority; // converts int to String
//        holder.priorityView.setText(priorityString);
//
//        GradientDrawable priorityCircle = (GradientDrawable) holder.priorityView.getBackground();
//        // Get the appropriate background color based on the priority
//        int priorityColor = getPriorityColor(priority);
//        priorityCircle.setColor(priorityColor);

    }


    /*
    Helper method for selecting the correct priority circle color.
    P1 = red, P2 = orange, P3 = yellow
    */
    private int getPriorityColor(int priority) {
        int priorityColor = 0;

        switch(priority) {
            case 1: priorityColor = ContextCompat.getColor(mContext, R.color.materialRed);
                break;
            case 2: priorityColor = ContextCompat.getColor(mContext, R.color.materialOrange);
                break;
            case 3: priorityColor = ContextCompat.getColor(mContext, R.color.materialYellow);
                break;
            default: break;
        }
        return priorityColor;
    }


    /**
     * Returns the number of items to display.
     */
    @Override
    public int getItemCount() {
        if (mCursor == null) {
            return 0;
        }
        return mCursor.getCount();
    }


    /**
     * When data changes and a re-query occurs, this function swaps the old Cursor
     * with a newly updated Cursor (Cursor c) that is passed in.
     */
    public Cursor swapCursor(Cursor c) {
        // check if this cursor is the same as the previous cursor (mCursor)
        if (mCursor == c) {
            return null; // bc nothing has changed
        }
        Cursor temp = mCursor;
        this.mCursor = c; // new cursor value assigned

        //check if this is a valid cursor, then update the cursor
        if (c != null) {
            this.notifyDataSetChanged();
        }
        return temp;
    }


    // Inner class for creating ViewHolders
    class PlayerViewHolder extends RecyclerView.ViewHolder {

        // Class variables for the task description and priority TextViews
        TextView nameView;
        TextView teamView;
        TextView numView;
        TextView startView;
        /**
         * Constructor for the TaskViewHolders.
         *
         * @param itemView The view inflated in onCreateViewHolder
         */
        public PlayerViewHolder(View itemView) {
            super(itemView);
//            nameView = (TextView) itemView.findViewById(R.id);
//            teamView = (TextView) itemView.findViewById(R.id);
//            numView = (TextView) itemView.findViewById(R.id);
//            startView = (TextView) itemView.findViewById(R.id);
        }
    }
}