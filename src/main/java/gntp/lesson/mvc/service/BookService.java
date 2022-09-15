package gntp.lesson.mvc.service;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import gntp.lesson.mvc.dao.BookDAO;
import gntp.lesson.mvc.vo.BookVO;

public class BookService {
	//DB에서 해당 도서정보를 가져와서 출력(상세조회/전체조회)할 경우 이미지와 다운로드할 파일이 이미 존재해야 하기
	//때문에 업로드가 완료된 다음 DB에 저장하는 로직을 가진다.
	public BookVO registBook(ServletRequestContext req) throws Exception {
		BookVO book = null;
		BookDAO dao = new BookDAO();
		String path = "C:\\dev\\eclipse\\gntp\\images";					//역슬래쉬를 \\ 2번 사용하는 것은 ""안에서는 2번써야 하나로 인식, \는 컴퓨터 주소
		DiskFileItemFactory factory = new DiskFileItemFactory();		//업로드할 파일을 받을 장소와 기타설정
		factory.setRepository(new File(path));							//Repository 저장소 : 저장위치
		factory.setSizeThreshold(1024*1024);							//Threshold 한계점 : 업로드 제한속도 설정
		//FileItem을 통해서 BookVO 필드값 구한 후 객체생성
		ServletFileUpload upload = new ServletFileUpload(factory);		//factory를 넣어줘야 한다. ServletFileUpload는 업로드장소와 설정인 factory를 생성자로 받는다.
		List<FileItem> list = upload.parseRequest(req);					//req(요청)을 upload가 받아서 받은 입력만큼 FileItem으로 list를 만든다.
		book = new BookVO();
		for(FileItem item : list) {
			if(item.isFormField()) {									//FileItem의 isFormField()메소드는 문자인지 파일인지 구별하는 메소드, 문자열이면 true, 파일이면 false
				String fieldName = item.getFieldName();					//FileItem의 getFieldName()메소드로 input안의 name을 받는다.
				if(fieldName.equals("bookTitle")) {
					book.setBookTitle(item.getString("UTF-8"));			//FileItem에서 문자열을 받을땐 getString, 여기서 한글도 받을려면 매개변수로 "UTF-8"로 인코딩해주기
				} else if(fieldName.equals("bookAuthor")) {
					book.setBookAuthor(item.getString("UTF-8"));
				} else if(fieldName.equals("bookPrice")) {
					book.setBookPrice(Integer.parseInt(item.getString("UTF-8")));
				}
			} else {
				String name = item.getFieldName();					//FileItem의 메소드getFieldName() : input안의 name
				if(name.equals("bookImage")) {
					String temp = item.getName();					//FileItem의 메소드getName() : 실재파일 이름(주소포함)
					System.out.println(temp);
					int idx = temp.lastIndexOf("\\");				//역슬래쉬\가 마지막에 있는 번호 획득
					String fileName = temp.substring(idx+1);		//파일이름만 저장하기 위해서 마지막\ 다음위치부터 끝까지 얻는다.
					System.out.println(fileName);
					book.setBookImage(fileName);
					//업로드 실행
					File filePath = new File(path+"\\"+fileName);
					item.write(filePath);						//filePath경로로 FileItem클래스 형식으로 받은 file을 write(쓰기), 즉 경로에 파일을 저장
				
				} else if(name.equals("bookAttache")) {
					String temp = item.getName();					//FileItem의 메소드getName() : 실제파일 이름
					System.out.println(temp);
					if(temp==null|| temp.equals("")) {
						
					}
					int idx = temp.lastIndexOf("\\");
					String fileName = temp.substring(idx+1);
					System.out.println(fileName);
					book.setBookAttachment(fileName);
					
					path = "C:\\dev\\eclipse\\gntp\\downloads";
					File filePath = new File(path + "\\" + fileName);
					item.write(filePath);
				
				} 	
			}
		}
		boolean flag = dao.insertBook(book);
		
		return book;
	}

	public void download(String fileName, HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub
		/////////////////////////////한글처리///////////////////////////////
		File download = new File("C:\\dev\\eclipse\\gntp\\downloads\\"+fileName);
		fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		/////////////////////////////한글처리///////////////////////////////
		resp.setContentType("text/html; charset=UTF-8");
		resp.setHeader("Cache-Control", "no-cache");
		resp.addHeader("Content-Disposition", "attatchment;filename="+fileName);
		System.out.println(fileName);
		
		FileInputStream fis = new FileInputStream(download);
		OutputStream os = resp.getOutputStream();
		byte[] buffer = new byte[256];
		int len = 0;
		while((len=fis.read(buffer))!=-1) {
			os.write(buffer, 0, len);
		}
		os.close();
		fis.close();
	}
}