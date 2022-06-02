package demo.ftmk.order;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import demo.ftmk.product.Product;

/**
 * Servlet implementation class OrderRedirectServlet
 */
@WebServlet("/demo/orderRedirectServlet")
public class OrderRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderRedirectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Get orderedProduct from session and cast to List of OrderedProduct
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		ArrayList<OrderedProduct> orderedProducts = 
		(ArrayList<OrderedProduct>) session.getAttribute("orderedProducts");
		
		//Validate list - instantiate new list if the list is null
		if(orderedProducts == null)
			orderedProducts = new ArrayList<OrderedProduct>();
		
		// Get data from a web form
		String getSelected = request.getParameter("product");
		String getQuantity = request.getParameter("quantity");
		
		int code = Integer.parseInt(getSelected);
		int quantity = Integer.parseInt(getQuantity);
		
		double price = 0.00;
		String productName = "";
		
		switch(code) {
		case 101:
			productName = "McChicken Value Meal";
			price = 13.20;
			break;
			case 102:
			productName = "Smoky Grilled Beef Meal";
			price = 17.90;
			break;
			case 103: 
			productName = "Ayam Goreng McD Spicy Meal 2pcs";
			price = 16.95;
			break;
			case 104: 
			productName = "Spicy McChicken Deluxe Meal";
			price = 16.65;
			break;
			case 105: 
			productName = "Chicken McNuggets 6pcs Meal";
			price = 13.20;
			break;
		}
		
		double subTotal = quantity * price;
		double serviceTax = subTotal * 0.06;
		double total = subTotal + serviceTax;
		
		//wrap into object
		Product product = new Product();
		product.setName(productName);
		product.setPrice(price);
		product.setProductId(code);
		
		OrderedProduct orderedProduct = new OrderedProduct();
		orderedProduct.setOrderedProduct(product);
		orderedProduct.setQuantity(quantity);
		
		orderedProducts.add(orderedProduct);
		
		//add to session
		session.setAttribute("orderedProducts", orderedProducts);
		
		//redirect to same page
		response.sendRedirect("orderRedirectForm.html");
	}

}
