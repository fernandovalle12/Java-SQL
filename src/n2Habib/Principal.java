package n2Habib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Principal {

	public static void inserirPessoa(String conURL, Pessoa p) {

	    String insertPessoaString = "INSERT INTO Pessoa (id, nome, idade) VALUES (?, ?, ?)";

	    try (
	    		Connection con = DriverManager.getConnection(conURL);
	    		PreparedStatement stmt = con.prepareStatement(insertPessoaString);
	    	) {

	    	stmt.setInt(1, p.getId());
	    	stmt.setString(2, p.getNome());
	    	stmt.setInt(3, p.getIdade());

	    	stmt.executeUpdate();

	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }

	}

	public static void updatePessoa(String conURL, Pessoa p) {

        String updatePessoaString = "UPDATE Pessoa SET id = ?, nome = ?, idade = ? WHERE id = ?";

	    try (
	    		Connection con = DriverManager.getConnection(conURL);
	    		PreparedStatement stmt = con.prepareStatement(updatePessoaString);
	    	) {

	    	stmt.setInt(1, p.getId());
	    	stmt.setString(2, p.getNome());
	    	stmt.setInt(3, p.getIdade());
	    	stmt.setInt(4, p.getId());

	    	stmt.executeUpdate();

	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }

	}

	public static void deletePessoa(String conURL, int id) {

        String deleteString = "DELETE Pessoa WHERE id = ?";

        try (
        		Connection con = DriverManager.getConnection(conURL); 
        		PreparedStatement stmt = con.prepareStatement(deleteString);
        	) {

        	stmt.setInt(1, id);

        	stmt.executeUpdate();

        } catch (SQLException e) {
        	e.printStackTrace();
        }

	}
	
	public static void listPessoa(String conURL) {

		int count = 0;

		try (
        		Connection con = DriverManager.getConnection(conURL); 
        		Statement stmt = con.createStatement();
        	) {

            String SQL = "SELECT * FROM Pessoa";
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {

            	Pessoa p = new Pessoa();
            	p.setId(rs.getInt("id"));
            	p.setNome(rs.getString("nome"));
            	p.setIdade(rs.getInt("idade"));

            	System.out.println(p);
            	count++;
            }

            if (count == 0) System.out.println("Nenhuma pessoa encontrada!");

        }

        catch (SQLException e) {
            e.printStackTrace();
        }

	}
	
	public static void inserirCasa(String conURL, Casa c) {

	    String insertCasaString = "INSERT INTO Casa (id, idPessoa, qtQuartos) VALUES (?, ?, ?, ?)";

	    try (
	    		Connection con = DriverManager.getConnection(conURL);
	    		PreparedStatement stmt = con.prepareStatement(insertCasaString);
	    	) {

	    	stmt.setInt(1, c.getId());
	    	stmt.setInt(2, c.getIdPessoa());
	    	stmt.setInt(4, c.getqtQuartos());

	    	stmt.executeUpdate();

	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }

	}

	public static void updateCasa(String conURL, Casa c) {

        String updateCasaString = "UPDATE Casa SET id = ?, idPessoa = ?, qtQuartos = ? WHERE id = ?";

	    try (
	    		Connection con = DriverManager.getConnection(conURL);
	    		PreparedStatement stmt = con.prepareStatement(updateCasaString);
	    	) {

	    	stmt.setInt(1, c.getId());
	    	stmt.setInt(2, c.getIdPessoa());
	    	stmt.setInt(3, c.getqtQuartos());
	    	stmt.setInt(4, c.getId());
	    	
	    	stmt.executeUpdate();

	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }

	}

	public static void deleteCasa(String conURL, int id) {

        String deleteString = "DELETE Casa WHERE id = ?";

        try (
        		Connection con = DriverManager.getConnection(conURL);
        		PreparedStatement stmt = con.prepareStatement(deleteString);
        	) {

        	stmt.setInt(1, id);

        	stmt.executeUpdate();

        } catch (SQLException e) {
        	e.printStackTrace();
        }

	}

	public static void listCasa(String conURL) {

		int count = 0;

		try (
        		Connection con = DriverManager.getConnection(conURL); 
        		Statement stmt = con.createStatement();
        	) {

            String SQL = "SELECT c.*, p.nome FROM Casa c INNER JOIN Pessoa p ON p.id = c.idPessoa";
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {

            	Casa c = new Casa();
            	c.setId(rs.getInt("id"));
            	c.setIdPessoa(rs.getInt("idPessoa"));
            	c.setqtQuartos(rs.getInt("qtQuartos"));

            	System.out.println(c);
            	count++;
            }

            if (count == 0) System.out.println("Nenhuma casa localizada");

        }

        catch (SQLException e) {
            e.printStackTrace();
        }

	}

	public static void listCasaPessoa(String conURL, int idPessoa) {

		int count = 0;
        String SQL = "SELECT c.*, p.nome FROM Casa c INNER JOIN Pessoa p ON p.Id = c.idPessoa WHERE c.idPessoa = ?";
		
		try (
        		Connection con = DriverManager.getConnection(conURL); 
        		PreparedStatement stmt = con.prepareStatement(SQL);
        	) {

        	stmt.setInt(1, idPessoa);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

            	Casa c = new Casa();
            	c.setId(rs.getInt("id"));
            	c.setIdPessoa(rs.getInt("idPessoa"));
            	c.setqtQuartos(rs.getInt("qtQuartos"));

            	System.out.println(c);
            	count++;
            }

            if (count == 0) System.out.println("Nenhuma casa localizada");

        }

        catch (SQLException e) {
            e.printStackTrace();
        }

	}
	
	public static void main(String[] args) {

		String connectionUrl = "jdbc:sqlserver://localhost;databaseName=Pessoa;user=aramys;password=111111";
        


    	Pessoa p = new Pessoa(1, "Aramys", 23);
    	Casa c = new Casa(1, p.getId(), 1);
   
    	listPessoa(connectionUrl);
    	listCasa(connectionUrl);

    	inserirPessoa(connectionUrl, p);
    	listPessoa(connectionUrl);
    	updatePessoa(connectionUrl, p);
    	listPessoa(connectionUrl);
    	inserirCasa(connectionUrl, c);
    	listCasa(connectionUrl);
    	listCasaPessoa(connectionUrl, p.getId());
    	deletePessoa(connectionUrl, p.getId());
    	listPessoa(connectionUrl);
    	deleteCasa(connectionUrl, c.getId());
    	listCasa(connectionUrl);
	}

}