package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView;
import kotlinx.android.synthetic.main.item_row_task.view.*
import kotlin.text.toInt

class RecyclerAdapterTask(parent_context : Context, DB : DataBaseHandler) : RecyclerView.Adapter<RecyclerAdapterTask.ViewHolder>() {

    val context : Context = parent_context
    var tasks : MutableList<Task> = DB.getTasks()
    val DB : DataBaseHandler = DB

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAdapterTask.ViewHolder {
        val layout_inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_row_task, parent, false)
        return ViewHolder(layout_inflater)
    }

    override fun onBindViewHolder(holder: RecyclerAdapterTask.ViewHolder, position: Int) {

        var tempComplex = String()
        if(tasks[position].complexity == 0)
            tempComplex = "Easy"
        if(tasks[position].complexity == 1)
            tempComplex = "Medium"
        if(tasks[position].complexity == 2)
            tempComplex = "Hard"

        var tempComplete = String()
        if(tasks[position].completion == 0)
            tempComplete = "Incomplete"
        if(tasks[position].completion == 1)
            tempComplete = "Complete"

        holder.t_date.text = tasks[position].date
        holder.t_difficulty.text = tempComplex
        holder.t_completion.text = tempComplete
        holder.t_description.text = tasks[position].details
        holder.t_id.text =  "ID: ${tasks[position].id}"
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var t_date : TextView = itemView.findViewById(R.id.item_date)
        var t_difficulty : TextView  = itemView.findViewById(R.id.item_difficulty)
        var t_completion : TextView  = itemView.findViewById(R.id.item_completion)
        var t_description : TextView  = itemView.findViewById(R.id.item_hyperlink)
        var t_id : TextView  = itemView.findViewById(R.id.item_id)

        init {
            itemView.button_go.setOnClickListener {
                var message = (t_id.text.toString()).trim('I','D',':',' ')
                var temp_task : Task? = tasks.find{it.id == message.toInt()}
                DB.updateCompletion(message, temp_task?.completion)
                if (temp_task?.completion == 1)
                    temp_task?.completion = 0
                else
                    temp_task?.completion = 1
                //DB.deleteElement(message)
                notifyDataSetChanged()
            }
            itemView.button_delete.setOnClickListener {
                var message = (t_id.text.toString()).trim('I','D',':',' ')
                var temp_task : Task? = tasks.find{it.id == message.toInt()}
                tasks.remove(temp_task)
                DB.deleteElement(message)
                notifyDataSetChanged()
            }
        }

    }

}