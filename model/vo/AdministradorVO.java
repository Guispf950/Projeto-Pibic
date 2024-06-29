package model.vo;


import java.util.Date;

public class AdministradorVO {
	

		private String nome;
		private String sobrenome;
		private String user;
		private String email;
		private String senha;
		private Date datanasc;
		private String nomeCachorro;
		private String comidaFav;
		private String dicaSenha;
		
		public AdministradorVO() {}
 
		public AdministradorVO(String nome, String sobrenome, String email, String user,String senha, Date datanasc, String nomeCachorro,
				String comidaFav) {
			super();
			this.nome = nome;
			this.sobrenome = sobrenome;
			this.user = user;
			this.email = email;
			this.senha = senha;
			this.datanasc = datanasc;
			this.nomeCachorro = nomeCachorro;
			this.comidaFav = comidaFav;
		}

		public AdministradorVO(String nomeUser, String senha, String dicaSenha) {
			 this.user = nomeUser;
			 this.senha = senha;
			 this.dicaSenha = dicaSenha;
		}

		public String getDicaSenha() {
			return dicaSenha;
		}

		public void setDicaSenha(String dicaSenha) {
			this.dicaSenha = dicaSenha;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

		public String getSenha() {
			return senha;
		}

		public void setSenha(String senha) {
			this.senha = senha;
		}

		public String getSobrenome() {
			return sobrenome;
		}

		public void setSobrenome(String senha) {
			this.sobrenome = senha;
		}

		public Date getDatanasc() {
			return datanasc;
		}

		public void setDatanasc(Date datanasc) {
			this.datanasc = datanasc;
		}

		public String getNomeCachorro() {
			return nomeCachorro;
		}

		public void setNomeCachorro(String nomeCachorro) {
			this.nomeCachorro = nomeCachorro;
		}

		public String getComidaFav() {
			return comidaFav;
		}

		public void setComidaFav(String comidaFav) {
			this.comidaFav = comidaFav;
		}
		
		

		
	}