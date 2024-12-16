Вариант A: Реализация операций добавления, изменения и удаления значений из ListView
1.Создание разметки (XML):
	Создадим файл activity_main.xml, в котором разместим ListView, EditText, и две кнопки для добавления и удаления элементов:
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <!-- EditText для ввода данных -->
    <EditText
        android:id="@+id/editText"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Enter item"
        android:layout_alignParentTop="true" />

    <!-- Кнопка для добавления элементов -->
    <Button
        android:id="@+id/addButton"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Add"
        android:layout_below="@id/editText"
        android:layout_alignParentLeft="true"
        android:layout_marginTop="10dp" />

    <!-- Кнопка для удаления элементов -->
    <Button
        android:id="@+id/deleteButton"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Delete"
        android:layout_below="@id/addButton"
        android:layout_alignParentLeft="true"
        android:layout_marginTop="10dp" />

    <!-- ListView для отображения элементов -->
    <ListView
        android:id="@+id/listView"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_below="@id/deleteButton"
        android:layout_alignParentBottom="true" />
</RelativeLayout>

2.Основной код (Java):
В MainActivity.java реализуем операции добавления, изменения и удаления элементов.
package com.example.listviewapp;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private ListView listView;
    private Button addButton, deleteButton;

    private ArrayList<String> itemList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        listView = findViewById(R.id.listView);
        addButton = findViewById(R.id.addButton);
        deleteButton = findViewById(R.id.deleteButton);

        itemList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);
        listView.setAdapter(adapter);

        // Добавление элемента в список
        addButton.setOnClickListener(v -> addItem());

        // Удаление выбранного элемента
        deleteButton.setOnClickListener(v -> removeItem());

        // Изменение элемента по нажатию Enter
        editText.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                editItem();
                return true;
            }
            return false;
        });

        // Обработчик кликов по элементам ListView для их редактирования
        listView.setOnItemClickListener((parent, view, position, id) -> editText.setText(itemList.get(position)));
    }

    // Добавление элемента в список
    private void addItem() {
        String text = editText.getText().toString().trim();
        if (!text.isEmpty()) {
            itemList.add(text);
            adapter.notifyDataSetChanged();
            editText.setText("");  // Очистить поле после добавления
        } else {
            Toast.makeText(this, "Please enter text", Toast.LENGTH_SHORT).show();
        }
    }

    // Удаление выбранного элемента
    private void removeItem() {
        String text = editText.getText().toString().trim();
        if (!text.isEmpty()) {
            if (itemList.contains(text)) {
                itemList.remove(text);
                adapter.notifyDataSetChanged();
                editText.setText("");  // Очистить поле после удаления
            } else {
                Toast.makeText(this, "Item not found", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please enter text", Toast.LENGTH_SHORT).show();
        }
    }

    // Изменение элемента в списке
    private void editItem() {
        String text = editText.getText().toString().trim();
        if (!text.isEmpty()) {
            int position = listView.getCheckedItemPosition();
            if (position != AdapterView.INVALID_POSITION) {
                itemList.set(position, text);
                adapter.notifyDataSetChanged();
                editText.setText("");  // Очистить поле после изменения
            } else {
                Toast.makeText(this, "Select an item to edit", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please enter text", Toast.LENGTH_SHORT).show();
        }
    }
}

Вариант Б: Реализация операций добавления, изменения и удаления значений из Spinner
1.Создание разметки (XML):
Создадим файл activity_main.xml, в котором разместим Spinner, EditText, и две кнопки для добавления и удаления элементов:
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <!-- EditText для ввода данных -->
    <EditText
        android:id="@+id/editText"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Enter item"
        android:layout_alignParentTop="true" />

    <!-- Кнопка для добавления элементов -->
    <Button
        android:id="@+id/addButton"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Add"
        android:layout_below="@id/editText"
        android:layout_alignParentLeft="true"
        android:layout_marginTop="10dp" />

    <!-- Кнопка для удаления элементов -->
    <Button
        android:id="@+id/deleteButton"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Delete"
        android:layout_below="@id/addButton"
        android:layout_alignParentLeft="true"
        android:layout_marginTop="10dp" />

    <!-- Spinner для отображения элементов -->
    <Spinner
        android:id="@+id/spinner"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@id/deleteButton" />
</RelativeLayout>

2.Основной код (Java):
В MainActivity.java реализуем операции добавления, изменения и удаления элементов для Spinner.
package com.example.spinnerapp;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Spinner spinner;
    private Button addButton, deleteButton;

    private ArrayList<String> itemList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        spinner = findViewById(R.id.spinner);
        addButton = findViewById(R.id.addButton);
        deleteButton = findViewById(R.id.deleteButton);

        itemList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, itemList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Добавление элемента в список
        addButton.setOnClickListener(v -> addItem());

        // Удаление выбранного элемента
        deleteButton.setOnClickListener(v -> removeItem());

        // Изменение элемента по нажатию Enter
        editText.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                editItem();
                return true;
            }
            return false;
        });
    }

    // Добавление элемента в список
    private void addItem() {
        String text = editText.getText().toString().trim();
        if (!text.isEmpty()) {
            itemList.add(text);
            adapter.notifyDataSetChanged();
            editText.setText("");  // Очистить поле после добавления
        } else {
            Toast.makeText(this, "Please enter text", Toast.LENGTH_SHORT).show();
        }
    }

    // Удаление выбранного элемента
    private void removeItem() {
        String text = editText.getText().toString().trim();
        if (!text.isEmpty()) {
            if (itemList.contains(text)) {
                itemList.remove(text);
                adapter.notifyDataSetChanged();
                editText.setText("");  // Очистить поле после удаления
            } else {
                Toast.makeText(this, "Item not found", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please enter text", Toast.LENGTH_SHORT).show();
        }
    }

    // Изменение элемента в списке
    private void editItem() {
        String text = editText.getText().toString().trim();
        if (!text.isEmpty()) {
            int position = spinner.getSelectedItemPosition();
            if (position != AdapterView.INVALID_POSITION) {
                itemList.set(position, text);
                adapter.notifyDataSetChanged();
                editText.setText("");  // Очистить поле после изменения
            } else {
                Toast.makeText(this, "Select an item to edit", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please enter text", Toast.LENGTH_SHORT).show();
        }
    }
}


