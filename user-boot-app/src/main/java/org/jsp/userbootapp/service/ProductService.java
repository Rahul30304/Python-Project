package org.jsp.userbootapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.userbootapp.dao.ProductDao;
import org.jsp.userbootapp.dao.UserDao;
import org.jsp.userbootapp.dto.Product;
import org.jsp.userbootapp.dto.ResponseStructure;
import org.jsp.userbootapp.dto.User;
import org.jsp.userbootapp.exception.ProductNotFoundException;
import org.jsp.userbootapp.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product, int user_id) {
		Optional<User> recUser = userDao.findById(user_id);
		ResponseStructure<Product> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			User dbUser = recUser.get();
			dbUser.getProducts().add(product);// Assigning Product to User
			product.setUser(dbUser);
			structure.setData(productDao.save(product));
			structure.setMessage("Product added");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
		}
		throw new UserNotFoundException("Invalid User Id");
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findByBrand(String brand) {
		List<Product> products = productDao.findByBrand(brand);
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		if (products.isEmpty()) {
			throw new ProductNotFoundException("No Products with entered brand");
		}
		structure.setData(products);
		structure.setMessage("List of Products for entered brand");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findByCategory(String category) {
		List<Product> products = productDao.findByCategory(category);
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		if (products.isEmpty()) {
			throw new ProductNotFoundException("No Products with entered category");
		}
		structure.setData(products);
		structure.setMessage("List of Products for entered category");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findByName(String name) {
		List<Product> products = productDao.findByName(name);
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		if (products.isEmpty()) {
			throw new ProductNotFoundException("No Products with entered name");
		}
		structure.setData(products);
		structure.setMessage("List of Products for entered name");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findByUserId(int user_id) {
		List<Product> products = productDao.findByUserId(user_id);
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		if (products.isEmpty()) {
			throw new ProductNotFoundException("No Products found for entered User Id");
		}
		structure.setData(products);
		structure.setMessage("List of Products for entered User Id");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findAll() {
		List<Product> products = productDao.findAll();
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		structure.setData(products);
		structure.setMessage("List of All Products");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}
}
