package controller;

import java.util.ArrayList;

import factory.GameVoucher;
import factory.GameVoucherFactory;
import factory.InGameItem;
import factory.InGameItemFactory;
import factory.Product;
import factory.ProductFactory;
import utilities.Session;

public class ProductController {
	private static ArrayList<Product> products = new ArrayList<>();
	private ProductFactory gameVoucherFactory = new GameVoucherFactory();
	private ProductFactory InGameItemFactory = new InGameItemFactory();
	
	public ProductController() {
		
	}

	public static ArrayList<Product> getProducts() {
		return products;
	}

	public static void setProducts(ArrayList<Product> products) {
		ProductController.products = products;
	}
	
	// Purpose: This function is used to create a product and validate the input is valid.
	public String createProduct(String name, String description, String priceText, Boolean GameVoucher, Boolean InGameItem, ArrayList<String> vouchers, String quantityText, byte[] images) {
		if(name.isEmpty()||description.isEmpty()||priceText.isEmpty()||(GameVoucher==false && InGameItem==false)||images==null) {
			return "All filled must be filled!";
		}else if(Integer.parseInt(priceText)<=0) {
			return "Price must be more than 0";
		}else if(GameVoucher) {
			if(vouchers.size()==0) {
				return "Input vouchers codes!";
			}
			System.out.println("=========================================");
			System.out.println("Creating Game Voucher Items Using Factory");
			System.out.println("=========================================");
			Product temp = gameVoucherFactory.createProduct(Session.getCurrentUser().getId(), Integer.parseInt(priceText), name, description, images, 0, vouchers);
			products.add(temp);
			System.out.println("Done!");
			System.out.println("=========================================");
		}else if(InGameItem) {
			if(quantityText.isEmpty() || Integer.parseInt(quantityText)<=0) {
				return "Quantity must be more than 0";
			}
			System.out.println("====================================");
			System.out.println("Creating In Game Items Using Factory");
			System.out.println("====================================");
			Product temp = InGameItemFactory.createProduct(Session.getCurrentUser().getId(), Integer.parseInt(priceText), name, description, images, Integer.parseInt(quantityText), null);
			products.add(temp);
			System.out.println("Done!");
			System.out.println("====================================");
		}
		return null;
	}
	
	// Purpose: Used to delete the product from the current arraylist.
	public void deleteProduct(Product product) {
		int index = products.indexOf(product);
		products.remove(index);
		System.out.println("[SYSTEM] : Completed removing product!");
	}
	
	// Purpose: Used to validate the data and update the product base on the data given.
	public String updateProduct(Product product, String name, String description, String priceText, ArrayList<String> vouchers, String quantityText, byte[] imageBytes) {
		if(name.isEmpty()||description.isEmpty()||priceText.isEmpty()) {
			return "All filled must be filled!";
		}else if(Integer.parseInt(priceText)<=0) {
			return "Price must be more than 0";
		}else if(product instanceof GameVoucher) {
			if(vouchers.size()==0) {
				return "Input vouchers codes!";
			}
			if(imageBytes!=null) product.setImage(imageBytes);
			product.setName(name);
			product.setDescription(description);
			product.setPrice(Integer.parseInt(priceText));
			((GameVoucher) product).setVoucherCodes(vouchers);
		}else if(product instanceof InGameItem) {
			if(quantityText.isEmpty() || Integer.parseInt(quantityText)<=0) {
				return "Quantity must be more than 0";
			}
			product.setImage(imageBytes);
			product.setName(name);
			product.setDescription(description);
			product.setPrice(Integer.parseInt(priceText));
			((InGameItem) product).setQuantity(Integer.parseInt(quantityText));
		}
		System.out.println("[SYSTEM] : Complete updating product!");
		return null;
	}
}
