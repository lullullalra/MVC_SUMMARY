package gntp.lesson.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import gntp.lesson.mvc.util.ConnectionManagerV2;
import gntp.lesson.mvc.vo.BookVO;

public class BookDAO {
	public boolean insertBook(BookVO book) throws SQLException {
		boolean flag = false;
		//테이블에 연결하여 삽입
		String sql = "insert into library (book_title, book_author, book_price, book_image, book_attachment) values(?,?,?,?,?)";
		Connection con = ConnectionManagerV2.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, book.getBookTitle());
		pstmt.setString(2, book.getBookAuthor());
		pstmt.setInt(3, book.getBookPrice());
		pstmt.setString(4, book.getBookImage());
		pstmt.setNString(5, book.getBookAttachment());
		int affectedCount = pstmt.executeUpdate();
		if(affectedCount>0) {
			flag = true;
		}
		ConnectionManagerV2.closeConnection(null, pstmt, con);
		return flag;
	}

	public ArrayList<BookVO> selectAll() throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<BookVO> list = new ArrayList<BookVO>();
		String sql = "select * from library";
		Connection con = ConnectionManagerV2.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		BookVO book = null;
		while(rs.next()) {
			book = new BookVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6));
			list.add(book);
		}
		ConnectionManagerV2.closeConnection(rs, pstmt, con);
		return list;
	}
	
	
}
