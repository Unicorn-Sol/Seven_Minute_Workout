package com.sevenminuteworkout

class Constants {

    companion object{
        fun getExercises() : ArrayList<ExerciseModel> {

            val list = ArrayList<ExerciseModel>()

            val jumping_jacks = ExerciseModel(1,"Jumping Jacks", R.drawable.ic_jumping_jacks, false,false)
            val abdominal_crunch = ExerciseModel(2,"Abdominal Crunch", R.drawable.ic_abdominal_crunch, false,false)
            val high_knees_running_in_place = ExerciseModel(3,"High Knees Running In Place", R.drawable.ic_high_knees_running_in_place, false,false)
            val lunge = ExerciseModel(4,"Lunge", R.drawable.ic_lunge, false,false)
            val plank = ExerciseModel(5,"Plank", R.drawable.ic_plank, false,false)
            val push_up = ExerciseModel(6,"Push Up", R.drawable.ic_push_up, false,false)
            val push_up_and_rotation = ExerciseModel(7,"Push Up And Rotation", R.drawable.ic_push_up_and_rotation, false,false)
            val side_plank = ExerciseModel(8,"Side Plank", R.drawable.ic_side_plank, false,false)
            val squat = ExerciseModel(9,"Squat", R.drawable.ic_squat, false,false)
            val step_up_onto_chair = ExerciseModel(10,"Step Up Onto Chair", R.drawable.ic_step_up_onto_chair, false,false)
            val triceps_dip_on_chair = ExerciseModel(11,"Triceps Dip On Chair", R.drawable.ic_triceps_dip_on_chair, false,false)
            val wall_sit = ExerciseModel(12,"Wall Sit", R.drawable.ic_wall_sit, false,false)


            list.add(jumping_jacks)
            list.add(abdominal_crunch)
            list.add(high_knees_running_in_place)
            list.add(lunge)
            list.add(plank)
            list.add(push_up)
            list.add(push_up_and_rotation)
            list.add(side_plank)
            list.add(squat)
            list.add(step_up_onto_chair)
            list.add(triceps_dip_on_chair)
            list.add(wall_sit)

            return list
        }
    }

}