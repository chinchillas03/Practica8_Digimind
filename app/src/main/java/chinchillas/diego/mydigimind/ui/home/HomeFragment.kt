package chinchillas.diego.mydigimind.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import chinchillas.diego.mydigimind.R
import chinchillas.diego.mydigimind.databinding.FragmentHomeBinding
import chinchillas.diego.mydigimind.ui.Task

class HomeFragment : Fragment() {

    private var adaptador: adaptadorTareas? = null

    private var _binding: FragmentHomeBinding? = null

    companion object{
        var tasks = ArrayList<Task>()
        var firts = true
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        if (firts){
            fillTasks()
            firts = false
        }

        adaptador = adaptadorTareas(root.context, tasks)

        val gridView : GridView = root.findViewById(R.id.reminders)

        gridView.adapter = adaptador

        return root
    }

    fun fillTasks(){
        tasks.add(Task("Practice 1", arrayListOf("Tuesday"), "17:30"))
        tasks.add(Task("Practice 2", arrayListOf("Monday", "Sunday"), "17:40"))
        tasks.add(Task("Practice 3", arrayListOf("Wednesday"), "14:00"))
        tasks.add(Task("Practice 4", arrayListOf("Saturday"), "11:00"))
        tasks.add(Task("Practice 5", arrayListOf("Friday"), "13:00"))
        tasks.add(Task("Practice 6", arrayListOf("Thursday"), "10:40"))
        tasks.add(Task("Practice 7", arrayListOf("Monday"), "12:00"))
    }

    private class adaptadorTareas :BaseAdapter{
        var tasks = ArrayList<Task>()
        var contexto : Context? = null
        constructor(context: Context, tasks: ArrayList<Task>){
            this.contexto = contexto
            this.tasks = tasks
        }

        override fun getCount(): Int {
            return tasks.size
        }

        override fun getItem(position: Int): Any {
            return tasks[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var task = tasks[position]
            var inflater = LayoutInflater.from(contexto)
            var vista = inflater.inflate(R.layout.task_view, null)

            var title : TextView = vista.findViewById(R.id.tv_title)
            var time : TextView = vista.findViewById(R.id.tv_time)
            var days : TextView = vista.findViewById(R.id.tv_days)

            time.setText(task.time)
            days.setText(task.days.toString())
            title.setText(task.title)

            return vista
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}