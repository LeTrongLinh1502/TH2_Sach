package com.example.thuchanh2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.thuchanh2.database.SQLiteHelper;
import com.example.thuchanh2.model.Book;

import java.util.Calendar;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edName, edAuthor, edPublishDate, edPrice;
    private Spinner spPublisher;
    private Button btnUpdate, btnCancel, btnDelete;
    private Book book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initView();
        // Hiện ra thông tin của book cần update
        Intent intent = getIntent();
        book = (Book) intent.getSerializableExtra("book");
        edName.setText(book.getTen());
        edAuthor.setText(book.getTacGia());
        edPublishDate.setText(book.getNgayXB());
        edPrice.setText(book.getGia());
        for(int i=0;i<spPublisher.getCount();i++){
            // Duyệt qua list item trong spinner publisher xem item nào trùng với publisher của book cần sửa thì hiện item ý được chọn trong spinner
            if(book.getNhaXB().equals(spPublisher.getItemAtPosition(i))){
                spPublisher.setSelection(i);
                break;
            }
        }
        btnDelete.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        edPublishDate.setOnClickListener(this);
    }

    private void initView() {
        edName = findViewById(R.id.edName);
        edAuthor = findViewById(R.id.edAuthor);
        edPublishDate = findViewById(R.id.edPublishDate);
        edPrice = findViewById(R.id.edPrice);
        spPublisher = findViewById(R.id.spinnerPublisher);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnCancel = findViewById(R.id.btnCancel);
        btnDelete = findViewById(R.id.btnDelete);
        spPublisher.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner,
                getResources().getStringArray(R.array.loaisach)));
    }

    @Override
    public void onClick(View view) {
        if (view==btnCancel){
            finish();
        }
        if (view==btnUpdate){
            String name = edName.getText().toString();
            String author = edAuthor.getText().toString();
            String publishDate = edPublishDate.getText().toString();
            String publisher = spPublisher.getSelectedItem().toString();
            String price = edPrice.getText().toString();

            Book bookToUpdate = new Book(book.getId(),name,author,publishDate,publisher,price);

            SQLiteHelper sqLiteHelper = new SQLiteHelper(UpdateDeleteActivity.this);
            sqLiteHelper.updateBook(bookToUpdate);

            Intent intentToMainActivity = new Intent(UpdateDeleteActivity.this, MainActivity.class);
            startActivity(intentToMainActivity);
        }
        if (view==btnDelete){
            SQLiteHelper sqLiteHelper = new SQLiteHelper(UpdateDeleteActivity.this);
            sqLiteHelper.deleteBook(book.getId());
            Intent intentToMainActivity = new Intent(UpdateDeleteActivity.this, MainActivity.class);
            startActivity(intentToMainActivity);
        }
        if (view==edPublishDate){
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateDeleteActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    m++;
                    String date = d+"/"+m+"/"+y;
                    edPublishDate.setText(date);
                }
            },year, month, day);
            datePickerDialog.show();
        }
    }
}