package model.vo;


import java.util.Date;

public class UsuarioVO {
	
		private long id;
		private String nome;
		private String senha;
		private Date datanasc;
		private String user;
		private String dicaSenha;
		private char sexo;
		
		public UsuarioVO() {}

		public UsuarioVO(String nome, String senha, Date datanasc, String user, String dicaSenha, char sexo) {
			super();
			this.nome = nome;
			this.senha = senha;
			this.datanasc = datanasc;
			this.user = user;
			this.dicaSenha = dicaSenha;
			this.sexo = sexo;
		}
		
		public UsuarioVO(long id, String nome, String senha, Date datanasc, String user, String dicaSenha, char sexo) {
			super();
			this.id = id;
			this.nome = nome;
			this.senha = senha;
			this.datanasc = datanasc;
			this.user = user;
			this.dicaSenha = dicaSenha;
			this.sexo = sexo;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getSenha() {
			return senha;
		}

		public void setSenha(String senha) {
			this.senha = senha;
		}

		public Date getDatanasc() {
			return datanasc;
		}

		public void setDatanasc(Date datanasc) {
			this.datanasc = datanasc;
		}

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

		public String getDicaSenha() {
			return dicaSenha;
		}

		public void setDicaSenha(String dicaSenha) {
			this.dicaSenha = dicaSenha;
		}

		public char getSexo() {
			return sexo;
		}

		public void setSexo(char sexo) {
			this.sexo = sexo;
		}
 
		 
		

		
	}