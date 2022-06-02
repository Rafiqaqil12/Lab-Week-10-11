package demo.ftmk.utem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

/**
 * Servlet implementation class DateServlet
 */
public class DateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, 
	 * HttpServletResponse response)
	 */
		protected void doGet(HttpServletRequest request,
				HttpServletResponse response)
				throws ServletException, IOException {
				// Get link parameter
				int paramValue =
				Integer.parseInt(request.getParameter("param"));
				// Get writer
				PrintWriter writer = response.getWriter();
				// Get current date
				LocalDate now = LocalDate.now();
				
				if(paramValue == -1){
					
					//Get yesterday's date
					 LocalDate previousDay = now.minusDays(1);
					// Display yesterdays's date
					writer.println("Yesterday is " + previousDay);
				}else if (paramValue == 0) {
					
					//Display Current date
					writer.println("Today is " + now.toString());

				}else if(paramValue == 1) {
					
					//Get tomorrow's date
					LocalDate tomorrowDays = now.plusDays(1);
					//Display tomorrow's date
					writer.println("Tomorrow is " + tomorrowDays);

				}
				
				}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request,
	 *  HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
