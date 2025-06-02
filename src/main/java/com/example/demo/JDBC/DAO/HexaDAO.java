package com.example.demo.JDBC.DAO;

import com.example.demo.JDBC.entity.Hexa;
import com.example.demo.JDBC.entity.Lines;
import com.example.demo.JDBC.entity.UsersHexa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class HexaDAO {
	private final DataSource dataSource;

	@Autowired
	public HexaDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Optional<Hexa> getHexagram(String hexaId, String langCode) throws SQLException {
		String sql = "SELECT * FROM hexagram_translations WHERE hexa_id = ? AND lang_code = ?";
		try (Connection connection = dataSource.getConnection();
			 PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, hexaId);
			stmt.setString(2, langCode);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					Hexa ht = new Hexa();
					ht.setHexaId(rs.getString("hexa_id"));
					ht.setLangCode(rs.getString("lang_code"));
					ht.setHexaName(rs.getString("hexa_name"));
					ht.setHexaContent(rs.getString("hexa_content"));
					return Optional.of(ht);
				}
			}
		}
		return Optional.empty();
	}

	public String getHexaName(String hexaId, String langCode) throws SQLException {
		String hexaName = "";
		String sql = "SELECT hexa_name FROM hexagram_translations WHERE hexa_id = ? AND lang_code = ?";
		try (Connection connection = dataSource.getConnection();
			 PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, hexaId);
			stmt.setString(2, langCode);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					hexaName = rs.getString("hexa_name");

				}
			}
		}
		return hexaName;
	}
	
	public List<Lines> getLine(String hexaId, String langCode) throws SQLException {
		String sql = """
	            SELECT t.line_id, t.lang_code, t.line_content, t.omen, t.explanation
	            FROM hexagram_line l
	            JOIN hexagram_line_translations t ON l.line_id = t.line_id
	            WHERE l.hexa_id = ? AND t.lang_code = ?
	        """;
		 List<Lines> results = new ArrayList<>();
	        try (Connection connection = dataSource.getConnection();
				 PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setString(1, hexaId);
	            stmt.setString(2, langCode);
	            try (ResultSet rs = stmt.executeQuery()) {
	                while (rs.next()) {
	                    Lines line = new Lines();
	                    line.setLineId(rs.getInt("line_id"));
	                    line.setLangCode(rs.getString("lang_code"));
	                    line.setLineContent(rs.getString("line_content"));
	                    line.setOmen(rs.getString("omen"));
	                    line.setExplanation(rs.getString("explanation"));
	                    results.add(line);
	                }
	            }
	        }
	        return results;
	}

	public void saveHexa(int userId, byte[] imageBytes, String question) throws SQLException {
		String sql = "INSERT INTO users_hexa (user_id, result, question) VALUES (?, ?, ?)";
		Connection connection = dataSource.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, userId);
		stmt.setBytes(2, imageBytes);
		stmt.setString(3, question);
		stmt.executeUpdate();
	}

	public Optional<UsersHexa> getLatestUserHexa(int userId) throws SQLException {
		String sql = "SELECT result, question FROM users_hexa WHERE user_id = ? LIMIT 1";
		try (Connection connection = dataSource.getConnection();
			 PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, userId);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					byte[] imageBytes = rs.getBytes("result");
					String question = rs.getString("question");
					return Optional.of(new UsersHexa(imageBytes, question));
				}
			}
		}
		return Optional.empty();
	}

	public void saveQuestion(int userId, String question) throws SQLException {
		String sql = "INSERT INTO users_hexa (user_id, question) VALUES (?, ?)";
		Connection connection = dataSource.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, userId);
		stmt.setString(2, question);
		stmt.executeUpdate();
	}



}
