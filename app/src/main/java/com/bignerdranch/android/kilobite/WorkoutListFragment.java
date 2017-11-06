package com.bignerdranch.android.kilobite;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by kent on 11/5/17.
 */

public class WorkoutListFragment extends Fragment {
    private RecyclerView mWorkoutRecyclerView;
    private WorkoutAdapter mAdapter;
    private TextView mExerciseTextView;
    private TextView mRepTextView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_workout_list, container,false);

        mWorkoutRecyclerView= (RecyclerView) view.findViewById(R.id.workout_recycler_view);
        mWorkoutRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private void updateUI(){
        WorkoutLab workoutLab = WorkoutLab.get(getActivity());
        List<Workout> workouts = workoutLab.getWorkouts();
        mAdapter = new WorkoutAdapter(workouts);
        mWorkoutRecyclerView.setAdapter(mAdapter);
    }

    private class WorkoutHolder extends RecyclerView.ViewHolder{
        public WorkoutHolder (LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_workout,parent,false));

            mExerciseTextView = (TextView) itemView.findViewById(R.id.exercise_name);
            mRepTextView = (TextView) itemView.findViewById(R.id.number_of_reps);
        }
        private Workout mWorkout;
        public void bind(Workout workout){
            mWorkout= workout;
            mExerciseTextView.setText(mWorkout.getExercise());
            mRepTextView.setText(Integer.toString(mWorkout.getReps()));
        }
    }

    private class WorkoutAdapter extends RecyclerView.Adapter<WorkoutHolder>{
        private List<Workout> mWorkouts;

        public WorkoutAdapter(List<Workout> workouts){
            mWorkouts=workouts;
        }

        @Override
        public WorkoutHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new WorkoutHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(WorkoutHolder holder, int position){
            Workout workout = mWorkouts.get(position);
            holder.bind(workout);
        }

        @Override
        public int getItemCount(){
            return mWorkouts.size();
        }
    }



}
