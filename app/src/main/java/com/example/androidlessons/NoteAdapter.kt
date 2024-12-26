import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlessons.R
import com.example.androidlessons.models.Note

class NoteAdapter(
    val notes: MutableList<Note>,
    private val onDelete: (Note) -> Unit,
    private val onEdit: (Note) -> Unit
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val checkbox: CheckBox = itemView.findViewById(R.id.note_checkbox)
        private val noteText: TextView = itemView.findViewById(R.id.note_text)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.delete_button)

        fun bind(note: Note) {
            noteText.text = note.text
            checkbox.isChecked = note.isCompleted

            checkbox.setOnCheckedChangeListener { _, isChecked ->
                note.isCompleted = isChecked
            }

            deleteButton.setOnClickListener {
                onDelete(note)
            }

            itemView.setOnClickListener {
                onEdit(note)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount(): Int = notes.size
}