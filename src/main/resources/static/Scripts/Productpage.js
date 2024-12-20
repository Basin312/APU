// Function to load and display products from localStorage
// Function to load and display products from localStorage
function loadProducts() {
    const storedProducts = JSON.parse(localStorage.getItem('products')) || [];
    const productContainer = document.querySelector('.allproductcontainer');
    productContainer.innerHTML = ''; // Clear any existing products

    storedProducts.forEach((product) => {
        // Create the product component
        const productDiv = document.createElement('div');
        productDiv.classList.add('productcomponent');
        
        // Product summary
        const productSummary = document.createElement('div');
        productSummary.classList.add('product-summary');

        const productName = document.createElement('p');
        productName.classList.add('product-name');
        productName.textContent = product.name;  // Display the correct product name

        const productPrice = document.createElement('p');
        productPrice.classList.add('product-price');
        productPrice.textContent = `Price: Rp ${product.price}`;

        const productSold = document.createElement('p');
        productSold.classList.add('product-sold');
        productSold.textContent = `Sold Amount: ${product.sold}`;

        // Display stock in product list
        const productStock = document.createElement('p');
        productStock.classList.add('product-stock');
        productStock.textContent = `Stock: ${product.stock}`;  // Display the stock value

        const toggleDetailsButton = document.createElement('button');
        toggleDetailsButton.classList.add('toggle-details');
        toggleDetailsButton.textContent = '⬇';  // Default to down arrow

        // Product details (hidden by default)
        const productDetails = document.createElement('div');
        productDetails.classList.add('product-details');
        productDetails.style.display = 'none'; // Hide initially

        const categoryText = document.createElement('p');
        categoryText.textContent = `Category: ${product.category}`;
        const priceText = document.createElement('p');
        priceText.textContent = `Price: Rp ${product.price}`;
        const soldAmountText = document.createElement('p');
        soldAmountText.textContent = `Sold Amount: ${product.sold}`;

        productDetails.append(categoryText, priceText, soldAmountText);

        // Append everything to the product div
        productSummary.append(productName, productPrice, productSold, productStock, toggleDetailsButton);
        productDiv.append(productSummary, productDetails);
        productContainer.append(productDiv);

        // Toggle product details visibility
        toggleDetailsButton.addEventListener('click', function () {
            if (productDetails.style.display === 'none') {
                productDetails.style.display = 'block';
                toggleDetailsButton.textContent = '⬆';  // Change to up arrow
            } else {
                productDetails.style.display = 'none';
                toggleDetailsButton.textContent = '⬇';  // Change back to down arrow
            }
        });
    });
}

// Call the loadProducts function when the page loads
document.addEventListener("DOMContentLoaded", function() {
    loadProducts(); // Load products only once when the page is ready
});

// Dynamic search functionality
document.getElementById('search-button').addEventListener('click', function () {
    const searchQuery = document.getElementById('search-bar').value.toLowerCase();
    const allProducts = document.querySelectorAll('.productcomponent');

    allProducts.forEach((product) => {
        const productName = product.querySelector('.product-name').textContent.toLowerCase();
        if (productName.includes(searchQuery)) {
            product.style.display = 'block';  // Show matching products
        } else {
            product.style.display = 'none';  // Hide non-matching products
        }
    });
});
