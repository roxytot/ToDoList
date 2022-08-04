package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var todoList = mutableListOf(
            Todo("Read a chapter", true),
            Todo("Do a lesson on Duolingo", true),
            Todo("Do a commit", true),
            Todo("Practice on ukulele", false),
            Todo("Go for a walk", true),
            Todo("Cook dinner", true),
            Todo("Exercise", true),
            Todo("Play Overcooked", false),
            Todo("Edit a video", false),
            Todo("Learn something new", true),
            Todo("Write in the journal", false),
        )

        val adapter = TodoAdapter(todoList)
        rvTodos.adapter = adapter
        rvTodos.layoutManager = LinearLayoutManager(this)
        rvTodos.scrollToPosition(findFirstUncheckedItem(todoList))

        btnAddTodo.setOnClickListener {
            val title = etTodo.text.toString()
            val todo = Todo(title, false)
            todoList.add(todo)
            adapter.notifyItemChanged(todoList.size - 1)
        }

    }

    fun findFirstUncheckedItem(todoList: List<Todo>): Int{
        for ((pos, todo) in todoList.withIndex()) {
            if (!todo.isChecked) {
                return pos
            }
        }
        return 0
    }
}