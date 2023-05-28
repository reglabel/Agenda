package com.example.agenda.ui.activity;

import static com.example.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agenda.R;
import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {
    private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo aluno";
    private static final String TITULO_APPBAR_EDITA_ALUNO = "Edita aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private final AlunoDAO dao = new AlunoDAO();

    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        inicializacaoDosCampos();
        carregaAluno();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_formulario_aluno_menu_salvar){
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaAluno() {
        Intent dados = getIntent();
        if(dados.hasExtra(CHAVE_ALUNO)) {
            setTitle(TITULO_APPBAR_EDITA_ALUNO);
            aluno = dados.getExtras().getParcelable(CHAVE_ALUNO);
            preencheCampos();
        } else {
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
            aluno = new Aluno();
        }
    }

    private void preencheCampos() {
        campoNome.setText(aluno.getNome());
        campoTelefone.setText(aluno.getTelefone());
        campoEmail.setText(aluno.getEmail());
    }

    private void finalizaFormulario() {
        if(this.validarDados()){
            preencheAluno();
            if(aluno.temIdValido()){
                dao.edita(aluno);
            } else{
                dao.salva(aluno);
            }
            finish();
        }
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private boolean validarDados(){
        boolean isValid = true;

        if(campoNome.getText().toString().length() == 0){
            customToastError("Nome é obrigatório!");
            isValid = false;
        }
        if(campoTelefone.getText().toString().length() < 11){
            customToastError("Telefone é obrigatório e deve conter 11 dígitos (com DD e 9)!");
            isValid =  false;
        }
        if(campoEmail.getText().toString().length() == 0 || campoEmail.getText().toString().indexOf('@') == -1){
            customToastError("Email é obrigatório e deve conter @!");
            isValid = false;
        }
        return isValid;
    }

    private void customToastError(String mensagem){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_layout_id));
        TextView text = layout.findViewById(R.id.custom_toast_text_id);
        text.setText(mensagem);
        Toast toast = Toast.makeText(getApplicationContext(),
                mensagem, Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
    }
}