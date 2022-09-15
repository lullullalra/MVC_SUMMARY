package gntp.lesson.mvc.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import gntp.lesson.mvc.dao.BookDAO;
import gntp.lesson.mvc.service.BookService;
import gntp.lesson.mvc.util.ConnectionManagerV2;
import gntp.lesson.mvc.vo.BookVO;

public class Controller extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//로직처리
		String url = req.getRequestURI();
		String[] temp = url.split("/");
		String command = temp[temp.length-1];
		System.out.println(command);
		BookDAO dao = new BookDAO();
		if(command.equals("viewInput.do")) {
			url = "./summary/input.jsp";			
		} else if(command.equals("output.do")) {
			//클라이언트 데이터 수집
//			String bookTitle = req.getParameter("bookTitle");
//			String bookAuthor = req.getParameter("bookAuthor");
//			String bookImage = req.getParameter("bookImage");
//			int bookPrice = Integer.parseInt(req.getParameter("bookPrice"));
//			//vo에 연결
//			BookVO vo = new BookVO(0, bookTitle, bookAuthor, bookPrice, bookImage, null);
			
			ServletRequestContext src = new ServletRequestContext(req);		//문자열말고 file도 input으로 들어오면 ServletRequestContext로 변환해줘야 file을 받을 수 있다.
			//로직 처리 요청
			BookService bs = new BookService();
			BookVO book = null;
			try {
				book = bs.registBook(src);						//받은 input값을 ServletRequestContext로 변환하고 service의 매개변수로 넣는다.
				
				if(book!=null) {
					//request객체에 저장
					req.setAttribute("book", book);
				} else {
					System.out.println("null");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			url = "./summary/output.jsp";	
		} else if(command.equals("download.do")) {
			String fileName = req.getParameter("fileName");
			BookService bs = new BookService();
			bs.download(fileName, resp);
		} else if(command.equals("read.do")) {
			ArrayList<BookVO> list;
			try {
				list = dao.selectAll();
				req.setAttribute("list", list);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			url = "./summary/list.jsp";
		} else if(command.equals("list.do")) {
			url = "viewInput.do";
		}
		
		if(!command.equals("download.do")) {						//다운로드 누를때 forward하면 안되므로 if로 처리
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, resp);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("destroy");
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("init");
	}

}
