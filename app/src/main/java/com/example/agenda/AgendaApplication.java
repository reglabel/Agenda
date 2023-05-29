package com.example.agenda;

import android.app.Application;

import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno;

public class AgendaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunosDeTeste();
    }

    private static void criaAlunosDeTeste() {
        AlunoDAO dao = new AlunoDAO();
        dao.salva(new Aluno("Alex", "86998441481", "alex@aluno.com"));
        dao.salva(new Aluno("Fran", "86998466681", "fran@aluno.com"));
    }
}
