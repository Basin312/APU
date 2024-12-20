const productForm = document.getElementById('product-form');

// Event Listener for Form Submission
productForm.addEventListener('submit', (e) => {
    e.preventDefault();

    // Get form values
    const productName = document.getElementById('productname').value.trim(); // Get updated product name
    const category = document.getElementById('category').value.trim();
    const price = parseFloat(document.getElementById('price').value);
    const productCode = document.getElementById('product-code').value.trim();
    const stocks = parseInt(document.getElementById('stocks').value, 10);

    // Validation
    if (!productName || !category || isNaN(price) || !productCode || isNaN(stocks)) {
        alert('Please fill out all fields correctly.');
        return;
    }

    // Create product object
    const newProduct = {
        name: productName,
        category: category,
        price: price,
        code: productCode,
        stock: stocks, // Store stock value here
        sold: 0, // Initial sold amount
    };

    // Retrieve existing products from localStorage
    let products = JSON.parse(localStorage.getItem('products')) || [];
    
    // Add new product to the list
    products.push(newProduct);
    
    // Save back to localStorage
    localStorage.setItem('products', JSON.stringify(products));

    alert('Product added successfully!');

    // Redirect to Product List Page
    window.location.href = 'ProductPage.html';
});
