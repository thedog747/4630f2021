package com.example.myapplication

import java.util.*
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView;
import kotlinx.android.synthetic.main.item_row_link.view.*
import kotlin.text.toInt
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.folder_view.*
import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService


class RecyclerAdapterLink(parent_context : Context, DB : DataBaseHandlerLinks) : RecyclerView.Adapter<RecyclerAdapterLink.ViewHolder>() {

    val context : Context = parent_context
    var links : MutableList<Link> = DB.getTasks()
    val DB : DataBaseHandlerLinks = DB
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAdapterLink.ViewHolder {
        val layout_inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_row_link, parent, false)
        return ViewHolder(layout_inflater)
    }

    override fun onBindViewHolder(holder: RecyclerAdapterLink.ViewHolder, position: Int) {
        holder.t_id.text =  "ID: ${links[position].id}"
        holder.t_title.text = links[position].title
        holder.t_hyperlink.text = links[position].hyperlink
        holder.t_date.text = links[position].date
    }

    override fun getItemCount(): Int {
        return links.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var t_id : TextView  = itemView.findViewById(R.id.item_id)
        var t_title : TextView  = itemView.findViewById(R.id.item_title)
        var t_hyperlink : TextView  = itemView.findViewById(R.id.item_hyperlink)
        var t_date : TextView = itemView.findViewById(R.id.item_date)


        init {

            itemView.button_delete.setOnClickListener {
                var message = (t_id.text.toString()).trim('I','D',':',' ')
                var temp_task : Link? = links.find{it.id == message.toInt()}
                links.remove(temp_task)
                DB.deleteElement(message)
                notifyDataSetChanged()
            }

            itemView.button_go.setOnClickListener {
                var message = (t_id.text.toString()).trim('I','D',':',' ')
                var temp_task : Link? = links.find{it.id == message.toInt()}
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(temp_task?.hyperlink))
                context.startActivity(intent)
            }

            itemView.button_copy.setOnClickListener{
                var message = (t_id.text.toString()).trim('I','D',':',' ')
                var temp_task : Link? = links.find{it.id == message.toInt()}
                var clip_data = ClipData.newPlainText("text", temp_task?.hyperlink)
                clipboard.setPrimaryClip(clip_data)
                Toast.makeText(context, "Link copied!", Toast.LENGTH_SHORT).show()
            }


        }

    }

}
