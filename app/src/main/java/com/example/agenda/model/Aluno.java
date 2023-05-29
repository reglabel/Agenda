package com.example.agenda.model;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

public class Aluno implements Parcelable {
    private String nome;
    private String telefone;
    private String email;
    private int id = 0;

    public Aluno(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public Aluno() {}

    private Aluno(Parcel p) {
        nome = p.readString();
        telefone = p.readString();
        email = p.readString();
        id = p.readInt();
    }

    public static final Creator<Aluno>
            CREATOR = new Creator<Aluno>() {

        public Aluno createFromParcel(Parcel in) {
            return new Aluno(in);
        }

        public Aluno[] newArray(int size) {
            return new Aluno[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return nome + " - " + telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(nome);
        parcel.writeString(telefone);
        parcel.writeString(email);
        parcel.writeInt(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean temIdValido() {
        return this.id > 0;
    }
}
