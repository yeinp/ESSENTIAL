package edu.sungshin.essential;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter2 extends RecyclerView.Adapter<NoteAdapter2.ViewHolder> {
    private static final String TAG = "NoteAdapter2";

    //todolist아이템이 들어갈 배열
    ArrayList<Note2> items2 = new ArrayList<>();



    //todo_item.xml을 인플레이션
    @NonNull
    @Override
    public NoteAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView2 = inflater.inflate(R.layout.todo_item, parent, false);


        return new ViewHolder(itemView2);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter2.ViewHolder holder, int position) {
        Note2 item2 = items2.get(position);
        holder.setItem(item2);
        holder.setLayout();
    }

    @Override
    public int getItemCount() {
        return items2.size();
    }


    //ViewHolder의 역할을 하는 클래스
    static class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout layoutTodo;
        TextView TV2;
        Button deleteButton;

        public ViewHolder(View itemView){
            super(itemView);

            layoutTodo = itemView.findViewById(R.id.layoutTodo);
            TV2 = itemView.findViewById(R.id.TV2);
            deleteButton = itemView.findViewById(R.id.deleteButton);

            //버튼 클릭 시 SQLite에서 데이터 삭제
            deleteButton.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    //CheckBox의 String 가져오기
                    String TODO2 = (String) TV2.getText();
                    deleteToDo(TODO2);
                    Toast.makeText(v.getContext(),"삭제되었습니다.",Toast.LENGTH_SHORT).show();
                }


                Context context2;

                private void deleteToDo(String TODO){
                    //테이블을 삭제하는 sql문 delete...
                    String deleteSql2 = "delete from " + NoteDatabase.TABLE_NOTE + " where " + "  TODO = '" + TODO+"'";
                    NoteDatabase database2 = NoteDatabase.getInstance(context2);
                    //삭제하는 sql문 실행
                    database2.execSQL(deleteSql2);
                }
            });


        }
        //EditText에서 입력받은 checkBox의 텍스트를 checkBox의 Text에 넣을 수 있게 하는 메서드
        public void setItem(Note2 item2){
            TV2.setText(item2.getTodo2());
        }

        //아이템들을 담은 LinearLayout을 보여주게하는 메서드
        public void setLayout(){
            layoutTodo.setVisibility(View.VISIBLE);
        }
    }

    //배열에 있는 item들을 가리킴
    public void setItems(ArrayList<Note2> items2){
        this.items2 = items2;
    }

}