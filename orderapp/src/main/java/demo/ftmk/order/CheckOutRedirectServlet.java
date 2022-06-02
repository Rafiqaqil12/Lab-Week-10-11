package demo.ftmk.order;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Servlet implementation class CheckOutRedirectServlet
 */
@WebServlet("/demo/checkOutRedirectServlet")
public class CheckOutRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOutRedirectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//get OrderedProduct from session
				HttpSession session = request.getSession();
				@SuppressWarnings("unchecked")
				ArrayList<OrderedProduct> orderedProducts = (ArrayList<OrderedProduct>) session.getAttribute("orderedProducts");
				
				// Forward servlet to zeroOrder.html if orderedProducts does not exist
				if(orderedProducts == null) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/demo/zeroOrder.html");
					dispatcher.forward(request, response);
					return;
				}
				
				// Calculate total quantity and total order
				// Invoke the appropriate method from OrderDataManager
				OrderDataManager odm = new OrderDataManager();
				Order order = odm.processOrder(orderedProducts);
				
				//Display details of order
				PrintWriter writer = response.getWriter();
				writer.print("<html><h3>Order Number: </h3><br><br>");
				writer.print("<h3>List of Ordered Products</h3>");
				writer.print("<table><tr><th>Product</th><th>Quantity</th><th>Price Per Unit (RM)</th></tr>");
				for(OrderedProduct orderProduct : order.getOrderedProducts()) {
					writer.print("<tr><td>" + orderProduct.getProduct().getName() + "</td><td>" + 
					orderProduct.getQuantity()+ "</td><td>" + 
					String.format("%.2f", orderProduct.getProduct().getPrice())
					+ "</td></tr>");
				}
				writer.print("</table><br><br><br>Total Quantity: " + 
				String.valueOf(order.getTotalQuantity()) + "<br>");
				writer.print("Service total: RM " + 
				String.format("%.2f", order.getServiceTax()) + "<br>");
				writer.print("Total Amount: RM " + 
				String.format("%.2f", order.getTotalAmount()) + "</b><br>");
				writer.print("<br><br>This order is made on " + order.getOrderDate() + "<br><br>");
				writer.print("<a href='../'>Home</a>");
				
				//remove attribute from session
				session.removeAttribute("orderList");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
